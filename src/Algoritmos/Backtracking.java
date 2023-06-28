package Algoritmos;

import java.util.Iterator;
import java.util.LinkedList;

import Clases.Solucion;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking {
	
	private GrafoNoDirigido<Integer> grafo;
	private Solucion  mejorSolucion = new Solucion();
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
    	
    	Solucion  solucion = new Solucion();

    	backtracking(solucion);
    	
		this.mejorSolucion.mostrarSolucion(); 	
	    System.out.println(iteraciones + " Iteraciones");

 	}
    
    /*
     * Complejidad computacional O(n^2) donde n es la cantidad de vértices.
	 * A pesar que dentro del método se hacen verificaciones de claves y valores, éstas
     * tienden a una complejidad constante, por lo tanto la complejidad es de O(2^n) donde n es la cantidad
     * de arcos totales (se realiza una exploración exahustiva de todas las combinasciones de los arcos).
     */
	private void  backtracking(Solucion solucionActual) {
		iteraciones++;
		if (arcosTotales.isEmpty()) {
			if ((solucionActual.esSolucion(this.grafo.cantidadVertices()))&& (solucionActual.obtenerDistancia() < mejorSolucion.obtenerDistancia() || mejorSolucion.isEmpty())) {
				this.mejorSolucion.clear();
				this.mejorSolucion.addAll(solucionActual);
			}			
		} else{
			if((solucionActual.obtenerDistancia() < mejorSolucion.obtenerDistancia() || mejorSolucion.isEmpty())) {			
				Arco<Integer> arco = arcosTotales.pop();
				
				backtracking(solucionActual);
				solucionActual.agregarTunel(arco);				
							
				backtracking(solucionActual);				
				solucionActual.quitarTunel(arco);
				arcosTotales.add(0,arco);
			}
				
		}

	}
	  
}