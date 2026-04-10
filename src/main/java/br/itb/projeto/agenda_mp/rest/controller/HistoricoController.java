package br.itb.projeto.agenda_mp.rest.controller;

import br.itb.projeto.agenda_mp.model.entity.Historico;
import br.itb.projeto.agenda_mp.rest.dto.HistoricoRequest;
import br.itb.projeto.agenda_mp.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    // Aceita "HH:mm" ou "HH:mm:ss"
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm")
            .optionalStart().appendPattern(":ss").optionalEnd()
            .toFormatter();

    // Histórico completo de um usuário
    @GetMapping("/usuarios/{usuarioId}/historico")
    public List<Historico> findByUsuario(@PathVariable Long usuarioId) {
        return historicoService.findByUsuarioId(usuarioId);
    }

    // Histórico de uma agenda específica
    @GetMapping("/agenda/{agendaId}/historico")
    public List<Historico> findByAgenda(@PathVariable Long agendaId) {
        return historicoService.findByAgendaId(agendaId);
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<Historico> findById(@PathVariable Long id) {
        return historicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registra entrada no histórico (status PENDENTE)
    @PostMapping("/agenda/{agendaId}/medicamentos/{medicamentoId}/historico")
    public ResponseEntity<?> registrar(
            @PathVariable Long agendaId,
            @PathVariable Long medicamentoId,
            @RequestBody HistoricoRequest request) {
        try {
            Historico historico = new Historico();
            historico.setNome(request.getNome() != null ? request.getNome() : "");
            historico.setDosagem(request.getDosagem() != null ? request.getDosagem() : "");
            historico.setObservacoes(request.getObservacoes() != null ? request.getObservacoes() : "");
            if (request.getHorario() != null && !request.getHorario().isBlank()) {
                historico.setHorario(LocalTime.parse(request.getHorario(), TIME_FORMATTER));
            } else {
                historico.setHorario(LocalTime.now());
            }
            return ResponseEntity.ok(historicoService.registrar(historico, agendaId, medicamentoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Confirma uso do medicamento via notificação
    @PatchMapping("/historico/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(historicoService.confirmarUso(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Registra que o medicamento foi ignorado
    @PatchMapping("/historico/{id}/ignorar")
    public ResponseEntity<?> ignorar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(historicoService.ignorarUso(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/historico/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (historicoService.findById(id).isPresent()) {
            historicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}