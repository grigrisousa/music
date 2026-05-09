package br.com.music.model;

import java.util.ArrayList;

public abstract class Usuario {

    protected String nome;
    protected ArrayList<Playlist> playlists;

    public Usuario(String nome) {
        this.nome = nome;
        this.playlists = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void adicionarPlaylist(Playlist p) {
        playlists.add(p);
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist.");
            return;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + " - " + playlists.get(i).nome);
        }
    }

    public abstract void ouvirMusica(Musica m);
}