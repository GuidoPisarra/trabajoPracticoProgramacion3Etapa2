package AlgoritmosOrdenamiento;

import java.util.*;

public class QuickSort {

    public HashMap<Integer, Integer> sort(HashMap<Integer, Integer> combinaciones) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(combinaciones.entrySet());
        sort(entries, 0, entries.size() - 1);
        combinaciones.clear();
        for (Map.Entry<Integer, Integer> entry : entries) {
            combinaciones.put(entry.getKey(), entry.getValue());
        }
        return combinaciones;
    }

    private void sort(List<Map.Entry<Integer, Integer>> entries, int low, int high) {
        if (low < high) {
            int pi = partition(entries, low, high);
            sort(entries, low, pi - 1);
            sort(entries, pi + 1, high);
        }
    }

    private int partition(List<Map.Entry<Integer, Integer>> entries, int low, int high) {
        int pivot = entries.get(high).getValue();
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (entries.get(j).getValue() < pivot) {
                i++;
                swap(entries, i, j);
            }
        }
        swap(entries, i + 1, high);
        return i + 1;
    }

    private void swap(List<Map.Entry<Integer, Integer>> entries, int i, int j) {
        Map.Entry<Integer, Integer> temp = entries.get(i);
        entries.set(i, entries.get(j));
        entries.set(j, temp);
    }
}
