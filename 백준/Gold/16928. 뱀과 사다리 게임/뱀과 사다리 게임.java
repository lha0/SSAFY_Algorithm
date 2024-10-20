import java.util.*;
import java.io.*;

public class Main {
	static int N, M, answer;
	static Map<Integer, Integer> ladder, snake;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ladder = new HashMap<>();
		snake = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			ladder.put(x, y);			
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			snake.put(u, v);	
		}

		answer = Integer.MAX_VALUE;
		

		run();
		
		bw.write(answer + "\n");
		
		br.close();
		bw.close();
	}


	private static void run() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		
		queue.add(new int[] {1, 0});
		visited[1] = true;
		
		L: while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int coord = cur[0];
			int dice = cur[1];
			
			for (int i = 1; i <= 6; i++) {
				int next = coord + i;
				
				if (ladder.containsKey(next)) {
					next = ladder.get(next);
				}
				else if (snake.containsKey(next)) {
					next = snake.get(next);
				}
				
				if (next >= 100) {
					answer = Math.min(dice+1, answer);
					break L;
				}
				
				if (!visited[next]) {
					visited[next] = true;
					queue.add(new int[] {next, dice+1});
				}
			}
		}
		
	}

}
