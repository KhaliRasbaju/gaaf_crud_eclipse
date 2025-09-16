package com.udi.gaaf.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;
}
