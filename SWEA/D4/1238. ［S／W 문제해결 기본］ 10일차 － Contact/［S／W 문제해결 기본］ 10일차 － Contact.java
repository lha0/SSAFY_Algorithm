import java.io.*;
import java.util.*;

public class Solution {
	static int N, start;
	static List<Integer>[] adjList;
	static List<int[]> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				adjList[i] = new ArrayList<>();
			}

			StringTokenizer sts = new StringTokenizer(br.readLine());

			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(sts.nextToken());
				int to = Integer.parseInt(sts.nextToken());

				adjList[from].add(to);
			}

			result = new ArrayList<>();

			bfs(start);

			Collections.sort(result, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] - o2[1] == 0) {
						return o1[0] - o2[0];
					}
					return o1[1] - o2[1];
				}

			});

			int index = result.size() - 1;

			int answer = result.get(index)[0];

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int start) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[101];
		queue.offer(new int[] { start, 0 });
		visited[start] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			result.add(cur);

			for (int next : adjList[cur[0]]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(new int[] { next, cur[1] + 1 });
				}
			}
		}

	}

}
