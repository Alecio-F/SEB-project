package seb.project.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import seb.project.model.Cliente;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seb.project.api.GeocodingAPI;

public class ClienteForm extends VBox {
    
    public ClienteForm(MainApp mainApp) {

        // Campos do Cliente com limite conforme o banco de dados
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Cliente");
        nomeField.setTextFormatter(createTextFormatter(50)); // Limite de 50 caracteres
        Label nomeLabel = new Label("Nome: ");
        HBox nomeHBox = new HBox();
        nomeHBox.getChildren().addAll(nomeLabel, nomeField);
        nomeHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField cnpjCpfField = new TextField();
        cnpjCpfField.setPromptText("CNPJ/CPF");
        cnpjCpfField.setTextFormatter(createCnpjCpfFormatter()); // Aceita apenas 11 ou 14 dígitos
        Label cnpjCpfLabel = new Label("CNPJ/CPF: ");
        HBox cnpjCpfHBox = new HBox();
        cnpjCpfHBox.getChildren().addAll(cnpjCpfLabel, cnpjCpfField);
        cnpjCpfHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");
        enderecoField.setTextFormatter(createTextFormatter(100)); // Limite de 100 caracteres
        Label enderecoLabel = new Label("Endereço: ");
        HBox enderecoHBox = new HBox();
        enderecoHBox.getChildren().addAll(enderecoLabel, enderecoField);
        enderecoHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField cidadeField = new TextField();
        cidadeField.setPromptText("Cidade");
        cidadeField.setTextFormatter(createTextFormatter(50)); // Limite de 50 caracteres
        Label cidadeLabel = new Label("Cidade: ");
        HBox cidadeHBox = new HBox();
        cidadeHBox.getChildren().addAll(cidadeLabel, cidadeField);
        cidadeHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField estadoField = new TextField();
        estadoField.setPromptText("Estado");
        estadoField.setTextFormatter(createTextFormatter(2)); // Limite de 2 caracteres (UF)
        Label estadoLabel = new Label("Estado: ");
        HBox estadoHBox = new HBox();
        estadoHBox.getChildren().addAll(estadoLabel, estadoField);
        estadoHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField cepField = new TextField();
        cepField.setPromptText("CEP");
        cepField.setTextFormatter(createNumericFormatter(8)); // Limite de 8 caracteres
        Label cepLabel = new Label("CEP: ");
        HBox cepHBox = new HBox();
        cepHBox.getChildren().addAll(cepLabel, cepField);
        cepHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setTextFormatter(createTextFormatter(50)); // Limite de 50 caracteres
        Label emailLabel = new Label("Email: ");
        HBox emailHBox = new HBox();
        emailHBox.getChildren().addAll(emailLabel, emailField);
        emailHBox.setSpacing(10); // Espaçamento entre o label e o campo

        TextField telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");
        telefoneField.setTextFormatter(createTelefoneFormatter()); // Máscara para telefone
        Label telefoneLabel = new Label("Telefone: ");
        HBox telefoneHBox = new HBox();
        telefoneHBox.getChildren().addAll(telefoneLabel, telefoneField);
        telefoneHBox.setSpacing(10); // Espaçamento entre o label e o campo

        // Adicionando todos os HBox ao VBox principal
        
        // Adicionando todos os HBox ao VBox principal
        this.getChildren().addAll(nomeHBox, cnpjCpfHBox, enderecoHBox, cidadeHBox, estadoHBox, cepHBox, emailHBox, telefoneHBox);

        // Adicionando o estilo
        Scene scene = new Scene(this, 400, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Button btnNext = new Button("Próximo");
        btnNext.setOnAction(e -> {
            if (validarFormulario(nomeField, cnpjCpfField, enderecoField, cidadeField, estadoField, cepField, emailField, telefoneField)) {
                Cliente cliente = new Cliente(
                    nomeField.getText(),
                    cnpjCpfField.getText(),
                    enderecoField.getText(),
                    cidadeField.getText(),
                    estadoField.getText(),
                    cepField.getText(),
                    emailField.getText(),
                    telefoneField.getText()
                );

                // Armazenando o cliente no MainApp
                mainApp.setCliente(cliente);
                
                        // Integração com a API Geocoding para obter coordenadas
                GeocodingAPI geocodingAPI = new GeocodingAPI();
                String enderecoCompleto = enderecoField.getText() + ", " + cidadeField.getText() + ", " + estadoField.getText() + ", " + cepField.getText();

                // Chamada da API e tratamento da resposta
                double[] coordenadas = geocodingAPI.obterCoordenadas(enderecoCompleto);
                if (coordenadas != null) {
                    double latitude = coordenadas[0];
                    double longitude = coordenadas[1];
                    mainApp.setLatitude((float) latitude);
                    mainApp.setLongitude((float) longitude);
                } else {
                    System.out.println("Falha ao obter coordenadas para o endereço fornecido.");
                }

                
                mainApp.showNextForm();
            }
        });

        getChildren().addAll(btnNext);
    }

    // Método para criar um TextFormatter que permite apenas números com um limite de caracteres
    private TextFormatter<String> createNumericFormatter(int maxLength) {
        Pattern validEditingState = Pattern.compile("\\d{0," + maxLength + "}");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            return validEditingState.matcher(text).matches() ? change : null;
        };
        return new TextFormatter<>(filter);
    }

