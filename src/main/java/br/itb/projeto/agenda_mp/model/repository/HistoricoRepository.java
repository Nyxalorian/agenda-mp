package br.itb.projeto.agenda_mp.model.repository;

import br.itb.projeto.agenda_mp.model.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByAgendaId(Long agendaId);
    List<Historico> findByAgendaUsuarioId(Long usuarioId);
    List<Historico> findByStatus(String status);

    @Modifying
    @Query("DELETE FROM Historico h WHERE h.agenda.id = :agendaId")
    void deleteByAgendaId(Long agendaId);
}