package streaming.model;

public class Musica {
    private String titulo, artista, genero;
    private int duracao;

    // ✅ Construtor com validação
    public Musica(String t, String a, int d, String g) {
        this.titulo = (t == null || t.isEmpty()) ? "Desconhecido" : t;
        this.artista = (a == null || a.isEmpty()) ? "Desconhecido" : a;
        this.genero = (g == null || g.isEmpty()) ? "Desconhecido" : g;
        this.duracao = (d > 0) ? d : 0;
    }

    // ✅ Sobrecarga + uso de this()
    public Musica(String titulo, String artista) {
        this(titulo, artista, 0, "Desconhecido");
    }

    // ✅ Getter
    public String getTitulo() {
        return titulo;
    }

    // ✅ Setters com validação
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isEmpty()) {
            this.titulo = titulo;
        }
    }

    public void setArtista(String artista) {
        if (artista != null && !artista.isEmpty()) {
            this.artista = artista;
        }
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.isEmpty()) {
            this.genero = genero;
        }
    }

    public void setDuracao(int duracao) {
        if (duracao > 0) {
            this.duracao = duracao;
        }
    }
}