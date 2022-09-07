/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.test;

import grama.container.Graphe;
import java.io.IOException;
import grama.model.Compare;
import grama.model.Voisinage;
import grama.utilitaire.GestionFichier;

/**
 *
 * @author wockehs
 */
public class TestCompare {

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

		Compare compare = new Compare(grama, "Cimes Aventures", "Assiette Champenoise");

		System.out.println(compare.hasMostOf(0, "V"));
		System.out.println(compare.hasMostOf(0, "L"));
		System.out.println(compare.hasMostOf(0, "R"));

		Voisinage voisin = new Voisinage(grama, "Cimes Aventures");
		Voisinage voisin2 = new Voisinage(grama, "Assiette Champenoise");

		System.out.println(voisin.getNeighbour(0));
		System.out.println(voisin2.getNeighbour(0));

	}
}
