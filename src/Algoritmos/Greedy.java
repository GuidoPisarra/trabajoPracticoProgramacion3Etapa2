package Algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Grafo.Arco;
import Grafo.GrafoNoDirigido;

public class Greedy {
    private GrafoNoDirigido<Integer> grafo;
    private int time = 0;
    
    public void greedy (GrafoNoDirigido<Integer> grafo){
        HashMap<Integer,Integer> resultado = new HashMap<Integer,Integer>(); 

        this.grafo = grafo;
    	
    	Iterator<Arco<Integer>> listaArcos = ordenarArcosPorMenorDistancia();
    	    	
    	while(listaArcos.hasNext()) {
    		Arco<Integer> arco = listaArcos.next();
    		this.time++;
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
        System.out.println(time+" Iteraciones");


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
    
    
// TERCER SOLUCION (NO SIRVE)    
//  public void greedy(GrafoNoDirigido<Integer> grafo) {
//	  Timer timer = new Timer();
//	  timer.start();
//	  LinkedList<Integer> resultado = new LinkedList<Integer>();
//	  HashMap<Integer, Integer> distancia = new HashMap<Integer, Integer>();
//	  HashMap<Integer, Integer> padre = new HashMap<Integer, Integer>();
//	  Iterator<Integer> vertices = grafo.obtenerVertices();
//	  LinkedList<Integer> listaVertices = new LinkedList<Integer>();
//	  
//	  while (vertices.hasNext()) {
//	      int vertice = vertices.next();
//	      distancia.put(vertice, distanciaMaxima);
//	      padre.put(vertice, -1);
//	      listaVertices.add(vertice);
//	  }
//	  int origen = listaVertices.getFirst();
//	  int destino = listaVertices.getLast();
//	  distancia.replace(origen, 0);
//	  vertices = grafo.obtenerVertices();
//	  while (resultado.size() < grafo.cantidadVertices()) {
//	      int u = obtenerAdyacenteMenorDistancia(distancia, resultado);
//	      resultado.add(u);
//	      Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(u);
//	      while (adyacentes.hasNext()) {
//	          int adyacente = adyacentes.next();
//	          if (!resultado.contains(adyacente)) {
//	              int dist = distancia.get(u) + distanciaEntre(grafo, u, adyacente);
//	              if (dist < distancia.get(adyacente)) {
//	                  distancia.replace(adyacente, dist);
//	                  padre.replace(adyacente, u);
//	              }
//	          }
//	      }
//	  }
//	  this.timer = timer.stop();
//	  if (!resultado.isEmpty() && padre.get(destino) != -1) {
//	      this.mostrarSolucion(grafo,resultado, distancia, destino);
//	  } else {
//	      this.mostrarSolucion(grafo,null, distancia, destino);
//	  }
//
//}
//
//private int obtenerAdyacenteMenorDistancia(HashMap<Integer, Integer> distancias, List<Integer> resultadoParcial) {
//  int verticeMenor = -1;
//  float distanciaMenor = this.distanciaMaxima;
//  for (Map.Entry<Integer, Integer> entry : distancias.entrySet()) {
//      int vertice = entry.getKey();
//      int dist = entry.getValue();
//      if (!resultadoParcial.contains(vertice) && dist < distanciaMenor) {
//          distanciaMenor = dist;
//          verticeMenor = vertice;
//      }
//  }
//  return verticeMenor;
//}
//
//private Integer distanciaEntre(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
//  if (grafo.existeArco(origen, destino)) {
//      Arco<Integer> arco = grafo.obtenerArco(origen, destino);
//      return arco.getEtiqueta();
//  }
//  return 0;
//}
//
//private void mostrarSolucion(GrafoNoDirigido<Integer> grafo, LinkedList<Integer> padre, HashMap<Integer, Integer> distancia, int destino) {
//  LinkedList<Integer> camino = new LinkedList<Integer>();
//  int distanciaTotal = 0;
//
//  if (!padre.isEmpty()) {   
//      camino.addAll(padre);
//  } else {
//      System.out.println("No se pudo determinar una solución");
//  }
//  System.out.println(distancia);
//  
//  Iterator<Integer> vertices = grafo.obtenerVertices();
//  for(Integer d: distancia.keySet()) {
//	  distanciaTotal= distanciaTotal + distancia.get(d);
//  }
//  int i = 0;
//  while(vertices.hasNext()) {
//	  int vertice = vertices.next();
//	  Integer res = camino.get(i);
//	  
//
//	  
//	  System.out.print("E" + res);
//	  if (i != camino.size() - 1) {
//		  System.out.print(" - ");
//	  }
//	  i++;
//  }
//  
//
//  System.out.println();
//  System.out.println(distanciaTotal + " Kms");
//  System.out.println("Tiempo: " + this.timer);
//}
 
// SEGUNDA SOLUCION
//    public HashMap<Integer,Integer> greedy (GrafoNoDirigido<Integer> grafo){
//    	HashMap<Integer, Integer> resultado = new HashMap<Integer, Integer>();
//    	LinkedList<Arco> combinaciones = crearCombinacionesPosibles(grafo);
//
//    	
//    	
//    	
//    	
//    	
//    	while(!combinaciones.isEmpty()) {
//    		Arco<Integer> arco = combinaciones.pop();
//			if(!resultado.containsKey(arco.getVerticeOrigen())) {
//				System.out.println(arco.getVerticeOrigen()+" "+ arco.getVerticeDestino());
//
//				resultado.put(arco.getVerticeOrigen(), arco.getVerticeDestino());
//			}else {
//				Arco<Integer> arcoResultado = grafo.obtenerArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
//				if(arco.getEtiqueta()<=arcoResultado.getEtiqueta()) {
//					
//					if(!resultado.containsKey(arco.getVerticeDestino())) {
//						System.out.println("Pendiente "+arco.getVerticeOrigen()+" "+ arco.getVerticeDestino());
//						System.out.println("Resultado "+arcoResultado.getVerticeOrigen()+" "+ arcoResultado.getVerticeDestino());
//						resultado.replace(arco.getVerticeOrigen(), arco.getVerticeDestino());
//					}
//				}
//			}
//    		
//    	}
//    	
//
//    	
//    	
//    	
//    	return resultado;
//    	
//    }
//    
//    
//    private LinkedList<Arco> crearCombinacionesPosibles(GrafoNoDirigido<Integer> grafo){
//    	LinkedList<Arco> combinacionesPosibles = new LinkedList<Arco>();
//    	Iterator<Integer> vertices = grafo.obtenerVertices();
//    	
//    	while(vertices.hasNext()) {
//    		int vertice = vertices.next();
//    		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
//    		while(adyacentes.hasNext()) {
//    			int adyacente = adyacentes.next();
//    			Arco<Integer> arco = grafo.obtenerArco(vertice, adyacente);
//    			combinacionesPosibles.add(arco);
//    		}
//    	}
//    	
//    	
//    	return combinacionesPosibles;
//    }
//    
//    
    
    
// PRIMER SOLUCION    
//    public void greedy(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
//        Timer timer = new Timer();
//        timer.start();
//        LinkedList<Integer> resultado = new LinkedList<Integer>();
//        HashMap<Integer, Integer> distancia = new HashMap<Integer, Integer>();
//        HashMap<Integer, Integer> padre = new HashMap<Integer, Integer>();
//        Iterator<Integer> vertices = grafo.obtenerVertices();
//
//        while (vertices.hasNext()) {
//            int vertice = vertices.next();
//            distancia.put(vertice, distanciaMaxima);
//            padre.put(vertice, -1);
//        }
//        distancia.replace(origen, 0);
//
//        while (resultado.size() < grafo.cantidadVertices()) {
//            int u = obtenerAdyacenteMenorDistancia(distancia, resultado);
//            resultado.add(u);
//
//            if (u == destino) {
//                break;
//            }
//
//            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(u);
//            while (adyacentes.hasNext()) {
//                int adyacente = adyacentes.next();
//                if (!resultado.contains(adyacente)) {
//                    int dist = distancia.get(u) + distanciaEntre(grafo, u, adyacente);
//                    if (dist < distancia.get(adyacente)) {
//                        distancia.replace(adyacente, dist);
//                        padre.replace(adyacente, u);
//                    }
//                }
//            }
//        }
//        this.timer = timer.stop();
//        if (!resultado.isEmpty() && padre.get(destino) != -1) {
//            this.mostrarSolucion(padre, distancia, destino);
//        } else {
//            this.mostrarSolucion(null, distancia, destino);
//        }
//
//    }
//
//    private int obtenerAdyacenteMenorDistancia(HashMap<Integer, Integer> distancias, List<Integer> resultadoParcial) {
//        int verticeMenor = -1;
//        float distanciaMenor = this.distanciaMaxima;
//        for (Map.Entry<Integer, Integer> entry : distancias.entrySet()) {
//            int vertice = entry.getKey();
//            int dist = entry.getValue();
//            if (!resultadoParcial.contains(vertice) && dist < distanciaMenor) {
//                distanciaMenor = dist;
//                verticeMenor = vertice;
//            }
//        }
//        return verticeMenor;
//    }
//
//    private Integer distanciaEntre(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
//        if (grafo.existeArco(origen, destino)) {
//            Arco<Integer> arco = grafo.obtenerArco(origen, destino);
//            return arco.getEtiqueta();
//        }
//        return 0;
//    }
//
//    private void mostrarSolucion(HashMap<Integer, Integer> padre, HashMap<Integer, Integer> distancia, int destino) {
//        LinkedList<Integer> camino = new LinkedList<Integer>();
//        int vertice = destino;
//        int distanciaTotal = distancia.get(vertice);
//
//        if (padre != null) {
//            while (vertice != -1) {
//                camino.addFirst(vertice);
//                vertice = padre.get(vertice);
//            }
//        } else {
//            System.out.println("No se pudo determinar una solución");
//        }
//        System.out.println();
//        for (int i = 0; i < camino.size(); i++) {
//            Integer res = camino.get(i);
//            System.out.print("E" + res);
//            if (i != camino.size() - 1) {
//                System.out.print(" - ");
//            }
//        }
//        System.out.println();
//        System.out.println(distanciaTotal + " Kms");
//        System.out.println("Tiempo: " + this.timer);
//    }
}

