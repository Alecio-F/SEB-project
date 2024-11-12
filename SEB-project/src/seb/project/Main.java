package seb.project;

//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.Arrays;
import seb.project.dao.SistemaFotovoltaicoDAO;
import seb.project.dao.ConsumoEnergeticoDAO;
import seb.project.ui.MainApp;
import seb.project.dao.ClienteDAO;
import seb.project.model.Cliente;
import java.util.List;
import seb.project.api.GeocodingAPI;
import seb.project.api.IrradiacaoSolarAPI;
import seb.project.dao.CustosDAO;
import seb.project.model.ConsumoEnergetico;
import seb.project.model.Custos;
import seb.project.model.SistemaFotovoltaico;
//import seb.project.util.DatabaseConnection;


public class Main {
    public static void main(String[] args) {

       MainApp.launch(MainApp.class, args);
        /* Instancia da API de irradiação
        IrradiacaoSolarAPI irradiacaoSolarAPI = new IrradiacaoSolarAPI();
        
        GeocodingAPI GeocodingAPI = new GeocodingAPI();
        
        // Instancia do DAO de consumo energético
        ConsumoEnergeticoDAO consumoEnergeticoDAO = new ConsumoEnergeticoDAO();
        
        // Instancia do DAO do sistema fotovoltaico
        SistemaFotovoltaicoDAO sistemaFotovoltaicoDAO = new SistemaFotovoltaicoDAO(irradiacaoSolarAPI, consumoEnergeticoDAO);
        
        double [] coordenadas = GeocodingAPI.obterCoordenadas("Rua Nazareno Pedroso de Oliveira, 15, Jardim São Luis - 05813060, São Paulo - SP, Brasil");
        // Parâmetros de teste para calcular a potência do sistema
        float consumoMensal = 374.77f; // Exemplo de consumo mensal em kWh

        // Chama o método calcularPotenciaSistema e armazena o resultado
        float potenciaSistema = sistemaFotovoltaicoDAO.calcularPotenciaSistema(consumoMensal, coordenadas[0], coordenadas[1]);

        // Exibe o resultado
        System.out.println("Potência do sistema fotovoltaico calculada: " + potenciaSistema + " kW");
        */
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
        } 
        
        // Instanciar o DAO
        ClienteDAO clienteDAO = new ClienteDAO();

        // Criar um cliente (com o modelo)
        Cliente cliente = new Cliente("Pedro", "12345678901234", "Rua Exemplo, 100", "São Paulo", "SP", "12345-678", "joao@example.com", "11987654321");

        // Inserir o cliente usando o DAO
        clienteDAO.inserirCliente(cliente);
        
        List<Integer> idsParaDeletar = Arrays.asList(7, 8, 9);
        clienteDAO.deletarClientes(idsParaDeletar);
        // Listar novamente para ver o efeito das alterações
        List<Cliente> clientes = clienteDAO.listarClientes();
        // exibe os clientes no terminal
        if (clientes.isEmpty()) {
        System.out.println("Nenhum cliente encontrado.");
        } else {
        for (Cliente cli: clientes) {
        System.out.println(cli.getId() + " - " + cli.getNome() + " - " + cli.getCnpj());
        }
        }*/
    }
}
