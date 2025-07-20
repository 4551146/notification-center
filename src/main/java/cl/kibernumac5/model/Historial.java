package cl.kibernumac5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Historial {

    private List<Notificacion> notificaciones = new ArrayList<>();

    public void add(Notificacion notificacion) {
        notificaciones.add(notificacion);
    }

    public List<Notificacion> getNotificaciones() {
        return Collections.unmodifiableList(notificaciones);
    }
    // public class PaymentHistory {
//     private final List<Payment> payments = new ArrayList<>();

//     public void add(Payment payment) {
//         payments.add(payment);
//     }

//     public List<Payment> getPayments() {
//         return Collections.unmodifiableList(payments);
//     }
// }
    
}
