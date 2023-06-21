package Algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;
import Union.UnionFind;

public class Greedy {
    private GrafoNoDirigido<Integer> grafo;
    private int iteraciones = 0;
    
    public void greedy (GrafoNoDirigido<Integer> grafo){
        HashMap<Integer,Integer> resultado = new HashMap<Integer,Integer>(); 

        this.grafo = grafo;
    	
    	Iterator<Arco<Integer>> listaArcos = ordenarArcosPorMenorDistancia();
    	    	
    	while(listaArcos.hasNext() && !this.esSolucion(resultado)) {
    		Arco<Integer> arco = listaArcos.next();
    		this.iteraciones++;
    		if(!resultado.containsKey(arco.getVerticeOrigen()) &&  !resultado.containsKey(arco.getVerticeDestino())) {   
    				resultado.put(arco.getVerticeOrigen(),arco.getVerticeDestino());
    		}
    	}
    	if(!resultado.isEmpty()) {
    		mostrarSolucion(resultado);
    	}else {
    		System.out.println("No hay solucion");
    	}   
    }
    
    
    /*
     * Costo computacional de esta funcion es de O( m log n) donde
     * m es la cantidad de pares de vértices y n la cantidad de vértices  
     */
    private boolean esSolucion(HashMap<Integer, Integer> resultado) {
        UnionFind unionFind = new UnionFind(this.grafo.cantidadVertices());
        
        // Unir los componentes según los pares de vértices en el resultado
        // Costo computacional de O(m log n) donde m es la cantidad de pares de vértices 
        //y n es la cantidad de vértices
        for (Map.Entry<Integer, Integer> entry : resultado.entrySet()) {
            int vertice1 = entry.getKey();
            int vertice2 = entry.getValue();
            unionFind.union(vertice1, vertice2);
        }
        
        // Verificar si todos los vértices están en la misma componente
        int representante = unionFind.find(0); // Obtener el representante de cualquier vértice
        // Costo computacional O(n log n) donde n es la cantidad de vértices
        for (int i = 1; i < this.grafo.cantidadVertices(); i++) {
            if (unionFind.find(i) != representante) {
            	// Los vértices no están conectados
                return false; 
            }
        }       
        // Todos los vértices están conectados
        return true; 
    }
    
    /*
     * Complejidad computacional con notación big O 
     * En el peor caso (los arcos estan desordenados) la complejidad es de O(n^2)
     * En el mejor caso (los arcos estan ordenados) la complejidad computacional es de O(n) 
     */
    private Iterator<Arco<Integer>>  ordenarArcosPorMenorDistancia(){
    	Iterator<Arco<Integer>> listadoAuxiliar = grafo.obtenerArcos();
    	List<Arco<Integer>> listaArcos = new ArrayList<>();

    	while (listadoAuxiliar.hasNext()) {
    	    Arco<Integer> arco = listadoAuxiliar.next();
    	    listaArcos.add(arco);
    	}

    	for (int i = 1; i < listaArcos.size(); i++) {
    	    Arco<Integer> arcoActual = listaArcos.get(i);
    	    int j = i - 1;
    	    while (j >= 0 && listaArcos.get(j).getEtiqueta() > arcoActual.getEtiqueta()) {
    	        listaArcos.set(j + 1, listaArcos.get(j));
    	        j--;
    	    }
    	    listaArcos.set(j + 1, arcoActual);
    	}

    	Iterator<Arco<Integer>> iteratorOrdenado = listaArcos.iterator();
    	return iteratorOrdenado;
    }
    
    private void mostrarSolucion(HashMap<Integer,Integer> solucion) {
    	int distancia = 0 ;
    	int i = 0;
    	for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
          System.out.print("E"+entry.getKey()+ "-" +"E"+entry.getValue());
          if (i < solucion.size() - 1) {
              System.out.print(", ");
          }
          i++;
          Arco<Integer> arco = this.grafo.obtenerArco(entry.getKey(), entry.getValue());
          distancia = distancia + arco.getEtiqueta();
    	}
    	System.out.println();
        System.out.println(distancia+" Kms");
        System.out.println(iteraciones+" Iteraciones");


    }
    
    
}

