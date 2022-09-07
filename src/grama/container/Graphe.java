/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.container;

import grama.model.Arete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import grama.model.Sommet;

/**
 *  Une classe qui permet la création et la gestion des sommets et arrètes de notre graphe
 * @author Hazim et Bastien
 */
public class Graphe {
    
     
         //Une Map  qui permet de stocker ses arrètes de chaque sommet sous forme d'une liste d'Objet Arete
         //Les sommets sont identifiés par leur nom en String
	private final Map<String, ArrayList<Arete>> listeAdjacence; 
        
        
        // La liste qui permet de stocker les Sommets sous forme d'Objet Sommet
	private ArrayList<Sommet> listeSommet; 
        

         //Un compteur qui indique le nombre de sommets de type ville qui sont présents sur le graphe
	private int nbVille; 
        
        
        // Un compteur qui indique le nombre de sommets de type loisirs graph qui sont présents sur le graphe
	private int nbLoisir; 
        
         
         // Un compteur qui indique le nombre de sommets de type restaurant qui sont présents sur le graphe
	private int nbRestaurant; 
        
         
         //Un compteur qui indique le nombre d'arêtes de type autoroute qui sont présents sur le graphe
	private int nbAutoroute; 
        
       
        //Un compteur qui indique le nombre d'arêtes de type nationale qui sont présents sur le graphe
	private int nbNationale; 
        
        
        //Un compteur qui indique le nombre d'arêtes de type departementale qui sont présents sur le graphe
	private int nbDepartementales;

        
        
        
        /**
         * Le constructeur qui initialise la HashMap listeAdjacence 
         */
	public Graphe() {
		listeAdjacence = new HashMap<>();
	}

	public Map<String, ArrayList<Arete>> getListeAdjacence() {
		return listeAdjacence;
	}

        
        /**
         *  Une fonction qui retourne nbVille
         * @return Le nombre de villes du graphe
         */
	public int getNbVille() {
		return nbVille;
	}

        
        /**
         *  Une fonction qui retourne nbLoisir
         * @return Le nombre de loisirs du graphe
         */
	public int getNbLoisir() {
		return nbLoisir;
	}

        /**
         *  Une fonction qui retourne nbRestaurant
         * @return Le nombre de restaurants du graphe
         */
	public int getNbRestaurant() {
		return nbRestaurant;
	}

        
        /**
         *  Une fonction qui retourne nbAutoroute
         * @return Le nombre d'autoroutes du graphe
         */
	public int getNbAutoroute() {
		return nbAutoroute;
	}

        /**
         *  Une fonction qui retourne nbNationale
         * @return Le nombre de nationales du graphe
         */
	public int getNbNationale() {
		return nbNationale;
	}

        /**
         *  Une fonction qui retourne nbDepartementales
         * @return Le nombre de départementales du graphe
         */
	public int getNbDepartementales() {
		return nbDepartementales;
	} 
        
        
        /**
         * Une fonction qui permet de récupérer la liste d'arêtes reliées au sommet indiqué dans les paramètres
         * @param nomSommet le nom du sommet dont vous voulez récupérer les arêtes
         * @return une liste d'arêtes sous forme d'une ArrayList contenant des Objets Arete
         */
	public ArrayList<Arete> getArete(String nomSommet) {
		return listeAdjacence.get(nomSommet);
	}

        /**
         * Une fonction qui permet de récupérer la liste de sommet d'un graphe
         * @return une ArrayList composée d'Objets de type Sommet
         */
	public ArrayList<Sommet> getListeSommet() {
		return listeSommet;
	}
	
        /**
         * Une fonction qui permet de récupérer un sommet grâce à son nom
         * @param sommet Le nom du sommet recherché
         * @return le sommet sous forme d'un objet Sommet
         */
	public Sommet getSommet(String sommet){
		for(Sommet s : listeSommet){
			if(s.getNom().equals(sommet)){
				return s;
			}
		}
		return null;
	} 
        
        
        /**
        * Une fonction qui permet de récupérer la liste des nom de tous les sommets du graphe
        * @return Une liste des nom des sommets du graphe sous forme de String
        */
	public ArrayList getListNameSommet() {
		ArrayList<String> array = new ArrayList<>();

		Set<String> keys = listeAdjacence.keySet();
		for (String node : keys) {
			array.add(node);
		}

		return array;
	}

        /**
         * Une fonction qui permet de modifier la variable nbVille
         * @param nbVille le nombre de sommet de type ville dans le graphe
         */
	public void setNbVille(int nbVille) {
		this.nbVille = nbVille;
	}

        /**
         * Une fonction qui permet de modifier la variable nbLoisir
         * @param nbLoisir  le nombre de sommet de type loisir dans le graphe
         */
	public void setNbLoisir(int nbLoisir) {
		this.nbLoisir = nbLoisir;
	}

        /**
         * Une fonction qui permet de modifier la variable nbRestaurant
         * @param nbRestaurant  le nombre de sommet de type restaurant dans le graphe
         */
	public void setNbRestaurant(int nbRestaurant) {
		this.nbRestaurant = nbRestaurant;
	}

        /**
         * Une fonction qui permet de modifier la variable nbAutoroute
         * @param nbAutoroute  le nombre d'arêtes de type autoroute dans le graphe
         */
	public void setNbAutoroute(int nbAutoroute) {
		this.nbAutoroute = nbAutoroute;
	}

        /**
         * Une fonction qui permet de modifier la variable nbNationale
         * @param nbNationale le nombre d'arêtes de type nationale dans le graphe
         */
	public void setNbNationale(int nbNationale) {
		this.nbNationale = nbNationale;
	}

        /**
         * Une fonction qui permet de modifier la variable nbDepartementales
         * @param nbDepartementales le nombre d'arêtes de type Departementale dans le graphe
         */
	public void setNbDepartementales(int nbDepartementales) {
		this.nbDepartementales = nbDepartementales;
	}

          /**
           * Une fonction qui permet de remplacer la liste de sommets du graphe
           * @param listeSommet une liste de sommets de type Sommet
           */
	public void setListeSommet(ArrayList<Sommet> listeSommet) {
		this.listeSommet = listeSommet;
	}

        /** 
         * Une fonction qui permet d'ajouter une arête de type Arete à une liste d'arête de type Arete
         * @param a L'arête que l'on veut ajouter
         * @param aretes  La liste d'arêtes auquel on veut ajouter a
         */
	public void addArete(Arete a, ArrayList<Arete> aretes) {
		aretes.add(a);
	}

        /**
         * Une fonction qui permet d'ajouter une paire sommet et liste d'arêtes à la liste d'arêtes du graphe
         * @param a le nom du sommet auquel est liée la liste d'arêtes.
         * @param aretes la liste d'arêtes atachée au sommet a.
         */
	public void addPairKeyValue(String a, ArrayList<Arete> aretes) {
		//Ajout cle et valeur
		listeAdjacence.put(a, aretes);
	}

}
