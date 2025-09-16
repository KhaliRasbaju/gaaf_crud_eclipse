CREATE TABLE transaccion_inventario(
	id_transaccion_inventario BIGINT NOT NULL AUTO_INCREMENT,
	tipo ENUM('ENTRADA', 'SALIDA', 'AJUSTE') NOT NULL,
	cantidad INTEGER NOT NULL,
	fecha DATE NOT NULL,
	motivo VARCHAR(200),
	id_inventario BIGINT NOT NULL, 
	id_pedido BIGINT,
	PRIMARY KEY(id_transaccion_inventario),
	CONSTRAINT fk_id_inventario
	FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario),
	CONSTRAINT fk_id_pedido_transaccion_inventario
	FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
)