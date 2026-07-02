package com.ms_pago.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ms_pago.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByIdReserva(Long idReserva);

    List<Pago> findByEstadoIgnoreCase(String estado);

    List<Pago> findByMetodoIgnoreCase(String metodo);
}