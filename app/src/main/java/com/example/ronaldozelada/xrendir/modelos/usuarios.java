package com.example.ronaldozelada.xrendir.modelos;

public class usuarios {

    private String Ruc, Nombres,ApellidoM,ApellidoP,DNI,Correo,Telefono,Usuario, Aprobador, Caja;




    public usuarios() {

    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getAprobador() {
        return Aprobador;
    }

    public void setAprobador(String aprobador) {
        Aprobador = aprobador;
    }

    public String getCaja() {
        return Caja;
    }

    public void setCaja(String caja) {
        Caja = caja;
    }

    public String getNombres() {

        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String apellidoM) {
        ApellidoM = apellidoM;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String apellidoP) {
        ApellidoP = apellidoP;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    @Override
    public String toString() {
        return DNI + Nombres;
    }
}
