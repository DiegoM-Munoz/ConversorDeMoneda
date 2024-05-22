import java.io.IOException;
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
            ****************** BIENVENID@ AL CONVERSOR DE MONEDA ******************
            Instrucciones que debes tener presente para usar el convesor de moneda:
            1. Indique la moneda base de conversión.
            2. Ingrese la cantidad a convertir.
            3. Indique la divisa en que desea hacer la conversión.
            ***********************************************************************
            
            *** Para ver las divisas disponibles visita: https://www.exchangerate-api.com/docs/supported-currencies ***
            1. Ingresa la moneda Base de conversión:""");
            var monedaBase = lectura.next();
            System.out.println("2. Ingresa la Cantidad a convertir: ");
            var Cantidad =  Double.valueOf(lectura.nextDouble());
            String formatedCantidad = String.format("%.2f", Cantidad);
            // Se asignan los datos de la consulta (String) a una variable de class "Moneda"
            System.out.println("3. Ingresa la moneda extranjera a convertir: ");
            var monedaExtranjera = lectura.next();
            // Se asignan los datos de la consulta (String) a una variable de class "Moneda"
            Moneda moneda = consulta.TasaDeCambio(monedaBase, monedaExtranjera);
            System.out.println(moneda);
            var tasaDeConversion = consulta.TasaDeCambio(monedaBase, monedaExtranjera).conversion_rate();
            var total = Cantidad * tasaDeConversion;
            String formatedTotal = String.format("%.2f", total);
            var resultado = formatedCantidad + " " + monedaBase + " equivalen a " + formatedTotal + " " + monedaExtranjera;
            System.out.println(resultado);
            GeneradorDeJson generador = new GeneradorDeJson();
            generador.guardarJson(moneda, resultado);
            // Capturamos el error y se asigna a una variable "e"
        } catch (NumberFormatException e){
            // Se imprime mensaje para el error
            System.out.println("Tasa de cambio no disponible. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}