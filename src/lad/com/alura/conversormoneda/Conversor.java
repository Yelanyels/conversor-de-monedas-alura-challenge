package lad.com.alura.conversormoneda;

public class Conversor {
    public static void exhibirMenu() {
        System.out.println("""
                ***************************************************
                Sea bienvenido/a al Conversor de Moneda Profesional
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Dólar =>> Sol peruano (PEN)
                8) Sol peruano =>> Dólar
                9) Dólar =>> Euro (EUR)
                10) Ver Historial de Conversiones
                11) Salir
                Elija una opción válida:
                ***************************************************
                """);
    }
}
