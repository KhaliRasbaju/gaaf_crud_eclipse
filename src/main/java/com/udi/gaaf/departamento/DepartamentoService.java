package com.udi.gaaf.departamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.errors.NotFoundException;

/**
 * Servicio encargado de la gestión y obtención de información de los departamentos.
 * <p>
 * Contiene la lógica de negocio necesaria para acceder a los datos de los departamentos,
 * transformarlos en objetos de transferencia (DTOs) y devolverlos al controlador.
 * </p>
 *
 * <p>Utiliza {@link DepartamentoRepository} para interactuar con la base de datos.</p>
 * 
 */
@Service
public class DepartamentoService {

    /** Repositorio que permite interactuar con la entidad {@link Departamento}. */
    @Autowired
    private DepartamentoRepository repository;

    /**
     * Convierte un objeto {@link Departamento} en un modelo común de detalle.
     *
     * @param departamento entidad de departamento
     * @return objeto {@link DatosDetalleCommon} con la información esencial
     */
    private DatosDetalleCommon detalleDepartamento(Departamento departamento) {
        return new DatosDetalleCommon(departamento.getId(), departamento.getNombre());
    }

    /**
     * Busca un departamento por su identificador único.
     *
     * @param id identificador del departamento
     * @return la entidad {@link Departamento} correspondiente
     * @throws NotFoundException si el departamento no existe
     */
    public Departamento obtenerDepartamentoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Departamento no encontrado por el id: " + id));
    }

    /**
     * Obtiene un departamento por su identificador y devuelve un objeto detallado.
     *
     * @param id identificador del departamento
     * @return objeto {@link DatosDetalleCommon} con los datos del departamento
     */
    public DatosDetalleCommon obtenerPorId(Long id) {
        var departamento = obtenerDepartamentoPorId(id);
        return detalleDepartamento(departamento);
    }

    /**
     * Obtiene todos los departamentos registrados en el sistema.
     *
     * @return lista de objetos {@link DatosDetalleCommon} con los datos de cada departamento
     */
    public List<DatosDetalleCommon> obtenerTodos() {
        var departamentos = repository.findAll();
        return departamentos.stream().map(d -> detalleDepartamento(d)).toList();
    }
}
