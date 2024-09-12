
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] T, P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		T = new int[N+1];
		P = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i+1] = Integer.parseInt(st.nextToken());
			P[i+1] = Integer.parseInt(st.nextToken());
		}
		
		answer = 0;
		
		run(1, 0);
		
		bw.write(answer + "\n");
		bw.close();
		br.close();
	}

	private static void run(int day, int cnt) {
//		System.out.println(day + " " + cnt);
		if (day >= N) {
			if (day == N && T[day] == 1) cnt += P[day];
			answer = Math.max(answer, cnt);
			return;
		}
		
		if (day + T[day]-1 < N+1) run(day + T[day], cnt + P[day]);
		
		run(day+1, cnt);
		
	}

}
