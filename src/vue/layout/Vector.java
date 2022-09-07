/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.layout;

/**
 * Une classe vecteur qui permet de manipuler le valeur x et y dans le Plan Cartésien
 * @author Hazim
 */
public class Vector {

	private float x;
	private float y;

	/**
	 * Constructeur Vecteur
	 * @param x position x
	 * @param y position y
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter pour x
	 * @return 
	 */
	public float getX() {
		return x;
	}

	/**
	 * Setter pour x
	 * @param x La position x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Getter pour y
	 * @return 
	 */
	public float getY() {
		return y;
	}

	/**
	 * Setter pour y
	 * @param y La position y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Une méthode pour soustraire 
	 * @param c L'objet vecteur
	 * @return Un objet vecteur resultat
	 */
	public Vector subtract(Vector c) {
		return new Vector(x - c.x, y - c.y);
	}

	/**
	 * Une méthode pour ajouter deux vecteur
	 * @param c L'objet vecteur
	 * @return 
	 */
	public Vector add(Vector c) {
		return new Vector(x + c.x, y + c.y);
	}

	/**
	 * Une méthode pour calculer l'unité de vecteur
	 * @return Un objet vecteur 
	 */
	public Vector unit() {
		if (length() == 0) {
			return new Vector(0.000001f, 0.0000001f);
		} else {
			return new Vector(x / (float) Math.sqrt(x * x + y * y), y / (float) Math.sqrt(y * y + x * x));
		}
	}

	/**
	 * Une méthode pour calculer la longeur d'un vecteur
	 * @return Un float de longeur
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * Une méthode pour calculer la distance entre deux vecteurs
	 * @param c Un vecteur
	 * @return Un float de distance entre deux points
	 */
	public float distance(Vector c) {
		return (float) Math.sqrt((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y));
	}

	/**
	 * Une méthode pour mettre à l'echelle le vecteur et un constant
	 * @param k Le constant
	 * @return Un objet vecteyr avec les positions escaladés
	 */
	public Vector scale(float k) {
		return new Vector(k * x, k * y);
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}

}
