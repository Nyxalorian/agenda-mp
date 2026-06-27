package br.itb.projeto.agenda_mp.model.repository;

import br.itb.projeto.agenda_mp.model.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByUsuarioId(Long usuarioId);

    @Query(value = """
    SELECT *
    FROM Agenda
    WHERE CAST(horario AS TIME) = CAST(:horario AS TIME)
    """, nativeQuery = true)
List<Agenda> buscarPorHorario(@Param("horario") LocalTime horario);
}