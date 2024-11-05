package seb.project.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IrradiacaoSolarAPI {

    private static final String API_URL = "https://api.openweathermap.org/energy/1.0/solar/data?lat=%s&lon=%s&date=%s&appid=%s";
    private static final String API_KEY = "4e805c73752"; 

    // Fator de correção para ajustar a irradiância ao contexto brasileiro
    private static final double FATOR_CORRECAO = 0.58; // Ajuste inicial

    // Método para obter e calcular a irradiação solar diária
    public double obterIrradiacaoSolar(double latitude, double longitude, String date) {
        double irradiacaoDiariaClearSky = 0.0; // Valor inicial

        try {
            String urlString = String.format(API_URL, latitude, longitude, date, API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("URL: " + urlString); 
            if (responseCode == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                // Converter a resposta para JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                if (jsonResponse.has("irradiance")) {
                    JSONObject irradiance = jsonResponse.getJSONObject("irradiance");

                    if (irradiance.has("daily")) {
                        JSONArray dailyArray = irradiance.getJSONArray("daily");
                        JSONObject dailyData = dailyArray.getJSONObject(0);

                        // Obter o valor de GHI para céu limpo
                        double ghiClearSky = dailyData.getJSONObject("clear_sky").getDouble("ghi");

                        // Converter de Wh/m² para kWh/m² e aplicar a correção
                        irradiacaoDiariaClearSky = (ghiClearSky / 1000.0) * FATOR_CORRECAO; // kWh/m²                   

                        System.out.printf("Irradiação Solar Diária (Céu Limpo): %.2f kWh/m²%n", irradiacaoDiariaClearSky);
                    } else {
                        System.out.println("Nenhum dado diário encontrado.");
                    }
                } else {
                    System.out.println("Nenhum resultado encontrado na irradiação.");
                }

            } else {
                System.out.println("Erro na requisição: Código " + responseCode + ", Mensagem: " + con.getResponseMessage());
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("Erro ao processar JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        return irradiacaoDiariaClearSky; // Retornar o valor calculado
    }
}
