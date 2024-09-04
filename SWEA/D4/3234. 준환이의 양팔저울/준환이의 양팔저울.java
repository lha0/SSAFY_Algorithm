import java.io.*;
import java.util.*;

public class Solution {
	static int  answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			int[] origin = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				origin[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			
			run(0, new int[N], new boolean[N], origin);
			
			bw.write("#" + tc + " " + answer + "\n");
		}
		
		bw.close();
		br.close();
	}

	private static void run(int idx, int[] sel, boolean[] visit, int[] origin) {
		//올리는 순서
		if (sel.length == idx) {
			
			// 왼쪽 오른쪽
			comb(0, 0, 0, sel);
			return;
		}
		
		for (int i = 0; i < sel.length; i++) {
			if (!visit[i]) {
				visit[i] = true;
				sel[idx] = origin[i];
				run(idx+1, sel, visit, origin);
				visit[i] = false;
			}
		}
	}

	private static void comb(int idx, int left, int right, int[] chose) {
		if (left < right) return;
		if (idx == chose.length) {
			answer++;
			
			return;
		}
		
		
		// 부분집합을 다 구해서 계산하는게 아니라
		// 넘어갈 때 매개변수에 sum을 더해가면서 바로 가지치기 할 수 있도록
		comb(idx+1, left + chose[idx], right, chose);
		comb(idx+1, left, right+ chose[idx], chose);
	
	}


}
