/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.model;

import java.util.Objects;

/**
 * Une classe qui sert à stocker les sommets de type restaurant
 * @author bastien et hazim
 */
public class Restaurant extends Sommet {

    /** 
     * Le constructeur de la classe Restaurant
     * @param nom le nom du sommet
     */
    public Restaurant(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return  "Restaurant : " + nom;
    }
	
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Ville)) return false;

        Restaurant p = (Restaurant) obj;

        return (p.nom.equals(nom));
    }

    @Override
    public int hashCode() {
       return Objects.hash (nom);
    }
}


