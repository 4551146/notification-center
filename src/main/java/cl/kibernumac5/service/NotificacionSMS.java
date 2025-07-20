package cl.kibernumac5.service;

import cl.kibernumac5.model.Destino;

public class NotificacionSMS implements SelectorCanal {
    @Override
    public boolean proceso(Destino destino, String mensaje) {   
        return true;
    }

    @Override
    public String getNombreCanal() {
        return "SMS";
    }
}
