import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] adjList;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		for (int i = 0; i < N; i++) {
			adjList[i+1] = new ArrayList<>();
		}
		
		degree = new int[N+1];
		for (int i = 0; i < M; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(sts.nextToken());
			int B = Integer.parseInt(sts.nextToken());
			
			adjList[A].add(B);
			
			//진입차수 증가
			degree[B] += 1;
		}
		
		run(bw);
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(BufferedWriter bw) throws IOException {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i < degree.length; i++) {
			if (degree[i] == 0) queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			bw.write(cur + " ");
			
			for (int next : adjList[cur]) {
				degree[next] -= 1;
				
				if (degree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}

}
