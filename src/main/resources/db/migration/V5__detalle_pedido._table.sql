CREATE TABLE detalle_pedido(
	id_pedido_producto BIGINT NOT NULL AUTO_INCREMENT,
	humedad FLOAT NOT NULL,
	estado_cacao FLOAT NOT NULL,
	fermentacion FLOAT NOT NULL,
	cantidad INTEGER NOT NULL,
	peso FLOAT NOT NULL,
	id_producto BIGINT NOT NULL,
	id_pedido BIGINT NOT NULL,
	PRIMARY KEY (id_pedido_producto),
	CONSTRAINT fk_id_producto
	FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_id_pedido
	FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
)