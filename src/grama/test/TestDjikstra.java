/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.test;

import grama.container.Graphe;
import java.io.IOException;
import java.util.ArrayList;
import grama.model.Djikstra;
import grama.utilitaire.GestionFichier;

/**
 *
 * @author wockehs
 */
public class TestDjikstra {

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
		Djikstra djikstra = new Djikstra(grama, "Michel Blanc");
		
		System.out.println(djikstra.getDistance("Grenoble"));
		
		ArrayList<String> path = (ArrayList<String>) djikstra.getPath("Melun");

		for (String s : path) {
			System.out.print(s + "<-");
		}
		System.out.println("");
	}
}
