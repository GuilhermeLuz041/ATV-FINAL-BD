package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class ReservaDAO {

    
    public void buscarLivrosReservados(Connection connection) throws SQLException {
        
        String sqlDisponiveis = "SELECT * FROM livro WHERE disponivel = true";
      
        String sqlReservados = "SELECT l.*, r.data_reserva, m.nome AS membro_nome " +
                               "FROM livro l " +
                               "JOIN reserva r ON l.id = r.livro_id " +
                               "JOIN membro m ON r.membro_id = m.id";

        System.out.println("\n--- LIVROS DISPONÍVEIS ---");
        try (PreparedStatement stmtDisponiveis = connection.prepareStatement(sqlDisponiveis);
             ResultSet rsDisponiveis = stmtDisponiveis.executeQuery()) {

            while (rsDisponiveis.next()) {
                int id = rsDisponiveis.getInt("id");
                String titulo = rsDisponiveis.getString("titulo");
                String autor = rsDisponiveis.getString("autor");
                int anoPublicacao = rsDisponiveis.getInt("anoPublicacao");
                String categoria = rsDisponiveis.getString("categoria");

                System.out.println("ID: " + id + ", Título: " + titulo + ", Autor: " + autor +
                        ", Ano: " + anoPublicacao + ", Categoria: " + categoria);
            }
        }

        System.out.println("\n--- LIVROS RESERVADOS ---");
        try (PreparedStatement stmtReservados = connection.prepareStatement(sqlReservados);
             ResultSet rsReservados = stmtReservados.executeQuery()) {

            while (rsReservados.next()) {
                int id = rsReservados.getInt("id");
                String titulo = rsReservados.getString("titulo");
                String autor = rsReservados.getString("autor");
                int anoPublicacao = rsReservados.getInt("anoPublicacao");
                String categoria = rsReservados.getString("categoria");
                LocalDate dataReserva = rsReservados.getDate("data_reserva").toLocalDate();
                String membroNome = rsReservados.getString("membro_nome");

                System.out.println("ID: " + id + ", Título: " + titulo + ", Autor: " + autor +
                        ", Ano: " + anoPublicacao + ", Categoria: " + categoria +
                        ", Reservado por: " + membroNome + ", Data da Reserva: " + dataReserva);
            }
        }
    }

    
    public void reservarLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do livro que deseja reservar: ");
        int livroId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o ID do membro que está reservando: ");
        int membroId = scanner.nextInt();
        scanner.nextLine(); 

        
        String sqlVerificaDisponibilidade = "SELECT disponivel FROM livro WHERE id = ?";
        try (PreparedStatement stmtVerifica = connection.prepareStatement(sqlVerificaDisponibilidade)) {
            stmtVerifica.setInt(1, livroId);
            ResultSet rsVerifica = stmtVerifica.executeQuery();

            if (rsVerifica.next() && rsVerifica.getBoolean("disponivel")) {
                // Marca como indisponível
                String sqlAtualizaLivro = "UPDATE livro SET disponivel = false WHERE id = ?";
                try (PreparedStatement stmtAtualiza = connection.prepareStatement(sqlAtualizaLivro)) {
                    stmtAtualiza.setInt(1, livroId);
                    stmtAtualiza.executeUpdate();
                }

                String sqlReserva = "INSERT INTO reserva (livro_id, membro_id, data_reserva) VALUES (?, ?, ?)";
                try (PreparedStatement stmtReserva = connection.prepareStatement(sqlReserva)) {
                    stmtReserva.setInt(1, livroId);
                    stmtReserva.setInt(2, membroId);
                    stmtReserva.setDate(3, Date.valueOf(LocalDate.now()));

                    stmtReserva.executeUpdate();
                    System.out.println("Livro reservado com sucesso!");
                }
            } else {
                System.out.println("Livro não está disponível para reserva.");
            }
        }
    }

    
    public void cancelarReserva(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da reserva que deseja cancelar: ");
        int reservaId = scanner.nextInt();
        scanner.nextLine(); 

        String sqlBuscaLivro = "SELECT livro_id FROM reserva WHERE id = ?";
        int livroId = -1;

        try (PreparedStatement stmtBuscaLivro = connection.prepareStatement(sqlBuscaLivro)) {
            stmtBuscaLivro.setInt(1, reservaId);
            ResultSet rsBuscaLivro = stmtBuscaLivro.executeQuery();

            if (rsBuscaLivro.next()) {
                livroId = rsBuscaLivro.getInt("livro_id");
            } else {
                System.out.println("Reserva não encontrada.");
                return;
            }
        }

       
        String sqlRemoveReserva = "DELETE FROM reserva WHERE id = ?";
        try (PreparedStatement stmtRemoveReserva = connection.prepareStatement(sqlRemoveReserva)) {
            stmtRemoveReserva.setInt(1, reservaId);
            stmtRemoveReserva.executeUpdate();
        }

        
        String sqlAtualizaLivro = "UPDATE livro SET disponivel = true WHERE id = ?";
        try (PreparedStatement stmtAtualizaLivro = connection.prepareStatement(sqlAtualizaLivro)) {
            stmtAtualizaLivro.setInt(1, livroId);
            stmtAtualizaLivro.executeUpdate();
        }

        System.out.println("Reserva cancelada com sucesso!");
    }
}