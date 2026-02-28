package com.academia.batch.processor;

import com.academia.batch.model.AsignacionReporte;
import com.academia.batch.model.Clasificacion;
import org.springframework.batch.item.ItemProcessor;

public class AsignacionProcessor implements ItemProcessor<Clasificacion, AsignacionReporte> {

    @Override
    public AsignacionReporte process(Clasificacion clasificacion) {

        AsignacionReporte asignacion = new AsignacionReporte();

        asignacion.setIdReporte(clasificacion.getIdReporte());
        asignacion.setUbicacion(clasificacion.getUbicacion());
        asignacion.setGrado(clasificacion.getGrado());

        switch (clasificacion.getGrado()) {
            case "ESPECIAL":
                asignacion.setHechiceroAsignado("Satoru Gojo");
                break;
            case "1":
                asignacion.setHechiceroAsignado("Nanami Kento");
                break;
            case "2":
                asignacion.setHechiceroAsignado("Megumi Fushiguro");
                break;
            default:
                asignacion.setHechiceroAsignado("Estudiantes de Segundo Año");
                break;
        }

        System.out.println("S2 - Asignando a " + asignacion.getHechiceroAsignado() + " para el reporte " + asignacion.getIdReporte());
        return asignacion;
    }
}
