package tpe;

import java.util.HashMap;
import java.util.List;

import Algoritmos.Backtracking;
import Algoritmos.Greedy;

public class main {

	public static void main(String[] args) {

		String path = "C:\\Users\\guidi\\OneDrive\\Documentos\\eclipse-workspace\\trabajoPracticoProgramacion3Etapa2\\datasets\\dataset2.txt";
		CSVReader reader = new CSVReader(path);
		reader.read();
		
		Backtracking back = new Backtracking(reader.obtenerInformacion());
		List<Integer>distancia= back.Backtracking_distancia(5, 3);
		System.out.println("Backtracking");
		System.out.println(distancia.toString());
		
		
		Greedy greedy = new Greedy();
		HashMap<Integer,Integer> distancia_greedy = greedy.greedy(reader.obtenerInformacion(), 1, 6);
		System.out.println("Greedy");
		System.out.println(distancia_greedy.toString());
	}

}