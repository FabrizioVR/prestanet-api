package com.prestanet.api;

public class LoginRequest {
    private String nombreUsuario;
    private String contrasena; // Asegúrate de que coincida

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() { // Asegúrate de que este nombre sea correcto
        return contrasena;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

}
