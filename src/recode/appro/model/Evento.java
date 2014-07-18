package recode.appro.model;

/**
 * Created by eccard on 09/07/14.
 */
public class Evento {
    private int codigo, confirmados;
    private String nome, descricao, organizadores, local, data, hora;

    public Evento(int codigo, String nome, String descricao, String organizadores, String local, String data, String hora) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.organizadores = organizadores;
        this.local = local;
        this.hora = hora;
        this.data = data;
        this.confirmados=0;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getConfirmados() {
        return confirmados;
    }

    public void setConfirmados(int confirmados) {
        this.confirmados = confirmados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(String organizadores) {
        this.organizadores = organizadores;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
