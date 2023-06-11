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
	Timer timer = new Timer();
	
	public Backtracking(GrafoNoDirigido<Integer> grafo) {
		this.grafo = grafo;
	}

	public void Backtracking_distancia( int origen, int destino) {
		this.timer.start();
		if(this.grafo.contieneVertice(origen) && this.grafo.contieneVertice(destino)) {
			Iterator<Integer> vertices = this.grafo.obtenerVertices();
			while(vertices.hasNext()) {
				int vertice = vertices.next();
				this.visitados.put(vertice,"BLANCO");
			}		
			//System.out.println(this.timer);
			
			
			mostrarResultado(backtracking(origen,destino), this.timer.stop());
			
		}
		mostrarResultado(new LinkedList<Integer>(), this.timer.stop());
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

					if((this.distancia(camino, origen)<this.distancia(caminoMenor, origen)) || (caminoMenor.isEmpty())) {
						caminoMenor.clear();						
						camino.add(origen);
						caminoMenor.addAll(camino);
						System.out.println(camino.toString());
						System.out.println(caminoMenor.toString());

						System.out.println(this.distancia(camino, origen));
						System.out.println(this.distancia(caminoMenor, origen));

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
			System.out.println(arco.getEtiqueta());
			distancia = distancia + arco.getEtiqueta();
		}
		return distancia;
	}
	
	private void mostrarResultado(List<Integer> result, double tiempo) {
		if(!result.isEmpty()) {
			for(Integer res :result) {
				System.out.print("E"+res+" - ");
			}
			System.out.println();
			System.out.println(this.distancia(result, result.get(0))+" Kms");
			System.out.println("Tiempo: "+tiempo);
		}else {
			System.out.println();
			System.out.println("La/s estacion/es que ingresó no existen");
			System.out.println("Tiempo: "+tiempo);
		}
	}
	
	
	
}
