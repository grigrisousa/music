package br.com.streaming.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {

    private String nome;
    private List<Playlist> playlists;
    private List<Musica> historico;

    public Usuario(String nome) {

        this.nome = nome;
        this.playlists = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<Musica> getHistorico() {
        return historico;
    }

    public void adicionarPlaylist(Playlist playlist) {

        playlists.add(playlist);

        System.out.println(
                "Playlist adicionada com sucesso!"
        );
    }

    public void listarPlaylists() {

        if (playlists.isEmpty()) {

            System.out.println(
                    "Nenhuma playlist cadastrada."
            );

            return;
        }

        System.out.println(
                "\n Playlists de " + nome
        );

        for (int i = 0; i < playlists.size(); i++) {

            System.out.println(
                    (i + 1)
                            + " - "
                            + playlists.get(i).getNome()
            );
        }
    }

    public abstract void ouvirMusica(
            Musica musica
    );
}