package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

public class Musica extends ItemReproducao
        implements Reproduzivel {

    private String artista;
    private String genero;
    private int reproducoes;

    public Musica(String titulo,
                  String artista,
                  int duracao,
                  String genero) {

        super(titulo, duracao);

        this.artista = artista;
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    @Override
    public void reproduzir() {

        reproducoes++;

        System.out.println(
                " Reproduzindo: " + getTitulo()
        );
    }

    @Override
    public void pausar() {
        System.out.println(" Música pausada");
    }

    @Override
    public void parar() {
        System.out.println(" Música parada");
    }

    @Override
    public int getDuracaoTotal() {
        return getDuracao();
    }

    public void exibirInfo() {

        System.out.println("Título: " + getTitulo());

        System.out.println("Artista: " + artista);

        System.out.println(
                "Duração: "
                        + getDuracao()
                        + "s"
        );

        System.out.println("Gênero: " + genero);

        System.out.println("----------------------");
    }

    public void exibirInfo(boolean simples) {

        if (simples) {
            System.out.println(
                    "Título: " + getTitulo()
            );
        } else {
            exibirInfo();
        }
    }
}

