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

    public Backtracking(GrafoNoDirigido<Integer> grafo) {
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
		}
		this.visitados.clear();
		Arco<Integer> arco =arcosTotales.pop();
		resultadoParcial.add(arco);
		
        mostrarSolucion(backtracking(arco, resultadoParcial ));

    }

    private LinkedList<Arco<Integer>> backtracking(Arco<Integer> arcoActual, LinkedList<Arco<Integer>> resultadoParcial) {
        LinkedList<Arco<Integer>> mejorSolucion = new LinkedList<Arco<Integer>>();
        
        if (arcosTotales.isEmpty()) {
            return resultadoParcial;
        } else {
            Iterator<Arco<Integer>> arcosAdyacentes = this.grafo.obtenerArcos(arcoActual.getVerticeOrigen());
        
            Arco<Integer> arco = arcosTotales.pop();
            visitados.add(arco);
            while (arcosAdyacentes.hasNext()) {
                iteraciones++;
        
                Arco<Integer> arcoAdyacente = arcosAdyacentes.next();
                if (!this.visitados.contains(arcoAdyacente)) {
                    resultadoParcial.add(arcoAdyacente);
        
                    LinkedList<Arco<Integer>> resultado = backtracking(arco, resultadoParcial);
                    resultadoParcial.remove(arcoAdyacente); // Remover el último arco agregado

                    if(esSolucion(resultado)) {
                    	if (calcularDistancia(resultado) < calcularDistancia(mejorSolucion) ) {
                    		System.out.println(resultadoParcial);
                    		System.out.println("resultado "+resultado);
                    		resultado.add(arcoAdyacente);
                    		mejorSolucion.clear();
                    		mejorSolucion.addAll(resultado);
                    		
                    	}

                    }
        
                }
                visitados.remove(arcoAdyacente);
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