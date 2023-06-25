package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking {
	private GrafoNoDirigido<Integer> grafo;
    private LinkedList<Arco<Integer>> mejorSolucion = new LinkedList<Arco<Integer>>();
    private LinkedList<Arco<Integer>>visitados = new LinkedList<Arco<Integer>>();
    private LinkedList<Arco<Integer>> arcosTotales = new LinkedList<Arco<Integer>>();

    private int iteraciones;
    private int kmMejorSolucion= Integer.MAX_VALUE;

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
       this.grafo = grafo;
       this.iteraciones = 0;
    }
    
    public void backtracking_distancia() {
    	Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();
    	
    	while(arcos.hasNext()) {
    		Arco<Integer> arco = arcos.next();
    		Arco<Integer> arcoAux = new Arco<>(arco.getVerticeDestino(),arco.getVerticeOrigen(),arco.getEtiqueta());
    		LinkedList<Arco<Integer>> solucion = new LinkedList<Arco<Integer>>();
   		
    		this.arcosTotales.add(arco);
    		int distanciaSolucionActual=0;
    		this.kmMejorSolucion= Integer.MAX_VALUE;
    		this.backtracking(solucion,arco, distanciaSolucionActual);
    	}
    	int distanciaInicial = 0;
    	
    	
 	}
    
    
    private void backtracking(LinkedList<Arco<Integer>> solucionActual, Arco<Integer> arcoActual, int distanciaSolucionActual) {
		
    	if(this.arcosTotales.isEmpty()) {
    		this.mostrarSolucion(this.mejorSolucion);
    	}else {
    		Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos(arcoActual.getVerticeOrigen());
    		while(arcos.hasNext()) {
    			Arco<Integer> arco = arcos.next();
        		Arco<Integer> arcoAux = new Arco<>(arco.getVerticeDestino(),arco.getVerticeOrigen(),arco.getEtiqueta());

    			if((!this.visitados.contains(arco))&&((!this.visitados.contains(arcoAux)))) {
    				this.visitados.add(arcoActual);
    				solucionActual.add(arcoActual);
    				backtracking(solucionActual,arco, distanciaSolucionActual+arco.getEtiqueta() );

    				if (distanciaSolucionActual < kmMejorSolucion 	) {
    					this.mejorSolucion.clear();
    					this.mejorSolucion.addAll(solucionActual);
    					this.kmMejorSolucion = distanciaSolucionActual;
    					System.out.println(mejorSolucion.toString());
    				}
    				
    				solucionActual.remove(arco);
    				this.visitados.remove(arco);
    			}
    		}
    	}
    	
	}

    

//	private boolean esSolucion(LinkedList<Arco<Integer>>  solucionParcial) {
//	
//	}
    
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
    
    private boolean arcoUtilizado(Arco<Integer> arcoPosiblementeUtilizado) {
		Arco<Integer> arcoAux = new Arco<Integer> (arcoPosiblementeUtilizado.getVerticeDestino(),arcoPosiblementeUtilizado.getVerticeOrigen(),arcoPosiblementeUtilizado.getEtiqueta());
		if((this.visitados.contains(arcoPosiblementeUtilizado) || this.visitados.contains(arcoAux))) {
			return true;
		}else {
			return false;
		}
    }
}