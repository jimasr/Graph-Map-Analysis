/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.model;

import grama.container.Graphe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Une classe qui permet de trouver les voisins d'un sommet à n-distance
 * @author Hazim et Bastien
 */
public class Voisinage {
        
        // un graphe
        private final Graphe graphe; 
        
        //La liste d'adjacence du graphe (donc la liste qui contient tous les voisins de tous les sommets du graphe)
	private final Map<String, ArrayList<Arete>> listeAdjacence; 
        
        //Le sommet duquel on veut connaitre les voisins
	private final String source;
        
        //Le nom de tous les voisins avec leur distance (nombre d'arêtes) par rapport au sommet source
	private HashMap<String, Integer> mapNeighbour;
	
        

	/**
	 * Le constructeur de Voisinage
	 * @param graphe un graphe 
         * @param source Le sommet duquel on veut connaitre les voisins
	 */
	public Voisinage(Graphe graphe, String source) {
		this.listeAdjacence = graphe.getListeAdjacence();
		this.graphe = graphe;
		this.mapNeighbour = new HashMap<>();
		this.source = source;
	}

        /**
         * Cette méthode permet de récupérer la HashMap mapNeighbour
         * @return une HashMap contenant tous les voisins du sommet source avec leur distance (en nombre d'arêtes) par raport à celui-ci
         */
	public HashMap<String, Integer> getMapNeighbour() {
		return mapNeighbour;
	}

        
	/**
	 * Cette fonction permet de remplir la HashMap mapNeighbour avec tous les voisins du sommet source avec leur distance (en nombre d'arêtes) par raport à celui-ci
	 */
	private void initNeighbour() {

		String sommet = source;

		//Structure queue that let us apply FIFO 
		LinkedList<String> queue = new LinkedList<>();

		List<String> notVisited = graphe.getListNameSommet();

		notVisited.remove(sommet);
		queue.add(sommet);
		mapNeighbour.put(sommet, 0);

		while (!queue.isEmpty()) {

			sommet = queue.poll(); //Take the first sommet

			Iterator<Arete> it = listeAdjacence.get(sommet).iterator(); //Iterates adjacency list  
			while (it.hasNext()) {
				String s = it.next().getSommetRelie().getNom(); //get name of voisin
				if (notVisited.contains(s)) { //Test if already treated
					notVisited.remove(s);
					queue.add(s); //Add to queue to be iterated    
					mapNeighbour.put(s, mapNeighbour.get(sommet) + 1); //all successor of sommet will be the next sommet + 1 for lvl
				}
			}

		}
	}

        /**
         * Cette fonction permet de récupérer tous les voisins du sommet source sous une certaine distance de celui-ci 
         * (ex si distance=2 on va récupérer tous les voisins ayant maximum 2 arêtes de distance avec le sommet source)
         * @param distance la distance maximum entre les voisins et le sommet source
         * @return une liste des voisins à maximum distance arête du sommet source
         */
	public List<String> getNeighbour(int distance) {
		initNeighbour();
		Set<Map.Entry<String, Integer>> parcours = mapNeighbour.entrySet();
		List<String> array = new ArrayList<>();
		//add all key into sommet non treated
		for (Map.Entry<String, Integer> entree : parcours) {
			String cle = entree.getKey();

			if (entree.getValue() <= distance) {
				array.add(cle);
			}
		}
		return array;
	}

        /**
         *  Une fonction qui permet d'afficher tous les voisins du sommet source jusqu'à une certaines distance de celui-ci
         * @param distance la distance maximum
         */
	public void printMap(int distance) {
		initNeighbour();
		Set<Map.Entry<String, Integer>> parcours = mapNeighbour.entrySet();

		//add all key into sommet non treated
		for (Map.Entry<String, Integer> entree : parcours) {
			String cle = entree.getKey();

			if (entree.getValue() == distance) {
				System.out.println(cle + " : " + entree.getValue());
			}
		}
	}

}
