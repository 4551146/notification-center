package cl.kibernumac5.service;

import cl.kibernumac5.model.Destino;

public interface SelectorCanal {       
    boolean proceso(Destino destino, String mensaje);
    String getNombreCanal();
}
    

