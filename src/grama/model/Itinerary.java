/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.model;

import grama.container.Graphe;
import grama.container.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Une classe qui permet de trouver tous les chemins possibles entre un sommet source et un sommet destination
 *
 * @author Hazim et Bastien
 */
public class Itinerary {

	//Un graphe
	private final Graphe graphe;

	//La liste d'adjacence du graphe (donc la liste qui contient tous les voisins de tous les sommets du graphe)
	private final Map< Integer, List<String>> possiblePath;

	/**
	 * le constructeur de la classe Itinerary
	 *
	 * @param graphe un graphe
	 */
	public Itinerary(Graphe graphe) {
		this.graphe = graphe;
		this.possiblePath = new HashMap<>();

	}

	/**
	 * La fonction qui permet de trouver un chemin entre le sommet current et le sommet destination avec un nombre nbVille de villes, nbLoisir de loisirs et nbRestaurant de Restaurant qui doivent être présents (ex si nbRestaurant=2 il doit y avoir 2 restaurants dans le chemin) Si celui-ci n'est pas possible la fonction va essayer de s'en raprocher Si des valeur iréalistes sont entrées dans ville, restaurant ou loisir elle renvera qu'il est impossible de faire ce chemin
	 *
	 * @param source le nom du sommet de départ du chemin
	 * @param destination le nom du sommet d'arrivé du chemin
	 * @param ville Le nombre de villes voulues dans le chemin
	 * @param loisir le nombre de restaurants voulus dans le chemin
	 * @param restaurant le nombre de loisirs voulus dans le chemin
	 * @return une liste contenant un chemin sous forme de nom de sommets
	 */
	public List<String> getShortestItinerary(String source, String destination, int ville, int restaurant, int loisir) {

		List<Path> path = new ArrayList<>();
		List<String> minPath = new ArrayList<>();
		path = possiblePath(source, destination, ville, loisir, restaurant);

		int min = Integer.MAX_VALUE;
		for (Path p : path) {
			if (p.getDistance() < min) {
				minPath = p.getPath();
				min = p.getDistance();
			}
		}
		System.out.println("Choice : " + minPath);
		return minPath;
	}

	/**
	 * Une fonction qui permet d'éxécuter deepFirstSearch uniquement avec la source, la destination et les nombres de types de sommets voulus
	 *
	 * @param source le nom du sommet de départ
	 * @param destination le nom du sommet d'arrivé
	 * @param ville Le nombre de villes voulues dans le chemin
	 * @param restaurant le nombre de restaurants voulus dans le chemin
	 * @param loisir le nombre de loisirs voulus dans le chemin
	 */
	private List<Path> possiblePath(String source, String destination, int nbVille, int nbLoisir, int nbRestaurant) {
		Queue<Path> queue = new LinkedList<>();
		List<Path> listPath = new ArrayList<>();

		Path path = new Path(new ArrayList<String>(), 0, nbVille, nbLoisir, nbRestaurant);
		path.addNode(source);
		queue.offer(path);

		while (!(queue.isEmpty())) {

			//take first path from queue
			path = queue.poll();
			String lastNode = path.getLastNode();
			if (lastNode.equals(destination) && path.getVille() <= 0 && path.getRestaurant() <= 0 && path.getLoisir() <= 0) {
				listPath.add(path);
			}

			//traverse all connected node
			List<Arete> arete = graphe.getArete(lastNode);
			for (Arete a : arete) {
				Sommet voisin = a.getSommetRelie();
				String nomVoisin = a.getSommetRelie().getNom();
				int distance = a.getDistance();

				//System.out.print(", " + voisin);
				if (!(path.contains(nomVoisin))) {
					int updateVille = path.getVille();
					int updateRestaurant = path.getRestaurant();
					int updateLoisir = path.getLoisir();

					if (voisin instanceof Ville) {
						updateVille -= 1;
					} else if (voisin instanceof Restaurant) {
						updateRestaurant -= 1;
					} else if (voisin instanceof Loisir) {
						updateLoisir -= 1;
					}

					Path newPath = new Path(new ArrayList<String>(path.getPath()), path.getDistance() + distance, updateVille, updateRestaurant, updateLoisir);
					newPath.addNode(nomVoisin);
					queue.offer(newPath);

				}
			}
		}
		return listPath;
	}

}
