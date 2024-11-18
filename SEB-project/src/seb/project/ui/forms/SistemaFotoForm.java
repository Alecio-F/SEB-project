package seb.project.ui;

import seb.project.model.SistemaFotovoltaico;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class SistemaFotoForm extends VBox {

    private MainApp mainApp;

    // Campos de entrada
    private TextField potenciaSistemaField;
    private TextField irradiacaoDiariaField;

    public SistemaFotoForm(MainApp mainApp) {
        this.mainApp = mainApp;

        // Configuração do layout
        setSpacing(10);  // Define o espaçamento entre os componentes
        setPadding(new Insets(10));  // Define o padding ao redor da VBox

        // Título da seção
        Label sistemaFotoTitle = new Label("Informações do Sistema Fotovoltaico");
        sistemaFotoTitle.getStyleClass().add("titulo"); // Classe CSS para o título

        // Campos do Sistema Fotovoltaico
        TextField localidadeField = new TextField();
        localidadeField.setPromptText("Localidade");
        VBox localidadeVBox = new VBox(new Label("Localidade: "), localidadeField);
        localidadeVBox.setSpacing(5);

        TextField tipoEstruturaField = new TextField();
        tipoEstruturaField.setPromptText("Tipo de Estrutura");
        VBox tipoEstruturaVBox = new VBox(new Label("Tipo de Estrutura: "), tipoEstruturaField);
        tipoEstruturaVBox.setSpacing(5);

        potenciaSistemaField = new TextField();  // Campo para potência
        potenciaSistemaField.setPromptText("Potência do Sistema (kW)");
        VBox potenciaSistemaVBox = new VBox(new Label("Potência do Sistema: "), potenciaSistemaField);
        potenciaSistemaVBox.setSpacing(5);

        irradiacaoDiariaField = new TextField();  // Campo para irradiação solar
        irradiacaoDiariaField.setPromptText("Irradiação Diária (kWh/m²)");
        VBox irradiacaoDiariaVBox = new VBox(new Label("Irradiação Diária: "), irradiacaoDiariaField);
        irradiacaoDiariaVBox.setSpacing(5);

        TextField inclinacaoPaineisField = new TextField();
        inclinacaoPaineisField.setPromptText("Inclinação dos Painéis (graus)");
        VBox inclinacaoPaineisVBox = new VBox(new Label("Inclinação dos Painéis: "), inclinacaoPaineisField);
        inclinacaoPaineisVBox.setSpacing(5);

        TextField areaUtilField = new TextField();
        areaUtilField.setPromptText("Área Útil (m²)");
        VBox areaUtilVBox = new VBox(new Label("Área Útil: "), areaUtilField);
        areaUtilVBox.setSpacing(5);

        TextField percentualPerdaField = new TextField();
        percentualPerdaField.setPromptText("Percentual de Perda (%)");
        VBox percentualPerdaVBox = new VBox(new Label("Percentual de Perda: "), percentualPerdaField);
        percentualPerdaVBox.setSpacing(5);

        // Botão "Próximo"
        Button btnNext = new Button("Próximo");
        btnNext.setOnAction(e -> {
            // Verifica se os campos estão preenchidos
            if (localidadeField.getText().isEmpty() || tipoEstruturaField.getText().isEmpty() ||
                potenciaSistemaField.getText().isEmpty() || irradiacaoDiariaField.getText().isEmpty() ||
                inclinacaoPaineisField.getText().isEmpty() || areaUtilField.getText().isEmpty() ||
                percentualPerdaField.getText().isEmpty()) {
                
                showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
            } else {
                // Cria o objeto SistemaFotovoltaico e insere no banco de dados
                SistemaFotovoltaico sistema = new SistemaFotovoltaico(
                    0,  // ID será gerado no banco
                    1,  // Cliente ID - Adapte conforme o cliente selecionado
                    localidadeField.getText(),
                    tipoEstruturaField.getText(),
                    Float.parseFloat(potenciaSistemaField.getText()),
                    Float.parseFloat(irradiacaoDiariaField.getText()),
                    Float.parseFloat(inclinacaoPaineisField.getText()),
                    Float.parseFloat(areaUtilField.getText()),
                    Float.parseFloat(percentualPerdaField.getText())
                );
                
                mainApp.setSistemaFoto(sistema);
                mainApp.showCustoForm();
            }
        });

        // Adiciona os componentes ao layout
        getChildren().addAll(sistemaFotoTitle, localidadeVBox, tipoEstruturaVBox, potenciaSistemaVBox,
                irradiacaoDiariaVBox, inclinacaoPaineisVBox, areaUtilVBox, percentualPerdaVBox, btnNext);

        // Atualiza os campos com os valores de potência e irradiância
        atualizarCamposSistema();
    }

    // Método para atualizar a potência do sistema e a irradiância solar a partir do MainApp
    private void atualizarCamposSistema() {
        float potenciaSistema = mainApp.getPotenciaSistema();
        double irradiacaoSolar = mainApp.getIrradiacaoSolar();

        // Atualiza os campos com a potência do sistema e a irradiação solar
        potenciaSistemaField.setText(String.valueOf(potenciaSistema));
        irradiacaoDiariaField.setText(String.valueOf(irradiacaoSolar));
    }

    private void showAlert(String title, String message) {
        // Exibe um alerta caso algum campo esteja vazio
        Alert alert = new Alert(AlertType.INFORMATION);  // Tipo de alerta
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
