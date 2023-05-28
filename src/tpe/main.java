package tpe;

public class main {

	public static void main(String[] args) {

		String path = "C:\\Users\\guidi\\OneDrive\\Documentos\\eclipse-workspace\\trabajoPracticoProgramacion3Etapa2\\datasets\\dataset1.txt";
		CSVReader reader = new CSVReader(path);
		reader.read();
		
	}

}