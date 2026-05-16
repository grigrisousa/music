package br.com.streaming.principal;

import br.com.streaming.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamingMusica {

    // Lista de músicas do sistema
    static List<Musica> musicas = new ArrayList<>();

    // Lista polimórfica de usuários
    static List<Usuario> usuarios = new ArrayList<>();

    // Usuário logado
    static Usuario usuarioLogado;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        cadastrarUsuariosTeste();

        adicionarMusicasTeste();

        login();

        int opcao;

        do {

            exibirMenu();

            opcao = lerOpcao();

            processarOpcao(opcao);

        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");

        scanner.close();
    }

    // LOGIN
    public static void login() {

        System.out.println("=== LOGIN ===");

        listarUsuarios();

        System.out.print(
                "Digite o número do usuário: "
        );

        int escolha = lerOpcao() - 1;

        if (escolha >= 0
                && escolha < usuarios.size()) {

            usuarioLogado =
                    usuarios.get(escolha);

            System.out.println(
                    "\n✅ Login realizado!"
            );

            System.out.println(
                    "Bem-vindo(a), "
                            + usuarioLogado.getNome()
            );

        } else {

            System.out.println(
                    "❌ Usuário inválido!"
            );

            login();
        }
    }

    // LISTAR USUÁRIOS
    public static void listarUsuarios() {

        System.out.println(
                "\n=== USUÁRIOS ==="
        );

        for (int i = 0;
             i < usuarios.size();
             i++) {

            Usuario usuario =
                    usuarios.get(i);

            String tipo;

            if (usuario instanceof UsuarioPremium) {

                tipo = "Premium";

            } else {

                tipo = "Free";
            }

            System.out.println(
                    (i + 1)
                            + " - "
                            + usuario.getNome()
                            + " ("
                            + tipo
                            + ")"
            );
        }
    }

    // MENU
    public static void exibirMenu() {

        System.out.println(
                "\n=== SISTEMA DE STREAMING ==="
        );

        System.out.println(
                "1. Cadastrar música"
        );

        System.out.println(
                "2. Listar músicas"
        );

        System.out.println(
                "3. Buscar por título"
        );

        System.out.println(
                "4. Gerenciar playlists"
        );

        System.out.println(
                "5. Criar playlist automática"
        );

        System.out.println(
                "6. Mostrar estatísticas"
        );

        if (usuarioLogado
                instanceof UsuarioPremium) {

            System.out.println(
                    "7. Baixar música"
            );
        }

        System.out.println("0. Sair");

        System.out.print("Escolha: ");
    }

    // LER OPÇÃO
    public static int lerOpcao() {

        try {

            return Integer.parseInt(
                    scanner.nextLine()
            );

        } catch (Exception e) {

            return -1;
        }
    }

    // PROCESSAR MENU
    public static void processarOpcao(
            int opcao
    ) {

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

                if (usuarioLogado
                        instanceof UsuarioPremium) {

                    baixarMusica();

                } else {

                    System.out.println(
                            "❌ Apenas Premium!"
                    );
                }

                break;

            case 0:
                break;

            default:

                System.out.println(
                        "❌ Opção inválida!"
                );
        }
    }

    // ESTATÍSTICAS
    public static void mostrarEstatisticas() {

        int free = 0;
        int premium = 0;

        for (Usuario usuario : usuarios) {

            if (usuario
                    instanceof UsuarioFree) {

                free++;
            }

            if (usuario
                    instanceof UsuarioPremium) {

                premium++;
            }
        }

        System.out.println(
                "\n=== ESTATÍSTICAS ==="
        );

        System.out.println(
                "Usuários Free: " + free
        );

        System.out.println(
                "Usuários Premium: "
                        + premium
        );

        System.out.println(
                "Total de músicas: "
                        + musicas.size()
        );
    }

    // DOWNLOAD
    public static void baixarMusica() {

        if (musicas.isEmpty()) {

            System.out.println(
                    "Nenhuma música disponível!"
            );

            return;
        }

        listarMusicas();

        System.out.print(
                "Escolha a música para download: "
        );

        int index = lerOpcao() - 1;

        if (index >= 0
                && index < musicas.size()) {

            ((UsuarioPremium) usuarioLogado)
                    .baixar(
                            musicas.get(index)
                    );

        } else {

            System.out.println(
                    "❌ Opção inválida!"
            );
        }
    }

    // MENU PLAYLISTS
    public static void menuPlaylists() {

        int opcao;

        do {

            System.out.println(
                    "\n=== PLAYLISTS ==="
            );

            System.out.println(
                    "1. Criar playlist"
            );

            System.out.println(
                    "2. Adicionar música à playlist"
            );

            System.out.println(
                    "3. Listar playlists"
            );

            System.out.println(
                    "0. Voltar"
            );

            System.out.print("Escolha: ");

            opcao = lerOpcao();

            switch (opcao) {

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

        } while (opcao != 0);
    }

    // CRIAR PLAYLIST
    public static void criarPlaylist() {

        System.out.print(
                "Nome da playlist: "
        );

        String nome =
                scanner.nextLine();

        Playlist playlist =
                new Playlist(nome);

        usuarioLogado
                .adicionarPlaylist(playlist);
    }

    // ADICIONAR MÚSICA
    public static void adicionarMusicaNaPlaylist() {

        if (usuarioLogado
                .getPlaylists()
                .isEmpty()) {

            System.out.println(
                    "Crie uma playlist primeiro!"
            );

            return;
        }

        listarMusicas();

        System.out.print(
                "Escolha o número da música: "
        );

        int musicaEscolhida =
                lerOpcao() - 1;

        System.out.println(
                "\nEscolha a playlist:"
        );

        for (int i = 0;
             i < usuarioLogado
                     .getPlaylists()
                     .size();
             i++) {

            Playlist playlist =
                    usuarioLogado
                            .getPlaylists()
                            .get(i);

            System.out.println(
                    (i + 1)
                            + " - "
                            + playlist.getNome()
            );
        }

        int playlistEscolhida =
                lerOpcao() - 1;

        if (musicaEscolhida >= 0
                && musicaEscolhida
                < musicas.size()
                && playlistEscolhida >= 0
                && playlistEscolhida
                < usuarioLogado
                .getPlaylists()
                .size()) {

            usuarioLogado
                    .getPlaylists()
                    .get(playlistEscolhida)
                    .adicionarMusica(
                            musicas.get(
                                    musicaEscolhida
                            )
                    );

        } else {

            System.out.println(
                    "❌ Opção inválida!"
            );
        }
    }

    // PLAYLIST AUTOMÁTICA
    public static void criarPlaylistAutomatica() {

        PlaylistAutomatica playlist =
                new PlaylistAutomatica(
                        "Automática"
                );

        Musica[] sistema =
                musicas.toArray(
                        new Musica[0]
                );

        playlist
                .gerarPlaylistAutomatica(
                        sistema
                );

        usuarioLogado
                .adicionarPlaylist(playlist);
    }

    // CADASTRAR MÚSICA
    public static void cadastrarMusica() {

        System.out.println(
                "\n--- CADASTRAR MÚSICA ---"
        );

        System.out.print("Título: ");

        String titulo =
                scanner.nextLine();

        System.out.print("Artista: ");

        String artista =
                scanner.nextLine();

        System.out.print("Duração: ");

        int duracao;

        try {

            duracao =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "❌ Duração inválida!"
            );

            return;
        }

        System.out.print("Gênero: ");

        String genero =
                scanner.nextLine();

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

    // LISTAR MÚSICAS
    public static void listarMusicas() {

        if (musicas.isEmpty()) {

            System.out.println(
                    "Nenhuma música!"
            );

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

    // BUSCAR
    public static void buscarPorTitulo() {

        System.out.print("Buscar: ");

        String busca =
                scanner.nextLine()
                        .toLowerCase();

        boolean encontrou = false;

        for (Musica musica : musicas) {

            if (musica.getTitulo()
                    .toLowerCase()
                    .contains(busca)) {

                usuarioLogado
                        .ouvirMusica(musica);

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println(
                    "❌ Música não encontrada!"
            );
        }
    }

    // USUÁRIOS TESTE
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

    // MÚSICAS TESTE
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