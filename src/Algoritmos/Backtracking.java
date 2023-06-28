package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;
import Union.UnionFind;

public class Backtracking {
	private GrafoNoDirigido<Integer> grafo;
	private LinkedList<Arco<Integer>>  mejorSolucion = new LinkedList<Arco<Integer>>();
	private LinkedList<Arco<Integer>> arcosTotales = new LinkedList<Arco<Integer>>();

    private int iteraciones;

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
       this.grafo = grafo;
       this.iteraciones = 0;
       
    }
    
    
    /*
     * Complejidad computacional O(n^2) donde n es la cantidad de vértices
     * Esto se debe a que en este método se utilizan dos bucles while, el primero
     * para setear todos los vértices en blanco en el hshMap de visitados y el segundo
     * para recorrerlos en su totalidad para obtener la solución. 
     */
    public void backtracking_distancia() {
    	Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();    	
    	
    	while(arcos.hasNext()) {    		
    		Arco<Integer> arco = arcos.next();
    		this.arcosTotales.add(arco);
    	}
    	
    	LinkedList<Arco<Integer>> solucion = new LinkedList<Arco<Integer>>();
    	
    	backtracking(solucion);
    	
		this.mostrarSolucion(this.mejorSolucion);  	
    	
 	}
    
    /*
     * Complejidad computacional O(n^2) donde n es la cantidad de vértices.
	 * A pesar que dentro del método se hacen verificaciones de claves y valores, éstas
     * tienden a una complejidad constante, por lo tanto la complejidad es de O(2^n) donde n es la cantidad
     * de arcos totales (se realiza una exploración exahustiva de todas las combinasciones de los arcos).
     */
	private void  backtracking(LinkedList<Arco<Integer>> solucionActual) {
		iteraciones++;
		if (arcosTotales.isEmpty()) {
			if ((esSolucion(solucionActual))&& ((calcularDistancia(solucionActual) < calcularDistancia(mejorSolucion)) || mejorSolucion.isEmpty())) {
				this.mejorSolucion.clear();
				this.mejorSolucion.addAll(solucionActual);
			}
			
		} else{
			if((calcularDistancia(solucionActual) < calcularDistancia(mejorSolucion) || mejorSolucion.isEmpty())) {			
				Arco<Integer> arco = arcosTotales.pop();
				
				backtracking(solucionActual);
				solucionActual.add(arco);				
				
				
				backtracking(solucionActual);				
				solucionActual.remove(arco);
				arcosTotales.add(0,arco);
			}
				
		}

	}
	

    /*
     * Complejidad computacional O(n) donde n es la cantidad de vértices
     * Este método itera sobre el mapa de solucionPosible, por lo tanto la complejidad 
     * es de O(n) donde n es la cantidad de vértices que posee el mapa solucionPosible.
     */
    private int calcularDistancia(LinkedList<Arco<Integer>>  solucionPosible) {
    	 int distancia = 0;
    	    for (Arco<Integer> arco : solucionPosible) {
    	        distancia += arco.getEtiqueta();
    	    }
    	    return distancia;
    	}
    
    /*
     * Complejidad computacional O(n) donde n es la cantidad de vértices
     * Este método itera sobre el mapa de solucionParcial, por lo tanto la complejidad 
     * es de O(n) donde n es la cantidad de vértices que posee el mapa solucionParcial.
     */
    private boolean esSolucion(LinkedList<Arco<Integer>> solucionParcial) {
        if (!solucionParcial.isEmpty()) {
            UnionFind union = new UnionFind(this.grafo.cantidadVertices());
            for (Arco<Integer> arco : solucionParcial) {
                int verticeOrigen = arco.getVerticeOrigen() - 1;
                int verticeDestino = arco.getVerticeDestino() - 1;
                union.Union(verticeOrigen, verticeDestino);
            }

            return union.size() == 1;
        }

        return false;
    }

    
    /*
     * Complejidad computacional O(n) donde n es la cantidad de vértices que contiene solución
     * Este método itera sobre el mapa de solución, por lo tanto la complejidad 
     * es de O(n) donde n es la cantidad de vértices que posee el mapa solución.
     */
	private void mostrarSolucion(LinkedList<Arco<Integer>> solucion) {
	    int distancia = 0;
	    int tamaño = solucion.size();
	    // contador solo sirve para controlar la "," al mostrar solucion
	    int contador = 0;
	    
	    for (Arco<Integer> arco : solucion) {
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