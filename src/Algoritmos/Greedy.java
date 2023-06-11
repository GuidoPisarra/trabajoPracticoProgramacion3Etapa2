package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;
import Timer.Timer;

public class Greedy {
	private int distanciaMaxima =  9999999;
	private double timer;
	
	public void greedy(GrafoNoDirigido<Integer> grafo, int origen, int destino){
		Timer timer = new Timer();
		timer.start();
		LinkedList<Integer> resultado = new LinkedList<Integer>();
		HashMap<Integer, Integer> distancia = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> padre = new HashMap<Integer, Integer>(); 
		Iterator<Integer> vertices = grafo.obtenerVertices();
		
		while(vertices.hasNext()) {
			int vertice = vertices.next();
			distancia.put(vertice, distanciaMaxima );
			padre.put(vertice, null);
		}
		distancia.replace(origen, 0);

		vertices = grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int vertice = vertices.next();

			resultado.add(vertice);
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
			while(adyacentes.hasNext()) {
				int adyacente = adyacentes.next();
				int u = obtenerAdyacenteMenorDistancia(grafo, vertice, resultado);

				if(distancia.get(vertice) + distanciaEntre(grafo,u,adyacente)<distancia.get(adyacente)) {
					distancia.replace(adyacente,distancia.get(vertice) + distanciaEntre(grafo,u,adyacente));
					padre.replace(vertice, u);
					System.out.println(padre.toString());
				}
			}
		}
		this.timer = timer.stop();
		if(!resultado.isEmpty()) {
			this.mostrarSolucion(padre, distancia);
		}else {
			this.mostrarSolucion(null,distancia);
		}
		
	}
	
	
//	for(int i = 0 ; i<resultado.size(); i++) {
//	int adyacente = resultado.get(i);
//	System.out.println("adyacente "+ adyacente);
//	if(distancia.get(u) + distanciaEntre(grafo,u,vertice)<distancia.get(vertice)) {
//		System.out.println("Distancia adya "+distancia.get(adyacente) + " " + adyacente);
//		System.out.println("Distancia vertice y u "+distanciaEntre(grafo,u,vertice));
//		distancia.replace(adyacente,distancia.get(u) + distanciaEntre(grafo,u,vertice) );
//		padre.replace(vertice, u);					
//	}
//	
//}
	
	private int obtenerAdyacenteMenorDistancia(GrafoNoDirigido<Integer> grafo, int origen, List<Integer> resultadoParcial) {
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(origen);
		int verticeMenor=0;
		float distancia = 9999999;
		while(adyacentes.hasNext()) {
			int adyacente = adyacentes.next();
			Arco<Integer> arco = grafo.obtenerArco(origen, adyacente);
			if(arco!=null && arco.getEtiqueta()<distancia && !resultadoParcial.contains(adyacente)) {
				distancia = arco.getEtiqueta();
				verticeMenor = adyacente;
			}
		}	
		return verticeMenor;
	}
	
	private Integer distanciaEntre(GrafoNoDirigido<Integer> grafo,int origen, int destino) {
		Arco<Integer> arco = grafo.obtenerArco(origen, destino);
		if(arco!=null) {
			return arco.getEtiqueta();
		}
		return distanciaMaxima;
	}
	
	private void mostrarSolucion(HashMap<Integer,Integer> padre, HashMap<Integer,Integer>distancia) {
		int distanciaTotal = 0;
		if(padre!=null) {
			for(Integer key:padre.keySet()) {
				Integer value = padre.get(key);
				System.out.print(value + " ");
			}
			for(Integer key: distancia.keySet() ) {
				Integer value = distancia.get(key);
				distanciaTotal = distanciaTotal + value;
			}
		}else {
			System.out.println("No se pudo determinar una solución");
		}
		System.out.println();
		System.out.println(distanciaTotal + " Kms");
		System.out.println("Tiempo: "+this.timer);
	}

}
