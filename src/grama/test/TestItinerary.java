/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.test;

import grama.container.Graphe;
import java.io.IOException;
import java.util.ArrayList;
import grama.model.Itinerary;
import grama.utilitaire.GestionFichier;

/**
 *
 * @author wockehs
 */
public class TestItinerary {

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

		Itinerary iti = new Itinerary(grama);
		ArrayList<String> shortestPath = (ArrayList<String>) iti.getShortestItinerary( "Paris", "Marseille", 2, 2, 2);
		
	
	}
}
