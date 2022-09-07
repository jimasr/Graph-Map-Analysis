/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.model;

import grama.container.Graphe;
import java.util.ArrayList;
import java.util.List;

/**
 * Une classe permet de comparer deux sommets au niveau du nombre de voisins qui sont soit des villes, des loisirs ou des restaurants.
 *
 * @author Hazim et Bastien
 */
public class Compare {

	//Le graphe dont les deux sommes à comparer sont originaires
	private final Graphe graph;

	//Le premier sommet à comparer
	private String x;
	//Le deuxième sommet à comparer
	private String y;
	//La liste des voisins du sommet x avec leur distance par rapport au sommet (tout le graphe sauf le sommet x est contenu dans cette liste)
	List<String> voisinX;

	//La liste des voisins du sommet y avec leur distance par rapport au sommet (tout le graphe sauf le sommet y est contenu dans cette liste)
	List<String> voisinY;

	/**
	 * Le constructeur de compare
	 *
	 * @param graph le graphe des sommets x et y
	 * @param x Le premier sommet à comparer
	 * @param y Le deuxième sommet à comparer
	 */
	public Compare(Graphe graph, String x, String y) {
		this.graph = graph;
		this.x = x;
		this.y = y;
		this.voisinX = new ArrayList<>();
		this.voisinY = new ArrayList<>();
	}

	/**
	 * Une fonction qui permet de récupérer le sommet x
	 *
	 * @return un sommet
	 */
	public String getX() {
		return x;
	}

	/**
	 * Une fonction qui permet de récupérer le sommet y
	 *
	 * @return un sommet
	 */
	public String getY() {
		return y;
	}

	/**
	 * Une fonction qui permet de récupérer la liste des voisins du sommet x
	 *
	 * @return une HashMap
	 */
	public List<String> getVoisinX() {
		return voisinX;
	}

	/**
	 * Une fonction qui permet de récupérer la liste des voisins du sommet y
	 *
	 * @return une HashMap
	 */
	public List<String> getVoisinY() {
		return voisinY;
	}

	/**
	 * Une fonction qui permet de modifier le premier sommet à comparer
	 *
	 * @param x un sommet
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * Une fonction qui permet de modifier le deuxième sommet à comparer
	 *
	 * @param y un sommet
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * Une fonction qui permet de modifier la liste des voisins du sommet x
	 *
	 * @param voisinX la nouvelle liste de voisins
	 */
	public void setVoisinX(List<String> voisinX) {
		this.voisinX = voisinX;
	}

	/**
	 * Une fonction qui permet de modifier la liste des voisins du sommet y
	 *
	 * @param voisinY la nouvelle liste de voisins
	 */
	public void setVoisinY(List<String> voisinY) {
		this.voisinY = voisinY;
	}

	/**
	 * Une fonction qui permet de savoir lequel des deux sommets a le plus de sommets d'un type voulus
	 *
	 * @param distance la distance jusqu'à laquelle la comparaison opère, ex : si on entre 2 on va comparer les voisins à 2-distance
	 * @param type le type de sommet à comparer
	 * @return 1 si le sommet x a le plus de sommets d'un type, -1 si c'est le sommet y quii a le plus de sommets d'un type et 0 si il y a une égalité
	 */
	public int hasMostOf(int distance, String type) {
		
		voisinX = initVoisinage(x, distance);
		voisinY = initVoisinage(y, distance);

		voisinX.remove(x);
		voisinY.remove(y);
		
		int countRestoX = countNode(voisinX, type);
		int countRestoY = countNode(voisinY, type);

		if (countRestoX > countRestoY) {
			return 1;
		} else if (countRestoX < countRestoY) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Compte le nombre de sommet d'un type à une distance donné d'un autre sommet implicite
	 *
	 * @param voisin Une hashMap qui contient l'intégralité des sommets d'un graph avec leur distance par rapport à un sommet central (qui n'est pas inclus dans la map)
	 * @param distance La distance sur laquelle vous voulez effectuer le comptage.
	 * @param type le type de sommet que vous voulez compter (V,R ou L)
	 * @return Le nombre de sommets d'un type
	 */
	private int countNode(List<String> voisin, String type) {

		int count = 0;

		for (String s : voisin) {

			Sommet node = graph.getSommet(s);

			switch (type) {
				case "V":
					if (node instanceof Ville) {
						count++;
					}
					break;
				case "L":
					if (node instanceof Loisir) {
						count++;
					}

					break;

				case "R":
					if (node instanceof Restaurant) {
						count++;
					}
					break;
			}
		}

		return count;

	}

	/**
	 * Permet de créer les listes de voisinages
	 *
	 * @param s le nom du sommet pour lequel on veut créer la liste de voisinage
	 * @return Une HashMap
	 */
	private List<String> initVoisinage(String s, int distance) {
		Voisinage listVoisin = new Voisinage(graph, s);
		
		return listVoisin.getNeighbour(distance);
	}

}
