package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MembroDAO {

    public void cadastrarMembro(Connection connection, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
    
        if (isCpfExistente(connection, cpf)) {
            System.out.println("Erro: CPF já cadastrado!");
            return;
        }
    
        String sql = "INSERT INTO membro (nome, email, telefone, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setString(4, endereco);
            pstmt.setString(5, cpf);
            pstmt.executeUpdate();
            System.out.println("Membro cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean isCpfExistente(Connection connection, String cpf) {
        String sql = "SELECT 1 FROM membro WHERE cpf = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();  // Se encontrar um CPF, retorna true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public void listarMembros(Connection connection) {
        String sql = "SELECT id, nome, email, telefone, endereco FROM membro";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {

            System.out.println("\n--- LISTA DE MEMBROS ---");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Telefone: " + resultSet.getString("telefone"));
                System.out.println("Endereço: " + resultSet.getString("endereco"));
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarMembro(Connection connection, Scanner scanner) {
        System.out.print("ID do membro a ser editado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo Endereço: ");
        String endereco = scanner.nextLine();

        String sql = "UPDATE membro SET nome = ?, email = ?, telefone = ?, endereco = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setString(4, endereco);
            pstmt.setInt(5, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Membro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum membro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirMembro(Connection connection, Scanner scanner) {
        System.out.print("ID do membro a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "DELETE FROM membro WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Membro excluído com sucesso!");
            } else {
                System.out.println("Nenhum membro encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









}