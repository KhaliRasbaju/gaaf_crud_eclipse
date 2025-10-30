package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.departamento.DepartamentoService;
import com.udi.gaaf.municipio.DatosDetalleMunicipio;
import com.udi.gaaf.municipio.MunicipioService;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas
 * con la ubicación geográfica dentro del sistema GAAF.
 * <p>
 * Este controlador permite consultar información sobre departamentos
 * y municipios registrados en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    /**
     * Obtiene la información de un departamento específico según su ID.
     *
     * @param id Identificador único del departamento.
     * @return {@link ResponseEntity} con los datos del departamento 
     *         encapsulados en un {@link DatosDetalleCommon}.
     *         <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/departamento/{id}")
    public ResponseEntity<DatosDetalleCommon> obtenerDepartamentoPorId(@PathVariable Long id) {
        var detalle = departamentoService.obtenerPorId(id);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene la lista completa de departamentos registrados.
     *
     * @return {@link ResponseEntity} con una lista de {@link DatosDetalleCommon}
     *         que representan todos los departamentos disponibles.
     *         <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/departamento")
    public ResponseEntity<List<DatosDetalleCommon>> obtenerDepartamentos() {
        var detalle = departamentoService.obtenerTodos();
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene la información de un municipio específico según su ID.
     *
     * @param id Identificador único del municipio.
     * @return {@link ResponseEntity} con los datos del municipio
     *         encapsulados en un {@link DatosDetalleMunicipio}.
     *         <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/municipio/{id}")
    public ResponseEntity<DatosDetalleMunicipio> obtenerMunicipioPorId(@PathVariable Long id) {
        var detalle = municipioService.obtenerPorId(id);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene todos los municipios asociados a un departamento específico.
     *
     * @param id Identificador único del departamento.
     * @return {@link ResponseEntity} con una lista de {@link DatosDetalleMunicipio}
     *         pertenecientes al departamento indicado.
     *         <p>Retorna un código HTTP { @code 200 (OK) } si la operación es exitosa.</p>
     */
    @GetMapping("/municipio-departamento/{id}")
    public ResponseEntity<List<DatosDetalleMunicipio>> obteneMunicipiosPorIdDepartamento(@PathVariable Long id) {
        var detalle = municipioService.obtenerTodos(id);
        return ResponseEntity.ok(detalle);
    }
}
