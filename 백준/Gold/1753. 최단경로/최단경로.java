import java.io.*;
import java.util.*;

public class Main {
	static int V, E, start;
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		start = Integer.parseInt(br.readLine().split(" ")[0]);

		graph = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(sts.nextToken());
			int end = Integer.parseInt(sts.nextToken());
			int cost = Integer.parseInt(sts.nextToken());

			graph[start].add(new Node(end, cost));
		}

		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra();

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				bw.write("INF \n");
			else
				bw.write(dist[i] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dijkstra() {
		boolean[] visited = new boolean[V + 1];
		Queue<Node> queue = new PriorityQueue<>();

		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (visited[cur.e])
				continue;

			visited[cur.e] = true;

			for (Node next : graph[cur.e]) {
				if (dist[cur.e] + next.w < dist[next.e]) {
					dist[next.e] = dist[cur.e] + next.w;
					queue.offer(new Node(next.e, dist[cur.e] + next.w));
				}
			}
		}
	}

}
