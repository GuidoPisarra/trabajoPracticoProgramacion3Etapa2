package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;
import Timer.Timer;

public class Backtracking {

	GrafoNoDirigido<Integer> grafo;
	HashMap<Integer,String> visitados = new HashMap<Integer,String>();
	Double timer;
	
	public Backtracking(GrafoNoDirigido<Integer> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> Backtracking_distancia( int origen, int destino) {
		Timer timer = new Timer();
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int vertice = vertices.next();
			this.visitados.put(vertice,"BLANCO");
		}		
		this.timer = timer.stop();
		return backtracking(origen,destino);
	}
	
	private List<Integer> backtracking(int origen, int destino){
		LinkedList<Integer> caminoMenor = new LinkedList<Integer>();
		//System.out.println(origen);
		if(origen==destino) {
			caminoMenor.add(origen);		
		}else {
			this.visitados.replace(origen,"AMARILLO");
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);
			while(adyacentes.hasNext()) {
				int adyacente = adyacentes.next();
				if(this.visitados.get(adyacente).equals("BLANCO")) {
					LinkedList<Integer> camino = new LinkedList<Integer>();
//					if(this.distancia(camino, origen)<this.distancia(caminoMenor, origen)) {
						camino.addAll(backtracking(adyacente,destino));
//					}
					//System.out.println(camino.toString());
//					System.out.println((this.distancia(camino, origen)<this.distancia(caminoMenor, origen)) || (caminoMenor.isEmpty()));
					if((this.distancia(camino, origen)<this.distancia(caminoMenor, origen)) || (caminoMenor.isEmpty())) {
						caminoMenor.clear();						
						camino.add(origen);
						caminoMenor.addAll(camino);
						//System.out.println(caminoMenor.toString());

					}
				}
			}
			this.visitados.replace(origen, "BLANCO");
		}	
		return caminoMenor;
	}
	
	private float distancia(List<Integer> camino,int origen) {
		Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos(origen);
		float distancia = 0;
		while(arcos.hasNext()) {
			Arco<Integer> arco = arcos.next();
			//System.out.println(arco.getEtiqueta());
			distancia = distancia + arco.getEtiqueta();
		}
		//System.out.println(distancia);
		return distancia;
	}
	
	
	
}
