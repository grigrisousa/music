import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("Ingrid");

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("4. Gerenciar playlists");
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
            case 0: break;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void menuPlaylists() {
        int op;
        do {
            System.out.println("\n=== PLAYLISTS ===");
            System.out.println("1. Criar playlist");
            System.out.println("2. Adicionar música à playlist");
            System.out.println("3. Remover música da playlist");
            System.out.println("4. Listar playlists");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            op = lerOpcao();

            switch (op) {
                case 1: criarPlaylist(); break;
                case 2: adicionarMusicaNaPlaylist(); break;
                case 3: removerMusicaDaPlaylist(); break;
                case 4: usuario.listarPlaylists(); break;
            }

        } while (op != 0);
    }

    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();

        Playlist p = new Playlist(nome);
        usuario.adicionarPlaylist(p);

        System.out.println("✅ Playlist criada!");
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

    public static void removerMusicaDaPlaylist() {
        if (usuario.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist!");
            return;
        }

        for (int i = 0; i < usuario.playlists.size(); i++) {
            System.out.println((i + 1) + " - " + usuario.playlists.get(i).nome);
        }

        System.out.print("Escolha a playlist: ");
        int p = lerOpcao() - 1;

        if (p < 0 || p >= usuario.playlists.size()) return;

        Playlist playlist = usuario.playlists.get(p);

        for (int i = 0; i < playlist.musicas.size(); i++) {
            System.out.println((i + 1) + " - " + playlist.musicas.get(i).titulo);
        }

        System.out.print("Escolha a música para remover: ");
        int m = lerOpcao() - 1;

        if (m >= 0 && m < playlist.musicas.size()) {
            playlist.musicas.remove(m);
            System.out.println("❌ Música removida!");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração (segundos): ");
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
                m.exibirInfo();
            }
        }
    }

    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }
}


class Musica {
    String titulo, artista, genero;
    int duracao;

    public Musica(String t, String a, int d, String g) {
        titulo = t;
        artista = a;
        duracao = d;
        genero = g;
    }

    public String formatarDuracao() {
        return (duracao / 60) + ":" + String.format("%02d", duracao % 60);
    }

    public void exibirInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Artista: " + artista);
        System.out.println("Duração: " + formatarDuracao());
        System.out.println("Gênero: " + genero);
        System.out.println("----------------");
    }
}

class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    public void listarMusicas() {
        System.out.println("\nPlaylist: " + nome);
        for (Musica m : musicas) {
            m.exibirInfo();
        }
    }
}

class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void adicionarPlaylist(Playlist p) {
        playlists.add(p);
    }

    public void listarPlaylists() {
        for (Playlist p : playlists) {
            p.listarMusicas();
        }
    }
}
