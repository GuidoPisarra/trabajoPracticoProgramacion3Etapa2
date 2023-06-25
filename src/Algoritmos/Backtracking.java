package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking {
	private GrafoNoDirigido<Integer> grafo;
	private HashMap<Integer,String> visitados = new HashMap<Integer,String>();
	private HashMap<Integer,Integer> solucion = new HashMap<Integer,Integer>();

    private int iteraciones;
    private int kmMejorSolucion= Integer.MAX_VALUE;

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
       this.grafo = grafo;
       this.iteraciones = 0;
    }
    
    public void backtracking_distancia() {
    	Iterator<Integer> vertices = this.grafo.obtenerVertices();
    	
    	while(vertices.hasNext()) {
    		int vertice = vertices.next();
    		this.visitados.put(vertice, "BLANCO");
    	}
    	
    	HashMap<Integer,Integer> solucionParcial = new HashMap<Integer,Integer>();
    	vertices = this.grafo.obtenerVertices();
    	
    	while(vertices.hasNext()) {
    		int distanciaInicial = 0;
    		int vertice = vertices.next();
    		backtracking(solucionParcial,vertice , distanciaInicial);
    	}
    	
		this.mostrarSolucion(this.solucion);  	
    	
 	}
    
    
    private void backtracking(HashMap<Integer,Integer> solucionActual, int verticeActual, int distanciaSolucionActual) {
		this.iteraciones++;
    	if(esSolucion(solucionActual)) {
    		if(calcularDistancia(solucionActual)<= calcularDistancia(this.solucion) || this.solucion.isEmpty()) {
    			this.solucion.clear();
    			this.solucion = (HashMap<Integer, Integer>) solucionActual.clone();
    		}
    	}else {
    		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeActual);
    		while(adyacentes.hasNext()) {
    			int adyacente = adyacentes.next();
    			
    			if((!solucionActual.containsKey(adyacente))&&(!solucionActual.containsValue(adyacente))) {
    				if(this.visitados.get(adyacente).equals("BLANCO")) {
    					this.visitados.replace(adyacente, "AMARILLO");
    					solucionActual.put(verticeActual, adyacente);
    					Arco<Integer> arco = this.grafo.obtenerArco(verticeActual, adyacente);
    					if(calcularDistancia(solucionActual)<= calcularDistancia(this.solucion) || this.solucion.isEmpty()) { // PODA
    						backtracking(solucionActual,adyacente,distanciaSolucionActual+arco.getEtiqueta());
    					}
    					solucionActual.remove(verticeActual,adyacente);
    					this.visitados.replace(adyacente, "BLANCO");
    				}
    			}
    		}    		
    	}
    	
	}

    
    private int calcularDistancia(HashMap<Integer,Integer>  solucionPosible) {
    	int distancia= 0;    	
    	for (Map.Entry<Integer, Integer> entry : solucionPosible.entrySet()) {
		    Integer clave = entry.getKey();
		    Integer valor = entry.getValue();
		    Arco<Integer> arco = this.grafo.obtenerArco(clave, valor);
		    distancia = distancia + arco.getEtiqueta();		    
		}   	    	
    	return distancia;
    }
    

	private boolean esSolucion(HashMap<Integer,Integer>  solucionParcial) {	
		// Controlo que tenga la catnidad de arcos que deberia contener para que esten todos conectados
		// se que no vana a estar repetidos por algoritmo backtracking	
		return solucionParcial.size()==this.grafo.cantidadVertices()-1 ;
	}
    

	private void mostrarSolucion(HashMap<Integer, Integer> solucion) {
	    int distancia = 0;
	    int tamaño = solucion.size();
	    // contador solo sirve para controlar la "," al mostrar solucion
	    int contador = 0;
	    
	    for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
	        Integer clave = entry.getKey();
	        Integer valor = entry.getValue();
	        Arco<Integer> arco = this.grafo.obtenerArco(clave, valor);
	        distancia += arco.getEtiqueta();
	        System.out.print("E" + arco.getVerticeOrigen() + "-" + "E" + arco.getVerticeDestino());
	        
	        contador++;
	        if (contador < tamaño) {
	            System.out.print(", ");
	        }
	    }
	    
	    System.out.println();
	    System.out.println(distancia + " Kms");
	    System.out.println(iteraciones + " Iteraciones");
	}

   
}