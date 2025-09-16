CREATE TABLE pedido(
	id_pedido BIGINT NOT NULL AUTO_INCREMENT,
	fecha_pedido DATE NOT NULL,
	fecha_entrega DATE,
	recibido BOOLEAN,
	valor DECIMAL(10,2) NOT NULL,
	nit_proveedor BIGINT NOT NULL,
	PRIMARY KEY(id_pedido),
	CONSTRAINT fk_nit_proveedor
	FOREIGN KEY (nit_proveedor) REFERENCES proveedor(nit)
)