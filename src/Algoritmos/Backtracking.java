package Algoritmos;

import java.util.Iterator;
import java.util.LinkedList;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking {
	private GrafoNoDirigido<Integer> grafo;
    private LinkedList<Arco<Integer>> mejorSolucion;
    private LinkedList<Arco<Integer>> visitados;
    private LinkedList<Arco<Integer>> arcosTotales;

    private int iteraciones = 0;
    private int kmMejorSolucion = 0;

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.mejorSolucion = new LinkedList<Arco<Integer>>();
        this.visitados = new LinkedList<Arco<Integer>>(); 
        this.arcosTotales = new LinkedList<Arco<Integer>>(); 


    }
    
    public void backtracking_distancia() {
       Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();

    	while(arcos.hasNext()) {
			Arco<Integer> arco = arcos.next();
			arcosTotales.add(arco);
		}
		LinkedList<Arco<Integer>> solucion = new LinkedList<Arco<Integer>>();
		backtracking(solucion, 0);
		mostrarSolucion(mejorSolucion);
	}
	
	private void backtracking(LinkedList<Arco<Integer>> solucionActual, int distanciaSolucionActual) {
		iteraciones++;
		if (arcosTotales.isEmpty() || this.mejorSolucion.isEmpty()) {
			if (esSolucion(solucionActual) &&(distanciaSolucionActual < kmMejorSolucion || this.mejorSolucion.isEmpty())	) {
				this.mejorSolucion.clear();
				this.mejorSolucion.addAll(solucionActual);
				this.kmMejorSolucion = distanciaSolucionActual;
			}
		} if (!arcosTotales.isEmpty() && !( !this.mejorSolucion.isEmpty() && this.kmMejorSolucion <= distanciaSolucionActual )) {
			Arco<Integer> arco = arcosTotales.poll();
			
			solucionActual.add(arco);
			distanciaSolucionActual = distanciaSolucionActual + arco.getEtiqueta();
			
			backtracking(solucionActual, distanciaSolucionActual);
			
			solucionActual.removeLast();
			distanciaSolucionActual = distanciaSolucionActual - arco.getEtiqueta();
			
			backtracking(solucionActual, distanciaSolucionActual);
			arcosTotales.addFirst(arco);
		}
		
	}
    
//	private boolean esSolucion(LinkedList<Arco<Integer>>  solucionParcial) {
//        boolean existeCamino = true;
//		for (Arco<Integer> arco : solucionParcial) {
//	        
//			for (Arco<Integer> arco2 : solucionParcial) {
//		        if(!arco.equals(arco2)) {
//		    		System.out.println(arco2);
//
//		        	if(arco.getVerticeDestino() == arco2.getVerticeOrigen() || arco.getVerticeDestino() == arco2.getVerticeDestino()) {
//		        		existeCamino =true;
//		        		break;
//		        	}
//		        }
//		        if(!existeCamino) {
//		        	existeCamino =false;
//		        	break;
//		        }
//				
//		    }
//	    }
//		System.out.println(existeCamino);
//		return existeCamino;
		
	
//	}
    

	private boolean esSolucion(LinkedList<Arco<Integer>>  solucionParcial) {
		LinkedList<Integer> vertices = new LinkedList<Integer>();
        LinkedList<Arco<Integer>> solucionAuxiliar = new LinkedList<Arco<Integer>>();
        solucionAuxiliar.addAll(solucionParcial);
        while (!solucionAuxiliar.isEmpty()) {
        	Arco<Integer> arco = solucionAuxiliar.pop();
        	if(!vertices.contains(arco.getVerticeOrigen())) {
        		vertices.add(arco.getVerticeOrigen());
        	}
        	if(!vertices.contains(arco.getVerticeDestino())) {
        		vertices.add(arco.getVerticeDestino());
        	}

        }
        System.out.println(vertices.toString());
        
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
    
    private boolean arcoUtilizado(Arco<Integer> arcoPosiblementeUtilizado) {
		Arco<Integer> arcoAux = new Arco<Integer> (arcoPosiblementeUtilizado.getVerticeDestino(),arcoPosiblementeUtilizado.getVerticeOrigen(),arcoPosiblementeUtilizado.getEtiqueta());
		if((this.visitados.contains(arcoPosiblementeUtilizado) || this.visitados.contains(arcoAux))) {
			return true;
		}else {
			return false;
		}
    }
}