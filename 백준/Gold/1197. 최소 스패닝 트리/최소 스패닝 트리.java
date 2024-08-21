import java.io.*;
import java.util.*;

public class Main {
	static int V, E, sum;
	static List<Node>[] graph;
	static int[] dist;

	static class Node implements Comparable<Node> {
		int e, w;

		Node(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];
		for (int i = 0; i < V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(sts.nextToken());
			int end = Integer.parseInt(sts.nextToken());
			int weight = Integer.parseInt(sts.nextToken());
			
			graph[start].add(new Node(end, weight));
			graph[end].add(new Node(start, weight));
		}
		
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		sum = 0;
		
		prim();

		
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void prim() {
		boolean[] visited = new boolean[V+1];
		Queue<Node> queue = new PriorityQueue<Node>();
		queue.offer(new Node(1, 0));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if (visited[cur.e]) continue;
			
			visited[cur.e] = true;
			sum += cur.w;
			
			for (Node next : graph[cur.e]) {
				if (!visited[next.e] && dist[next.e] > next.w) {
					queue.offer(new Node(next.e, next.w));
					dist[next.e] = next.w;
				}
			}
		}
	}

}
