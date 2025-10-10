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
	
	@GetMapping("/compra")
	public ResponseEntity<List<VistaCompra>> obtenerReporteCompra() {
		var detalle = compraService.obtenerReporte();
		return ResponseEntity.ok(detalle);
	}
	
	@RequestMapping("/pedido-proveedor")
	public ResponseEntity<List<VistaPedidosProveedor>> obtenerReportePedidosProveedor(){
		var detalle = pedidosProveedorService.obtenerReporte();
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/inventario-movimiento")
	public ResponseEntity<List<VistaInventarioMovimiento>> obtenerReporteInventarioMovimiento(){
		var detalle = inventarioMovimientoService.obtenerReporte();
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/producto-bodega")
	public ResponseEntity<List<VistaInventarioProductosBodega>> obtenerReporteInventarioProductosBodega(){
		var detalle = inventarioProductosBodegaService.obtenerReporte();
		return ResponseEntity.ok(detalle);
	}
}
