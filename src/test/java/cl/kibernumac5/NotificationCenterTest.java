package cl.kibernumac5;

// import cl.kibernumac5.service.Validador;
import cl.kibernumac5.model.Historial;
import cl.kibernumac5.model.Destino;
import cl.kibernumac5.service.ProcesarNotificacion;
import cl.kibernumac5.service.SelectorCanal;
import cl.kibernumac5.model.Notificacion;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.mockito.ArgumentMatchers.any;
// Mockito
import static org.mockito.BDDMockito.*;
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



    @Captor // Permite capturar argumentos pasados a métodos mockeados
    private ArgumentCaptor<Notificacion> notificacionCaptor;

    @InjectMocks // Inyecta los mocks en la instancia real PaymentProcessor
    private ProcesarNotificacion procesarNotificacion;

    private Destino destino;

    @BeforeEach
    void setUp(){
        procesarNotificacion = new ProcesarNotificacion(notificacionEmail, notificacionSMS, historial);
        
    }

    @Test
    void notificacionEmail_pruebaEmail(){
        destino = new Destino("Email");
        given(notificacionEmail.proceso(destino, "Mensaje")).willReturn(true);
        boolean resultado = procesarNotificacion.procesarNotificacion(destino, "Mensaje", "Email");
        assertTrue(resultado);
        verify(notificacionEmail).proceso(destino, "Mensaje");
        verify(historial).add((any(Notificacion.class)));
    }

    @Test
    void notificacionSMS_pruebaSMS(){
        Destino destino = new Destino("SMS");
        given(notificacionSMS.proceso(destino, "Mensaje")).willReturn(true);
        boolean resultado = procesarNotificacion.procesarNotificacion(destino, "Mensaje", "SMS");
        assertTrue(resultado);
        verify(notificacionSMS).proceso(destino, "Mensaje");
        verify(historial).add((any(Notificacion.class)));
    }

//     @Test
//     void testDestinoValido() {
//         assertTrue(validador.esDestinoValido("destino")); // Destino Válido
//         assertFalse(validador.esDestinoValido("")); // Destino Vacío
//         assertFalse(validador.esDestinoValido(null)); // Destino null
//         assertNotNull(validador);
//   }

//     @Test
//     void testMensajeValido(){
//         assertTrue(validador.esMensajeValido("Hola mundo")); //Mensaje válido.
//         assertFalse(validador.esMensajeValido(null)); // Mensaje null.
//         assertFalse(validador.esMensajeValido("")); //Mensaje vacío.
//     }

    
    


}
