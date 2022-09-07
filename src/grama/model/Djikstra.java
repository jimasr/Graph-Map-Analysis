         /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.model;

import grama.container.Graphe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Une classe permet l'utilisation d'un algorithme de Djiktra pour calculer le plus petit chemin entre deux sommets
 *
 * @author Hazim et Bastien
 */
public class Djikstra {

	// Le sommet source duquel part l'algorithme de djikstra 
	private final String source;

	// Une Map qui stocke les distances de chaque sommets par rapport au sommet source
	private final Map<String, Integer> distance;

	// Une Map qui stocke le plus petit chemin pour aller du sommet source à un sommet d donné 
	private final Map<String, String> path;

	// Une Map qui permet de créer la map distance 
	private final Map<String, ArrayList<Arete>> listeAdjacence;

	// le graphe du sommet Source
	private final Graphe graphe;

	/**
	 * Le constructeur de la calsse qui permet d'initialiser le sommet source et son graphe
	 *
	 * @param graphe le graphe du sommet source
	 * @param source le sommet source duquel part l'algorithme
	 */
	public Djikstra(Graphe graphe, String source) {
		this.graphe = graphe;
		this.listeAdjacence = graphe.getListeAdjacence();
		this.source = source;
		this.distance = new HashMap<>();
		this.path = new HashMap<>();

	}

	/**
	 * Une fonction qui permet de voir si le chemin que l'on possède (et qui est présent dans la Map distance) déjà est meilleur que celui que l'on voit de trouver et qui passe par a (on doit rajouter la distance entre a et b).
	 *
	 * @param a le sommet par lequel le nouveau chemin passe
	 * @param b le sommet avec un chemin déjà présent
	 * @param distanceAToB la distance entre le sommet a et b
	 */
	private void relacher(String a, String b, int distanceAToB) {

		if (distance.get(b) > distance.get(a) + distanceAToB) {
			distance.put(b, distance.get(a) + distanceAToB);
			path.put(b, a);

		}

	}

        
        /** 
         * Une fonction qui permet d'éxécuter l'algorithme de Djikstra et ainsi de trouver le chemin le plus court entre tous les sommets du graphe
         * et le sommet source donné dans le constructeur
         */
	private void procedureDjikstra() {

		List<String> notVisited = graphe.getListNameSommet();

		Set<Map.Entry<String, ArrayList<Arete>>> parcours = listeAdjacence.entrySet();

		for (Map.Entry<String, ArrayList<Arete>> entree : parcours) {
			String cle = entree.getKey();
			distance.put(cle, Integer.MAX_VALUE);
		}

		distance.put(source, 0);

		while (!notVisited.isEmpty()) {
			String sommetMin = minDistance(distance, notVisited);

			notVisited.remove(sommetMin);

			//update all sommet that is not yet treated
			ArrayList<Arete> listSuccessor = listeAdjacence.get(sommetMin);

			for (Arete a : listSuccessor) {
				String sommetRelie = a.getSommetRelie().getNom();
				if (notVisited.contains(sommetRelie)) {
					int distToSommet = a.getDistance();
					relacher(sommetMin, sommetRelie, distToSommet);
				}

			}
		}

		//afficherDistance();
	}

	/**
	 *Permet de trouver le sommet non visité précédament par l'algorithme de djikstra avec la plus courte distance entre lui-même 
         * et le sommet source
	 * @param distance la liste de tous les sommets avec leur distance par rapport au sommet source
	 * @param notVisited la liste des sommets non traitées par l'algorithme de djikstra
	 * @return le nom du sommet non traité le plus proche du sommet source
	 */
	public String minDistance(Map<String, Integer> distance, List<String> notVisited) {

		int min = Integer.MAX_VALUE;
		String sommet = null;
		for (String s : notVisited) {
			if (distance.get(s) <= min) {
				min = distance.get(s);
				sommet = s;
			}
		}

		return sommet;
	} 
        
        /**
         *  Permet de convertir de manière récursive la HashMap path qui contient le plus court chemin de la destination au sommet source 
         * en liste de nom de sommet qui peut être traitée par les fonctions d'affichage du graph
         * @param currentSommet Le sommet destination
         * @param list une nouvelle liste
         * @return Une liste contenant un chemin du sommet source au sommet destination sous forme d'une array list
         */

	public List<String> fillPath(String currentSommet, List<String> list) {
		if (currentSommet.equals(source)) {
			list.add(source);
			return list;
		}
		list.add(currentSommet);
		return fillPath(path.get(currentSommet), list);
		//System.out.print(currentSommet + "->");

	}

	/**
	 * Une fonction qui affiche les distances de chaque sommets par raport au sommet source
	 */
	public void afficherAll() {
		Set<Map.Entry<String, Integer>> parcours = distance.entrySet();

		for (Map.Entry<String, Integer> entree : parcours) {

			String cle = entree.getKey();
			if (!(cle.equalsIgnoreCase(source))) {
				System.out.println(cle + ":" + entree.getValue());
				System.out.print(source + "->");
				//afficherPath("cle");
			}
			System.out.println("");

		}
	}

        /**
         * Une fonction qui exécute la procedureDjikstra et qui ensuite renvoie la distance du sommet source au sommet destination
         * @param destination le nom du sommet destination
         * @return la distance entre le sommet destination et le sommet source
         */
	public int getDistance(String destination) {
		procedureDjikstra();
		return distance.get(destination);
	}

        /**
         *  Une fonction qui exécute la procedureDjikstra et qui ensuite renvoie le chemin du sommet source au sommet destination sous forme d'une liste de nom
         * @param destination le nom du sommet destination 
         * @return le chemin entre le sommet source et le sommet destination
         */
	public List<String> getPath(String destination) {
		procedureDjikstra();
		return fillPath(destination, new ArrayList<>());

	}



}
