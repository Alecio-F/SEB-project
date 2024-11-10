package seb.project.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seb.project.model.Cliente;
import seb.project.model.ConsumoEnergetico;
import seb.project.model.Custos;
import seb.project.model.SistemaFotovoltaico;

public class MainApp extends Application {

    private Stage primaryStage; // Armazena o palco principal
    private Cliente cliente;
    private ConsumoEnergetico consumoEnergetico;
    private Custos custos;
    private SistemaFotovoltaico sistemaFotovoltaico;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Armazena a referência ao palco principal

        // Criação dos botões da tela inicial
        Button btnNovoOrcamento = new Button("Novo Orçamento");
        Button btnConsultarOrcamento = new Button("Consultar Orçamento");
        Button btnGerarPDF = new Button("Gerar PDF");
        Button btnBackup = new Button("Fazer Backup");
        Button btnCarregarBackup = new Button("Carregar Backup");

        // Adicionando funcionalidades aos botões
        btnNovoOrcamento.setOnAction(e -> abrirTelaNovoOrcamento());
        btnConsultarOrcamento.setOnAction(e -> abrirTelaConsultarOrcamento());
        btnGerarPDF.setOnAction(e -> gerarPDF());
        btnBackup.setOnAction(e -> fazerBackup());
        btnCarregarBackup.setOnAction(e -> carregarBackup());

        // Layout da tela inicial
        VBox layout = new VBox(10); // 10px de espaçamento entre os botões
        layout.getChildren().addAll(btnNovoOrcamento, btnConsultarOrcamento, btnGerarPDF, btnBackup, btnCarregarBackup);

        // Definir a cena e o palco
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Sistema de Orçamento de Energia Solar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirTelaNovoOrcamento() {
        // Criação do formulário de cliente
        ClienteForm clienteForm = new ClienteForm(this); // Passa o MainApp para o ClienteForm

        // Criação de uma nova cena para o ClienteForm
        Scene novaCena = new Scene(clienteForm, 400, 300);
        
        // Altera a cena do primaryStage para a nova cena
        primaryStage.setScene(novaCena);
    }

    // Torne esse método público
    public void showNextForm() {
        // Aqui você pode criar e exibir o próximo formulário (Consumo)
        ConsumoEnergeticoForm consumoForm = new ConsumoEnergeticoForm(this); // Passando o MainApp

        // Criação de uma nova cena para o próximo formulário
        Scene novaCena = new Scene(consumoForm, 400, 300);
        primaryStage.setScene(novaCena); // Muda para a próxima cena
    }

    // Torne esse método público para ir para o próximo formulário (Custos)
    public void showCustoForm() {
        // Aqui você pode criar e exibir o formulário de custos
        CustosForm custoForm = new CustosForm(this); // Passando o MainApp

        // Criação de uma nova cena para o próximo formulário
        Scene novaCena = new Scene(custoForm, 400, 300);
        primaryStage.setScene(novaCena); // Muda para a próxima cena
    }

    public void showSistemaFotoForm() {
        // Cria o formulário do sistema fotovoltaico
        SistemaFotoForm sistemaFotoForm = new SistemaFotoForm(this);

        // Cria uma nova cena com o SistemaFotoForm
        Scene novaCena = new Scene(sistemaFotoForm, 400, 300);

        // Altera a cena no primaryStage para exibir o novo formulário
        primaryStage.setScene(novaCena);
    }


    private void abrirTelaConsultarOrcamento() {
        // Implementar a lógica para consultar orçamentos anteriores
        System.out.println("Consultando orçamentos...");
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para armazenar o consumo energético
    public void setConsumoEnergetico(ConsumoEnergetico consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }

    // Método para armazenar os custos
    public void setCustos(Custos custos) {
        this.custos = custos;
    }

    // Método para armazenar o sistema fotovoltaico
    public void setSistemaFoto(SistemaFotovoltaico sistemaFoto) {
        this.sistemaFotovoltaico = sistemaFoto;
    }

    private void gerarPDF() {
        // Implementar a lógica para gerar o PDF
        System.out.println("Gerando PDF...");
    }

    private void fazerBackup() {
        // Implementar a lógica de backup
        System.out.println("Fazendo backup...");
    }

    private void carregarBackup() {
        // Implementar a lógica para carregar o backup
        System.out.println("Carregando backup...");
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }
}
