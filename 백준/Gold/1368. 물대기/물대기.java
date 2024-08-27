import java.io.*;
import java.util.*;

/*
0번노드부터 시작해서 0번에서의 각 정점으로의 MST를 구현
*/

public class Main {
	static int N, answer;
	static int[] self;
	static List<Pond>[] adjList;

	static class Pond implements Comparable<Pond> {
		int idx, cost;

		Pond(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pond o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		// 인접리스트 초기화
		adjList = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 0번 노드 만들기
		for (int i = 0; i < N; i++) {
		  int in = Integer.parseInt(br.readLine().split(" ")[0]);
		  adjList[0].add(new Pond(i+1, in));
		}

		// 인풋 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val != 0) {
					adjList[i+1].add(new Pond(j+1, val));
				} 
			}
		}

		self = new int[N+1];
		Arrays.fill(self, Integer.MAX_VALUE);
		
		prim();

		for (int i = 1; i < self.length; i++) {
			answer += self[i];
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	private static void prim() {
		Queue<Pond> pq = new PriorityQueue<Pond>();
		boolean[] visited = new boolean[N+1];
		
		pq.offer(new Pond(0, 0));

		while (!pq.isEmpty()) {
			Pond cur = pq.poll();

			if (visited[cur.idx])
				continue;

			visited[cur.idx] = true;

			for (Pond next : adjList[cur.idx]) {
				
				if (!visited[next.idx] && self[next.idx] > next.cost) {
					pq.offer(new Pond(next.idx, next.cost));
					self[next.idx] = next.cost;
				}
			}

		}
	}

}
