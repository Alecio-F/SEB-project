package seb.project.ui;

import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import seb.project.dao.ClienteDAO;
import seb.project.dao.ConsumoEnergeticoDAO; // Supondo que exista o DAO para Consumo
import seb.project.dao.SistemaFotovoltaicoDAO; // Supondo que exista o DAO para SistemaFoto
import seb.project.dao.CustosDAO; // Supondo que exista o DAO para Custos
import seb.project.model.Cliente;
import seb.project.model.ConsumoEnergetico; // Supondo que exista a classe Consumo
import seb.project.model.SistemaFotovoltaico; // Supondo que exista a classe SistemaFoto
import seb.project.model.Custos; // Supondo que exista a classe Custos

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;

public class ConsultarOrcamentos extends VBox {

    private MainApp mainApp;  // Referência ao MainApp para obter a conexão

    public ConsultarOrcamentos(MainApp mainApp) {
        this.mainApp = mainApp;

        // Criar um TabPane para organizar as tabelas em abas
        TabPane tabPane = new TabPane();

        // Criar a aba de clientes
        Tab clientesTab = new Tab("Clientes");
        clientesTab.setContent(createClientesTable());  // Método para criar e retornar a tabela de clientes

        // Criar a aba de consumo
        Tab consumoTab = new Tab("Consumo");
        consumoTab.setContent(createConsumoTable());  // Método para criar e retornar a tabela de consumo

        // Criar a aba de Sistema Foto
        Tab sistemaFotoTab = new Tab("Sistema Foto");
        sistemaFotoTab.setContent(createSistemaFotoTable());  // Método para criar e retornar a tabela de Sistema Foto

        // Criar a aba de Custos
        Tab custosTab = new Tab("Custos");
        custosTab.setContent(createCustosTable());  // Método para criar e retornar a tabela de Custos

        // Adicionar todas as abas ao TabPane
        tabPane.getTabs().addAll(clientesTab, consumoTab, sistemaFotoTab, custosTab);

        // Adicionar o TabPane ao layout
        getChildren().add(tabPane);
    }

