package br.com.streaming.modelo;

public class UsuarioFree extends Usuario {

    private static final int LIMITE_PLAYLISTS = 2;

    public UsuarioFree(String nome) {
        super(nome);
    }

    @Override
    public void adicionarPlaylist(
            Playlist playlist
    ) {

        if (getPlaylists().size()
                >= LIMITE_PLAYLISTS) {

            System.out.println(
                    " Limite de playlists atingido (FREE)"
            );

        } else {

            super.adicionarPlaylist(playlist);

            System.out.println(
                    " Playlist adicionada!"
            );
        }
    }

    @Override
    public void ouvirMusica(Musica musica) {

        getHistorico().add(musica);

        System.out.println(
                "\n👤 Usuário FREE: "
                        + getNome()
        );

        musica.exibirInfo(true);

        System.out.println(
                "🎧 Tocando com anúncios..."
        );

        musica.reproduzir();
    }
}