import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] origin;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		origin = new int[N];
		for (int i = 0; i < N; i++) {
			origin[i] = i+1;
		}
		
		comb( 0, new int[M], new boolean[N], bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb( int k, int[] sel, boolean[] visited, BufferedWriter bw )  throws IOException {
		
		if (sel.length == k) {
			for (int i = 0; i < k; i++) {
				bw.write(sel[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for (int i = 0; i < origin.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[k] = origin[i];
				comb(k+1, sel, visited, bw);
				visited[i] = false;
			}
		}
	}

}
