import java.io.*;
import java.util.*;

public class Solution {
	static int T, V, E;
	static long cost;
	static Edge[] edgeList;
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int s, e;
		long w;

		Edge(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			cost = 0;

			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				long w = Integer.parseInt(st.nextToken());

				edgeList[i] = new Edge(s, e, w);
			}

			make();

			Arrays.sort(edgeList);

			run();

			bw.write("#" + tc + " " + cost + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static void run() {
		int cnt = 0;
		for (Edge e : edgeList) {
			if (union(e.s, e.e)) {
				cost += e.w;
				if (++cnt == V-1) break;
			}
			
		}
	}

	private static boolean union(int s, int e) {
		int sRoot = find(s);
		int eRoot = find(e);

		if (sRoot == eRoot)
			return false;

		if (sRoot < eRoot) {
			parents[eRoot] = sRoot;
		} else {
			parents[sRoot] = eRoot;
		}

		return true;
	}

	private static int find(int s) {
		if (s == parents[s])
			return s;
		else return parents[s] = find(parents[s]);
	}

	private static void make() {
		parents = new int[V + 1];
		for (int i = 0; i < V + 1; i++) {
			parents[i] = i;
		}
	}

}
