package Algoritmos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Grafo.Arco;
import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;
import Timer.Timer;

public class Greedy {
    private int distanciaMaxima = Integer.MAX_VALUE;
    private double timer;

    public void greedy(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
        Timer timer = new Timer();
        timer.start();
        LinkedList<Integer> resultado = new LinkedList<Integer>();
        HashMap<Integer, Integer> distancia = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> padre = new HashMap<Integer, Integer>();
        Iterator<Integer> vertices = grafo.obtenerVertices();

        while (vertices.hasNext()) {
            int vertice = vertices.next();
            distancia.put(vertice, distanciaMaxima);
            padre.put(vertice, -1);
        }
        distancia.replace(origen, 0);

        while (resultado.size() < grafo.cantidadVertices()) {
            int u = obtenerAdyacenteMenorDistancia(distancia, resultado);
            resultado.add(u);

            if (u == destino) {
                break;
            }

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(u);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!resultado.contains(adyacente)) {
                    int dist = distancia.get(u) + distanciaEntre(grafo, u, adyacente);
                    if (dist < distancia.get(adyacente)) {
                        distancia.replace(adyacente, dist);
                        padre.replace(adyacente, u);
                    }
                }
            }
        }
        this.timer = timer.stop();
        if (!resultado.isEmpty() && padre.get(destino) != -1) {
            this.mostrarSolucion(padre, distancia, destino);
        } else {
            this.mostrarSolucion(null, distancia, destino);
        }

    }

    private int obtenerAdyacenteMenorDistancia(HashMap<Integer, Integer> distancias, List<Integer> resultadoParcial) {
        int verticeMenor = -1;
        float distanciaMenor = this.distanciaMaxima;
        for (Map.Entry<Integer, Integer> entry : distancias.entrySet()) {
            int vertice = entry.getKey();
            int dist = entry.getValue();
            if (!resultadoParcial.contains(vertice) && dist < distanciaMenor) {
                distanciaMenor = dist;
                verticeMenor = vertice;
            }
        }
        return verticeMenor;
    }

    private Integer distanciaEntre(GrafoNoDirigido<Integer> grafo, int origen, int destino) {
        if (grafo.existeArco(origen, destino)) {
            Arco<Integer> arco = grafo.obtenerArco(origen, destino);
            return arco.getEtiqueta();
        }
        return 0;
    }

    private void mostrarSolucion(HashMap<Integer, Integer> padre, HashMap<Integer, Integer> distancia, int destino) {
        LinkedList<Integer> camino = new LinkedList<Integer>();
        int vertice = destino;
        int distanciaTotal = distancia.get(vertice);

        if (padre != null) {
            while (vertice != -1) {
                camino.addFirst(vertice);
                vertice = padre.get(vertice);
            }
        } else {
            System.out.println("No se pudo determinar una solución");
        }
        System.out.println();
        for (int i = 0; i < camino.size(); i++) {
            Integer res = camino.get(i);
            System.out.print("E" + res);
            if (i != camino.size() - 1) {
                System.out.print(" - ");
            }
        }
        System.out.println();
        System.out.println(distanciaTotal + " Kms");
        System.out.println("Tiempo: " + this.timer);
    }
}

