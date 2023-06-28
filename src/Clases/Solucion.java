package Clases;

import java.util.Iterator;
import java.util.LinkedList;

import Grafo.Arco;

public class Solucion {
	private LinkedList<Arco<Integer>> conjuntoSolucion = new LinkedList<Arco<Integer>>(); 
	private int distancia;
	
	public Solucion() {
		this.distancia = 0 ;
	}
	
	public void agregarTunel(Arco<Integer> arco) {
		this.conjuntoSolucion.add(arco);
		this.distancia = distancia + arco.getEtiqueta();
	}
	
	public void quitarTunel(Arco<Integer> arco) {
		if(!this.conjuntoSolucion.isEmpty() && this.conjuntoSolucion.contains(arco)) {
			this.conjuntoSolucion.remove(arco);
			this.distancia = distancia- arco.getEtiqueta();
		}
	}
	
	public int obtenerDistancia() {
		return this.distancia;
	}
	
	public int size() {
		return this.conjuntoSolucion.size();
	}
	
	public Iterator<Arco<Integer>> obtenerTuneles() {
		return this.conjuntoSolucion.iterator();

	}
	
	public boolean isEmpty() {
		return this.conjuntoSolucion.isEmpty();
	}
	
	public void clear() {
		this.conjuntoSolucion.clear();
		this.distancia = 0;
	}
	
	public void addAll(Solucion solucion) {
	    // Obtener el iterador de los túneles de la solución a agregar
	    Iterator<Arco<Integer>> iterator = solucion.obtenerTuneles();
	    
	    // Agregar cada túnel de la solución a esta solución actual
	    while (iterator.hasNext()) {
	        Arco<Integer> arco = iterator.next();
	        this.agregarTunel(arco);
	    }
	}
	/*
     * Complejidad computacional O(n) donde n es la cantidad de vértices que contiene solución
     * Este método itera sobre el mapa de solución, por lo tanto la complejidad 
     * es de O(n) donde n es la cantidad de vértices que posee el mapa solución.
     */
	public void mostrarSolucion() {
	    
	    // contador solo sirve para controlar la "," al mostrar solucion
	    int contador = 0;
	
	    for (Arco<Integer> arco : this.conjuntoSolucion) {	        
	        System.out.print("E" + arco.getVerticeOrigen() + "-" + "E" + arco.getVerticeDestino());	        
	        contador++;
	        if (contador < this.size()) {
	            System.out.print(", ");
	        }
	    }
	    
	    System.out.println();
	    System.out.println(this.distancia + " Kms");
	}
	
	
	
}
