package seb.project.ui;

import seb.project.model.Cliente;
import seb.project.model.ConsumoEnergetico;
import seb.project.model.SistemaFotovoltaico;
import seb.project.model.Custos;

import seb.project.dao.ClienteDAO;
import seb.project.dao.ConsumoEnergeticoDAO;
import seb.project.dao.SistemaFotovoltaicoDAO;
import seb.project.dao.CustosDAO;

import seb.project.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {

    private ClienteDAO clienteDAO;
    private ConsumoEnergeticoDAO consumoDAO;
    private SistemaFotovoltaicoDAO sistemaDAO;
    private CustosDAO custoDAO;

    // Construtor para inicializar os DAOs
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
        this.consumoDAO = new ConsumoEnergeticoDAO();
        this.sistemaDAO = new SistemaFotovoltaicoDAO();
        this.custoDAO = new CustosDAO();
    }

    // Método para salvar todos os dados de uma vez
    public void salvarDadosCompletos(Cliente cliente, ConsumoEnergetico consumo, SistemaFotovoltaico sistema, Custos custo) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        connection.setAutoCommit(false); // Inicia a transação

        try {
            // Insere o cliente e obtém o ID gerado
            int clienteId = clienteDAO.inserirCliente(cliente);

            // Define o ID do cliente nas outras tabelas para manter a relação
            consumo.setCliente_id(clienteId);
            sistema.setCliente_id(clienteId);
            custo.setCliente_id(clienteId);

            // Salva os dados nas outras tabelas
            consumoDAO.inserirConsumoEnergetico(consumo);
            sistemaDAO.inserirSistema(sistema);
            custoDAO.inserirCusto(custo);

            connection.commit(); // Confirma a transação
            System.out.println("Dados completos salvos com sucesso!");

        } catch (SQLException e) {
            connection.rollback(); // Reverte a transação em caso de erro
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

}
