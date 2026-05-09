package br.com.music.model;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nome) {
        super(nome);
    }

    @Override
    public  void ouvirMusica(Musica m) {
        System.out.println("\n Usuário PREMIUM: " + nome);
        m.exibirInfo(true);

        System.out.println("🎧 Tocando sem anúncios (Premium)");
    }

    public void baixarMusica(Musica m) {
        System.out.println("⬇️ Download da música: " + m.getTitulo);
    }
}