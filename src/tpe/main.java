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
		System.out.println("Backtracking");
		back.Backtracking_distancia(1, 3);
		
		
//		Greedy greedy = new Greedy();
//		System.out.println("Greedy");
//		greedy.greedy(reader.obtenerInformacion(), 1, 6);
		
	}

}