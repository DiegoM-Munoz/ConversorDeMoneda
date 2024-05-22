import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneradorDeJson {
    // Se agrega "throws IOException" para darle manejo al error en Class Principal
    public void guardarJson(Moneda moneda, String resultado) throws IOException {
        ArrayList<String> historial = new ArrayList<>();
        // Crea un objeto JSON que contendrá todos los datos
        // "LinkedHashMap" asegura que el orden de los datos se mantenga igual a como es creado
        Map<String, Object> jsonData = new LinkedHashMap<>();
        // Agrega los datos de la moneda al objeto JSON
        jsonData.put("base_code", moneda.base_code());
        jsonData.put("target_code", moneda.target_code());
        jsonData.put("conversion_rate", moneda.conversion_rate());
        // Agrega el resultado como un nuevo campo al objeto JSON
        jsonData.put("resultado", resultado);

        // Crea un objeto Gson para generar el formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Convierte el objeto JSON a una cadena de texto JSON
        String json = gson.toJson(jsonData);
        historial.add(json);
        // "FileWriter" crea un documento (Con el nombre de la moneda en formato .json
        FileWriter escritura = new FileWriter("Historial.json");
        // Escribe el contenido JSON en el archivo
        escritura.write(String.valueOf(historial));
        escritura.close();
    }
}
