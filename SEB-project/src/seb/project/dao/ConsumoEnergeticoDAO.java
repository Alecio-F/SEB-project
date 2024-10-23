package seb.project.dao;

import seb.project.model.ConsumoEnergetico;
import seb.project.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumoEnergeticoDAO {

    // Método para inserir um novo consumo energético
    public void inserirConsumoEnergetico(ConsumoEnergetico consumo) {
        String sql = "INSERT INTO ConsumoEnergetico (cliente_id, concessao, grupo, consumo_mensal, valor_kwh) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, consumo.getCliente_id());
            statement.setString(2, consumo.getConcessao());
            statement.setString(3, consumo.getGrupo());
            statement.setDouble(4, consumo.getConsumoMensal());
            statement.setDouble(5, consumo.getValor_kwh());

            statement.executeUpdate();
            System.out.println("Consumo energético inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os consumos energéticos
    public List<ConsumoEnergetico> listarConsumos() {
        String sql = "SELECT * FROM ConsumoEnergetico";
        List<ConsumoEnergetico> listaConsumos = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int cliente_id = resultSet.getInt("cliente_id");
                String concessao = resultSet.getString("concessao");
                String grupo = resultSet.getString("grupo");
                float consumo_mensal = resultSet.getFloat("consumo_mensal");
                float valor_kwh = resultSet.getFloat("valor_kwh");

                // Criar objeto ConsumoEnergetico com os dados do ResultSet
                ConsumoEnergetico consumo = new ConsumoEnergetico(id, cliente_id, concessao, grupo, consumo_mensal, valor_kwh);
                listaConsumos.add(consumo); // Adicionar o consumo na lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaConsumos; // Retorna a lista de consumos energéticos
    }

    // Método para atualizar um consumo energético
    public void atualizarConsumoEnergetico(ConsumoEnergetico consumo) {
        String sql = "UPDATE ConsumoEnergetico SET cliente_id = ?, concessao = ?, grupo = ?, consumo_mensal = ?, valor_kwh = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, consumo.getCliente_id());
            statement.setString(2, consumo.getConcessao());
            statement.setString(3, consumo.getGrupo());
            statement.setDouble(4, consumo.getConsumoMensal());
            statement.setDouble(5, consumo.getValor_kwh());
            statement.setInt(6, consumo.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Consumo energético atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um consumo energético pelo ID
    public void deletarConsumoEnergetico(int id) {
        String sql = "DELETE FROM ConsumoEnergetico WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Consumo energético deletado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
