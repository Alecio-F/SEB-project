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
    private static final String API_KEY = "API_KEY"; 

    // Método para obter e calcular a irradiação solar diária
    public void obterIrradiacaoSolar(double latitude, double longitude, String date) {
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

                    if (irradiance.has("hourly")) {
                        JSONArray hourlyArray = irradiance.getJSONArray("hourly");
                        double irradiacaoTotal = 0.0;

                        // Iterar sobre os dados horários
                        for (int i = 0; i < hourlyArray.length(); i++) {
                            JSONObject hourlyData = hourlyArray.getJSONObject(i);
                            JSONObject clearSky = hourlyData.getJSONObject("clear_sky");
                            JSONObject cloudySky = hourlyData.getJSONObject("cloudy_sky");

                            // Acessar o GHI de ambos os tipos de céu
                            double ghiClear = clearSky.getDouble("ghi");
                            double ghiCloudy = cloudySky.getDouble("ghi");

                            // Somar o maior GHI se não for zero
                            if (ghiClear > 0 || ghiCloudy > 0) {
                                irradiacaoTotal += Math.max(ghiClear, ghiCloudy); // Usando o maior valor
                            }
                        }

                        // Calcular a irradiação diária em kWh/m²
                        double irradiacaoDiaria = irradiacaoTotal / 1000; // Convertendo W/m² para kWh/m²
                        System.out.printf("Irradiação Solar Diária: %.2f kWh/m²%n", irradiacaoDiaria);
                    } else {
                        System.out.println("Nenhum dado horário encontrado.");
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
        }
    }
}
