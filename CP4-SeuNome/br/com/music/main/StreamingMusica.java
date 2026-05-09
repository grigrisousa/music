package br.com.music.main;

import br.com.music.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    // Lista de músicas do sistema
    static ArrayList<Musica> musicas = new ArrayList<>();

    // ArrayList polimórfico de usuários
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    // Usuário logado
    static Usuario usuarioLogado;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        cadastrarUsuariosTeste();

        login();

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

    // Login de usuários
    public static void login() {

        System.out.println("=== LOGIN ===");

        listarUsuarios();

        System.out.print("Digite o número do usuário: ");

        int escolha = lerOpcao() - 1;

        if (escolha >= 0 && escolha < usuarios.size()) {

            usuarioLogado = usuarios.get(escolha);

            System.out.println(
                    "\n✅ Login realizado com sucesso!"
            );

            System.out.println(
                    "Bem-vindo(a), "
                            + usuarioLogado.getNome()
            );

        } else {

            System.out.println("❌ Usuário inválido!");

            login();
        }
    }

    // Lista usuários cadastrados
    public static void listarUsuarios() {

        System.out.println("\n=== USUÁRIOS ===");

        for (int i = 0; i < usuarios.size(); i++) {

            Usuario u = usuarios.get(i);

            String tipo;

            if (u instanceof UsuarioPremium) {

                tipo = "Premium";

            } else {

                tipo = "Free";
            }

            System.out.println(
                    (i + 1)
                            + " - "
                            + u.getNome()
                            + " ("
                            + tipo
                            + ")"
            );
        }
    }

    public static void exibirMenu() {

        System.out.println("\n=== SISTEMA DE STREAMING ===");

        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("4. Gerenciar playlists");
        System.out.println("5. Criar playlist automática");
        System.out.println("6. Mostrar estatísticas");

        // instanceof
        if (usuarioLogado instanceof UsuarioPremium) {

            System.out.println("7. Baixar música");
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

            case 1:
                cadastrarMusica();
                break;

            case 2:
                listarMusicas();
                break;

            case 3:
                buscarPorTitulo();
                break;

            case 4:
                menuPlaylists();
                break;

            case 5:
                criarPlaylistAutomatica();
                break;

            case 6:
                mostrarEstatisticas();
                break;

            case 7:

                if (usuarioLogado instanceof UsuarioPremium) {

                    baixarMusica();

                } else {

                    System.out.println("❌ Apenas Premium!");
                }

                break;

            case 0:
                break;

            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    // Estatísticas por tipo
    public static void mostrarEstatisticas() {

        int free = 0;
        int premium = 0;

        for (Usuario u : usuarios) {

            if (u instanceof UsuarioFree) {

                free++;
            }

            if (u instanceof UsuarioPremium) {

                premium++;
            }
        }

        System.out.println("\n=== ESTATÍSTICAS ===");

        System.out.println("Usuários Free: " + free);

        System.out.println("Usuários Premium: " + premium);

        System.out.println(
                "Total de músicas: "
                        + musicas.size()
        );
    }

    public static void baixarMusica() {

        if (musicas.isEmpty()) {

            System.out.println("Nenhuma música disponível!");

            return;
        }

        listarMusicas();

        System.out.print(
                "Escolha a música para download: "
        );

        int index = lerOpcao() - 1;

        if (index >= 0 && index < musicas.size()) {

            // Casting
            ((UsuarioPremium) usuarioLogado)
                    .baixarMusica(musicas.get(index));

        } else {

            System.out.println("❌ Opção inválida!");
        }
    }

    public static void menuPlaylists() {

        int op;

        do {

            System.out.println("\n=== PLAYLISTS ===");

            System.out.println("1. Criar playlist");
            System.out.println(
                    "2. Adicionar música à playlist"
            );
            System.out.println("3. Listar playlists");
            System.out.println("0. Voltar");

            System.out.print("Escolha: ");

            op = lerOpcao();

            switch (op) {

                case 1:
                    criarPlaylist();
                    break;

                case 2:
                    adicionarMusicaNaPlaylist();
                    break;

                case 3:
                    usuarioLogado.listarPlaylists();
                    break;
            }

        } while (op != 0);
    }

    public static void criarPlaylist() {

        System.out.print("Nome da playlist: ");

        String nome = scanner.nextLine();

        Playlist p = new Playlist(nome);

        usuarioLogado.adicionarPlaylist(p);
    }

    public static void adicionarMusicaNaPlaylist() {

        if (usuarioLogado.getPlaylists().isEmpty()) {

            System.out.println(
                    "Crie uma playlist primeiro!"
            );

            return;
        }

        listarMusicas();

        System.out.print(
                "Escolha o número da música: "
        );

        int m = lerOpcao() - 1;

        System.out.println("\nEscolha a playlist:");

        for (int i = 0;
             i < usuarioLogado.getPlaylists().size();
             i++) {

            System.out.println(
                    (i + 1)
                            + " - "
                            + usuarioLogado
                            .getPlaylists()
                            .get(i)
                            .getNome()
            );
        }

        int p = lerOpcao() - 1;

        if (m >= 0
                && m < musicas.size()
                && p >= 0
                && p < usuarioLogado
                .getPlaylists()
                .size()) {

            usuarioLogado
                    .getPlaylists()
                    .get(p)
                    .adicionarMusica(
                            musicas.get(m)
                    );

        } else {

            System.out.println("❌ Opção inválida!");
        }
    }

    // Playlist automática
    public static void criarPlaylistAutomatica() {

        PlaylistAutomatica pa =
                new PlaylistAutomatica(
                        "Automática"
                );

        Musica[] sistema =
                musicas.toArray(new Musica[0]);

        pa.gerarPlaylistAutomatica(sistema);

        usuarioLogado.adicionarPlaylist(pa);
    }

    public static void cadastrarMusica() {

        System.out.println(
                "\n--- CADASTRAR MÚSICA ---"
        );

        System.out.print("Título: ");

        String titulo = scanner.nextLine();

        System.out.print("Artista: ");

        String artista = scanner.nextLine();

        System.out.print("Duração: ");

        int duracao =
                Integer.parseInt(
                        scanner.nextLine()
                );

        System.out.print("Gênero: ");

        String genero = scanner.nextLine();

        musicas.add(
                new Musica(
                        titulo,
                        artista,
                        duracao,
                        genero
                )
        );

        System.out.println(
                "✅ Música cadastrada!"
        );
    }

    public static void listarMusicas() {

        if (musicas.isEmpty()) {

            System.out.println("Nenhuma música!");

            return;
        }

        System.out.println(
                "\n🎵 LISTA DE MÚSICAS"
        );

        for (int i = 0;
             i < musicas.size();
             i++) {

            System.out.println(
                    "\n" + (i + 1)
            );

            musicas.get(i).exibirInfo();
        }
    }

    public static void buscarPorTitulo() {

        System.out.print("Buscar: ");

        String busca =
                scanner.nextLine().toLowerCase();

        boolean encontrou = false;

        for (Musica m : musicas) {

            if (m.getTitulo()
                    .toLowerCase()
                    .contains(busca)) {

                // Polimorfismo
                usuarioLogado.ouvirMusica(m);

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println(
                    "❌ Música não encontrada!"
            );
        }
    }

    // Usuários de teste
    public static void cadastrarUsuariosTeste() {

        usuarios.add(
                new UsuarioFree("Ingrid")
        );

        usuarios.add(
                new UsuarioPremium("Carlos")
        );

        usuarios.add(
                new UsuarioFree("Ana")
        );
    }

    // Músicas de teste
    public static void adicionarMusicasTeste() {

        musicas.add(
                new Musica(
                        "Bohemian Rhapsody",
                        "Queen",
                        354,
                        "Rock"
                )
        );

        musicas.add(
                new Musica(
                        "Billie Jean",
                        "Michael Jackson",
                        293,
                        "Pop"
                )
        );

        musicas.add(
                new Musica(
                        "Shape of You",
                        "Ed Sheeran",
                        240,
                        "Pop"
                )
        );
    }
}