package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Reproduzivel {

    private String nome;
    private List<Musica> musicas;

    public Playlist(String nome) {

        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {

        musicas.add(musica);

        System.out.println(
                "Música adicionada à playlist!"
        );
    }

    public void listarMusicas() {

        System.out.println(
                "\n Playlist: " + nome
        );

        if (musicas.isEmpty()) {

            System.out.println("Playlist vazia!");

            return;
        }

        for (Musica musica : musicas) {
            musica.exibirInfo(true);
        }
    }

    public String getNome() {
        return nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public void reproduzir() {

        System.out.println(
                "\n Reproduzindo playlist: "
                        + nome
        );

        for (Musica musica : musicas) {
            musica.reproduzir();
        }
    }

    @Override
    public void pausar() {
        System.out.println(" Playlist pausada");
    }

    @Override
    public void parar() {
        System.out.println(" Playlist parada");
    }

    @Override
    public int getDuracaoTotal() {

        int total = 0;

        for (Musica musica : musicas) {
            total += musica.getDuracao();
        }

        return total;
    }
}
