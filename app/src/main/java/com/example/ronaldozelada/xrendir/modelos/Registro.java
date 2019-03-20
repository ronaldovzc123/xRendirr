package com.example.ronaldozelada.xrendir.modelos;

public class Registro {

    private String Uid;
    private String Fecha;

    private String Motivo;
    private String Descripcion;
    private String CentroCosto;
    private String Importe;
    private String EstadoSolicitud;
    private String Hora;
    private String FechaHora;
    private String FechaHasta;
    private String FechaDesde;
    private String DNI;
    private String FechaSolicitud;
    private String dniApro;
    private String dniAproDesembol;
    private String Nombres_Apellidos;
    private String FechaAprobador;
    private String DniAproLiquidacion;
    private String FechaDesembolso;
    private String Ruc;

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

/*public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }*/

    public String getFechaDesembolso() {
        return FechaDesembolso;
    }

    public void setFechaDesembolso(String fechaDesembolso) {
        FechaDesembolso = fechaDesembolso;
    }

    public String getNombres_Apellidos() {
        return Nombres_Apellidos;
    }

    public void setNombres_Apellidos(String nombres_Apellidos) {
        Nombres_Apellidos = nombres_Apellidos;
    }


    public String getDniAproLiquidacion() {
        return DniAproLiquidacion;
    }

    public void setDniAproLiquidacion(String dniAproLiquidacion) {
        DniAproLiquidacion = dniAproLiquidacion;
    }

    public String getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(String fechaApro) {
        FechaSolicitud = fechaApro;
    }

    public String getDniApro() {
        return dniApro;

    }

    public String getFechaAprobador() {
        return FechaAprobador;
    }

    public void setFechaAprobador(String fechaAprobador) {
        FechaAprobador = fechaAprobador;
    }

    public String getDniAproDesembol() {
        return dniAproDesembol;
    }

    public void setDniAproDesembol(String dniAproDesembol) {
        this.dniAproDesembol = dniAproDesembol;
    }

    public void setDniApro(String dniApro) {
        this.dniApro = dniApro;
    }



    public Registro() {
    }


    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }



    public String getFechaHasta() {
        return FechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        FechaHasta = fechaHasta;
    }

    public String getFechaDesde() {
        return FechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        FechaDesde = fechaDesde;
    }



    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }



    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCentroCosto() {
        return CentroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        CentroCosto = centroCosto;
    }

    public String getImporte() {
        return Importe;
    }

    public void setImporte(String importe) {
        Importe = importe;
    }

    public String getEstadoSolicitud() {
        return EstadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        EstadoSolicitud = estadoSolicitud;
    }

    @Override
    public String toString() {
        return   Fecha +" / "+  Importe+" / " + EstadoSolicitud + " / "+ Motivo;
    }

}
