package cl.kibernumac5.model;

public class Notificacion {
    private Destino destino;
    private String mensaje;
    private String canal;
    private final java.time.LocalDateTime date;

    public Notificacion(Destino destino, String mensaje, String canal, java.time.LocalDateTime date){
        this.destino = destino;
        this.mensaje = mensaje;
        this.canal = canal;
        this.date = date;
    }

    public Destino getDestino() { return destino; }
    public String getMensaje() { return mensaje; }
    public String getCanal() { return canal; }
    public java.time.LocalDateTime getDate() { return date; }
    
}
