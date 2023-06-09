package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Greedy {
	private int distanciaMaxima =  9999999;
	
	public HashMap<Integer, Integer> greedy(GrafoNoDirigido grafo, int origen, int destino){
		LinkedList<Integer> resultado = new LinkedList<Integer>();
		Iterator<Integer> vertices = grafo.obtenerVertices();
		HashMap<Integer, Integer> distancia = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> padre = new HashMap<Integer, Integer>(); 

		while(vertices.hasNext()) {
			int vertice = vertices.next();
			distancia.put(vertice, distanciaMaxima );
			padre.put(vertice, null);
		}
		distancia.replace(origen, 0);

		vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int vertice = vertices.next();
			int u = obtenerAdyacenteMenorDistancia(grafo, vertice);
			resultado.add(vertice);
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
			while(adyacentes.hasNext()) {
				int adyacente = adyacentes.next();
				

				if(distancia.get(u) + distanciaEntre(grafo,u,vertice)<distancia.get(vertice)) {
					System.out.println("Distancia adya "+distancia.get(adyacente) + " " + adyacente);
					System.out.println("Distancia vertice y u "+distanciaEntre(grafo,u,vertice));
					distancia.replace(adyacente,distancia.get(u) + distanciaEntre(grafo,u,vertice) );
					padre.replace(vertice, u);					
				}
			}
		}
		if(!resultado.isEmpty()) {
			return padre;
		}
		
		return null;
		
	}
	
	private int obtenerAdyacenteMenorDistancia(GrafoNoDirigido<Integer> grafo, int origen) {
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(origen);
		int verticeMenor=0;
		float distancia = 9999999;
		while(adyacentes.hasNext()) {
			int adyacente = adyacentes.next();
			Arco<Integer> arco = grafo.obtenerArco(origen, adyacente);
			if(arco!=null && arco.getEtiqueta()<distancia) {
				distancia = arco.getEtiqueta();
				verticeMenor = adyacente;
			}
		}	
		return verticeMenor;
	}
	
	private Integer distanciaEntre(GrafoNoDirigido<Integer> grafo,int origen, int destino) {
		Arco<Integer> arco = grafo.obtenerArco(origen, destino);
		return arco.getEtiqueta();
	}

}
