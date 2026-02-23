package lad.com.alura.conversormoneda;

public record DatosMoneda(String base_code,
                          String target_code,
                          double conversion_rate,
                          double conversion_result,
                          String time_last_update_utc) {
}
