CREATE VIEW vista_reporte_inventario AS 
	SELECT 
		date_format(i.fecha_actualizado, '%d/%m/%Y') as fecha, i.cantidad_disponible, i.cantidad_reservada, p.nombre AS Producto, concat(concat(b.nombre, " - "), b.ubicacion) AS Bodega
	FROM 
		inventario i JOIN producto p ON p.id_producto = i.id_producto 
		JOIN bodega b ON b.id_bodega = i.id_bodega 
	ORDER BY i.fecha_actualizado DESC;