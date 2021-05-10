package co.edu.unicundi.proyectoSpringPrueba.dto;

import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;

public class Examenes {
    
    private Examen examen;

    private String infoAdicional;

    public Examenes(Examen examen, String infoAdicional) {
        this.examen = examen;
        this.infoAdicional = infoAdicional;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    

    

}
