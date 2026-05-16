package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;

public class GeradorRecomendacoes {

    public void recomendar(Musica musica) {

        if (musica.getReproducoes() >= 5) {

            System.out.println(
                    " Música recomendada: "
                            + musica.getTitulo()
            );

        } else {

            System.out.println(
                    "🎵 Música ainda não possui "
                            + "reproduções suficientes."
            );
        }
    }
}
