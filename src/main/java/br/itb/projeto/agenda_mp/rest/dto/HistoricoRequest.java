package br.itb.projeto.agenda_mp.rest.dto;

public class HistoricoRequest {
    private String nome;
    private String dosagem;
    private String observacoes;
    private String horario; // aceita "HH:mm" ou "HH:mm:ss"
    private String status;
    private String motivoIgnorado;

    public HistoricoRequest() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMotivoIgnorado() { return motivoIgnorado; }
    public void setMotivoIgnorado(String motivoIgnorado) { this.motivoIgnorado = motivoIgnorado; }
}
