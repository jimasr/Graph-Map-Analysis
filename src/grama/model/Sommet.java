/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.model;


/**
 * Une classe mère qui permet de définir la base de ce qu'est un sommet 
 * @author bastien et hazim
 */
abstract public class Sommet {
    public String nom;

    /**
     * Le constructeur de la classe Sommet
     * @param nom le nom du sommet
     */
    public Sommet(String nom) { 
        this.nom = nom;
    }

    /**
     * Permet de récupérer le nom d'un sommet
     * @return le nom du sommet
     */
    public String getNom(){
        return nom;
    }

    @Override
    public String toString() {
        return  "Sommet : " + nom;
    }

    
}
