package com.academia.batch.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "misiones_finales")
public class AsignacionReporte {

    public String idReporte;
    public String ubicacion;
    public Double densidadEnergia;
    public Integer numVictimas;
    public String grado;
    public String hechiceroAsignado;

    public Integer getNumVictimas() {
        return numVictimas;
    }

    public void setNumVictimas(Integer numVictimas) {
        this.numVictimas = numVictimas;
    }

    public Double getDensidadEnergia() {
        return densidadEnergia;
    }

    public void setDensidadEnergia(Double densidadEnergia) {
        this.densidadEnergia = densidadEnergia;
    }

    public String getHechiceroAsignado() {
        return hechiceroAsignado;
    }

    public void setHechiceroAsignado(String hechiceroAsignado) {
        this.hechiceroAsignado = hechiceroAsignado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "AsignacionReporte{" +
                "densidadEnergia=" + densidadEnergia +
                ", idReporte='" + idReporte + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", numVictimas=" + numVictimas +
                ", grado='" + grado + '\'' +
                ", hechiceroAsignado='" + hechiceroAsignado + '\'' +
                '}';
    }
}