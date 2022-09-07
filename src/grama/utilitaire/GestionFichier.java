/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.utilitaire;

import grama.container.Graphe;
import grama.model.Arete;
import grama.model.Loisir;
import grama.model.Restaurant;
import grama.model.Sommet;
import grama.model.Ville;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Cette fonction permet la récupération d'un graphe à partir d'un fichier csv formaté d'une manière spécifique : 
 * TYPE DE SOMMET (V,R ou L),NOM DU SOMMET: TYPE DE L'ARETE RELIANT AU VOISIN(A, D ou N),LONGUEUR DE L'ARETE::TYPE DE SOMMET DU VOISIN,NOM DU VOISIN; 
 * on peut rajouter autant de fois que l'on veut la partie concernant le voisin pour avoir plusieurs voisins
 * @author Hazim et Bastien 
 */
public class GestionFichier {

    //Le nombre de villes du graphe
    private int countVille = 0;
    
    //Le nombre de loisirs du graphe
    private int countLoisir = 0;
    
    //Le nombre de restaurants du graphe
    private int countRestaurant = 0;
    
    //Le nombre d'Autoroutes du graphe
    private int countAutoroute = 0;
    
    //Le nombre de routes nationales du graphe
    private int countNationale = 0; 
    
    //Le nombre de routes departementales du graphe
    private int countDepartmentale = 0;

    /**
     * Cette fonction permet d'ouvrir un fichier csv formaté spécifiquement et d'en extraire un graphe 
     * Elle va aussi compter le nombre de sommet de chaque type dans le graphe et mettre ces résultats dans le variables au dessus
     * @param nomFich le nom du fichier à ouvrir
     * @return un graphe
     * @throws IOException  une erreur qui peut subvenir lors de la lecture de fichier
     * @throws ArrayIndexOutOfBoundsException une erreur qui peut subvenir lors de la manipulation de tableau
     */
    public Graphe ouvertureFichier(String nomFich) throws IOException, ArrayIndexOutOfBoundsException{
	
	Graphe graphe = new Graphe();
	File file = new File(nomFich);

	ArrayList<Sommet> listeSommet = new ArrayList<>();
        Reader reader = new FileReader(file);
        try ( BufferedReader br = new BufferedReader(reader)) {
            String line;
            Sommet tempSommet = null;


            while ((line = br.readLine()) != null) {
                //Split sommet de ses voisins
                String[] regexSommet = line.split("\\:", 2);

                String[] voisins = regexSommet[1].split(";");
                ArrayList<Arete> tempArete = new ArrayList<>();

                //Creation et Recuperation des aretes
                for (String voisin : voisins) {

                    //Creation des sommets temporaire
                    switch (recuperationTypeSommetRelie(voisin)) {

                        case "V":
                            tempSommet = new Ville(recuperationNomVoisin(voisin));

                            break;
                        case "R":
                            tempSommet = new Restaurant(recuperationNomVoisin(voisin));

                            break;
                        case "L":
                            tempSommet = new Loisir(recuperationNomVoisin(voisin));

                            break;

                    }

                    tempArete.add(new Arete(recuperationDistance(voisin), recuperationTypeRoute(voisin), tempSommet));
                }

                //Split nom et type
                String[] sommetPart = regexSommet[0].split(",");
                String typeRef = sommetPart[0];
		String sommet = sommetPart[1];

                //Count nb ville, restaurant, loisir
                countTypeSommet(typeRef);
                //Ajout list adjacent et sommet
                graphe.addPairKeyValue(sommetPart[1], tempArete);
		//ajout list sommet dans un liste temporaire
		
		switch(typeRef){
			case "V" : 
				listeSommet.add(new Ville(sommet));
				break;
			case "R" : 
				listeSommet.add(new Restaurant(sommet));
				break;
			case "L" : 
				listeSommet.add(new Loisir(sommet));
				break;
		}
				
            }
        }

        //Set ville, loisir, restaurant count
        graphe.setNbLoisir(countLoisir);
        graphe.setNbRestaurant(countRestaurant);
        graphe.setNbVille(countVille);
        graphe.setNbAutoroute(countAutoroute);
        graphe.setNbDepartementales(countDepartmentale);
        graphe.setNbNationale(countNationale);
	graphe.setListeSommet(listeSommet);
		
	return graphe;

    }

