import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    // Esta Class se crea para pedir la moneda base de cambio y buscarlo en la API
    // por lo que tiene un metodo que devuelve una Class(Record) 'Moneda'
    // Recibe como parametro la moneda base ingresada por el usuario.
    public Moneda TasaDeCambio(String MonedaBase, String MonedaExtranjera) {
        // Se crea la "URI" con la dirección de la API donde se van a hacer las consultas
        URI direccion = URI.create("https://v6.exchangerate-api.com/" +
                "v6/011b185e5be0d9f50536b1e3/pair/" + MonedaBase + "/" + MonedaExtranjera);

        HttpClient client = HttpClient.newHttpClient();
        // El código de la siguiente linea que inicia con punto
        // .hace parte de una misma linea de código
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // "HttpResponse"...?
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            // Se importa la librería Gson para poder usar el metodo 'fromJson', que
            //.tiene como parametros el Json a convertir y la clase en la que se convierte
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No puedo hacer cambio de ese tipo de moneda");
        }
    }
}