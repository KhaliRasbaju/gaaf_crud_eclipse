package com.udi.gaaf.controllers;

import com.udi.gaaf.bodega.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bodega")
public class BodegaController {

	@Autowired
	private BodegaService service;
}
