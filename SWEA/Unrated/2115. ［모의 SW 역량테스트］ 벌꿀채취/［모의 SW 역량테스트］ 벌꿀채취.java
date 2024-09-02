import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, C, max, answer = 0, maxAnswer;
	static int[][] honeyMap;
	static int[] rows;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honeyMap = new int[N][N];
			for (int i =0 ; i < N; i++) {
				StringTokenizer sts = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeyMap[i][j] = Integer.parseInt(sts.nextToken());
				}
			}
			
			rows = new int[N];
			for (int i =0 ; i < N; i++) {
				rows[i] = i;
			}
			
			maxAnswer = 0;
			
			run(0, 0, new int[2]);
			
			bw.write("#" + tc + " " + maxAnswer + "\n");
			
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int idx, int k, int[] sel) {
		if (k == sel.length) {
//			System.out.println("############ two workers " + Arrays.toString(sel) + " #############");
			get(sel);
			return;
		} 
		
		if (idx == N) {
			return;
		}
		
		sel[k] = rows[idx];
		run(idx+1, k+1, sel);
		run(idx+1, k, sel);
	}

	private static void get(int[] sel) {
		//한 명의 일꾼당
		answer = 0;
		for (int i =0 ; i < sel.length; i++) {
			int worker = sel[i]; //한 일꿀이 할 행 번호
			
			max = 0;
			
//			System.out.println("----------- one worker ----------- " + worker);
			
			choose(worker); // 부분조합으로 연속된 벌통 선택
			
//			System.out.println("일꾼 한명의 값 " + max);
			
			answer += max;
		}
		
//		System.out.println("한 조합 당 결과 : " + answer + " " + maxAnswer);
		
		maxAnswer = Math.max(answer, maxAnswer);
	}

	private static void choose(int worker) {
		for (int i = 0; i <= N-M; i++) {
			cal(worker, i, i, new boolean[N]);
		}
	}

	private static void cal(int worker, int idx, int init, boolean[] sel) {
		if (idx == sel.length) {
			
			int sum = 0;
			int each = 0;
			for (int i = init; i < init + M; i++) {
				if (sel[i]) {
					each += honeyMap[worker][i];
					sum += honeyMap[worker][i] * honeyMap[worker][i];
				}
			}
			
			if (each <= C) 
			{
				max = Math.max(sum, max);
			}
			return;
			
		}
		
		sel[idx] = true;
		cal(worker, idx+1, init, sel);
		sel[idx] = false;
		cal(worker, idx+1, init, sel);
		
	}

}
