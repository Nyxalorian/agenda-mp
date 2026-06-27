package br.itb.projeto.agenda_mp.service;

import java.time.LocalTime;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.itb.projeto.agenda_mp.model.entity.Agenda;
import br.itb.projeto.agenda_mp.model.repository.AgendaRepository;

@Service
public class AgendadorNotificacaoService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    @PostConstruct
    public void teste() {
        System.out.println("SERVIÇO DO SCHEDULER CARREGADO");
    }

    @Scheduled(cron = "0 * * * * *")
    public void verificarMedicamentos() {

        LocalTime agora = LocalTime.now()
                .withSecond(0)
                .withNano(0);

        System.out.println("=================================");
        System.out.println("SCHEDULER RODANDO");
        System.out.println("Verificando horário: " + agora);
        System.out.println("=================================");

        try {

            List<Agenda> todas = agendaRepository.findAll();

            System.out.println("Total agendas: " + todas.size());

            for (Agenda agenda : todas) {
                System.out.println(
                        "ID: " + agenda.getId() +
                        " | Horário banco: " + agenda.getHorario() +
                        " | Igual? " + agenda.getHorario().equals(agora)
                );
            }

            List<Agenda> agendas = todas.stream()
                    .filter(a -> a.getHorario().equals(agora))
                    .toList();

            System.out.println("Encontradas: " + agendas.size());

            for (Agenda agenda : agendas) {

                System.out.println("Medicamento encontrado: " + agenda.getNome());

                String token = agenda.getUsuario().getFcmToken();

                if (token != null && !token.isBlank()) {

                    try {

                        notificacaoService.enviar(
                                token,
                                "Hora do medicamento!",
                                "Está na hora de tomar " + agenda.getNome()
                        );

                        System.out.println("Notificação enviada!");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Usuário sem token FCM.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}