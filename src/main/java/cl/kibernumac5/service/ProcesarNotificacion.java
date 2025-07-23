package cl.kibernumac5.service;

import java.time.LocalDateTime;

import cl.kibernumac5.model.Notificacion;
import cl.kibernumac5.model.Destino;
import cl.kibernumac5.model.Historial;


public class ProcesarNotificacion {

    private SelectorCanal notificacionEmail;
    private SelectorCanal notificacionSMS;
    private Historial historial;

    public ProcesarNotificacion(SelectorCanal notificacionEmail, SelectorCanal notificacionSMS, Historial historial) {
        this.notificacionEmail = notificacionEmail;
        this.notificacionSMS = notificacionSMS;
        this.historial = historial;
    }

    public boolean procesarNotificacion(Destino destino, String mensaje, String canal) {

       if (destino == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos se deben rellenar");
        }

        boolean resultado;
        if ("Email".equalsIgnoreCase(canal)){
            resultado  = notificacionEmail.proceso(destino, mensaje);
        } else if ("SMS".equalsIgnoreCase(canal)) {
            resultado = notificacionSMS.proceso(destino, mensaje);
        } else{
            throw new IllegalArgumentException("Canal desconocido");
        }


        if (resultado) {
            historial.add(new Notificacion(destino, mensaje, canal, LocalDateTime.now()));
        }
        return resultado;
    }

    public Historial getHistorial() {
        return historial;
    }
    
}
