package com.udi.gaaf.cuenta;

/**
 * Enumeración que define los tipos de cuenta bancaria disponibles
 * para los proveedores dentro del sistema.
 *
 * <ul>
 *   <li>{@code AHORROS}: cuenta de ahorros tradicional.</li>
 *   <li>{@code CORRIENTE}: cuenta corriente.</li>
 *   <li>{@code DEPOSITO_ELECTRONICO}: cuenta digital o virtual.</li>
 *   <li>{@code EMPRESARIAL}: cuenta exclusiva para uso empresarial.</li>
 * </ul>
 */
public enum TipoCuenta {
    /** Cuenta de ahorros tradicional. */
    AHORROS,

    /** Cuenta corriente. */
    CORRIENTE,

    /** Cuenta digital o de depósito electrónico. */
    DEPOSITO_ELECTRONICO,

    /** Cuenta bancaria empresarial. */
    EMPRESARIAL
}
