import java.io.*;
import java.util.*;

public class Main {
	static int T, N, sx, sy, ex, ey;
	static String answer;
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);

			list = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				if (i == 0) {
					sx = start;
					sy = end;
				} else if (i == N + 1) {
					ex = start;
					ey = end;
				} else {
					list.add(new int[] { start, end });
				}

			}

			if (bfs())
				answer = "happy";
			else
				answer = "sad";

			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean bfs() {

		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[N];
		queue.offer(new int[] { sx, sy });

		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			

			if (Math.abs(ex - pos[0]) + Math.abs(ey - pos[1]) <= 1000) {
				return true;
			}

			for (int i = 0; i < list.size(); i++) {
				int[] next = list.get(i);

				if (!visited[i] && Math.abs(pos[0] - next[0]) + Math.abs(pos[1] - next[1]) <= 1000) {
					visited[i] = true;
					queue.add(next);
				}
			}
		}

		return false;

	}

}
