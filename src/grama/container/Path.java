/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.container;

import java.util.List;

/**
 * Une classe qui permet la création de chemin avec la distance et les nombres de villes, restaurant et loisir dans ce chemin 
 * @author Hazim et Bastien
 */
public class Path {
	private List<String> path;
	private int distance;
	private int ville;
	private int restaurant;
	private int loisir;

	/**
	 * Le constructeur de Path
	 * @param path
	 * @param distance
	 * @param ville
	 * @param restaurant
	 * @param loisir 
	 */
	public Path(List<String> path, int distance, int ville, int restaurant, int loisir) {
		this.path = path;
		this.distance = distance;
		this.ville = ville;
		this.restaurant = restaurant;
		this.loisir = loisir;
	}

	/**
	 * Getter de path
	 * @return Une liste de String contenant les sommets
	 */
	public List<String> getPath() {
		return path;
	}

	/**
	 * Getter distance
	 * @return 
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Getter pour le nombre de ville
	 * @return Le nombre de ville
	 */
	public int getVille() {
		return ville;
	}

	/**
	 * Getter pour le nombre de restaurant
	 * @return Le nombre de restaurant
	 */
	public int getRestaurant() {
		return restaurant;
	}

	/**
	 * Getter pour le nombre de loisir
	 * @return Le nombre de loisir 
	 */
	public int getLoisir() {
		return loisir;
	}

	/**
	 * Setter pour le path
	 * @param path Une liste de String contenant les sommets
	 */
	public void setPath(List<String> path) {
		this.path = path;
	}

	/**
	 * Setter de distance
	 * @param distance 
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Setter de ville
	 * @param ville Un nombre de ville
	 */
	public void setVille(int ville) {
		this.ville = ville;
	}

	/**
	 * Setter de restaurant
	 * @param restaurant Un nombre de restaurant
	 */
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Setter de loisir
	 * @param loisir Un nombre de loisir
	 */
	public void setLoisir(int loisir) {
		this.loisir = loisir;
	}
	
	/**
	 * Une méthode pour ajouter les noeuds dans le path
	 * @param node Un noeud à ajouter
	 */
	public void addNode(String node){
		path.add(node);
	}
	
	/**
	 * Une méthode pour supprimer un noeud dans le path
	 * @param node Un noeud à supprimer
	 */
	public void removeNode(String node){
		path.remove(node);
	}
	
	/**
	 * Une méthode pour ajouter la distance
	 * @param d La distance
	 */
	public void addDistance(int d){
		distance += d;
	}
	
	/**
	 * Une méthode pour retourner les derniers noeuds
	 * @return Un noeud situé dans le dernier position dans la liste
	 */
	public String getLastNode(){
		return path.get(path.size()-1);
	}
	
	/**
	 * Une méthode pour determiner si un noeud contient dans le path
	 * @param node Un noeud
	 * @return Vrai s'il contient le noeud, faux sinon
	 */
	public boolean contains(String node){
		return path.contains(node);
	}
	
	@Override
	public String toString(){
		return path + " distance : " + distance;
	}
}
