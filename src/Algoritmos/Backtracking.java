package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Grafo.Arco;
import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;
import Timer.Timer;

public class Backtracking {

	GrafoNoDirigido<Integer> grafo;
	HashMap<Integer,String> visitados = new HashMap<Integer,String>();
	Timer timer = new Timer();
	int origen;
	int destino;
	
	public Backtracking(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
	}

	public void Backtracking_distancia() {
		this.timer.start();
		if(this.grafo.contieneVertice(this.origen) && this.grafo.contieneVertice(this.destino)) {
			LinkedList<Integer> camino = new LinkedList<Integer>();
			Iterator<Integer> vertices = this.grafo.obtenerVertices();
			while(vertices.hasNext()) {
				int vertice = vertices.next();
				this.visitados.put(vertice,"BLANCO");
			}		
			camino.add(this.origen);
			mostrarResultado(backtracking(camino,origen), this.timer.stop());
		}else {
			mostrarResultado(new LinkedList<Integer>(), this.timer.stop());
		}
	}
	
	private List<Integer> backtracking(List<Integer> caminoActual, int actual) {
        LinkedList<Integer> caminoMenor = new LinkedList<Integer>();
        if (actual == this.destino) {
            caminoMenor.addAll(caminoActual);
        } else {
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);
            this.visitados.replace(actual, "AMARILLO");
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (this.visitados.get(adyacente).equals("BLANCO")) {
                    caminoActual.add(adyacente);
                    List<Integer> caminoRecursivo = backtracking(caminoActual, adyacente);
                    if (!caminoRecursivo.isEmpty() &&
                            (caminoMenor.isEmpty() || this.distancia(caminoRecursivo) < this.distancia(caminoMenor))) {
                        caminoMenor.clear();
                        caminoMenor.addAll(caminoRecursivo);
                    }
                    caminoActual.remove(caminoActual.size()-1);
                }
            }
            this.visitados.replace(actual, "BLANCO");
        }
        return caminoMenor;
    }
	
	private float distancia(List<Integer> camino) {	
		List<Integer> listaAuxiliar = camino;
		float distancia = 0;
		for(int i = 0; i<camino.size()-1; i++) {
			int estacion = listaAuxiliar.get(i);
			int estacion2 = listaAuxiliar.get(i+1);
			if(this.grafo.existeArco(estacion, estacion2)) {
				Arco<Integer> arco = this.grafo.obtenerArco(estacion, estacion2);
				distancia = distancia + arco.getEtiqueta();
			}			
		}
		return distancia;
	}
	
	private void mostrarResultado(List<Integer> result, double tiempo) {
		if(!result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
			    Integer res = result.get(i);
			    System.out.print("E" + res);
			    if (i != result.size()-1) {
			        System.out.print(" - ");
			    }
			}
			System.out.println();
			System.out.println(this.distancia(result)+" Kms");
			System.out.println("Tiempo: "+tiempo);
		}else {
			System.out.println();
			System.out.println("La/s estacion/es que ingresó no existen");
			System.out.println("Tiempo: "+tiempo);
		}
	}
	

	
}
