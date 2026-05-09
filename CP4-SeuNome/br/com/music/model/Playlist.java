package br.com.music.model;

import java.util.ArrayList;

public class Playlist {

    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
        System.out.println("Música adicionada!");
    }
    public void listarMusicas() {

        System.out.println("\n🎵 Playlist: " + nome);

        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia!");
            return;
        }

        for (Musica m : musicas) {
            m.exibirInfo(true);
        }
    }

    public String getNome() {
        return nome;
    }
}
