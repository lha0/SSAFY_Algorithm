import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(x, y));
		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(br.readLine());
			
			if (next > 0) pq.add(next);
			else {
				if (!pq.isEmpty()) {
					int n = pq.poll();
					bw.write(n + "\n");
				} else {
					bw.write(0 + "\n");
				}
				
			}
			
		}
		
		
		bw.close();
		br.close();
	}

}
