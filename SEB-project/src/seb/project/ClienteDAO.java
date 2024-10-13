package seb.project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    public void inserirCliente(String nome, String cnpj, String endereco, String cidade, String estado, String cep, String email, String telefone) {
        String sql = "INSERT INTO Cliente (nome, cnpj, endereco, cidade, estado, cep, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            
            statement.setString(1, nome);
            statement.setString(2, cnpj);
            statement.setString(3, endereco);
            statement.setString(4, cidade);
            statement.setString(5, estado);
            statement.setString(6, cep);
            statement.setString(7, email);
            statement.setString(8, telefone);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("Cliente inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
