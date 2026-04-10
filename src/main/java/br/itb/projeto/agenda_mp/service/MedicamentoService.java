package br.itb.projeto.agenda_mp.service;

import br.itb.projeto.agenda_mp.model.entity.Medicamento;
import br.itb.projeto.agenda_mp.model.entity.Agenda;
import br.itb.projeto.agenda_mp.model.repository.AgendaRepository;
import br.itb.projeto.agenda_mp.model.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Medicamento> findAll() {
        return medicamentoRepository.findAll();
    }

    public List<Medicamento> findByAgendaId(Long agendaId) {
        return medicamentoRepository.findByAgendaId(agendaId);
    }

    public Optional<Medicamento> findById(Long id) {
        return medicamentoRepository.findById(id);
    }

    public Medicamento save(Medicamento medicamento, Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada: " + agendaId));
        medicamento.setAgenda(agenda);
        if (medicamento.getDataCadastro() == null) {
            medicamento.setDataCadastro(LocalDateTime.now());
        }
        if (medicamento.getStatusMedicamento() == null) {
            medicamento.setStatusMedicamento("ATIVO");
        }
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento save(Medicamento medicamento) {
        if (medicamento.getDataCadastro() == null) {
            medicamento.setDataCadastro(LocalDateTime.now());
        }
        if (medicamento.getStatusMedicamento() == null) {
            medicamento.setStatusMedicamento("ATIVO");
        }
        return medicamentoRepository.save(medicamento);
    }

    public void deleteById(Long id) {
        medicamentoRepository.deleteById(id);
    }
}