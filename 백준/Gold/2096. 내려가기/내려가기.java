import java.io.*;
import java.util.*;

public class Main {
	static int N, maxResult, minResult;
	static int[][] newMap, origin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		newMap = new int[2][3];
		origin = new int[2][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int next = Integer.parseInt(st.nextToken());

				if (i == 0) {
					newMap[0][j] = next;
					newMap[1][j] = next;
				}
				if (j == 0) {
					newMap[0][j] = next + Math.max(origin[0][j], origin[0][j + 1]);
					newMap[1][j] = next + Math.min(origin[1][j], origin[1][j + 1]);
				} else if (j == 2) {
					newMap[0][j] = next + Math.max(origin[0][j - 1], origin[0][j]);
					newMap[1][j] = next + Math.min(origin[1][j - 1], origin[1][j]);
				} else {
					int firstMax = Math.max(origin[0][j - 1], origin[0][j]);
					int firstMin = Math.min(origin[1][j - 1], origin[1][j]);
					
					newMap[0][j] = next + Math.max(firstMax, origin[0][j + 1]);
					newMap[1][j] = next + Math.min(firstMin, origin[1][j + 1]);
				}
			}
			
			for (int idx = 0; idx < 3; idx++) {
				origin[0][idx] = newMap[0][idx];
				origin[1][idx] = newMap[1][idx];
			}
		}

		minResult = Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++) {
			maxResult = Math.max(maxResult, origin[0][i]);
			minResult = Math.min(minResult, origin[1][i]);
		}

		bw.write(maxResult + " " + minResult + "\n");

		br.close();
		bw.close();
	}
}
