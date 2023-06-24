package Algoritmos;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking_old {
    private GrafoNoDirigido<Integer> grafo;
    private LinkedList<Arco<Integer>> mejorSolucion;
    private LinkedList<Arco<Integer>> visitados;
    private LinkedList<Arco<Integer>> arcosTotales;

    private int iteraciones = 0;

    public Backtracking_old(GrafoNoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.mejorSolucion = new LinkedList<Arco<Integer>>();
        this.visitados = new LinkedList<Arco<Integer>>(); 
        this.arcosTotales = new LinkedList<Arco<Integer>>(); 

    }
    
    public void backtracking_distancia() {
        LinkedList<Arco<Integer>> resultadoParcial= new LinkedList<Arco<Integer>> ();;
        Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();
		while(arcos.hasNext()) {
			Arco<Integer> arco = arcos.next();
			arcosTotales.add(arco);
			backtracking(arcosTotales.getFirst(), resultadoParcial );
		}
        	

        
        
        mostrarSolucion(mejorSolucion);

    }

    private LinkedList<Arco<Integer>> backtracking(Arco<Integer> arcoActual, LinkedList<Arco<Integer>> resultadoParcial) {
    	System.out.println("sol "+ resultadoParcial);

    	System.out.println("sol "+ resultadoParcial);
        if (esSolucion(resultadoParcial)) {
        	
            if ((calcularDistancia(resultadoParcial) < calcularDistancia(mejorSolucion)) ) {
                mejorSolucion.clear();
                mejorSolucion.addAll(resultadoParcial);
            }
        } else {
            
            while (!arcosTotales.isEmpty()) {
                Arco<Integer> arco = arcosTotales.pop();
                iteraciones++;
            	LinkedList<Arco<Integer>> resultado= new LinkedList<Arco<Integer>> ();
            	resultado.add(arco);
            	visitados.add(arco);
            	resultado.addAll(backtracking(arco,resultadoParcial));
                 visitados.remove(arco);                 resultado.remove(arco);
                    
               
            }
        }
        return mejorSolucion;
    }


    private int calcularDistancia(LinkedList<Arco<Integer>>  resultado) {
        int distancia = 0;
        
        LinkedList<Arco<Integer>> resultadoAuxiliar = new LinkedList<Arco<Integer>>();
        resultadoAuxiliar.addAll(resultado);
        if(!resultado.isEmpty()) {
        	for (Arco<Integer> arco : resultado) {
                distancia += arco.getEtiqueta();
            }
        	return distancia;
        }
        return  Integer.MAX_VALUE;
    }

	private boolean esSolucion(LinkedList<Arco<Integer>>  solucionParcial) {
		LinkedList<Integer> vertices = new LinkedList<Integer>();
        LinkedList<Arco<Integer>> solucionAuxiliar = new LinkedList<Arco<Integer>>();
        solucionAuxiliar.addAll(solucionParcial);
        System.out.println("cantVertices aolucion"+vertices.size());
        System.out.println("cant vertices grafo "+this.grafo.cantidadVertices());;
        while (!solucionAuxiliar.isEmpty()) {
        	Arco<Integer> arco = solucionAuxiliar.pop();
        	if(!vertices.contains(arco.getVerticeOrigen())) {
        		vertices.add(arco.getVerticeOrigen());
        	}
        	if(!vertices.contains(arco.getVerticeDestino())) {
        		vertices.add(arco.getVerticeDestino());
        	}

        }
       System.out.println("cantVertices aolucion"+vertices.size());
        System.out.println("cant vertices grafo "+this.grafo.cantidadVertices());
 
		return vertices.size()==this.grafo.cantidadVertices();
	}
    
    private void mostrarSolucion(LinkedList<Arco<Integer>> solucion) {
        int distancia = 0;
        LinkedList<Arco<Integer>> solucionAuxiliar = solucion;
        while (!solucionAuxiliar.isEmpty()) {
        	Arco<Integer> arco = solucionAuxiliar.pop();
            System.out.print("E" + arco.getVerticeOrigen() + "-" + "E" + arco.getVerticeDestino());
            if (!solucionAuxiliar.isEmpty() ) {
                System.out.print(", ");
            }

            distancia = distancia + arco.getEtiqueta();
        }
        System.out.println();
        System.out.println(distancia + " Kms");
        System.out.println(iteraciones + " Iteraciones");
    }
    
    private boolean arcoUtilizado(Arco<Integer> arco) {
	    Arco<Integer> arcoAuxiliar = new Arco<Integer>(arco.getVerticeDestino(),arco.getVerticeOrigen(), arco.getEtiqueta());
	    return (this.visitados.contains(arcoAuxiliar)|| this.visitados.contains(arco));
    }
}