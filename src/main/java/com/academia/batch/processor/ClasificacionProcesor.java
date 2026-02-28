package com.academia.batch.processor;

import com.academia.batch.model.Clasificacion;
import org.springframework.batch.item.ItemProcessor;

public class ClasificacionProcesor implements ItemProcessor<Clasificacion, Clasificacion> {
    @Override
    public Clasificacion process(Clasificacion clasificacion) {
        if (clasificacion.getDensidadEnergia() >= 10000) {
            clasificacion.setGrado("ESPECIAL");
        } else if (clasificacion.getDensidadEnergia() >= 5000) {
            clasificacion.setGrado("1");
        } else if (clasificacion.getDensidadEnergia() >= 1000) {
            clasificacion.setGrado("2");
        } else {
            clasificacion.setGrado("4");
        }

        System.out.println("S1 - Clasificando reporte " + clasificacion.getIdReporte() + " como Grado: " + clasificacion.getGrado());
        return clasificacion;
    }
}
