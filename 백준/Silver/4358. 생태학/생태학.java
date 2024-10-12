import java.io.*;
import java.util.*;

public class Main {
	static Map<String, Integer> treeMap = new HashMap<>();
	static int total = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String line = "";
		while((line = br.readLine()) != null) {
			String tree = line;
			total++;
			if (treeMap.containsKey(tree)) treeMap.put(tree, treeMap.get(tree) + 1);
			else treeMap.put(tree, 1);
		}
		
		//정렬 1
		Map<String, Integer> sortedMap = new TreeMap<>(treeMap);
		
//		//정렬 2
//		List<String> sortedKey = new ArrayList<>(treeMap.keySet());
//		Collections.sort(sortedKey);
		
		sortedMap.forEach((key, value) -> {
			System.out.printf("%s %.4f\n", key, (double) value / total * 100);
		});
		
	}

}
