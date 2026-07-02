package com.ms_pago.service;

import com.ms_pago.client.ReservaClient;
import com.ms_pago.dto.PagoRequestDTO;
import com.ms_pago.dto.PagoResponseDTO;
import com.ms_pago.dto.ReservaDTO;
import com.ms_pago.exception.PagoNoEncontradoException;
import com.ms_pago.exception.ReservaNoEncontradaException;
import com.ms_pago.model.Pago;
import com.ms_pago.repository.PagoRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository = null;
    private final ReservaClient reservaClient = null;

    public List<PagoResponseDTO> listar() {
        log.info("Listando todos los pagos");

        return pagoRepository.findAll()
                .stream()
                .map(this::mapearAResponseConReserva)
                .toList();
    }

    public PagoResponseDTO buscarPorId(Long id) {
        log.info("Buscando pago con ID: {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pago no encontrado con ID: {}", id);
                    return new PagoNoEncontradoException(id);
                });

        return mapearAResponseConReserva(pago);
    }

    public List<PagoResponseDTO> listarPorReserva(Long idReserva) {
        log.info("Listando pagos de la reserva ID: {}", idReserva);

        validarReservaExiste(idReserva);

        return pagoRepository.findByIdReserva(idReserva)
                .stream()
                .map(this::mapearAResponseConReserva)
                .toList();
    }

    public List<PagoResponseDTO> listarPorEstado(String estado) {
        log.info("Listando pagos por estado: {}", estado);

        return pagoRepository.findByEstadoIgnoreCase(estado)
                .stream()
                .map(this::mapearAResponseConReserva)
                .toList();
    }

    public List<PagoResponseDTO> listarPorMetodo(String metodo) {
        log.info("Listando pagos por método: {}", metodo);

        return pagoRepository.findByMetodoIgnoreCase(metodo)
                .stream()
                .map(this::mapearAResponseConReserva)
                .toList();
    }

    public PagoResponseDTO crear(PagoRequestDTO request) {
        log.info("Creando pago para reserva ID: {}", request.getIdReserva());

        ReservaDTO reserva = validarReservaExiste(request.getIdReserva());

        Pago pago = Pago.builder()
                .fechaPago(request.getFechaPago())
                .monto(request.getMonto())
                .metodo(request.getMetodo().toUpperCase())
                .estado("PAGADO")
                .idReserva(request.getIdReserva())
                .build();

        Pago pagoGuardado = pagoRepository.save(pago);

        log.info("Pago creado correctamente con ID: {}", pagoGuardado.getIdPago());

        return mapearAResponse(pagoGuardado, reserva);
    }

    public PagoResponseDTO actualizar(Long id, PagoRequestDTO request) {
        log.info("Actualizando pago con ID: {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede actualizar. Pago no encontrado con ID: {}", id);
                    return new PagoNoEncontradoException(id);
                });

        ReservaDTO reserva = validarReservaExiste(request.getIdReserva());

        pago.setFechaPago(request.getFechaPago());
        pago.setMonto(request.getMonto());
        pago.setMetodo(request.getMetodo().toUpperCase());
        pago.setIdReserva(request.getIdReserva());

        Pago pagoActualizado = pagoRepository.save(pago);

        log.info("Pago actualizado correctamente con ID: {}", pagoActualizado.getIdPago());

        return mapearAResponse(pagoActualizado, reserva);
    }

    public PagoResponseDTO cambiarEstado(Long id, String estado) {
        log.info("Cambiando estado de pago ID: {} a {}", id, estado);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede cambiar estado. Pago no encontrado con ID: {}", id);
                    return new PagoNoEncontradoException(id);
                });

        pago.setEstado(estado.toUpperCase());

        Pago pagoActualizado = pagoRepository.save(pago);

        return mapearAResponseConReserva(pagoActualizado);
    }

    public void eliminar(Long id) {
        log.info("Eliminando pago con ID: {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede eliminar. Pago no encontrado con ID: {}", id);
                    return new PagoNoEncontradoException(id);
                });

        pagoRepository.delete(pago);

        log.info("Pago eliminado correctamente con ID: {}", id);
    }

    private ReservaDTO validarReservaExiste(Long idReserva) {
            log.info("Validando existencia de reserva ID: {}", idReserva);

            ReservaDTO reserva = reservaClient.buscarReservaPorId(idReserva);

            if (reserva == null || reserva.getIdReserva() == null) {
                throw new ReservaNoEncontradaException(idReserva);
            }

            log.info("Reserva validada correctamente. Estado: {}", reserva.getEstado());

            return reserva;
    }

    private PagoResponseDTO mapearAResponseConReserva(Pago pago) {
        ReservaDTO reserva = validarReservaExiste(pago.getIdReserva());
        return mapearAResponse(pago, reserva);
    }

    private PagoResponseDTO mapearAResponse(Pago pago, ReservaDTO reserva) {
        return PagoResponseDTO.builder()
                .idPago(pago.getIdPago())
                .fechaPago(pago.getFechaPago())
                .monto(pago.getMonto())
                .metodo(pago.getMetodo())
                .estado(pago.getEstado())
                .idReserva(pago.getIdReserva())
                .estadoReserva(reserva.getEstado())
                .nombreMascota(reserva.getNombreMascota())
                .nombreServicio(reserva.getNombreServicio())
                .build();
    }
}