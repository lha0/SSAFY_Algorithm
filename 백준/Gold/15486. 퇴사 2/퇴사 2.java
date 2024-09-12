
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] T, P, mem;
	
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
		mem = new int[N+2];
		
//		System.out.println(Arrays.toString(T));
		run();
		
		bw.write(mem[N+1] + "\n");
		bw.close();
		br.close();
	}

	private static void run() {
		for (int i = 1; i <= N; i++) {
			mem[i] = Math.max(mem[i-1], mem[i]);
			if (i + T[i] <= N+1)
				mem[i + T[i]] = Math.max(mem[i + T[i]], mem[i] + P[i]);
			if (i == N) 
				mem[N+1] = Math.max(mem[N], mem[N+1]);
		}
		
//		if (T[N] == 1) mem[N+1] = mem[N] + P[N];
//		else mem[N+1] = mem[N];
		
//		System.out.println(Arrays.toString(mem));
		
	}


}
