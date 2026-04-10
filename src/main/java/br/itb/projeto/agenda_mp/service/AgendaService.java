package br.itb.projeto.agenda_mp.service;

import br.itb.projeto.agenda_mp.model.entity.Agenda;
import br.itb.projeto.agenda_mp.model.entity.Usuario;
import br.itb.projeto.agenda_mp.model.repository.AgendaRepository;
import br.itb.projeto.agenda_mp.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public List<Agenda> findByUsuarioId(Long usuarioId) {
        return agendaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Agenda> findById(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda save(Agenda agenda, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + usuarioId));
        agenda.setUsuario(usuario);
        return agendaRepository.save(agenda);
    }

    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public void deleteById(Long id) {
        agendaRepository.deleteById(id);
    }
}