package com.ms_pedidos.repository;

import example.ms_pedidos.model.PedidosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidosDetalle, Long> {
}