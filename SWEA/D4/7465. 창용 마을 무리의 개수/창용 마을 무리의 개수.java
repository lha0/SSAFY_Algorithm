import java.io.*;
import java.util.*;

public class Solution {
	static int T, answer, N, M;
	static ArrayList<Integer>[] adjList;
	static int[] parents, sizeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			
			make();
			
			adjList = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(sts.nextToken());
				int B = Integer.parseInt(sts.nextToken());

				adjList[A].add(B);
				
//				union(A, B);
			}
			
			for (int i = 1; i <= N; i++) {
				ArrayList<Integer> start = adjList[i];

				for (int child : start) {
					union(i, child);
				}
			}
			
			answer = 0;
			for (int i = 1; i <= N; i++) {
				if (i == parents[i]) answer++;
			}

			bw.write("#" + tc + " " + answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int find(int i) {
		if (i == parents[i])
			return i;
		else
			return parents[i] = find(parents[i]);
	}

	private static void union(int i, int child) {
		int aRoot = find(i);
		int bRoot = find(child);
		
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}

	private static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

}
