package com.udi.gaaf.transaccion_inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionInventarioService {
	@Autowired
	private TransaccionInventarioRepository repository;
}
