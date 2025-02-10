import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MembroDAO;
import dao.ReservaDAO;

public class ConexaoMySQL {

    public static void main(String[] args) {
       
        String jdbcUrl = "jdbc:mysql://wagnerweinert.com.br:3306/tads24_guilherme";
        String username = "tads24_guilherme";
        String password = "tads24_guilherme";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Conexão estabelecida com sucesso!");


            LivroDAO livroDAO = new LivroDAO();
            MembroDAO membroDAO = new MembroDAO();
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            ReservaDAO reservaDAO = new ReservaDAO();

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("\n--- MENU BIBLIOTECA ---");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Listar Livros");
                System.out.println("3. Cadastrar Membro");
                System.out.println("4. Listar Membros");
                System.out.println("5. Editar Membro");
                System.out.println("6. Excluir Membro");
                System.out.println("7. Emprestar Livro");
                System.out.println("8. Devolver Livro");
                System.out.println("9. Reservar Livro");
                System.out.println("10. Cancelar Reserva");
                System.out.println("11. Listar Reservas");
                System.out.println("12. Excluir Livro");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        livroDAO.cadastrarLivro(connection, scanner);
                        break;
                    case 2:
                        livroDAO.listarLivros(connection);
                        break;
                    case 3:
                        membroDAO.cadastrarMembro(connection, scanner);
                        break;
                    case 4:
                        membroDAO.listarMembros(connection);
                        break;
                    case 5:
                        membroDAO.editarMembro(connection, scanner);
                        break;
                    case 6:
                        membroDAO.excluirMembro(connection, scanner);
                        break;
                    case 7:
                        emprestimoDAO.emprestarLivro(connection, scanner);
                        break;
                    case 8:
                        emprestimoDAO.devolverLivro(connection, scanner);
                        break;
                    case 9:
                        reservaDAO.reservarLivro(connection, scanner);
                        break;
                    case 10:
                        reservaDAO.cancelarReserva(connection, scanner);
                        break;
                    case 11:
                        reservaDAO.buscarLivrosReservados(connection);
                        break;
                    case 12:
                        livroDAO.excluirLivro(connection, scanner);
                        break;
                    case 0:
                        System.out.println("Encerrando o programa.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 0);

            scanner.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão ou operação: " + e.getMessage());
        }
    }
}