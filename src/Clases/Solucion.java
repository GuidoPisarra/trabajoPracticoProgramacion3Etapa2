package Clases;

import java.util.Iterator;
import java.util.LinkedList;

import Grafo.Arco;
import Union.UnionFind;

public class Solucion {
	private LinkedList<Arco<Integer>> conjuntoSolucion = new LinkedList<Arco<Integer>>(); 
	private int distancia;
	
	public Solucion() {
		this.distancia = 0 ;
	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método agrega un tunel al conjunto de solución, su complejidad es constante
     * 
     */
	public void agregarTunel(Arco<Integer> arco) {
		this.conjuntoSolucion.add(arco);
		this.distancia = distancia + arco.getEtiqueta();
	}
	
	/*
     * Complejidad computacional O(n) donde n es la cantidad de arcos que posee el conjunto de
     * solución 
     * Este método elimina un tunel al conjunto de solución, su complejidad es lineal en el 
     * peor de los casos.
     * 
     */
	public void quitarTunel(Arco<Integer> arco) {
		if(!this.conjuntoSolucion.isEmpty() && this.conjuntoSolucion.contains(arco)) {
			this.conjuntoSolucion.remove(arco);
			this.distancia = distancia- arco.getEtiqueta();
		}
	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método retorna la distancia del recorrido del conjunto de solución, su complejidad es constante
     * 
     */
	public int obtenerDistancia() {
		return this.distancia;
	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método retorna la cantidad de elementos (arcos) que contiene el conjunto de solución, 
     * su complejidad es constante.
     * 
     */
	public int size() {
		return this.conjuntoSolucion.size();
	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método devuelve un iterador del conjunto de solución , su complejidad es constante
     * según la documentación de java.
     * 
     */
	public Iterator<Arco<Integer>> obtenerTuneles() {
		return this.conjuntoSolucion.iterator();

	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método retorna si conjunto de solución esta vacío, su complejidad es constante.
     * 
     */
	public boolean isEmpty() {
		return this.conjuntoSolucion.isEmpty();
	}
	
	/*
     * Complejidad computacional O(1) 
     * Este método vacía al conjunto de solución , su complejidad es constante.
     * 
     */
	public void clear() {
		this.conjuntoSolucion.clear();
		this.distancia = 0;
	}
	
	/*
     * Complejidad computacional O(j) donde j es el tamaño del conjunto de solución. 
     * Este método agrega todos los elementos de solución al conjunto de solución.
     * 
     */
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
	
	/*
     * Complejidad computacional O(n) donde n es la cantidad de vértices
     * Este método itera sobre el mapa de solucionParcial, por lo tanto la complejidad 
     * es de O(n) donde n es la cantidad de vértices que posee el mapa solucionParcial.
     */
    public boolean esSolucion( int cantidadTunelesTotales) {
        if (!this.conjuntoSolucion.isEmpty()) {
            UnionFind union = new UnionFind(cantidadTunelesTotales);
            Iterator<Arco<Integer>> tuneles = this.obtenerTuneles();
            while(tuneles.hasNext()) {
            	Arco<Integer> tunel = tuneles.next();
            	int verticeOrigen = tunel.getVerticeOrigen() - 1;
            	int verticeDestino = tunel.getVerticeDestino() - 1;
            	union.Union(verticeOrigen, verticeDestino);
            }
            return union.size() == 1;
        }
        return false;
    }
	
}
