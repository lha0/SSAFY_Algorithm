import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		run(0, new int[M], bw);
		
		bw.close();
		br.close();
	}

	private static void run(int idx, int [] sel, BufferedWriter bw) throws IOException {
		if (sel.length == idx) {
			for (int i = 0; i < sel.length; i++) {
				bw.write(sel[i] + " ");
			}
			bw.write("\n");

			return;
		}
		
		int before = 0;
		for (int i = 0; i < arr.length; i++) {
			if (before == arr[i]) continue;
			else {
				sel[idx] = arr[i];
				before = arr[i];
				run(idx+1, sel, bw);
			}
			
		}
	}

	
}