    // Método para criar um TextFormatter que aceita texto com um limite de caracteres
    private TextFormatter<String> createTextFormatter(int maxLength) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.isContentChange()) {
                String newText = change.getControlNewText();
                return newText.length() <= maxLength ? change : null;
            }
            return change;
        };
        return new TextFormatter<>(filter);
    }

    // Método para criar um TextFormatter para CNPJ/CPF que aceita apenas 11 ou 14 dígitos
    private TextFormatter<String> createCnpjCpfFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            // Permite a mudança se o novo texto tiver exatamente 11 ou 14 dígitos ou se estiver vazio (para apagar)
            if (newText.isEmpty() || newText.matches("\\d{0,11}") || newText.matches("\\d{0,14}")) {
                return change;
            }
            return null; // Bloqueia a mudança se for um valor intermediário inválido
        };
        return new TextFormatter<>(filter);
    }

    // Método para criar um TextFormatter com máscara para telefone (formato (XX) XXXXX-XXXX)
    private TextFormatter<String> createTelefoneFormatter() {
    UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        String digits = newText.replaceAll("[^\\d]", ""); // Remove caracteres que não são dígitos

        if (digits.length() > 11) { // Limita a quantidade de dígitos
            return null;
        }

        StringBuilder formatted = new StringBuilder();
        if (digits.length() > 0) {
            formatted.append("(").append(digits.substring(0, Math.min(2, digits.length())));
        }
        if (digits.length() > 2) {
            formatted.append(") ").append(digits.substring(2, Math.min(7, digits.length())));
        }
        if (digits.length() > 7) {
            formatted.append("-").append(digits.substring(7));
        }

        change.setText(formatted.toString());
        change.setRange(0, change.getControlText().length());
        return change;
    };
    return new TextFormatter<>(filter);
}


    private boolean validarFormulario(TextField nomeField, TextField cnpjCpfField, TextField enderecoField, TextField cidadeField, TextField estadoField, TextField cepField, TextField emailField, TextField telefoneField) {
        if (nomeField.getText().isEmpty() || enderecoField.getText().isEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos obrigatórios.");
            return false;
        }
        if (!cepField.getText().matches("\\d{8}")) {
            showAlert("Erro no CEP", "O CEP deve conter exatamente 8 dígitos.");
            return false;
        }
        if (!cnpjCpfField.getText().matches("\\d{11}|\\d{14}")) {
            showAlert("Erro no CNPJ/CPF", "O CPF deve conter 11 dígitos e o CNPJ 14 dígitos.");
            return false;
        }
        if (!telefoneField.getText().matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            showAlert("Erro no Telefone", "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.");
            return false;
        }
        if (!isValidEmail(emailField.getText())) {
            showAlert("Erro no Email", "O email informado é inválido.");
            return false;
        }
        return true;
    }

    // Método para validar o formato do email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
