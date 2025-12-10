package dto;

import java.time.LocalDateTime;

public class ReporteDTO {

    private String id;

    private UsuarioDTO reportante;
    private PasajeroDTO reportado;
    private ViajeDTO viaje;

    private String motivo;
    private String comentario;
    private String foto;

    private String estado;
    private LocalDateTime fechaHora;

    public ReporteDTO() {
        this.estado = "PENDIENTE";
        this.fechaHora = LocalDateTime.now();
    }

    public ReporteDTO(UsuarioDTO reportante,
            PasajeroDTO reportado,
            ViajeDTO viaje,
            String motivo,
            String comentario,
            String foto) {

        this.reportante = reportante;
        this.reportado = reportado;
        this.viaje = viaje;
        this.motivo = motivo;
        this.comentario = comentario;
        this.foto = foto;

        this.estado = "PENDIENTE";
        this.fechaHora = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioDTO getReportante() {
        return reportante;
    }

    public void setReportante(UsuarioDTO reportante) {
        this.reportante = reportante;
    }

    public PasajeroDTO getReportado() {
        return reportado;
    }

    public void setReportado(PasajeroDTO reportado) {
        this.reportado = reportado;
    }

    public ViajeDTO getViaje() {
        return viaje;
    }

    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
