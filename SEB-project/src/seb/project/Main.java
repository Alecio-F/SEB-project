package seb.project;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        
        // testando conexão com sql
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            // Criar uma instância de ClienteDAO
            ClienteDAO clienteDAO = new ClienteDAO();
            
            // Chamar o método listarClientes
            clienteDAO.listarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
