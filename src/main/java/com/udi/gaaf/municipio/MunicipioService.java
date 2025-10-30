package com.udi.gaaf.municipio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udi.gaaf.errors.NotFoundException;

/**
 * Servicio encargado de la lógica de negocio relacionada con los municipios.
 * Permite obtener municipios por ID y listar municipios por departamento.
 */
@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;

    /**
     * Convierte una entidad {@link Municipio} en un DTO {@link DatosDetalleMunicipio}.
     *
     * @param municipio Objeto entidad de tipo Municipio.
     * @return DTO con los datos detallados del municipio.
     */
    private DatosDetalleMunicipio detalleMunicipio(Municipio municipio) {
        return new DatosDetalleMunicipio(
            municipio.getId(),
            municipio.getNombre(),
            municipio.getDepartamento().getNombre()
        );
    }

    /**
     * Busca un municipio por su ID.
     *
     * @param id ID del municipio.
     * @return Objeto {@link Municipio} correspondiente al ID.
     * @throws NotFoundException Si no se encuentra un municipio con ese ID.
     */
    public Municipio obtenerMunicipioPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Municipio no encontrado por el id: " + id));
    }

    /**
     * Obtiene un municipio por ID y devuelve su detalle.
     *
     * @param id ID del municipio.
     * @return DTO {@link DatosDetalleMunicipio} con los datos del municipio.
     */
    public DatosDetalleMunicipio obtenerPorId(Long id) {
        var municipio = obtenerMunicipioPorId(id);
        return detalleMunicipio(municipio);
    }

    /**
     * Obtiene todos los municipios pertenecientes a un departamento.
     *
     * @param id ID del departamento.
     * @return Lista de {@link DatosDetalleMunicipio} con los municipios del departamento.
     */
    public List<DatosDetalleMunicipio> obtenerTodos(Long id) {
        var municipios = repository.findAllByDepartamento_Id(id);
        return municipios.stream()
            .map(this::detalleMunicipio)
            .toList();
    }
}
