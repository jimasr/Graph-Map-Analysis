/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.layout;

import java.util.Objects;

/**
 * Une classe qui permet de créer des objets point qui permet de stocker la position d'un objet
 * @author Hazim
 */
public class Point {
	private String node;
	private Vector coord;
	private Vector disp;

	/**
	 * Constructeur d'objet Point
	 * @param node 
	 */
	public Point(String node) {
		this.node = node;
		this.coord = new Vector(0, 0);
		this.disp = new Vector(0, 0);
	}

	/**
	 * Getter node
	 * @return 
	 */
	public String getNode() {
		return node;
	}

	/**
	 * Getter coordinate
	 * @return 
	 */
	public Vector getCoord() {
		return coord;
	}

	/**
	 * Getter displacement
	 * @return 
	 */
	public Vector getDisp() {
		return disp;
	}

	/**
	 * Setter coordinate
	 * @param coord Un objet vecteur contenant la position x et y
	 */
	public void setCoord(Vector coord) {
		this.coord = coord;
	}
	
	/**
	 * Setter coordinate 
	 * @param x La position x
	 * @param y La position y
	 */
	public void setCoord(float x, float y){
		coord.setX(x);
		coord.setY(y);
	}

	/**
	 * Setter pour le displacement
	 * @param disp Un objet vecteur contenant la position x et y
	 */
	public void setDisp(Vector disp) {
		this.disp = disp;
	}
	
	/**
	 * Setter pour le displacement
	 * @param x
	 * @param y 
	 */
	public void setDisp(float x, float y){
		disp.setX(x);
		disp.setY(y);
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + Objects.hashCode(this.node);
		hash = 17 * hash + Objects.hashCode(this.coord);
		hash = 17 * hash + Objects.hashCode(this.disp);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Point other = (Point) obj;
		if (!Objects.equals(this.node, other.node)) {
			return false;
		}
		if (!Objects.equals(this.coord, other.coord)) {
			return false;
		}
		return Objects.equals(this.disp, other.disp);
	}
	
	
	
	
}
