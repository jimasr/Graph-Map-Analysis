/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.test;

import grama.utilitaire.GestionFichier;
import grama.container.Graphe;
import grama.model.Djikstra;
import grama.model.Itinerary;
import grama.model.Voisinage;
import java.io.IOException;
import java.util.ArrayList;
import grama.model.Arete;
import grama.model.Compare;

/**
 *
 * @author bastien
 */
public class TestGraphe {

	/**
	 * @param args the command line arguments
	 *
	 */
	public static void main(String[] args) {

		Graphe grama = new Graphe();
		GestionFichier fichier = new GestionFichier();
		try {
			grama = fichier.ouvertureFichier("graph.csv");
			System.out.println("Initialisation graphe reussit!");
		} catch (IOException ex) {
			System.err.println("Echec lors de l'ouverture de fichier!");
			System.exit(0);
		}

		System.out.println("\nVotre graphe contient\n");
		System.out.println("\t> " + grama.getNbVille() + " Ville");
		System.out.println("\t> " + grama.getNbRestaurant() + " Restaurant");
		System.out.println("\t> " + grama.getNbLoisir() + " Loisir");
		System.out.println("\t> " + grama.getNbAutoroute() + " Autoroute");
		System.out.println("\t> " + grama.getNbNationale() + " Route Nationale");
		System.out.println("\t> " + grama.getNbDepartementales() + " Route Departementale");

		System.out.println("");

		
		
		//test voisinage
		//Voisinage voisinage = new Voisinage(grama);
		//voisinage.searchNeighbour(2, "Marseille");
		
		//test pathfinder
		//Itinerary iti = new Itinerary(grama);
		//iti.pathFinder("Paris", "Marseille", 4, 2, 1);
		
		
		//test compare
		/*
		Compare comp = new Compare(grama, "Macon", "Toulon");

		System.out.println(comp.hasMostOf(2, "R"));
		System.out.println(comp.hasMostOf(2, "C"));
                System.out.println(comp.hasMostOf(2, "V"));
                System.out.println(comp.hasMostOf(1, "R"));
*/
	}

}
