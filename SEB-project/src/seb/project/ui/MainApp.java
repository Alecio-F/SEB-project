package seb.project.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seb.project.model.Cliente;
import seb.project.model.ConsumoEnergetico;
import seb.project.model.SistemaFotovoltaico;
import seb.project.ui.forms.ClienteForm;
import seb.project.ui.forms.ConsumoEnergeticoForm;
import seb.project.ui.forms.CustosForm;
import seb.project.ui.forms.SistemaFotoForm;

public class MainApp extends Application {

    private BorderPane mainLayout;
    private Cliente cliente;
    private ConsumoEnergetico consumoEnergetico;
    private SistemaFotovoltaico sistemaFotovoltaico;
    private float potenciaSistema;
    private double irradiacaoSolar;

    @Override
    public void start(Stage primaryStage) {
        this.mainLayout = new BorderPane();

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

        // Configuração do VBox para os botões
        VBox menuBox = new VBox(15);
        menuBox.getChildren().addAll(btnNovoOrcamento, btnConsultarOrcamento, btnGerarPDF, btnBackup, btnCarregarBackup);
        menuBox.setAlignment(Pos.TOP_LEFT);
        menuBox.setPrefWidth(150);

        // Separador Vertical
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);

        // Adicionando o menu e o separador na esquerda do BorderPane
        HBox menuContainer = new HBox(10, menuBox, separator);
        mainLayout.setLeft(menuContainer);

        // Configuração da cena
        Scene scene = new Scene(mainLayout, 900, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Adiciona o CSS
        primaryStage.setTitle("Sistema de Orçamento de Energia Solar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para abrir o formulário de novo orçamento
    private void abrirTelaNovoOrcamento() {
        ClienteForm clienteForm = new ClienteForm(this);
        SistemaFotoForm sistemaFotoForm = new SistemaFotoForm(this);
        ConsumoEnergeticoForm consumoEnergeticoForm = new ConsumoEnergeticoForm(this);
        CustosForm custosForm = new CustosForm(this);
        mainLayout.setCenter(clienteForm); // Define o ClienteForm na área central do BorderPane
    }

    // Método para abrir o formulário de consulta de orçamentos
    private void abrirTelaConsultarOrcamento() {
        ConsultarOrcamentos consultarOrcamentos = new ConsultarOrcamentos(this);
        mainLayout.setCenter(consultarOrcamentos); // Define a tela de consulta de orçamentos na área central
    }

    // Exibir o próximo formulário (Consumo) na mesma área central
    public void showNextForm() {
        ConsumoEnergeticoForm consumoForm = new ConsumoEnergeticoForm(this);
        mainLayout.setCenter(consumoForm); // Define o ConsumoEnergeticoForm na área central
    }

    // Exibir o formulário de custos na mesma área central
    public void showCustoForm() {
        CustosForm custoForm = new CustosForm(this);
        mainLayout.setCenter(custoForm); // Define o CustosForm na área central
    }

    // Métodos para armazenar os valores de potência e irradiância
    public void setPotenciaSistema(float potenciaSistema) {
        this.potenciaSistema = potenciaSistema;
    }

    public float getPotenciaSistema() {
        return potenciaSistema;
    }

    public void setIrradiacaoSolar(double irradiacaoSolar) {
        this.irradiacaoSolar = irradiacaoSolar;
    }

    public float getIrradiacaoSolar() {
        return (float) irradiacaoSolar;
    }

    public void setConsumoEnergetico(ConsumoEnergetico consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }

    public ConsumoEnergetico getConsumoEnergetico() {
        return consumoEnergetico;
    }

    // Método para exibir o formulário do sistema fotovoltaico
    public void showSistemaFotoForm() {
        SistemaFotoForm sistemaFotoForm = new SistemaFotoForm(this);
        mainLayout.setCenter(sistemaFotoForm); // Define o SistemaFotoForm na área central
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setSistemaFoto(SistemaFotovoltaico sistemaFoto) {
        this.sistemaFotovoltaico = sistemaFoto;
    }

    public SistemaFotovoltaico getSistemaFoto() {
        return sistemaFotovoltaico;
    }

    private void gerarPDF() {
        System.out.println("Gerando PDF...");
    }

    private void fazerBackup() {
        System.out.println("Fazendo backup...");
    }

    private void carregarBackup() {
        System.out.println("Carregando backup...");
    }

    private float latitude;
    private float longitude;

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
