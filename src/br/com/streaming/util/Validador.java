package br.com.streaming.util;

public class Validador {

    // para verificar o texto que está vazio
    public static boolean textoValido(
            String texto
    ) {

        return texto != null
                && !texto.trim().isEmpty();
    }

    // para ver se duração é válida
    public static boolean duracaoValida(
            int duracao
    ) {

        return duracao > 0;
    }
}
