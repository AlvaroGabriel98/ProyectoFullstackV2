package com.ms_pedidos.service;

import com.ms_pedidos.dto.*;
import java.util.List;

public interface PedidosService {
    PedidoResponseDTO createPedido(PedidoRequestDTO request);

    List<PedidoResponseDTO> getAllPedidos();

    PedidoResponseDTO getPedidoById(Long id);

    PedidoResponseDTO updateEstado(Long id, String estado);

    MessageResponseDTO deletePedido(Long id);
}
