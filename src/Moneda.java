// "record" tiene los getters y setters creados de forma automatica
// Se deben asignar los parametros de las monedas que se usaran
// "JsonObject" es el tipo de dato para llamar un dato Json dentro del Json del API
public record Moneda(String base_code, String target_code, float conversion_rate, String resultado) { }
