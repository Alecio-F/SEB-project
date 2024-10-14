package seb.project;

//import java.sql.Connection;
//import java.sql.SQLException;
import seb.project.dao.ClienteDAO;
import seb.project.model.Cliente;
import java.util.List;
//import seb.project.util.DatabaseConnection;


public class Main {
    public static void main(String[] args) {
        /*
        // testando conexão com sql
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } */
        
        // Instanciar o DAO
        ClienteDAO clienteDAO = new ClienteDAO();

        // Criar um cliente (com o modelo)
        Cliente cliente = new Cliente("João Silva", "12345678901234", "Rua Exemplo, 100", "São Paulo", "SP", "12345-678", "joao@example.com", "11987654321");

        // Inserir o cliente usando o DAO
        clienteDAO.inserirCliente(cliente);

        // Listar novamente para ver o efeito das alterações
        clienteDAO.listarClientes();

    }
}
