package seb.project.dao;

import seb.project.model.Custos;
import seb.project.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustosDAO {

    // Método para inserir um novo custo
    public void inserirCusto(Custos custo) {
        String sql = "INSERT INTO Custos (cliente_id, custo_materiais, custo_mao_obra, total_custo) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, custo.getCliente_id());
            statement.setFloat(2, custo.getCusto_materiais());
            statement.setFloat(3, custo.getCusto_mao_obra());
            statement.setFloat(4, custo.getTotal_custo());

            statement.executeUpdate();
            System.out.println("Custo inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os custos
    public List<Custos> listarCustos() {
        String sql = "SELECT * FROM Custos";
        List<Custos> listaCustos = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int cliente_id = resultSet.getInt("cliente_id");
                float custo_materiais = resultSet.getFloat("custo_materiais");
                float custo_mao_obra = resultSet.getFloat("custo_mao_obra");
                float total_custo = resultSet.getFloat("total_custo");

                // Criar objeto Custos com os dados do ResultSet
                Custos custo = new Custos(id, cliente_id, custo_materiais, custo_mao_obra, total_custo);
                listaCustos.add(custo); // Adicionar o custo na lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCustos; // Retorna a lista de custos
    }

    // Método para atualizar um custo
    public void atualizarCusto(Custos custo) {
        String sql = "UPDATE Custos SET cliente_id = ?, custo_materiais = ?, custo_mao_obra = ?, total_custo = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, custo.getCliente_id());
            statement.setFloat(2, custo.getCusto_materiais());
            statement.setFloat(3, custo.getCusto_mao_obra());
            statement.setFloat(4, custo.getTotal_custo());
            statement.setInt(5, custo.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Custo atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um custo pelo ID
    public void deletarCusto(int id) {
        String sql = "DELETE FROM Custos WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Custo deletado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
