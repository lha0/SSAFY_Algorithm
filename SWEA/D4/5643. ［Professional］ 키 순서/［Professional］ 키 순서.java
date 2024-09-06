
import java.util.*;
import java.io.*;

//중복된 탐색을 하지 않는 버전

public class Solution {
	static int N, adjMatrix[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생 번호 1번부터 시작
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}

			for (int i = 1; i <= N; i++) {
				adjMatrix[i][0] = -1; // 탐색되지 않은 학생을 나타냄 (후에 탐색이 완료되면 자신보다 큰 학생 수 저장)
			}

			// 각 학생마다 자신보다 큰, 자신보다 작은 학생 각각 탐색
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[i][0] != -1) continue;
				gtDFS(i);
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[0][j] += adjMatrix[i][j];
				}
			}
			
			int ans = 0; // 자신의 키를 알 수 있는 학생 수
			for (int k = 1; k <= N; k++) {
				if (adjMatrix[k][0] + adjMatrix[0][k] == N-1) ans++;
			}
			

			bw.write("#" + tc + " " + ans + "\n");
		}
		in.close();
		bw.close();
	}

	private static void gtDFS(int cur) { // 자신보다 큰 학생따라 탐색
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[cur][i] == 0) continue;
			if (adjMatrix[i][0] == -1) { // 탐색되지 않은 학생이므로 탐색
				gtDFS(i);
			}
			
			//나보다 키가 큰 학생이 탐색을 완료한 상태
			// i보다 키가 큰 학생이 있다면 그 학생들의 정보를 cur에게 반영 (간접 관계를 직접 관계로 경로 압축!)
			if (adjMatrix[i][0] > 0) {
				for (int j = 1; j <= N; j++) {
					if (adjMatrix[i][j] != 0) adjMatrix[cur][j] = 1;
					
				}
			}
		}
		
		adjMatrix[cur][0] = 0; //초깃값이 -1이므로 누적 위해 0으로 초기화
		for (int k = 1; k <= N; k++) {
			adjMatrix[cur][0] += adjMatrix[cur][k];
		}
	}
}
