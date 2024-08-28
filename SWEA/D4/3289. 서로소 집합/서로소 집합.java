import java.io.*;
import java.util.*;

public class Solution {
	static int T, n, m, answer;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			for (int i = 0 ; i < n+1; i++) {
				parents[i] = i;
			}
			
			bw.write("#" + tc + " ");
			
			for (int i = 0; i < m; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(sts.nextToken());
				int a = Integer.parseInt(sts.nextToken());
				int b = Integer.parseInt(sts.nextToken());
				
				if (op == 0) {
					union(a, b);
				} else {
					if (isSame(a, b)) {
						bw.write(1 + "");
					} else {
						bw.write(0 + "");
					}
				}
			}
			
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static boolean isSame(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) return true;
		else return false;
	}
	
	private static int find(int a) {
		if (parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}

}
