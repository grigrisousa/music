package br.com.music.model;

public class Musica {

    public String getTitulo;
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    public Musica(String titulo, String artista, int duracao, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void exibirInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Artista: " + artista);
        System.out.println("Duração: " + duracao + "s");
        System.out.println("Gênero: " + genero);
        System.out.println("----------------------");
    }

    public void exibirInfo(boolean simples) {
        if (simples) {
            System.out.println("Título: " + titulo);
        } else {
            exibirInfo();
        }
    }
}

