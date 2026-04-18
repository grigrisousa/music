package br.com.music.model;

public class UsuarioFree extends Usuario {

    private final int LIMITE_PLAYLISTS = 2;

    public UsuarioFree(String nome) {
        super(nome); // ✅ super
    }

    @Override
    public void adicionarPlaylist(Playlist p) {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido (Free)");
        } else {
            super.adicionarPlaylist(p); // ✅ super.metodo()
        }
    }

    @Override
    public void ouvirMusica(Musica m) {
        System.out.println("🎧 Tocando com anúncios...");
        super.ouvirMusica(m);
    }
}