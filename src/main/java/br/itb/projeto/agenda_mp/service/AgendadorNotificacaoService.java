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

        List<Agenda> agendas = agendaRepository.findByHorario(agora);

        System.out.println("Encontradas: " + agendas.size());

    } catch (Exception e) {
        e.printStackTrace();
    }
}