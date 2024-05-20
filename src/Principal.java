import java.util.Scanner;

public class Principal {
        // 'record(Moneda)' contiene los datos de la Moneda base de cambio
        // 'ConsultaMoneda' pide la moneda base de consulta en la API
        //.y retorna la información del Json en el formato del 'record (Moneda)'
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        // Crear la instancia de consulta de las monedas (llamado a la class 'ConsultaMoneda')
        ConsultaMoneda consulta = new ConsultaMoneda();
        try{
            System.out.println("""
            *** Para ver las divisas disponibles visita: https://www.exchangerate-api.com/docs/supported-currencies ***
            Ingresa la moneda Base de conversión:""");
            var monedaBase = lectura.next();
            System.out.println("Ingresa la Cantidad a convertir: ");
            var Cantidad =  Double.valueOf(lectura.nextDouble());
            String formatedCantidad = String.format("%.2f", Cantidad);
            // Se asignan los datos de la consulta (String) a una variable de class "Moneda"
            System.out.println("Ingresa la moneda extranjera a convertir (COP, EUR, MXN o USD): ");
            var monedaExtranjera = lectura.next();
            // Se asignan los datos de la consulta (String) a una variable de class "Moneda"
            Moneda moneda = consulta.TasaDeCambio(monedaBase, monedaExtranjera);
            System.out.println(moneda);
            var tasaDeConversion = consulta.TasaDeCambio(monedaBase, monedaExtranjera).conversion_rate();
            var total = Cantidad * tasaDeConversion;
            String formatedTotal = String.format("%.2f", total);
            System.out.println(formatedCantidad + " " + monedaBase + " equivalen a " + formatedTotal + " " + monedaExtranjera);
            // Capturamos el error y se asigna a una variable "e"
        } catch (NumberFormatException e){
            // Se imprime mensaje para el error
            System.out.println("Tasa de cambio no disponible. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");
        }
    }
}