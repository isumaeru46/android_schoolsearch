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

    private Long codEscola;
    private Long latitude;
    private Long longitude;
    private Long qtdSalasExistentes;
    private Long qtdSalasUtilizadas;
    private Long qtdFuncionarios;
    private Long qtdComputadores;
    private Long qtdComputadoresPorAluno;
    private Long qtdAlunos;

    private EnderecoEscolaModel endereco;
    private InfraestruturaEscolaModel infraestrutura;

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

    public Long getCodEscola() {
        return codEscola;
    }

    public void setCodEscola(Long codEscola) {
        this.codEscola = codEscola;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getQtdSalasExistentes() {
        return qtdSalasExistentes;
    }

    public void setQtdSalasExistentes(Long qtdSalasExistentes) {
        this.qtdSalasExistentes = qtdSalasExistentes;
    }

    public Long getQtdSalasUtilizadas() {
        return qtdSalasUtilizadas;
    }

    public void setQtdSalasUtilizadas(Long qtdSalasUtilizadas) {
        this.qtdSalasUtilizadas = qtdSalasUtilizadas;
    }

    public Long getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(Long qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public Long getQtdComputadores() {
        return qtdComputadores;
    }

    public void setQtdComputadores(Long qtdComputadores) {
        this.qtdComputadores = qtdComputadores;
    }

    public Long getQtdComputadoresPorAluno() {
        return qtdComputadoresPorAluno;
    }

    public void setQtdComputadoresPorAluno(Long qtdComputadoresPorAluno) {
        this.qtdComputadoresPorAluno = qtdComputadoresPorAluno;
    }

    public Long getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(Long qtdAlunos) {
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


}
