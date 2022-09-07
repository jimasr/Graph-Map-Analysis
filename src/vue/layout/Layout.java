/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.layout;

import grama.container.Graphe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import grama.model.Arete;

/**
 * Une classe qui permet d'appliquer l'algorithme de Fruchterman et Reingold pour la visualisation de graphe
 * @author Hazim
 */
public class Layout {

	private final float EPSILON = 0.003f;
	private static HashMap<String, Point> mapPoint;
	private final Graphe graph;
	private final ArrayList<String> node;
	private int nbVertices;
	private int width;
	private int height;

	/**
	 * Constructeur de classe Layout
	 *
	 * @param graph un objet Graphe
	 * @param node une liste de noeuds
	 * @param width le largeur d'ecran
	 * @param height le hauteur d'ecran
	 */
	public Layout(Graphe graph, List<String> node, int width, int height) {
		this.node = (ArrayList<String>) node;
		this.mapPoint = new HashMap<>();
		this.graph = graph;
		this.nbVertices = node.size();
		this.width = width;
		this.height = height;
	}

	/**
	 * Setter pour le largeur
	 *
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Setter pour le hauteur
	 *
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Getter pour le nombre de sommets
	 *
	 * @return
	 */
	public int getNbVertices() {
		return nbVertices;
	}

	/**
	 * Getter pour le largeur
	 *
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter pour le hauteur
	 *
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Getter pour le map avec les coordinates
	 *
	 * @return
	 */
	public HashMap<String, Point> getMapPoint() {
		return mapPoint;
	}

	/**
	 * Getter pour le graphe
	 *
	 * @return
	 */
	public Graphe getGraph() {
		return graph;
	}

	/**
	 * Getter pour la liste de noeud passé en parametre
	 *
	 * @return
	 */
	public ArrayList<String> getNode() {
		return node;
	}

	/**
	 * Une méthode pour génerer un nombre aléatoire de type float
	 * @param min le minimum nombre
	 * @param max le maximum nombre
	 * @return
	 */
	private float generateRandomFloat(int min, int max) {
		Random r = new Random();
		return min + r.nextFloat() * (max - min);
	}

	/**
	 * Une méthode pour initialiser les coordinates 
	 */
	public void initialiseCoordinate() {

		for (String node : node) {

			float x = generateRandomFloat(20, width - 20);
			float y = generateRandomFloat(20, height - 20);

			Point tempPoint = new Point(node);

			tempPoint.setCoord(x, y);
			mapPoint.put(node, tempPoint);
		}

	}

	/**
	 * Une procedure pour utiliser l'algorithme de Fruchterman et Reingold pour la visualisation de graphe
	 * @throws ArithmeticException 
	 */
	public void setFruchtermanLayout() throws ArithmeticException {
		int area = width * height;
		final double k = Math.sqrt(area / nbVertices);
		final int MAXITERATION = 10000;
		double temperature = 1000;

		initialiseCoordinate();

		for (int i = 0; i < MAXITERATION; i++) {
			//apply attractive force
			for (String m : node) {
				Point v = mapPoint.get(m);
				v.setDisp(0, 0);
				for (String n : node) {
					Point u = mapPoint.get(n);
					if (!(v.equals(u))) {
						Vector delta = v.getCoord().subtract(u.getCoord());
						//calculate repulsive force
						double force = forceRep(u, v, k);

						v.setDisp(v.getDisp().add(delta).unit().scale((float) force));

					}
				}

			}

			//apply repulsive force
			for (String m : node) {
				Point v = mapPoint.get(m);
				ArrayList<Arete> adjacentList = graph.getArete(m);
				for (Arete a : adjacentList) {
					if (node.contains(a.getSommetRelie().getNom())) {

						Point u = mapPoint.get(a.getSommetRelie().getNom());
						Vector delta = v.getCoord().subtract(u.getCoord());
						double force = forceAtt(u, v, k);
						v.setDisp(v.getDisp().subtract(delta.unit().scale((float) force)));
						u.setDisp(u.getDisp().add(delta.unit().scale((float) force)));
					}

				}
			}

			//adjusting nodes
			for (String m : node) {
				Point v = mapPoint.get(m);

				Vector newCoord = (v.getCoord().add(v.getDisp().unit().scale((float) Math.min(v.getDisp().length(), temperature))));
				v.setCoord(newCoord.getX(), newCoord.getY());

				//limit node from crossing border
				float x = (float) Math.min(width - 50, Math.max(20, v.getCoord().getX()));
				float y = (float) Math.min(height - 50, Math.max(20, v.getCoord().getY()));

				v.setCoord(x, y);
				mapPoint.put(m, v);
			}
			temperature *= 0.9;
		}
	}

	/**
	 * Une methode pour calculer la force attraction entre deux sommet
	 * @param pi Point coordinate de premier sommet
	 * @param pj Point coordinate de deuxieme sommet
	 * @param k Un constant k 
	 * @return La force attraction entre deux sommets
	 */
	private double forceAtt(Point pi, Point pj, double k) {
		double distance = pi.getCoord().distance(pj.getCoord());
		return distance * distance / k;
	}

	/**
	 *  Une methode pour calculer la force repulsion  entre deux sommet
	 * @param pi Point coordinate de premier sommet
	 * @param pj Point coordinate de deuxieme sommet
	 * @param k Un constant k 
	 * @return La force repulsion entre deux sommets
	 */
	private double forceRep(Point pi, Point pj, double k) {
		double distance = pi.getCoord().distance(pj.getCoord());
		return k * k / (distance + 0.00001);
	}

}