    // Método para criar a tabela de clientes
    private TableView<Cliente> createClientesTable() {
        TableView<Cliente> tableView = new TableView<>();

        // Criar colunas para a tabela de clientes
        TableColumn<Cliente, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> cnpjColumn = new TableColumn<>("CNPJ");
        cnpjColumn.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        TableColumn<Cliente, String> enderecoColumn = new TableColumn<>("Endereço");
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        TableColumn<Cliente, String> cidadeColumn = new TableColumn<>("Cidade");
        cidadeColumn.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        TableColumn<Cliente, String> estadoColumn = new TableColumn<>("Estado");
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<Cliente, String> cepColumn = new TableColumn<>("CEP");
        cepColumn.setCellValueFactory(new PropertyValueFactory<>("cep"));

        TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cliente, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Adicionar as colunas ao TableView
        tableView.getColumns().addAll(idColumn, nomeColumn, cnpjColumn, enderecoColumn, cidadeColumn, estadoColumn, cepColumn, emailColumn, telefoneColumn);

        // Criar o DAO para acessar o banco de dados e listar os clientes
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarClientes();  // Método para listar todos os clientes

        // Adicionar os clientes ao TableView
        tableView.getItems().addAll(clientes);
        // Criar um ContextMenu com as opções
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Deletar");
        MenuItem updateItem = new MenuItem("Atualizar");

        // Ação para o menu "Deletar"
        deleteItem.setOnAction(e -> {
        // Coleta todos os clientes selecionados
        ObservableList<Cliente> clientesSelecionados = tableView.getSelectionModel().getSelectedItems();
    
        if (!clientesSelecionados.isEmpty()) {
            // Cria uma lista de IDs dos clientes selecionados
            List<Integer> ids = new ArrayList<>();
            for (Cliente cliente : clientesSelecionados) {
                ids.add(cliente.getId());
            }

            // Chama o método de deletar múltiplos clientes
            clienteDAO.deletarClientes(ids);

            // Remove os clientes selecionados da tableView
            tableView.getItems().removeAll(clientesSelecionados);
        } else {
            System.out.println("Nenhum cliente selecionado para excluir.");
        }
    });


        // Ação para o menu "Atualizar"
        updateItem.setOnAction(e -> {
            Cliente clienteSelecionado = tableView.getSelectionModel().getSelectedItem();
            if (clienteSelecionado != null) {
                // Lógica para atualizar o cliente
                // Aqui você pode abrir uma nova tela ou formulário para editar o cliente
                // mainApp.showClienteEditDialog(clienteSelecionado);
            }
        });

        // Adicionando os itens ao ContextMenu
        contextMenu.getItems().addAll(deleteItem, updateItem);

        // Adicionar o ContextMenu na tabela
        tableView.setContextMenu(contextMenu);

        // Configurar o comportamento do clique com o botão direito
        tableView.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnContextMenuRequested(event -> {
                // Exibe o menu de contexto quando o botão direito é clicado
                if (!row.isEmpty()) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        return tableView;
    }

    // Método para criar a tabela de Consumo
    private TableView<ConsumoEnergetico> createConsumoTable() {
        TableView<ConsumoEnergetico> tableView = new TableView<>();

        // Criar colunas para a tabela de Consumo
        TableColumn<ConsumoEnergetico, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ConsumoEnergetico, Integer> clienteIdColumn = new TableColumn<>("Cliente ID");
        clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("cliente_id"));

        TableColumn<ConsumoEnergetico, String> concessaoColumn = new TableColumn<>("Concessão");
        concessaoColumn.setCellValueFactory(new PropertyValueFactory<>("concessao"));

        TableColumn<ConsumoEnergetico, String> grupoColumn = new TableColumn<>("Grupo");
        grupoColumn.setCellValueFactory(new PropertyValueFactory<>("grupo"));

        TableColumn<ConsumoEnergetico, Float> consumoMensalColumn = new TableColumn<>("Consumo Mensal (kWh)");
        consumoMensalColumn.setCellValueFactory(new PropertyValueFactory<>("consumoMensal"));

        TableColumn<ConsumoEnergetico, Float> valorKwhColumn = new TableColumn<>("Valor kWh (R$)");
        valorKwhColumn.setCellValueFactory(new PropertyValueFactory<>("valor_kwh"));

        TableColumn<ConsumoEnergetico, String> tipoConexaoColumn = new TableColumn<>("Tipo de Conexão");
        tipoConexaoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_conexao"));

        // Adicionar as colunas ao TableView
        tableView.getColumns().addAll(idColumn, clienteIdColumn, concessaoColumn, grupoColumn, consumoMensalColumn, valorKwhColumn, tipoConexaoColumn);

        // Criar o DAO para acessar o banco de dados e listar os consumos
        ConsumoEnergeticoDAO consumoDAO = new ConsumoEnergeticoDAO();
        List<ConsumoEnergetico> consumos = consumoDAO.listarConsumos();  // Método para listar todos os consumos

        // Adicionar os consumos ao TableView
        tableView.getItems().addAll(consumos);

        return tableView;
    }


    private TableView<SistemaFotovoltaico> createSistemaFotoTable() {
        TableView<SistemaFotovoltaico> tableView = new TableView<>();

        // Criar colunas para a tabela de SistemaFotovoltaico
        TableColumn<SistemaFotovoltaico, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<SistemaFotovoltaico, Integer> clienteIdColumn = new TableColumn<>("Cliente ID");
        clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("cliente_id"));

        TableColumn<SistemaFotovoltaico, String> localidadeColumn = new TableColumn<>("Localidade");
        localidadeColumn.setCellValueFactory(new PropertyValueFactory<>("localidade"));

        TableColumn<SistemaFotovoltaico, String> tipoEstruturaColumn = new TableColumn<>("Tipo de Estrutura");
        tipoEstruturaColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_estrutura"));

        TableColumn<SistemaFotovoltaico, Float> potenciaSistemaColumn = new TableColumn<>("Potência do Sistema (kW)");
        potenciaSistemaColumn.setCellValueFactory(new PropertyValueFactory<>("potencia_sistema"));

        TableColumn<SistemaFotovoltaico, Float> irradiacaoDiariaColumn = new TableColumn<>("Irradiação Diária (kWh/m²)");
        irradiacaoDiariaColumn.setCellValueFactory(new PropertyValueFactory<>("irradiacao_diaria"));

        TableColumn<SistemaFotovoltaico, Float> inclinacaoPaineisColumn = new TableColumn<>("Inclinação dos Painéis (°)");
        inclinacaoPaineisColumn.setCellValueFactory(new PropertyValueFactory<>("inclinacao_paineis"));

        TableColumn<SistemaFotovoltaico, Float> areaUtilColumn = new TableColumn<>("Área Útil (m²)");
        areaUtilColumn.setCellValueFactory(new PropertyValueFactory<>("area_util"));

        TableColumn<SistemaFotovoltaico, Float> percentualPerdaColumn = new TableColumn<>("Percentual de Perda (%)");
        percentualPerdaColumn.setCellValueFactory(new PropertyValueFactory<>("percentual_perda"));

        // Adicionar as colunas ao TableView
        tableView.getColumns().addAll(idColumn, clienteIdColumn, localidadeColumn, tipoEstruturaColumn, potenciaSistemaColumn, 
                                  irradiacaoDiariaColumn, inclinacaoPaineisColumn, areaUtilColumn, percentualPerdaColumn);

        // Criar o DAO para acessar o banco de dados e listar os sistemas fotovoltaicos
        SistemaFotovoltaicoDAO sistemaFotoDAO = new SistemaFotovoltaicoDAO();
        List<SistemaFotovoltaico> sistemaFotos = sistemaFotoDAO.listarSistemas();  // Método para listar todos os sistemas fotovoltaicos

        // Adicionar os sistemas fotovoltaicos ao TableView
        tableView.getItems().addAll(sistemaFotos);

        return tableView;
    }


    private TableView<Custos> createCustosTable() {
        TableView<Custos> tableView = new TableView<>();

        // Criar colunas para a tabela de Custos
        TableColumn<Custos, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Custos, Integer> clienteIdColumn = new TableColumn<>("Cliente ID");
        clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("cliente_id"));

        TableColumn<Custos, Float> custoMateriaisColumn = new TableColumn<>("Custo de Materiais");
        custoMateriaisColumn.setCellValueFactory(new PropertyValueFactory<>("custo_materiais"));

        TableColumn<Custos, Float> custoMaoObraColumn = new TableColumn<>("Custo de Mão de Obra");
        custoMaoObraColumn.setCellValueFactory(new PropertyValueFactory<>("custo_mao_obra"));

        TableColumn<Custos, Float> totalCustoColumn = new TableColumn<>("Custo Total");
        totalCustoColumn.setCellValueFactory(new PropertyValueFactory<>("total_custo"));

        // Adicionar as colunas ao TableView
        tableView.getColumns().addAll(idColumn, clienteIdColumn, custoMateriaisColumn, custoMaoObraColumn, totalCustoColumn);

        // Criar o DAO para acessar o banco de dados e listar os custos
        CustosDAO custosDAO = new CustosDAO();
        List<Custos> custos = custosDAO.listarCustos();  // Método para listar todos os custos

        // Adicionar os custos ao TableView
        tableView.getItems().addAll(custos);

        return tableView;
    }

}
