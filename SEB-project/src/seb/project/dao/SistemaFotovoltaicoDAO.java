package seb.project.dao;

import seb.project.model.SistemaFotovoltaico;
import seb.project.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaFotovoltaicoDAO {

    // Método para inserir um novo sistema fotovoltaico
    public void inserirSistema(SistemaFotovoltaico sistema) {
        String sql = "INSERT INTO SistemaFotovoltaico (cliente_id, localidade, tipo_estrutura, " +
                     "potencia_sistema, irradiacao_diaria, inclinacao_paineis, " +
                     "area_util, percentual_perda) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, sistema.getCliente_id());
            statement.setString(2, sistema.getLocalidade());
            statement.setString(3, sistema.getTipo_estrutura());
            statement.setFloat(4, sistema.getPotencia_sistema());
            statement.setFloat(5, sistema.getIrradiacao_diaria());
            statement.setFloat(6, sistema.getInclinacao_paineis());
            statement.setFloat(7, sistema.getArea_util());
            statement.setFloat(8, sistema.getPercentual_perda());

            statement.executeUpdate();
            System.out.println("Sistema fotovoltaico inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os sistemas fotovoltaicos
    public List<SistemaFotovoltaico> listarSistemas() {
        String sql = "SELECT * FROM SistemaFotovoltaico";
        List<SistemaFotovoltaico> listaSistemas = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int cliente_id = resultSet.getInt("cliente_id");
                String localidade = resultSet.getString("localidade");
                String tipo_estrutura = resultSet.getString("tipo_estrutura");
                float potencia_sistema = resultSet.getFloat("potencia_sistema");
                float irradiacao_diaria = resultSet.getFloat("irradiacao_diaria");
                float inclinacao_paineis = resultSet.getFloat("inclinacao_paineis");
                float area_util = resultSet.getFloat("area_util");
                float percentual_perda = resultSet.getFloat("percentual_perda");

                // Criar objeto SistemaFotovoltaico com os dados do ResultSet
                SistemaFotovoltaico sistema = new SistemaFotovoltaico(id, cliente_id, localidade, tipo_estrutura, 
                    potencia_sistema, irradiacao_diaria, inclinacao_paineis, area_util, percentual_perda);
                listaSistemas.add(sistema); // Adicionar o sistema na lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSistemas; // Retorna a lista de sistemas
    }

    // Método para atualizar um sistema fotovoltaico
    public void atualizarSistema(SistemaFotovoltaico sistema) {
        String sql = "UPDATE SistemaFotovoltaico SET cliente_id = ?, localidade = ?, tipo_estrutura = ?, " +
                     "potencia_sistema = ?, irradiacao_diaria = ?, inclinacao_paineis = ?, " +
                     "area_util = ?, percentual_perda = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, sistema.getCliente_id());
            statement.setString(2, sistema.getLocalidade());
            statement.setString(3, sistema.getTipo_estrutura());
            statement.setFloat(4, sistema.getPotencia_sistema());
            statement.setFloat(5, sistema.getIrradiacao_diaria());
            statement.setFloat(6, sistema.getInclinacao_paineis());
            statement.setFloat(7, sistema.getArea_util());
            statement.setFloat(8, sistema.getPercentual_perda());
            statement.setInt(9, sistema.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sistema fotovoltaico atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um sistema fotovoltaico pelo ID
    public void deletarSistema(int id) {
        String sql = "DELETE FROM SistemaFotovoltaico WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Sistema fotovoltaico deletado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
