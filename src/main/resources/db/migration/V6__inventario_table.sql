CREATE TABLE inventario(
	id_inventario BIGINT NOT NULL AUTO_INCREMENT,
	fecha_actualizado DATE NOT NULL,
	cantidad_disponible INTEGER NOT NULL,
	cantidad_reservada INTEGER NOT NULL,
	id_producto BIGINT NOT NULL,
	id_bodega BIGINT NOT NULL,
	PRIMARY KEY(id_inventario),
	CONSTRAINT fk_id_producto_inventario
	FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_id_bodega_inventario
    FOREIGN KEY (id_bodega) REFERENCES bodega(id_bodega)
)