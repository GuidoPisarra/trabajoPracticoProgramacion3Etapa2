package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Interfaces.Grafo;

public class ServicioDFS {

		private int time;
		private HashMap<Integer, String> colores = new HashMap<Integer, String>();
		private Grafo<?> grafo;
		
		
		public ServicioDFS (Grafo<?> grafo ) {
			this.grafo = grafo;		
		}
		
		
		public List<Integer> dfsForest(){
			Iterator<Integer> vertices = this.grafo.obtenerVertices();
			while(vertices.hasNext()) {
				int vertice =  vertices.next();
				this.colores.put(vertice, "BLANCO");
			}
			
			ArrayList<Integer> listado = new ArrayList<Integer>();
			vertices = this.grafo.obtenerVertices();
			while(vertices.hasNext()) {
				int vertice = vertices.next();
				if(colores.get(vertice).equals("BLANCO")) {
					listado.addAll(this.dfsForest(vertice));
				}
			}
			return listado;
		}
		
		private List<Integer> dfsForest(int verticeId){ 
			Iterator<Integer> adyacentes =  this.grafo.obtenerAdyacentes(verticeId);
			ArrayList<Integer> listado = new ArrayList<Integer>();
			this.colores.replace(verticeId,"AMARILLO");
			listado.add(verticeId);

			while (adyacentes.hasNext()) { 
				int adyacente = adyacentes.next();
				if(this.colores.get(adyacente).equals("BLANCO")) {
				    listado.addAll(dfsForest(adyacente));
				}			
			}
			
			this.colores.replace(verticeId, "NEGRO");
			return listado;
		}
		
		
	}
