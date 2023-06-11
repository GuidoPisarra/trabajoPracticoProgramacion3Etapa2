package tpe;

import Algoritmos.Backtracking;
import Algoritmos.Greedy;

public class main {

	public static void main(String[] args) {

		String path = "C:\\Users\\guidi\\OneDrive\\Documentos\\eclipse-workspace\\trabajoPracticoProgramacion3Etapa2\\datasets\\dataset2.txt";
		CSVReader reader = new CSVReader(path);
		reader.read();
		
		System.out.println("Backtracking");
		Backtracking back = new Backtracking(reader.obtenerInformacion(), 1, 3);
		back.Backtracking_distancia();
		System.out.println();
		
		Greedy greedy = new Greedy();
		System.out.println("Greedy");
		greedy.greedy(reader.obtenerInformacion(), 1, 3);
		
	}

}