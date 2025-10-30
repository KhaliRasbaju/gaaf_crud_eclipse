package com.udi.gaaf.transaccion_inventario;

/**
 * Enumeración que define los tipos posibles de transacción de inventario.
 */
public enum TipoTransaccion {
    /** Entrada de productos al inventario (compra, recepción, etc.) */
    ENTRADA,

    /** Consumo o uso del producto en un proceso de producción. */
    PRODUCCION,

    /** Pérdida o merma de producto (por daño, caducidad, etc.) */
    MERMA
}
