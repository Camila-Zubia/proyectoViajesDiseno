/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Usuario
 */
public enum EstatusReservacion {
    /**
     * La reservación ha sido enviada y está a la espera de la confirmación del
     * conductor.
     */
    ESPERA,
    /**
     * El conductor ha aceptado la solicitud de reservación.
     */
    ACEPTADA,
    /**
     * El conductor ha rechazado la solicitud de reservación.
     */
    RECHAZADA,
    /**
     * La reservación ha sido cancelada por el pasajero.
     */
    CANCELADA,
    /**
     * El viaje asociado a la reservación ha finalizado.
     */
    TERMINADA
}
