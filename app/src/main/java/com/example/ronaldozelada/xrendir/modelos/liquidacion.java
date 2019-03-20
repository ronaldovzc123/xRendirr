package com.example.ronaldozelada.xrendir.modelos;

public class liquidacion {

    private String  FechaCreacion, idLiquidacion, idSolicitud, importe, Ruc, Proveedor, FechaDoc, Numdoc, TipoDoc, Categoria,Descripcion;

    public liquidacion() {
    }

    public String getIdLiquidacion() {
        return idLiquidacion;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public void setIdLiquidacion(String idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }

    public String getFechaDoc() {
        return FechaDoc;
    }

    public void setFechaDoc(String fechaDoc) {
        FechaDoc = fechaDoc;
    }

    public String getNumdoc() {
        return Numdoc;
    }

    public void setNumdoc(String numdoc) {
        Numdoc = numdoc;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        TipoDoc = tipoDoc;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
