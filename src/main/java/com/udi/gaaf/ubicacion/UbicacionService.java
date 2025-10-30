package com.udi.gaaf.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.municipio.MunicipioService;
import com.udi.gaaf.proveedor.Proveedor;

/**
 * Servicio encargado de manejar la lógica de negocio relacionada con las ubicaciones
 * de los proveedores, incluyendo creación, edición, obtención y eliminación.
 */
@Service
public class UbicacionService {

	@Autowired
	private UbicacionRepository repository;
	
	@Autowired
	private MunicipioService municipioService;

	/**
	 * Convierte una entidad {@link Ubicacion} en un objeto {@link DatosDetalleUbicacion}.
	 *
	 * @param ubicacion Entidad Ubicacion.
	 * @return Detalle completo de la ubicación.
	 */
	private DatosDetalleUbicacion detalleUbicacion(Ubicacion ubicacion) {
		var detalleMunicipio = municipioService.obtenerPorId(ubicacion.getMunicipio().getId());
		return new DatosDetalleUbicacion(ubicacion.getId(), ubicacion.getDireccion(), detalleMunicipio);
	}

	/**
	 * Busca una ubicación por su identificador.
	 *
	 * @param id Identificador único de la ubicación.
	 * @return Entidad Ubicacion encontrada.
	 * @throws NotFoundException Si no existe una ubicación con el ID proporcionado.
	 */
	public Ubicacion obtenerUbicacionPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ubicacion no encontrada por el id: " + id));
	}

	/**
	 * Crea una nueva ubicación para un proveedor.
	 *
	 * @param datos      Datos para registrar la ubicación.
	 * @param proveedor  Proveedor al que pertenece la ubicación.
	 * @return Objeto con el detalle de la ubicación creada.
	 */
	public DatosDetalleUbicacion crear(DatosRegistrarUbicacion datos, Proveedor proveedor) {
		var municipio = municipioService.obtenerMunicipioPorId(datos.idMunicipio());
		var ubicacion = new Ubicacion(datos, municipio, proveedor);
		var nuevaUbicacion = repository.save(ubicacion);
		return detalleUbicacion(nuevaUbicacion);
	}

	/**
	 * Edita la ubicación asociada a un proveedor existente.
	 *
	 * @param datos      Nuevos datos de la ubicación.
	 * @param proveedor  Proveedor cuya ubicación será modificada.
	 * @return Objeto con el detalle actualizado de la ubicación.
	 */
	public DatosDetalleUbicacion editar(DatosRegistrarUbicacion datos, Proveedor proveedor) {
		var municipio = municipioService.obtenerMunicipioPorId(datos.idMunicipio());
		var ubicacion = obtenerUbicacionPorId(proveedor.getUbicaciones().getId());
		
		if (ubicacion.getDireccion() != datos.direccion())
			ubicacion.setDireccion(datos.direccion());
		
		if (ubicacion.getMunicipio() != municipio)
			ubicacion.setMunicipio(municipio);
		
		repository.save(ubicacion);
		return detalleUbicacion(ubicacion);
	}

	/**
	 * Obtiene los detalles de una ubicación por su ID.
	 *
	 * @param id Identificador de la ubicación.
	 * @return Objeto con el detalle de la ubicación.
	 */
	public DatosDetalleUbicacion obtenerPorId(Long id) {
		var ubicacion = obtenerUbicacionPorId(id);
		return detalleUbicacion(ubicacion);
	}

	/**
	 * Obtiene todas las ubicaciones asociadas a un proveedor por su NIT.
	 *
	 * @param nit Identificador del proveedor (NIT).
	 * @return Lista de ubicaciones detalladas del proveedor.
	 */
	public List<DatosDetalleUbicacion> obtenerTodosPorNit(Long nit) {
		var ubicaciones = repository.findAllByProveedor_Nit(nit);
		return ubicaciones.stream().map(u -> detalleUbicacion(u)).toList();
	}

	/**
	 * Elimina una ubicación por su identificador.
	 *
	 * @param id Identificador de la ubicación a eliminar.
	 * @return Respuesta con mensaje de éxito.
	 */
	public DatosDetalleResponse eliminarPorId(Long id) {
		var ubicacion = obtenerUbicacionPorId(id);
		repository.delete(ubicacion);
		return new DatosDetalleResponse(200, "Ubicacion eliminada correctamente");
	}
}
