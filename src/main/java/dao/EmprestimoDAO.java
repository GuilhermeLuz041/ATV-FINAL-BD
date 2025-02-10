package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmprestimoDAO {

    public void emprestarLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do livro que deseja emprestar: ");
        int livroId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do membro que está emprestando: ");
        int membroId = scanner.nextInt();
        scanner.nextLine(); 

        String sqlVerificaDisponibilidade = "SELECT disponivel FROM livro WHERE id = ?";
        try (PreparedStatement stmtVerifica = connection.prepareStatement(sqlVerificaDisponibilidade)) {
            stmtVerifica.setInt(1, livroId);
            ResultSet rsVerifica = stmtVerifica.executeQuery();

            if (rsVerifica.next() && rsVerifica.getBoolean("disponivel")) {
                String sqlAtualizaLivro = "UPDATE livro SET disponivel = false WHERE id = ?";
                try (PreparedStatement stmtAtualiza = connection.prepareStatement(sqlAtualizaLivro)) {
                    stmtAtualiza.setInt(1, livroId);
                    stmtAtualiza.executeUpdate();
                }

                LocalDate dataEmprestimo = LocalDate.now();
                LocalDate dataPrevistaDevolucao = dataEmprestimo.plusDays(14); 

                String sqlEmprestimo = "INSERT INTO emprestimo (livro_id, membro_id, data_emprestimo, data_prevista_devolucao) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmtEmprestimo = connection.prepareStatement(sqlEmprestimo)) {
                    stmtEmprestimo.setInt(1, livroId);
                    stmtEmprestimo.setInt(2, membroId);
                    stmtEmprestimo.setDate(3, Date.valueOf(dataEmprestimo));
                    stmtEmprestimo.setDate(4, Date.valueOf(dataPrevistaDevolucao));

                    stmtEmprestimo.executeUpdate();
                    System.out.println("Livro emprestado com sucesso!");
                }
            } else {
                System.out.println("Livro não está disponível para empréstimo.");
            }
        }
    }


    public void devolverLivro(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do empréstimo que deseja devolver: ");
        int emprestimoId = scanner.nextInt();
        scanner.nextLine(); 

        String sqlBuscaLivro = "SELECT livro_id FROM emprestimo WHERE id = ?";
        int livroId = -1;

        try (PreparedStatement stmtBuscaLivro = connection.prepareStatement(sqlBuscaLivro)) {
            stmtBuscaLivro.setInt(1, emprestimoId);
            ResultSet rsBuscaLivro = stmtBuscaLivro.executeQuery();

            if (rsBuscaLivro.next()) {
                livroId = rsBuscaLivro.getInt("livro_id");
            } else {
                System.out.println("Empréstimo não encontrado.");
                return;
            }
        }


        String sqlAtualizaEmprestimo = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
        try (PreparedStatement stmtAtualizaEmprestimo = connection.prepareStatement(sqlAtualizaEmprestimo)) {
            stmtAtualizaEmprestimo.setDate(1, Date.valueOf(LocalDate.now()));
            stmtAtualizaEmprestimo.setInt(2, emprestimoId);
            stmtAtualizaEmprestimo.executeUpdate();
        }


        String sqlAtualizaLivro = "UPDATE livro SET disponivel = true WHERE id = ?";
        try (PreparedStatement stmtAtualizaLivro = connection.prepareStatement(sqlAtualizaLivro)) {
            stmtAtualizaLivro.setInt(1, livroId);
            stmtAtualizaLivro.executeUpdate();
        }

        System.out.println("Livro devolvido com sucesso!");
    }
}
  