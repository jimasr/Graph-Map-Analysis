/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.model;

import java.util.Objects;

/**
 * Une classe qui sert à stocker les sommets de type ville
 * @author bastien et hazim
 */
public class Ville extends Sommet { 
    
    /**
     * Le constructeur de la classe Ville
     * @param nom le nom du sommet
     */
    public Ville(String nom) {
        super(nom);

    } 

    @Override
    public String toString() {
        return  "Ville : " + nom;
    }

    @Override
    public boolean equals(Object obj) {

            if (!(obj instanceof Ville)) return false;

            Ville p = (Ville) obj;

            return (p.nom.equals(nom));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
	


    
}
