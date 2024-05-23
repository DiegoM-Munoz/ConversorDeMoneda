import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneradorDeJson {
    // Se crea un ArrayList para el historial de las consultas
    // Map<String, Object>: Define el tipo de elementos que contendrá el ArrayList.
    // En este caso cada elemento es un Map con claves de tipo String y valores de tipo Object.
    private final ArrayList<Map<String, Object>> historial = new ArrayList<>();
    // Se agrega "throws IOException" para darle manejo al error en Class Principal
    public void guardarJson(Moneda moneda, String resultado) throws IOException {
        // Crea un objeto JSON que contendrá todos los datos
        // "LinkedHashMap" asegura que el orden de los datos se mantenga igual a como es creado
        Map<String, Object> jsonData = new LinkedHashMap<>();
        // Agrega los datos de la moneda al objeto JSON
        jsonData.put("base_code", moneda.base_code());
        jsonData.put("target_code", moneda.target_code());
        jsonData.put("conversion_rate", moneda.conversion_rate());
        // Agrega el resultado como un nuevo campo al objeto JSON
        jsonData.put("resultado", resultado);
        historial.add(jsonData);

        System.out.println("""
            
            *********************************************************
             ****** PARA INICIAR OTRA CONVERSIÓN PRESIONE "1" ******
             *******   PARA CERRAR EL PROGRAMA PRESIONE "0"  *******
            *********************************************************""");
    }

    public void CrearElHistorial() throws IOException {
        // Crea un objeto Gson para generar el formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Crea el archivo .json con la información del gson
        try (FileWriter escritura = new FileWriter("Historial.json")) {
            gson.toJson(historial, escritura);
        }
    }
}
