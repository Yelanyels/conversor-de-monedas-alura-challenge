package lad.com.alura.conversormoneda;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        HttpClient cliente = HttpClient.newHttpClient();
        List<String> historial = new ArrayList<>();
        int opcion = 0;

        while (opcion != 11) {
            Conversor.exhibirMenu();
            try {
                opcion = Integer.parseInt(lectura.next());
                if (opcion == 11) break;

                // Opción para mostrar historial en consola
                if (opcion == 10) {
                    System.out.println("--- HISTORIAL DE CONVERSIONES ---");
                    historial.forEach(System.out::println);
                    continue;
                }

                String monedaBase = "";
                String monedaDestino = "";

                // Soporte para más monedas
                switch (opcion) {
                    case 1 -> {
                        monedaBase = "USD";
                        monedaDestino = "ARS";
                    }
                    case 2 -> {
                        monedaBase = "ARS";
                        monedaDestino = "USD";
                    }
                    case 3 -> {
                        monedaBase = "USD";
                        monedaDestino = "BRL";
                    }
                    case 4 -> {
                        monedaBase = "BRL";
                        monedaDestino = "USD";
                    }
                    case 5 -> {
                        monedaBase = "USD";
                        monedaDestino = "COP";
                    }
                    case 6 -> {
                        monedaBase = "COP";
                        monedaDestino = "USD";
                    }
                    case 7 -> {
                        monedaBase = "USD";
                        monedaDestino = "PEN";
                    }
                    case 8 -> {
                        monedaBase = "PEN";
                        monedaDestino = "USD";
                    }
                    case 9 -> {
                        monedaBase = "USD";
                        monedaDestino = "EUR";
                    }
                    default -> {
                        System.out.println("Opción no válida.");
                        continue;
                    }
                }

                System.out.println("Ingrese el valor que desea convertir:");
                double cantidad = lectura.nextDouble();

                // Validación de Entradas Negativas
                if (cantidad < 0) {
                    System.out.println("Error: No se pueden convertir valores negativos.");
                    continue;
                }

                String url = "https://v6.exchangerate-api.com/v6/475c6a8061fb82c7b572576a/pair/" +
                        monedaBase + "/" + monedaDestino + "/" + cantidad;

                HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

                DatosMoneda misDatos = new Gson().fromJson(respuesta.body(), DatosMoneda.class);

                // Formateamos el resultado
                String resultadoTxt = String.format("%.2f [%s] = %.2f [%s] (Actualizado: %s)",
                        cantidad, monedaBase, misDatos.conversion_result(), monedaDestino, misDatos.time_last_update_utc());

                System.out.println("\n" + resultadoTxt + "\n");
                historial.add(resultadoTxt); // Guardamos en la lista

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Guardar en un Archivo JSON
        GeneradorDeArchivos generador = new GeneradorDeArchivos();
        try {
            generador.guardarJson(historial);
            System.out.println("***************************************************");
            System.out.println("Historial profesional guardado en 'historial_conversiones.json'");
            System.out.println("***************************************************");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }

        System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
    }

}