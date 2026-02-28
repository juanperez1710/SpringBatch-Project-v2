package com.academia.batch.model;

public class Clasificacion {


    public String idReporte;
    public String ubicacion;
    public Double densidadEnergia;
    public Integer numVictimas;
    public String grado;

    public Double getDensidadEnergia() {
        return densidadEnergia;
    }

    public void setDensidadEnergia(Double densidadEnergia) {
        this.densidadEnergia = densidadEnergia;
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

    public Integer getNumVictimas() {
        return numVictimas;
    }

    public void setNumVictimas(Integer numVictimas) {
        this.numVictimas = numVictimas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Clasificacion{" +
                "densidadEnergia=" + densidadEnergia +
                ", idReporte='" + idReporte + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", numVictimas=" + numVictimas +
                ", grado='" + grado + '\'' +
                '}';
    }
}