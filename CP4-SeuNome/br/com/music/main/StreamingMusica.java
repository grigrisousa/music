package br.com.music.main;

import br.com.music.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        escolherTipoUsuario();
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    public static void escolherTipoUsuario() {
        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1 - Free");
        System.out.println("2 - Premium");
        System.out.print("Opção: ");

        int tipo = lerOpcao();

        if (tipo == 1) {
            usuario = new UsuarioFree("Ingrid");
        } else {
            usuario = new UsuarioPremium("Ingrid");
        }
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("4. Gerenciar playlists");

        if (usuario instanceof UsuarioPremium) {
            System.out.println("5. Baixar música");
        }

        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarPorTitulo(); break;
            case 4: menuPlaylists(); break;

            case 5:
                if (usuario instanceof UsuarioPremium) {
                    baixarMusica();
                } else {
                    System.out.println("Opção inválida!");
                }
                break;

            case 0: break;

            default: System.out.println("Opção inválida!");
        }
    }

    public static void baixarMusica() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música disponível!");
            return;
        }

        listarMusicas();

        System.out.print("Escolha a música para download: ");
        int index = lerOpcao() - 1;

        if (index >= 0 && index < musicas.size()) {
            ((UsuarioPremium) usuario).baixarMusica(musicas.get(index));
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public static void menuPlaylists() {
        int op;
        do {
            System.out.println("\n=== PLAYLISTS ===");
            System.out.println("1. Criar playlist");
            System.out.println("2. Adicionar música à playlist");
            System.out.println("3. Listar playlists");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            op = lerOpcao();

            switch (op) {
                case 1: criarPlaylist(); break;
                case 2: adicionarMusicaNaPlaylist(); break;
                case 3: usuario.listarPlaylists(); break;
            }

        } while (op != 0);
    }

    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();

        Playlist p = new Playlist(nome);
        usuario.adicionarPlaylist(p);
    }

    public static void adicionarMusicaNaPlaylist() {
        if (usuario.playlists.isEmpty()) {
            System.out.println("Crie uma playlist primeiro!");
            return;
        }

        listarMusicas();

        System.out.print("Escolha o número da música: ");
        int m = lerOpcao() - 1;

        System.out.print("Escolha o número da playlist: ");
        for (int i = 0; i < usuario.playlists.size(); i++) {
            System.out.println((i + 1) + " - " + usuario.playlists.get(i).nome);
        }

        int p = lerOpcao() - 1;

        if (m >= 0 && m < musicas.size() && p >= 0 && p < usuario.playlists.size()) {
            usuario.playlists.get(p).adicionarMusica(musicas.get(m));
            System.out.println("✅ Música adicionada!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração: ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        musicas.add(new Musica(titulo, artista, duracao, genero));
        System.out.println("✅ Música cadastrada!");
    }

    public static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música!");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i + 1));
            musicas.get(i).exibirInfo();
        }
    }

    public static void buscarPorTitulo() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine().toLowerCase();

        for (Musica m : musicas) {
            if (m.titulo.toLowerCase().contains(busca)) {
                usuario.ouvirMusica(m); // ✅ usa comportamento do tipo de usuário
            }
        }
    }

    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }


}
