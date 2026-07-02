package br.itb.projeto.agenda_mp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String dosagem;

    @Column(nullable = false, length = 100)
    private String observacoes;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horario;

    // Data/hora em que o uso foi confirmado
    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "motivo_ignorado", length = 200)
    private String motivoIgnorado;

    @Column(name = "data_hora_ignorado")
    private LocalDateTime dataHoraIgnorado;

    // Status: PENDENTE, TOMADO, PERDIDO, IGNORADO
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'PENDENTE'")
    private String status = "PENDENTE";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicamento_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "agenda"})
    private Medicamento medicamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agenda_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuario"})
    private Agenda agenda;

    public Historico() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }

    public LocalDateTime getDataConfirmacao() { return dataConfirmacao; }
    public void setDataConfirmacao(LocalDateTime dataConfirmacao) { this.dataConfirmacao = dataConfirmacao; }

    public String getMotivoIgnorado() { return motivoIgnorado; }
    public void setMotivoIgnorado(String motivoIgnorado) { this.motivoIgnorado = motivoIgnorado; }

    public LocalDateTime getDataHoraIgnorado() { return dataHoraIgnorado; }
    public void setDataHoraIgnorado(LocalDateTime dataHoraIgnorado) { this.dataHoraIgnorado = dataHoraIgnorado; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    public Agenda getAgenda() { return agenda; }
    public void setAgenda(Agenda agenda) { this.agenda = agenda; }
}
