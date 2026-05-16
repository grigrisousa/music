package br.com.streaming.modelo;

public class PlaylistAutomatica extends Playlist {

    public PlaylistAutomatica(String nome) {
        super(nome);
    }

    public void gerarPlaylistAutomatica(Musica[] musicasSistema) {

        for (Musica musica : musicasSistema) {

            adicionarMusica(musica);
        }

        System.out.println(
                " Playlist automática criada!"
        );
    }

    @Override
    public void listarMusicas() {

        System.out.println(
                "\n Playlist automática:"
        );

        super.listarMusicas();
    }
}