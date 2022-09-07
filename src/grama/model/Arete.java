/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.model;

import java.util.Objects;

/**
 * Une classe qui permet la création et la gestion des arêtes qui lient les sommets
 * @author hazim et bastien
 */
public class Arete { 
        
        //La longueur de l'arête
	private int distance; 
        
        
         
        //Le type de route que représente l'arête (Autoroute, Départementale, Nationale)
	private String typeRoute; 
        
        
        //Le deuxième sommet relié par l'arête (le premier se trouve dans l'HashMap de Graphe
	private Sommet sommetRelie;

        /**
         * Le constructeur de Arete
         * @param distance La longueur de l'arête
         * @param type le type de l'arête
         * @param sommetRelie le deuxième sommet relié par l'arête
         */
	public Arete(int distance, String type, Sommet sommetRelie) {
            this.distance = distance;
            this.typeRoute = type;
            this.sommetRelie = sommetRelie;
	}

        /**
         * Une fonction qui récupère la distance de l'arête
         * @return la distance de l'arête en int
         */
	public int getDistance() {
		return distance;
	}

        /**
         * Une fonction qui récupère le type de l'arête
         * @return le type de l'arête en String
         */
	public String getType() {
		return typeRoute;
	}

        /**
         * Une fonction qui récupère le deuxième sommet relié par l'arête.
         * @return le deuxième sommet rélié par l'arête sous forme d'un Objet Sommet
         */
	public Sommet getSommetRelie() {
		return sommetRelie;
	}

        /**
         * Une fonction qui permet de modifier la longueur de l'arête
         * @param distance la longueur voulue en int
         */
	public void setDistance(int distance) {
		this.distance = distance;
	} 
        
        /**
         * Une fonciton qui permet de modifier le type de l'arête
         * @param type le type de l'arête sous forme d'un String
         */
	public void setType(String type) {
		this.typeRoute = type;
	}

        /**
         * Une fonction qui permet de modifier le deuxième sommet relié par l'arête 
         * @param sommetRelie le nouveau sommet
         */
	public void setSommetRelie(Sommet sommetRelie) {
		this.sommetRelie = sommetRelie;
	}

        
        /**
         * Une fonction qui permet de voir si deux arêtes sont égales
         * @param obj une arête sous forme d'un Objet Arete
         * @return Un booléen, true si les deux objets sont égaux false sinon
         */
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Arete)) return false;
		
		Arete p = (Arete) obj;
		
		return p.sommetRelie == this.sommetRelie;
	}
	
	
   
	@Override
	public int hashCode() {
            return Objects.hash(distance, typeRoute, sommetRelie);
        }
        
        /**
         * Une fonciton qui renvoie un String décrivant l'arête voulue
         * @return un String
         */
	@Override
	public String toString() {
            return  "---> D : " + distance + ", Type : " + typeRoute + ", "+ sommetRelie ;
	}
	

}
