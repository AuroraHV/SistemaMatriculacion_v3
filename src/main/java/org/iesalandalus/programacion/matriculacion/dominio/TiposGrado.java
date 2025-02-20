package org.iesalandalus.programacion.matriculacion.dominio;

public enum TiposGrado {

    GRADOD("Grado D"),
    GRADOE("Grado E");

    private String cadenaAMostrar;

    //Constructor
    private TiposGrado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    //Método imprimir
    public String imprimir() {
        int digito = 0;
        if(cadenaAMostrar == GRADOD.cadenaAMostrar) {
            digito = 0;
        } else if(cadenaAMostrar == GRADOE.cadenaAMostrar) {
            digito = 1;
        } else {
            digito = 2;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    //Representación en texto de los valores
    @Override
    public String toString() {
        return cadenaAMostrar;
    }


}
