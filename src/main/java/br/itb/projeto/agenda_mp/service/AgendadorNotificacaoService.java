package br.itb.projeto.agenda_mp.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.itb.projeto.agenda_mp.model.repository.AgendaRepository;

@Service
public class AgendadorNotificacaoService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    @Scheduled(cron = "0 * * * * *")
    public void verificarMedicamentos() {

        LocalTime agora = LocalTime.now()
                .withSecond(0)
                .withNano(0);

        System.out.println("=================================");
        System.out.println("SCHEDULER RODANDO");
        System.out.println("Verificando horário: " + agora);
        System.out.println("=================================");

        // List<Agenda> agendas = agendaRepository.findByHorario(agora);
        // System.out.println("Encontradas: " + agendas.size());
    }
}