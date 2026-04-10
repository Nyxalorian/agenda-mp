package br.itb.projeto.agenda_mp.rest.controller;

import br.itb.projeto.agenda_mp.model.entity.Agenda;
import br.itb.projeto.agenda_mp.rest.dto.AgendaRequest;
import br.itb.projeto.agenda_mp.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm")
            .optionalStart().appendPattern(":ss").optionalEnd()
            .toFormatter();

    private static final DateTimeFormatter DT_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm")
            .optionalStart().appendPattern(":ss").optionalEnd()
            .toFormatter();

    // Lista todas as agendas de um usuário
    @GetMapping("/usuarios/{usuarioId}/agenda")
    public List<Agenda> findByUsuario(@PathVariable Long usuarioId) {
        return agendaService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/agenda/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable Long id) {
        return agendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria agenda vinculada ao usuário
    @PostMapping("/usuarios/{usuarioId}/agenda")
    public ResponseEntity<?> create(@PathVariable Long usuarioId, @RequestBody AgendaRequest request) {
        try {
            Agenda agenda = toAgenda(request);
            return ResponseEntity.ok(agendaService.save(agenda, usuarioId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/agenda/{id}")
    public ResponseEntity<Agenda> update(@PathVariable Long id, @RequestBody AgendaRequest request) {
        return agendaService.findById(id).map(existing -> {
            Agenda agenda = toAgenda(request);
            agenda.setId(id);
            agenda.setUsuario(existing.getUsuario());
            return ResponseEntity.ok(agendaService.save(agenda));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/agenda/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (agendaService.findById(id).isPresent()) {
            agendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Agenda toAgenda(AgendaRequest r) {
        Agenda a = new Agenda();
        a.setNome(r.getNome() != null ? r.getNome() : "");
        a.setDosagem(r.getDosagem() != null ? r.getDosagem() : "-");
        a.setObservacoes(r.getObservacoes() != null ? r.getObservacoes() : "");
        a.setHorario(r.getHorario() != null && !r.getHorario().isBlank()
                ? LocalTime.parse(r.getHorario(), TIME_FORMATTER)
                : LocalTime.of(8, 0));
        a.setDataInicio(r.getDataInicio() != null && !r.getDataInicio().isBlank()
                ? LocalDateTime.parse(r.getDataInicio(), DT_FORMATTER)
                : LocalDateTime.now());
        a.setDataFim(r.getDataFim() != null && !r.getDataFim().isBlank()
                ? LocalDateTime.parse(r.getDataFim(), DT_FORMATTER)
                : LocalDateTime.now().plusYears(1));
        return a;
    }
}