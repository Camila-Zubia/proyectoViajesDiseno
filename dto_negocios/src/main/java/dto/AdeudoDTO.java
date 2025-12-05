/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class AdeudoDTO {

    private String id;
    private int monto;
    private String concepto;
    private LocalDateTime fecha;
    private boolean pagado;

    public AdeudoDTO() {
        this.pagado = false;
    }

    public AdeudoDTO(int monto, String concepto, LocalDateTime fecha) {
        this.monto = monto;
        this.concepto = concepto;
        this.fecha = fecha;
        this.pagado = false;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getMonto() { return monto; }
    public void setMonto(int monto) { this.monto = monto; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }

    @Override
    public String toString() {
        return String.format("Adeudo: $%d - %s (%s)",
            monto, concepto, pagado ? "Pagado" : "Pendiente");
    }
}
