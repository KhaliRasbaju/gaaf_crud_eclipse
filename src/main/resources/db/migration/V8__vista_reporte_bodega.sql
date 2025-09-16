CREATE VIEW vista_reporte_bodega AS 
	SELECT 
		b.nombre as bodega, SUM(i.cantidad_disponible) AS cantidad_disponible_total, sum(i.cantidad_reservada) AS cantidad_reservada_total ,sum(i.cantidad_disponible + i.cantidad_reservada) AS total
	FROM 
		inventario i JOIN bodega b ON i.id_bodega = b.id_bodega
	GROUP BY 
		i.id_bodega;