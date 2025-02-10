package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Livro;

public class LivroDAO {

    public void cadastrarLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ano de publicação do livro: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite a categoria do livro: ");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, anoPublicacao, categoria, true);

        String sql = "INSERT INTO livro (titulo, autor, anoPublicacao, categoria, disponivel) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.setString(4, livro.getCategoria());
            ps.setBoolean(5, livro.getDisponivel());

            ps.executeUpdate();
            System.out.println("Livro cadastrado com sucesso!");
        }
    }

    public void listarLivros(Connection connection) throws SQLException {
        String sql = "SELECT * FROM livro";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            List<Livro> livros = new ArrayList<>();
            
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("anoPublicacao"),
                    rs.getString("categoria"),
                    rs.getBoolean("disponivel")
                );
                livros.add(livro);
            }

            if (livros.isEmpty()) {
                System.out.println("\nNenhum livro cadastrado.");
            } else {
                System.out.println("\n=== LIVROS CADASTRADOS ===");
                for (Livro livro : livros) {
                    System.out.println(livro);
                }
            }
        }
    }

    public Livro buscarLivroPorId(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anoPublicacao"),
                        rs.getString("categoria"),
                        rs.getBoolean("disponivel")
                    );
                }
                return null;
            }
        }
    }

    public void editarLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do livro que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Livro livro = buscarLivroPorId(connection, id);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        System.out.print("Novo título (" + livro.getTitulo() + "): ");
        String novoTitulo = scanner.nextLine();

        System.out.print("Novo autor (" + livro.getAutor() + "): ");
        String novoAutor = scanner.nextLine();

        System.out.print("Novo ano (" + livro.getAnoPublicacao() + "): ");
        int novoAno = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nova categoria (" + livro.getCategoria() + "): ");
        String novaCategoria = scanner.nextLine();

        System.out.print("Disponível? (" + livro.getDisponivel() + ") (true/false): ");
        boolean novaDisponibilidade = scanner.nextBoolean();
        scanner.nextLine();

        String sql = "UPDATE livro SET titulo = ?, autor = ?, anoPublicacao = ?, categoria = ?, disponivel = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, novoTitulo.isEmpty() ? livro.getTitulo() : novoTitulo);
            ps.setString(2, novoAutor.isEmpty() ? livro.getAutor() : novoAutor);
            ps.setInt(3, novoAno);
            ps.setString(4, novaCategoria.isEmpty() ? livro.getCategoria() : novaCategoria);
            ps.setBoolean(5, novaDisponibilidade);
            ps.setInt(6, id);

            ps.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");
        }
    }
    
    public void excluirLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do livro que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Livro livro = buscarLivroPorId(connection, id);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
 
        String sql = "DELETE FROM livro WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o ID fornecido.");
            }
        }
    }

}
