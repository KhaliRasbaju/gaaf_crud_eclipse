package com.udi.gaaf.common;

/**
 * Enumeración que representa los posibles estados de visualización
 * o procesamiento dentro del sistema.
 * <p>
 * Generalmente utilizada para marcar el estado de una solicitud, registro
 * o entidad en procesos internos.
 * </p>
 *
 * <ul>
 *   <li>{@code PENDIENTE}: el elemento está en espera de ser procesado.</li>
 *   <li>{@code RECIBIDO}: el elemento ha sido recibido o completado.</li>
 * </ul>
 */
public enum VistaEstado {
    /** Estado cuando el proceso o acción aún no ha sido completado. */
    PENDIENTE,

    /** Estado cuando el proceso o acción se ha completado correctamente. */
    RECIBIDO
}
