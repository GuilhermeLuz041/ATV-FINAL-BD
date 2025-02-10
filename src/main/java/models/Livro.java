package models;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String categoria;
    private boolean disponivel;


    public Livro(String titulo, String autor, int anoPublicacao, String categoria, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.disponivel = true;
    }


    public Livro(int id, String titulo, String autor, int anoPublicacao, String categoria, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.disponivel = true;
    }


    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Título: " + titulo +
               " | Autor: " + autor +
               " | Ano: " + anoPublicacao +
               " | Categoria: " + categoria +
               " | Disponível: " + (disponivel ? "Sim" : "Não");
    }
}