    /**
     * Permet de récupérer le nom d'un voisin à partir d'une chaine de caractère formaté comme ceci : 
     * TYPE DE L'ARETE RELIANT AU VOISIN(A, D ou N),LONGUEUR DE L'ARETE::TYPE DE SOMMET DU VOISIN,NOM DU VOISIN;  
     * on récupère donc la dernière information le nom du voisin
     * @param voisin une chaine de caractère formaté comme dit précédament
     * @return le nom du voisin d'un sommet
     */
    private String recuperationNomVoisin(String voisin) {
        String[] nom = voisin.split(",");

        return nom[2];
    }

    /**
     *  Permet de récupérer la distance d'une arête à partir d'une chaine de caractère formaté comme ceci :  
     * TYPE DE L'ARETE RELIANT AU VOISIN(A, D ou N),LONGUEUR DE L'ARETE::TYPE DE SOMMET DU VOISIN,NOM DU VOISIN;   
     * On doit donc dabord séparer l'ârete du voisin en lui même puis on récupère la longueur
     * @param voisin une chaine de caractère formaté comme dit précédament
     * @return la distance d'un arête
     */
    private int recuperationDistance(String voisin) {
        String[] firstLayer = voisin.split("::");
        String[] secondLayer = firstLayer[0].split(",");
        int distance = Integer.parseInt(secondLayer[1]);

        return distance;
    }

    /**
     *  Permet de récupérer le type d'une arête à partir d'une chaine de caractère formaté comme ceci :  
     * TYPE DE L'ARETE RELIANT AU VOISIN(A, D ou N),LONGUEUR DE L'ARETE::TYPE DE SOMMET DU VOISIN,NOM DU VOISIN;   
     * On doit donc dabord séparer l'ârete du voisin en lui même puis on récupère le type 
     * Cette fonction incrémente aussi l'un des compteur de type de route grâce à la fonction couTypeRoute
     * @param voisin une chaine de caractère formaté comme dit précédament
     * @return la distance d'un arête
     */
    private String recuperationTypeRoute(String voisin) {
        String[] nom = voisin.split(",");
        String typeRoute = nom[0];
        countTypeRoute(typeRoute);

        return typeRoute;
    } 
    
    

    /**
     *  Permet de récupérer le type d'une arête à partir d'une chaine de caractère formaté comme ceci :  
     * TYPE DE L'ARETE RELIANT AU VOISIN(A, D ou N),LONGUEUR DE L'ARETE::TYPE DE SOMMET DU VOISIN,NOM DU VOISIN;   
     * On doit donc dabord séparer l'ârete du voisin en lui même puis on récupère le type du voisin
     * @param voisin une chaine de caractère formaté comme dit précédament
     * @return le type du voisin
     */
    private static String recuperationTypeSommetRelie(String voisin) {
        String[] firstLayer = voisin.split("::");
        String[] secondLayer = firstLayer[1].split(",");

        return secondLayer[0];
    }

    /**
     * Cette fonction permet d'incrémenter un compteur de nombre de route d'un type en fonction du type entré en paramètre 
     * @param typeRoute un type de route
     */
    private void countTypeRoute(String typeRoute) {
        switch (typeRoute) {
            case "A":
                countAutoroute++;
                break;
            case "N":
                countNationale++;
                break;
            case "D":
                countDepartmentale++;
                break;
            default:
                throw new AssertionError();
        }
    }

     /**
     * Cette fonction permet d'incrémenter un compteur de nombre de sommet d'un type en fonction du type entré en paramètre 
     * @param typeRoute un type de sommet
     */
    private void countTypeSommet(String typeRef) {
        switch (typeRef) {
            case "V":
                countVille++;
                break;
            case "R":
                countRestaurant++;
                break;
            case "L":
                countLoisir++;
                break;
            default:
                throw new AssertionError();
        }
    }

}
