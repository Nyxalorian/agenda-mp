package br.itb.projeto.agenda_mp.rest.dto;

public class AgendaRequest {
    private String nome;
    private String dosagem;
    private String horario;     // "HH:mm" ou "HH:mm:ss"
    private String dataInicio;  // "yyyy-MM-dd'T'HH:mm:ss"
    private String dataFim;     // "yyyy-MM-dd'T'HH:mm:ss"
    private String observacoes;

    public AgendaRequest() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
