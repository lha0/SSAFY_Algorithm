import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				pq.add(next);
			}
		}

		int n = 0;
		for (int i = 1; i <= N; i++) {
			 n = pq.poll();
			
		}
		
		bw.write(n + "\n");
		
		bw.close();
		br.close();
	}

}
