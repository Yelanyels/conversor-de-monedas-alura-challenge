package lad.com.alura.conversormoneda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorDeArchivos {
    public void guardarJson(List<String> historial) throws IOException {
        // Configuramos Gson para que el archivo sea legible
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Creamos el archivo y escribimos el historial
        try (FileWriter escritura = new FileWriter("historial_conversiones.json")) {
            escritura.write(gson.toJson(historial));
        }
    }
}
