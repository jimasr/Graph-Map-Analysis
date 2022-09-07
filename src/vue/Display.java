/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import grama.container.Graphe;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JPanel;
import grama.model.Arete;
import grama.model.Loisir;
import grama.model.Restaurant;
import grama.model.Sommet;
import grama.model.Ville;
import vue.layout.Layout;
import vue.layout.Point;

/**
 * Une classe display pour l'affichage de graphe en 2D
 * @author Hazim
 */
public class Display extends JPanel {

	private final Graphe graphe;
	private HashMap<String, Point> mapCoordinate;
	private Layout layout;
	private boolean autoroute;
	private boolean departementale;
	private boolean nationale;

	public Display(Graphe graphe, Layout layout, boolean autoroute, boolean departementale, boolean nationale) {
		this.graphe = graphe;
		this.mapCoordinate = layout.getMapPoint();
		this.layout = layout;
		this.autoroute = autoroute;
		this.departementale = departementale;
		this.nationale = nationale;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setBackground(Color.WHITE);

		//draw edge
		ArrayList<String> listNode = layout.getNode();
		//ArrayList<String> treated = layout.getNode();
		for (String source : listNode) {
			//System.out.println(source);
			int x1 = (int) mapCoordinate.get(source).getCoord().getX() + 7;
			int y1 = (int) mapCoordinate.get(source).getCoord().getY() + 7;

			ArrayList<Arete> listArete = graphe.getArete(source);

			for (Arete arete : listArete) {

				String destination = arete.getSommetRelie().getNom();
				String weight = String.valueOf(arete.getDistance());
				String type = arete.getType();
				if (listNode.contains(destination)) {
					int x2 = (int) mapCoordinate.get(destination).getCoord().getX() + 7;
					int y2 = (int) mapCoordinate.get(destination).getCoord().getY() + 7;

					//non-directed graph only need drawing once
					switch (type) {
						case "A":
							g.setColor(new Color(140, 190, 41));
							break;
						case "D":
							g.setColor(new Color(128, 0, 128));
							break;
						case "N":
							g.setColor(new Color(25, 162, 222));
							break;
					}
					if ((type.equals("A") && autoroute) || (type.equals("D") && departementale) || (type.equals("N") && nationale)) {
						g.drawLine(x1, y1, x2, y2);

						g.setColor(Color.BLACK);
						g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
						g.drawString(weight, (x1 + x2 - 6) / 2, (y1 + y2 - 6) / 2);
					}
				}
				//treated.remove(source);

			}

		}
		//draw nodes
		Collection<Point> values = mapCoordinate.values();
		for (Point v : values) {

			Sommet node = graphe.getSommet(v.getNode());
			if (node instanceof Ville) {
				g.setColor(new Color(0, 171, 169));
			} else if (node instanceof Restaurant) {
				g.setColor(new Color(162, 0, 37));
			} else if (node instanceof Loisir) {
				g.setColor(new Color(109, 135, 100));
			}

			g.fillOval((int) v.getCoord().getX(), (int) v.getCoord().getY(), 15, 15);

			g.setColor(Color.BLACK);
			g.drawOval((int) v.getCoord().getX(), (int) v.getCoord().getY(), 15, 15);
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
			g.drawString(v.getNode(), (int) v.getCoord().getX() - 6, (int) v.getCoord().getY() - 6);
		}

	}
}
