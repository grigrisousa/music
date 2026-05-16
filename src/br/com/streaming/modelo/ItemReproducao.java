package br.com.streaming.modelo;

public abstract class ItemReproducao {

    private String titulo;
    private  int duracao;

    public ItemReproducao(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }
}
