package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Interfaces.Grafo;

public class ServicioBFS {

	private Grafo<?> grafo;
	private HashMap<Integer, Boolean> verticesVisitados = new HashMap<Integer,Boolean>() ; 
	private LinkedList<Integer> fila = new LinkedList<Integer>();
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
		
	public List<Integer> bfsForest(){
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		
		while(vertices.hasNext()) {
			int vertice =  vertices.next();
			this.verticesVisitados.put(vertice, false);			
		}
		ArrayList<Integer> listado = new ArrayList<Integer>();

		this.fila.clear();
		vertices = this.grafo.obtenerVertices();
		while(vertices.hasNext()) {
			int vertice = vertices.next();
			if(verticesVisitados.get(vertice).equals(false)) {
				listado.addAll(bfsForest(vertice));
			}
		}		
		return listado;
	}
	
	private List<Integer> bfsForest(int verticeId){ 
		ArrayList<Integer> listado = new ArrayList<Integer>();
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeId);
		this.verticesVisitados.replace(verticeId, true);
		fila.add(verticeId);
		listado.add(verticeId);
		
		while(!fila.isEmpty()) {
			fila.remove(0);
			while (adyacentes.hasNext()) {
				int adyacente = adyacentes.next();				
					fila.add(adyacente);
			}
		}
		return listado;
	}


}
