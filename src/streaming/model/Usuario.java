package streaming.model;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = (nome == null || nome.isEmpty()) ? "Usuário" : nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void adicionarPlaylist(Playlist p) {
        playlists.add(p);
    }

    public void listarPlaylists() {
        for (Playlist p : playlists) {
            p.listarMusicas();
        }
    }
}