package br.com.music.model;

import java.util.ArrayList;

public class Playlist {

    public String nome;
    public ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia!");
            return;
        }

        for (Musica m : musicas) {
            m.exibirInfo();
        }
    }
}
