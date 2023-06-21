package Algoritmos;

import java.util.Iterator;
import java.util.LinkedList;
import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Backtracking {
    private GrafoNoDirigido<Integer> grafo;
    private LinkedList<Arco<Integer>> mejorSolucion;
    private LinkedList<Arco<Integer>> visitados;
    private int iteraciones = 0;

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.mejorSolucion = new LinkedList<Arco<Integer>>();
        this.visitados = new LinkedList<Arco<Integer>>();       
    }
    
    public void backtracking_distancia() {
        LinkedList<Arco<Integer>> resultadoParcial = new LinkedList<Arco<Integer>> ();
        
        Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();
        while(arcos.hasNext()) {
        	Arco<Integer> arco = arcos.next();
        	backtracking(arco,resultadoParcial);
       	
        }
        

            mostrarSolucion(mejorSolucion);
            System.out.println(calcularDistancia(mejorSolucion));

    }

    private void backtracking(Arco<Integer>arcoActual,LinkedList<Arco<Integer>> resultadoParcial) {
        if (esSolucion(resultadoParcial)) {
        	if (this.calcularDistancia(resultadoParcial)<this.calcularDistancia(mejorSolucion) ) {
        		mejorSolucion.clear();
                mejorSolucion.addAll(resultadoParcial);
            }            
        }else {
        	Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(arcoActual.getVerticeOrigen());
        	while (adyacentes.hasNext()) {
        		int adyacente = adyacentes.next();
        		Arco<Integer> arco = this.grafo.obtenerArco(arcoActual.getVerticeOrigen(), adyacente);
        		this.iteraciones++;
        		if (this.arcoUtilizado(arco)) {
        			resultadoParcial.add(arco);
        			this.visitados.add(arco);
        			if(this.calcularDistancia(resultadoParcial)<this.calcularDistancia(mejorSolucion)) {
        				backtracking(arco,resultadoParcial);
        			}
                    
        			visitados.remove(arco);
        			resultadoParcial.remove(arco);
        		}
        	}
        }

    }

    private int calcularDistancia(LinkedList<Arco<Integer>>  resultado) {
        int distancia = 0;
        
        LinkedList<Arco<Integer>> resultadoAuxiliar = new LinkedList<Arco<Integer>>();
        resultadoAuxiliar.addAll(resultado);
        if(!resultado.isEmpty()) {
        	while(!resultadoAuxiliar.isEmpty()) {
        		Arco<Integer> arco = resultadoAuxiliar.pop();
        		distancia = distancia + arco.getEtiqueta();
        	}
        	
        	return distancia;
        }
        return  Integer.MAX_VALUE;
    }

	private boolean esSolucion(LinkedList<Arco<Integer>>  solucionParcial) {
//		LinkedList<Integer> vertices = new LinkedList<Integer>();
//        LinkedList<Arco<Integer>> solucionAuxiliar = new LinkedList<Arco<Integer>>();
//        solucionAuxiliar.addAll(solucionParcial);
//        while (!solucionAuxiliar.isEmpty()) {
//        	Arco<Integer> arco = solucionAuxiliar.pop();
//        	if(!vertices.contains(arco.getVerticeOrigen())) {
//        		vertices.add(arco.getVerticeOrigen());
//        	}
//        	if(!vertices.contains(arco.getVerticeDestino())) {
//        		vertices.add(arco.getVerticeDestino());
//        	}
//        }
//        System.out.println(vertices.toString());
        
		return solucionParcial.size()==this.grafo.cantidadVertices();
	}
    
    private void mostrarSolucion(LinkedList<Arco<Integer>> solucion) {
        int distancia = 0;
        int i = 0;

        LinkedList<Arco<Integer>> solucionAuxiliar = solucion;
        while (!solucionAuxiliar.isEmpty()) {
        	Arco<Integer> arco = solucionAuxiliar.pop();
            System.out.print("E" + arco.getVerticeOrigen() + "-" + "E" + arco.getVerticeDestino());
            if (i < solucion.size() ) {
                System.out.print(", ");
            }
            i++;


            distancia = distancia + arco.getEtiqueta();
        }
        System.out.println();
        System.out.println(distancia + " Kms");
        System.out.println(iteraciones + " Iteraciones");
    }
    
    private boolean arcoUtilizado(Arco<Integer> arco) {
	    Arco<Integer> arcoAuxiliar = new Arco<Integer>(arco.getVerticeDestino(),arco.getVerticeOrigen(), arco.getEtiqueta());
	    System.out.println(this.visitados.contains(arcoAuxiliar)|| this.visitados.contains(arco));

	    return (this.visitados.contains(arcoAuxiliar)|| this.visitados.contains(arco));
    }
}