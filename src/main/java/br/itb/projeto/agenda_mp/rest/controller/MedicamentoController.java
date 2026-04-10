package br.itb.projeto.agenda_mp.rest.controller;

import br.itb.projeto.agenda_mp.model.entity.Medicamento;
import br.itb.projeto.agenda_mp.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // Lista medicamentos de uma agenda
    @GetMapping("/agenda/{agendaId}/medicamentos")
    public List<Medicamento> findByAgenda(@PathVariable Long agendaId) {
        return medicamentoService.findByAgendaId(agendaId);
    }

    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<Medicamento> findById(@PathVariable Long id) {
        return medicamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria medicamento vinculado a uma agenda
    @PostMapping("/agenda/{agendaId}/medicamentos")
    public ResponseEntity<?> create(@PathVariable Long agendaId, @RequestBody Medicamento medicamento) {
        try {
            return ResponseEntity.ok(medicamentoService.save(medicamento, agendaId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/medicamentos/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return medicamentoService.findById(id).map(existing -> {
            medicamento.setId(id);
            medicamento.setAgenda(existing.getAgenda());
            medicamento.setDataCadastro(existing.getDataCadastro());
            if (medicamento.getStatusMedicamento() == null) {
                medicamento.setStatusMedicamento(existing.getStatusMedicamento());
            }
            return ResponseEntity.ok(medicamentoService.save(medicamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/medicamentos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (medicamentoService.findById(id).isPresent()) {
            medicamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}