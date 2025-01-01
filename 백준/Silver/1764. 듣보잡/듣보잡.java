import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> listenMap = new HashMap<>();
		for (int i = 0; i < N+M; i++) {
			String cur = br.readLine().split(" ")[0];
			
			if (listenMap.containsKey(cur)) listenMap.put(cur, listenMap.get(cur) + 1);
			else listenMap.put(cur, 1);
		}
		
		List<String> answer = new ArrayList<>();
		listenMap.forEach((key, value) -> {
			if (value >= 2) answer.add(key);
		});
		
		Collections.sort(answer);
		
		System.out.println(answer.size());
		for (String name : answer) {
			System.out.println(name);
		}
		
		br.close();
		bw.close();

	}

}
