package com.udi.gaaf.bodega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodegaService {
	@Autowired
	private BodegaRepository repository;
}
