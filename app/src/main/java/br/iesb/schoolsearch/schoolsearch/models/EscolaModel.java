package br.iesb.schoolsearch.schoolsearch.models;


public class EscolaModel {

    private String nome;
    private String rede;
    private String email;
    private String esferaAdministrativa;
    private String categoriaEscolaPrivada;
    private String situacaoFuncionamento;
    private String seFimLucrativo;
    private String seConveniadaSetorPublico;
    private String zona;

    private String codEscola;
    private String latitude;
    private String longitude;
    private String qtdSalasExistentes;
    private String qtdSalasUtilizadas;
    private String qtdFuncionarios;
    private String qtdComputadores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String rede) {
        this.rede = rede;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEsferaAdministrativa() {
        return esferaAdministrativa;
    }

    public void setEsferaAdministrativa(String esferaAdministrativa) {
        this.esferaAdministrativa = esferaAdministrativa;
    }

    public String getCategoriaEscolaPrivada() {
        return categoriaEscolaPrivada;
    }

    public void setCategoriaEscolaPrivada(String categoriaEscolaPrivada) {
        this.categoriaEscolaPrivada = categoriaEscolaPrivada;
    }

    public String getSituacaoFuncionamento() {
        return situacaoFuncionamento;
    }

    public void setSituacaoFuncionamento(String situacaoFuncionamento) {
        this.situacaoFuncionamento = situacaoFuncionamento;
    }

    public String getSeFimLucrativo() {
        return seFimLucrativo;
    }

    public void setSeFimLucrativo(String seFimLucrativo) {
        this.seFimLucrativo = seFimLucrativo;
    }

    public String getSeConveniadaSetorPublico() {
        return seConveniadaSetorPublico;
    }

    public void setSeConveniadaSetorPublico(String seConveniadaSetorPublico) {
        this.seConveniadaSetorPublico = seConveniadaSetorPublico;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCodEscola() {
        return codEscola;
    }

    public void setCodEscola(String codEscola) {
        this.codEscola = codEscola;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getQtdSalasExistentes() {
        return qtdSalasExistentes;
    }

    public void setQtdSalasExistentes(String qtdSalasExistentes) {
        this.qtdSalasExistentes = qtdSalasExistentes;
    }

    public String getQtdSalasUtilizadas() {
        return qtdSalasUtilizadas;
    }

    public void setQtdSalasUtilizadas(String qtdSalasUtilizadas) {
        this.qtdSalasUtilizadas = qtdSalasUtilizadas;
    }

    public String getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(String qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public String getQtdComputadores() {
        return qtdComputadores;
    }

    public void setQtdComputadores(String qtdComputadores) {
        this.qtdComputadores = qtdComputadores;
    }

    public String getQtdComputadoresPorAluno() {
        return qtdComputadoresPorAluno;
    }

    public void setQtdComputadoresPorAluno(String qtdComputadoresPorAluno) {
        this.qtdComputadoresPorAluno = qtdComputadoresPorAluno;
    }

    public String getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(String qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    public EnderecoEscolaModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEscolaModel endereco) {
        this.endereco = endereco;
    }

    public InfraestruturaEscolaModel getInfraestrutura() {
        return infraestrutura;
    }

    public void setInfraestrutura(InfraestruturaEscolaModel infraestrutura) {
        this.infraestrutura = infraestrutura;
    }

    private String qtdComputadoresPorAluno;
    private String qtdAlunos;

    private EnderecoEscolaModel endereco;
    private InfraestruturaEscolaModel infraestrutura;



}
