import java.io.*;
import java.util.*;

public class Main {
	static int N, C;
	static long[] coord;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		coord = new long[N];
		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(br.readLine().split(" ")[0]);
			coord[i] = next;
		}
		
		Arrays.sort(coord);
		
		run();
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}

	private static void run() {
		long min = 0;
		long max = coord[N-1];
		
		while(min <= max) {
			long mid = (min + max) / 2;
			
			int cnt = 1; //설치한 집 개수
			int lastIdx = 0; //마지막에 설치한 집 인덱스
			for (int i = 1; i < N; i++) {
				if (coord[i] >= coord[lastIdx] + mid) {
					cnt++;
					lastIdx = i;
				}
			}
			
			if (cnt < C) { //거리 줄여야함
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		
		answer = min - 1;
	}
}