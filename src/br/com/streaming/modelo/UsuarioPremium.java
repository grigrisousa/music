package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPremium extends Usuario
        implements Baixavel {

    private List<Musica> downloads;

    public UsuarioPremium(String nome) {

        super(nome);

        this.downloads = new ArrayList<>();
    }

    @Override
    public void ouvirMusica(Musica musica) {

        getHistorico().add(musica);

        System.out.println(
                "\n👑 Usuário PREMIUM: "
                        + getNome()
        );

        musica.exibirInfo(true);

        System.out.println(
                "🎧 Tocando sem anúncios"
        );

        musica.reproduzir();
    }

    @Override
    public void baixar(Musica musica) {

        downloads.add(musica);

        System.out.println(
                " Download realizado: "
                        + musica.getTitulo()
        );
    }

    @Override
    public void removerDownload(Musica musica) {

        downloads.remove(musica);

        System.out.println(
                " Download removido"
        );
    }

    @Override
    public boolean estaBaixada(
            Musica musica
    ) {

        return downloads.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {

        return downloads.size();
    }

    public void listarDownloads() {

        if (downloads.isEmpty()) {

            System.out.println(
                    "Nenhuma música baixada."
            );

            return;
        }

        System.out.println(
                "\n Downloads:"
        );

        for (Musica musica : downloads) {

            System.out.println(
                    "- " + musica.getTitulo()
            );
        }
    }
}