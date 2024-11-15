package seb.project.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class GeocodingAPI {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String API_KEY = "AIzaSyCQve9Nj0j3TE"; 

    // Método para obter coordenadas (latitude, longitude) com base no endereço
    public double[] obterCoordenadas(String endereco) {
        double[] coordenadas = new double[2]; // [latitude, longitude]

        try {
            // Formatar o endereço para a URL
            String enderecoFormatado = URLEncoder.encode(endereco, "UTF-8");
            String urlString = API_URL + "?address=" + enderecoFormatado + "&key=" + API_KEY;

            // Criar a conexão com a API
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Verificar se a resposta foi bem-sucedida (código 200)
            int responseCode = con.getResponseCode();
            // System.out.println("URL: " + urlString); // Imprimir a URL gerada
            if (responseCode == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    // Ler a resposta da API
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                // Converter a resposta para JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extrair latitude e longitude do JSON
                if (jsonResponse.has("results") && jsonResponse.getJSONArray("results").length() > 0) {
                    JSONObject resultado = jsonResponse.getJSONArray("results").getJSONObject(0);
                    JSONObject geometry = resultado.getJSONObject("geometry");
                    coordenadas[0] = geometry.getJSONObject("location").getDouble("lat");  // latitude
                    coordenadas[1] = geometry.getJSONObject("location").getDouble("lng");  // longitude
                } else {
                    System.out.println("Nenhum resultado encontrado.");
                }

            } else {
                System.out.println("Erro na requisição: Código " + responseCode + ", Mensagem: " + con.getResponseMessage());
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("Erro ao processar JSON: " + e.getMessage());
        }

        return coordenadas;
    }
}
