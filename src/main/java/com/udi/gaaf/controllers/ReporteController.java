package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.vista_pedidos_proveedor.VistaPedidosProveedor;
import com.udi.gaaf.vista_pedidos_proveedor.VistaPedidosProveedorService;
import com.udi.gaaf.vista_reporte_compras.VistaCompra;
import com.udi.gaaf.vista_reporte_compras.VistaCompraService;
import com.udi.gaaf.vista_reporte_inventario_movimientos.VistaInventarioMovimiento;
import com.udi.gaaf.vista_reporte_inventario_movimientos.VistaInventarioMovimientoService;
import com.udi.gaaf.vista_reporte_inventario_productos_por_bodega.VistaInventarioProductosBodega;
import com.udi.gaaf.vista_reporte_inventario_productos_por_bodega.VistaInventarioProductosBodegaService;

/**
 * Controlador REST encargado de gestionar los distintos reportes del sistema GAAF.
 * <p>
 * Proporciona endpoints para consultar información consolidada de compras,
 * pedidos a proveedores, movimientos de inventario y productos por bodega.
 * </p>
 */
@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private VistaCompraService compraService;

    @Autowired
    private VistaPedidosProveedorService pedidosProveedorService;

    @Autowired
    private VistaInventarioMovimientoService inventarioMovimientoService;

    @Autowired
    private VistaInventarioProductosBodegaService inventarioProductosBodegaService;

    /**
     * Obtiene el reporte general de compras realizadas.
     *
     * @return {@link ResponseEntity} con una lista de {@link VistaCompra} que contiene
     * la información consolidada de las compras.
     * <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/compra")
    public ResponseEntity<List<VistaCompra>> obtenerReporteCompra() {
        var detalle = compraService.obtenerReporte();
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene el reporte de pedidos realizados a proveedores.
     *
     * @return {@link ResponseEntity} con una lista de {@link VistaPedidosProveedor}
     * que muestra los pedidos y sus estados actuales.
     * <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @RequestMapping("/pedido-proveedor")
    public ResponseEntity<List<VistaPedidosProveedor>> obtenerReportePedidosProveedor() {
        var detalle = pedidosProveedorService.obtenerReporte();
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene el reporte de los movimientos de inventario (entradas y salidas).
     *
     * @return {@link ResponseEntity} con una lista de {@link VistaInventarioMovimiento}
     * que representa los registros de movimientos en el inventario.
     * <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/inventario-movimiento")
    public ResponseEntity<List<VistaInventarioMovimiento>> obtenerReporteInventarioMovimiento() {
        var detalle = inventarioMovimientoService.obtenerReporte();
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene el reporte de los productos disponibles por bodega.
     *
     * @return {@link ResponseEntity} con una lista de {@link VistaInventarioProductosBodega}
     * que muestra el inventario actual agrupado por bodega.
     * <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/producto-bodega")
    public ResponseEntity<List<VistaInventarioProductosBodega>> obtenerReporteInventarioProductosBodega() {
        var detalle = inventarioProductosBodegaService.obtenerReporte();
        return ResponseEntity.ok(detalle);
    }
}
