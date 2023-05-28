package Interfaces;

import java.util.Iterator;

import Grafo.Arco;

public interface Grafo<T> {
	
	 
	/**
	* Agrega un vertice 
	* Complejidad: O(1) es constante debido a que debe
	* realiza una inserción.
	*/
	public void agregarVertice(int verticeId);

	/**
	* Borra un vertice
	* Complejidad: O(n) donde n es el numero de vertices que tiene el grafo,
	* porque si bien la operación de busqueda y eliminacion en un HashMap es de O(1), 
	* debido a que se hace un for para la busqueda de claves en el mapa, de forma tal 
	* que se elimine ademas del vertice, tambien se elimine este del mapa de adyacentes.
	*/
	public void borrarVertice(int verticeId);


	/**
	* Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	* Complejidad: O(1)  debido a que se realiza una inserción y es el tipo de complejidad
	* que posee el Hashmap para tal operacíon.	* 
	*/
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta);

	/**
	 * Borra el arco que conecta el verticeId1 con el verticeId2 
	 * Complejidad: O(1) debido a que al eliminar un arco solo realiza una búsqueda y eliminación
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 * según la documentación de Java.
	 */
	public void borrarArco(int verticeId1, int verticeId2);

	/**
	 * Verifica si existe un vertice
	 * Complejidad: O(1) debido a que solo realiza una búsqueda 
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 */
	public boolean contieneVertice(int verticeId);  

	/**
	 * Verifica si existe un arco entre dos vertices
	 * Complejidad: O(1) debido a que solo realiza una búsqueda 
	 * y al estar implementado con HashMap, este es la complejidad para este tipo de operaciones 
	 */
	public boolean existeArco(int verticeId1, int verticeId2);
	
	/**
	 * Obtener el arco que conecta el verticeId1 con el verticeId2
	 * Complejidad: O(1) debido a que solo realiza una búsqueda de un arco y todas las operaciones 
	 * realizadas tienen un tiempo constante, independientemente del tamaño del grafo.
	 */
	public Arco<T> obtenerArco(int verticeId1, int verticeId2);

	/**
	 * Devuelve la cantidad total de vertices en el grafo
	 * Complejidad: O(1) debido a que simplemente devuelve el tamaño del mapa vertices utilizando el método size()
	 */
	public int cantidadVertices();


	/**
	 * Devuelve la cantidad total de arcos en el grafo
	 * Complejidad: O(n) donde n es la cantidad de vertices que tiene el grafo.
	 * En el peor de los casos dode un vertice contiene un arco para cada uno de 
	 * los vértices, la cantidad total de arcos va a ser la suma de todos los tamaños de los árboles de adyacencia.
	 * El método recorre todos los vertices utilizando un bucle for y para cada vértice obtiene el tamaño de
	 * su mapa de adyacencia correspondiente.
	 */
	public int cantidadArcos();

	/**
	 * Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	 * Complejidad: O(1) debido a que devuelve un listado de claves, cuyo tamaño es igual al numero de vértices en el grafo.
	 * el tiempo de acceso a las claves es constante por la indexación interna de la estructura.
	 */
	public Iterator<Integer> obtenerVertices();

	/**
	 * Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId 
	 * Complejidad: O(1) debido a que recibe un parámetro y devuelve un iterador sobre las claves de los
	 * adyacentes. El acceso a esa lista es de tiempo constante, porque se utiliza el identificador del vértice para acceder
	 * a su mapa de adyacencia.
	 */
	public Iterator<Integer> obtenerAdyacentes(int verticeId);

	/**
	 * Obtiene un iterador que me permite recorrer todos los arcos del grafo
	 * Complejidad: O(n + a) donde n es la cantidad de vertices y a es la cantidad de arcos en el grafo.
	 * Debido a que se recorren todos los vertices y sus adyacentes, la complejidad de éste método es igual a la cantidad de 
	 * vértices y la cantidad de adyacentes
	 */
	public Iterator<Arco<T>> obtenerArcos();
		
	/**
	 * Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
	 * Complejidad: O(a) donde a es la cantidad de de arcos del vértice que recibe como parámetro.
	 * Para realizar esto, se realiza una iteración, en la que se recorren cada uno de los adyacentes, creando un arco y 
	 * agregándolo a una lista de arcos; del vértice que recibe como parámetro.
	 */
	public Iterator<Arco<T>> obtenerArcos(int verticeId);
	
	
}
