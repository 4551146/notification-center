package cl.kibernumac5;

// import cl.kibernumac5.service.Validador;
import cl.kibernumac5.model.Historial;
import cl.kibernumac5.model.Destino;
import cl.kibernumac5.service.ProcesarNotificacion;
import cl.kibernumac5.service.SelectorCanal;
import cl.kibernumac5.model.Notificacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.mockito.ArgumentMatchers.any;

// Mockito
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class NotificationCenterTest {
    
    
    @Mock
    private SelectorCanal notificacionEmail;

    @Mock
    private SelectorCanal notificacionSMS;

    @Mock
    private Historial historial;

    // @Mock
    // private Validador validador;



    @Captor
    private ArgumentCaptor<Notificacion> notificacionCaptor;

    @InjectMocks
    private ProcesarNotificacion procesarNotificacion;

    private Destino destino;

    @BeforeEach
    void setUp(){
        procesarNotificacion = new ProcesarNotificacion(notificacionEmail, notificacionSMS, historial);  
    }

    @AfterEach
    void cleanSetup(){
        System.out.println("Limpiando Informacion");
        procesarNotificacion = null;
    }
    @Test
    void notificacionEmail_pruebaEmail(){
        destino = new Destino("dasdasd@gmail.com");
        String mensaje = "Mensaje Email";
        String canal = "Email";
        given(notificacionEmail.proceso(destino, mensaje)).willReturn(true);
        boolean resultado = procesarNotificacion.procesarNotificacion(destino, mensaje, canal);
        assertTrue(resultado);
        verify(notificacionEmail).proceso(destino, mensaje);
        verify(historial).add((any(Notificacion.class)));
    }

    @Test
    void notificacionSMS_pruebaSMS(){
        destino = new Destino("912345678");
        String mensaje = "MensajeSMS";
        String canal = "SMS";
        given(notificacionSMS.proceso(destino, mensaje)).willReturn(true);
        boolean resultado = procesarNotificacion.procesarNotificacion(destino, mensaje, canal);
        assertTrue(resultado);
        verify(notificacionSMS).proceso(destino, mensaje);
        verify(historial).add((any(Notificacion.class)));
    }
    @Test
    void notificacionEmail_lanzaExcepcion() {
        destino = new Destino("correo@invalido.cl");
        String mensaje = "mensaje";
        String canal = "Email";
    
        // Simula que el canal Email lanza una excepción
        given(notificacionEmail.proceso(destino, mensaje))
            .willThrow(new RuntimeException("Fallo en envío"));
    
        assertThrows(RuntimeException.class, () -> {
            procesarNotificacion.procesarNotificacion(destino, mensaje, canal);
        });
    
        verify(notificacionEmail).proceso(destino, mensaje);
        // No se debería guardar en el historial si falla
        verify(historial, never()).add(any());
    }



}
