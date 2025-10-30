package com.udi.gaaf.transaccion_inventario;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.inventario.DatosBuscarInventarioIds;
import com.udi.gaaf.inventario.DatosRegistrarInventario;
import com.udi.gaaf.inventario.InventarioService;
import com.udi.gaaf.pedido.PedidoService;

/**
 * Servicio encargado de manejar la lógica de negocio relacionada con las transacciones de inventario.
 */
@Service
public class TransaccionInventarioService {

    @Autowired
    private TransaccionInventarioRepository repository;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private PedidoService pedidoService;

    /**
     * Convierte una entidad de transacción en un objeto de detalle.
     *
     * @param transaccion Entidad TransaccionInventario.
     * @return Objeto con la información detallada de la transacción.
     */
    @SuppressWarnings("unused")
	private DatosDetalleTransaccion detalleTransaccion(TransaccionInventario transaccion) {
        return new DatosDetalleTransaccion(
                transaccion.getId(),
                transaccion.getTipo(),
                transaccion.getCantidad(),
                transaccion.getObservacion(),
                transaccion.getPedido().getId(),
                transaccion.getInventario().getProducto().getNombre(),
                transaccion.getInventario().getBodega().getNombre()
        );
    }

    /**
     * Obtiene una transacción de inventario por su identificador.
     *
     * @param id Identificador de la transacción.
     * @return Entidad TransaccionInventario.
     * @throws NotFoundException Si no se encuentra la transacción.
     */
    public TransaccionInventario obtenerTransaccioPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transacción no encontrada por el id: " + id));
    }

    /**
     * Crea una nueva transacción de inventario.
     *
     * @param datos Datos necesarios para registrar la transacción.
     * @return Respuesta con mensaje de éxito.
     * @throws NotFoundException Si no se encuentran entidades relacionadas (inventario o pedido).
     */
    @Transactional
    public DatosDetalleResponse crear(DatosRegistrarTransaccion datos) {
        DatosRegistrarInventario datosInventario = new DatosRegistrarInventario(
                LocalDateTime.now(),
                datos.cantidad(),
                datos.idProducto(),
                datos.idBodega()
        );

        if (datos.tipo().equals(TipoTransaccion.ENTRADA)) {
            inventarioService.crear(datosInventario, false);
        } else {
            inventarioService.crear(datosInventario, true);
        }

        DatosBuscarInventarioIds buscar = new DatosBuscarInventarioIds(datos.idBodega(), datos.idProducto());
        var inventario = inventarioService.obtenerPorInventarioIds(buscar);
        var pedido = (datos.idPedido() != null)
                ? pedidoService.obtenerPedidoPorId(datos.idPedido())
                : null;

        var transaccion = new TransaccionInventario(datos, inventario, pedido);
        repository.save(transaccion);

        return new DatosDetalleResponse(200, "Transacción creada correctamente");
    }
}