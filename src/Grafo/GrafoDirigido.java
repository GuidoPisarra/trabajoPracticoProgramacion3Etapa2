package Grafo;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import Interfaces.Grafo;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private HashMap<Integer, HashMap<Integer, T>> vertices = new HashMap<>();

	private HashMap<Integer, String> colores = new HashMap<>();
	
	private int cantidadDeArcos = 0;
		
	
	/**
	* Agrega un vertice 
	* Complejidad: O(1) es constante debido a que debe
	* realiza una inserción.
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if (!vertices.containsKey(verticeId)) {
			vertices.put(verticeId, new HashMap<>());
		}
	}

	/**
	* Borra un vertice
	* Complejidad: O(n) donde n es el numero de vertices que tiene el grafo,
	* porque si bien la operación de busqueda y eliminacion en un HashMap es de O(1), 
	* debido a que se hace un for para la busqueda de claves en el mapa, de forma tal 
	* que se elimine ademas del vertice, tambien se elimine este del mapa de adyacentes.
	*/
	@Override
	public void borrarVertice(int verticeId) {
		for(int i : vertices.keySet()) {
			vertices.get(i).remove(verticeId);			
		}
		vertices.remove(verticeId);
	}


	/**
	* Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	* Complejidad: O(1)  debido a que se realiza una inserción y es el tipo de complejidad
	* que posee el Hashmap para tal operacíon.	* 
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
			vertices.get(verticeId1).put(verticeId2, etiqueta);
			this.cantidadDeArcos++;
		}
	}

	/**
	 * Borra el arco que conecta el verticeId1 con el verticeId2 
	 * Complejidad: O(1) debido a que al eliminar un arco solo realiza una búsqueda y eliminación
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 * según la documentación de Java.
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)) {			
			vertices.get(verticeId1).remove(verticeId2);
			this.cantidadDeArcos--;
		}
	}

	/**
	 * Verifica si existe un vertice
	 * Complejidad: O(1) debido a que solo realiza una búsqueda 
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	
	/**
	 * Verifica si existe un arco entre dos vertices
	 * Complejidad: O(1) debido a que solo realiza una búsqueda 
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)) {
			if(vertices.get(verticeId1).containsKey(verticeId2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtener el arco que conecta el verticeId1 con el verticeId2
	 * Complejidad: O(1) debido a que solo realiza una búsqueda de un arco y todas las operaciones 
	 * realizadas tienen un tiempo constante, independientemente del tamaño del grafo.
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1) && vertices.get(verticeId1).containsKey(verticeId2)) {
			Arco<T> tmp = new Arco<T> (verticeId1, verticeId2, vertices.get(verticeId1).get(verticeId2));
			return tmp;
		}
		return null;
	}
	
	/**
	 * Devuelve la cantidad total de vertices en el grafo
	 * Complejidad: O(1) debido a que simplemente devuelve el tamaño del mapa vertices utilizando el método size()
	 */
	@Override
	public int cantidadVertices() {		
		return vertices.size();
	}

	/**
	 * Devuelve la cantidad total de arcos en el grafo
	 * Complejidad: O(n) donde n es la cantidad de vertices que tiene el grafo.
	 * En el peor de los casos dode un vertice contiene un arco para cada uno de 
	 * los vértices, la cantidad total de arcos va a ser la suma de todos los tamaños de los árboles de adyacencia.
	 * El método recorre todos los vertices utilizando un bucle for y para cada vértice obtiene el tamaño de
	 * su mapa de adyacencia correspondiente.
	 */
	@Override
	public int cantidadArcos() {
		return this.cantidadDeArcos;
	}
	
	/**
	 * Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	 * Complejidad: O(1) debido a que devuelve un listado de claves, cuyo tamaño es igual al numero de vértices en el grafo.
	 * el tiempo de acceso a las claves es constante por la indexación interna de la estructura.
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}

	/**
	 * Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId 
	 * Complejidad: O(1) debido a que recibe un parámetro y devuelve un iterador sobre las claves de los
	 * adyacentes. El acceso a esa lista es de tiempo constante, porque se utiliza el identificador del vértice para acceder
	 * a su mapa de adyacencia.
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		return vertices.get(verticeId).keySet().iterator();
	}
	
	/**
	 * Obtiene un iterador que me permite recorrer todos los arcos del grafo
	 * Complejidad: O(n + a) donde n es la cantidad de vertices y a es la cantidad de arcos en el grafo.
	 * Debido a que se recorren todos los vertices y sus adyacentes, la complejidad de éste método es igual a la cantidad de 
	 * vértices y la cantidad de adyacentes
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();
		for(int i: vertices.keySet()) {
			for(int j: vertices.get(i).keySet()) {
				Arco<T> tmp = new Arco<T>(i,j,vertices.get(i).get(j));
				arcos.add(tmp);
			}
		}		
		return arcos.iterator();
	}
	
	/**
	 * Devuelve un iterador que me permite recorrer todos los arcos que parten desde verticeId
	 * Complejidad: O(a) donde a es la cantidad de de arcos del vértice que recibe como parámetro.
	 * Para realizar esto, se realiza una iteración, en la que se recorren cada uno de los adyacentes, creando un arco y 
	 * agregándolo a una lista de arcos; del vértice que recibe como parámetro.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();
		
		for(int i: vertices.get(verticeId).keySet()) {
				Arco<T> tmp = new Arco<T> (verticeId,i, vertices.get(verticeId).get(i));
				arcos.add(tmp);
			
		}
		return arcos.iterator();
	}	

	@Override
	public String toString() {
		for(int i : vertices.keySet()) {
			System.out.println("Vertice: " + i );
			for(int j : vertices.get(i).keySet()) {
				System.out.print("      " + "--> " + j + " : " + vertices.get(i).get(j));
			}
			System.out.println("");
		}
		
		return "";		
	}

}