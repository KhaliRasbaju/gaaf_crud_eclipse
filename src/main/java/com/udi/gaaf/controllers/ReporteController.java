package com.udi.gaaf.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<VistaCompra>> obtenerReporteCompra(
    		
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fechaPedido,
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fechaEntrega,
    		@RequestParam(required = false) String estado,
    		@RequestParam(required = false) String producto,
    		@RequestParam(required = false) Integer cantidad,
    		@RequestParam(required = false) Double valorPedido,
    		
    		Pageable paginacion) {
        var detalle = compraService.obtenerReporte(        		
        		fechaPedido, 
        		fechaEntrega,
        		estado, 
        		producto,
        		cantidad,
        		valorPedido,        		
        		paginacion);
        System.out.println(detalle);
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
    public ResponseEntity<Page<VistaPedidosProveedor>> obtenerReportePedidosProveedor(
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fechaPedido,
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fechaEntrega,
    		@RequestParam(required = false) String estado,
    		@RequestParam(required = false) String metodoPago,
    		@RequestParam(required = false) Double valorPedido,
    		Pageable paginacion) {
        var detalle = pedidosProveedorService.obtenerReporte(
        		fechaPedido,
        		fechaEntrega,
        		estado,
        		metodoPago,
        		valorPedido,        		
        		paginacion);
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
    public ResponseEntity<Page<VistaInventarioMovimiento>> obtenerReporteInventarioMovimiento(
    		@RequestParam(required = false) String producto,
    		@RequestParam(required = false) String tipo,
    		@RequestParam(required = false) Integer cantidad,
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fecha,
    		Pageable paginacion) {
        var detalle = inventarioMovimientoService.obtenerReporte(
        		producto,
        		tipo,
        		cantidad,
        		fecha,
        		paginacion);
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
    public ResponseEntity<Page<VistaInventarioProductosBodega>> obtenerReporteInventarioProductosBodega(
    		@RequestParam(required = false) String producto,
    		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fecha,
    		@RequestParam(required = false) Integer cantidad,
    		Pageable paginacion
    		) {
        var detalle = inventarioProductosBodegaService.obtenerReporte(producto, fecha, cantidad,paginacion);
        return ResponseEntity.ok(detalle);
    }
}
