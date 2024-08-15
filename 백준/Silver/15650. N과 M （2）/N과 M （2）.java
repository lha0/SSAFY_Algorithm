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
		
		int[] sel = new int[M];
		comb(0, 0, sel, bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb( int idx, int k, int[] sel, BufferedWriter bw )  throws IOException {
		if (k == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				bw.write(sel[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		if (idx == origin.length) return;
		
		sel[k] = origin[idx];
		comb(idx+1, k+1, sel, bw);
		comb(idx+1, k, sel, bw);
		
	}

}
