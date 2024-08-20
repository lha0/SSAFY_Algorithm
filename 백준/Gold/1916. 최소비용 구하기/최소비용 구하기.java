import java.io.*;
import java.util.*;

public class Main {
	static int N, M, sum, startCity, targetCity;
	static List<City>[] adj;
	static int[] distance;
	
	static class City implements Comparable<City> {
		int e, w;
		
		City(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(City o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		M = Integer.parseInt(br.readLine().split(" ")[0]);
		
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[start].add(new City(end, cost));
		}
		
		StringTokenizer sts = new StringTokenizer(br.readLine());
		startCity = Integer.parseInt(sts.nextToken());
		targetCity = Integer.parseInt(sts.nextToken());
		
		sum = 0;
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra();
		
		bw.write(distance[targetCity] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dijkstra() {
		Queue<City> queue = new PriorityQueue<City>();
		boolean[] visited = new boolean[N+1];

		queue.offer(new City(startCity, 0));
		distance[startCity] = 0;
		
		while (!queue.isEmpty()) {
			City cur = queue.poll();
			
			if (visited[cur.e]) continue;
			
			visited[cur.e] = true;
			
			for (City next: adj[cur.e]) {
				// 출발점에서 지금 시티까지 오는데 걸린 비용 = distance[cur.e] + 다음에 갈 도시 비용 => 계속 누적
				if ( distance[cur.e] + next.w < distance[next.e]) {
					queue.offer(new City(next.e, distance[cur.e] + next.w));
					distance[next.e] = distance[cur.e] + next.w;
				}
			}
		}
	}

}
