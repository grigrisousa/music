package streaming.model;

import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = (nome == null || nome.isEmpty()) ? "Playlist sem nome" : nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    public void listarMusicas() {
        System.out.println("\nPlaylist: " + nome);
        for (Musica m : musicas) {
            m.exibirInfo();
        }
    }
}