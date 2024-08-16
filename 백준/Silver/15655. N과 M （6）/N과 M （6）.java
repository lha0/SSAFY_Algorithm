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
		
		StringTokenizer sts = new StringTokenizer(br.readLine());
		origin = new int[N];
		for (int i = 0; i < N; i++) {
			origin[i] = Integer.parseInt(sts.nextToken());
		}
		Arrays.sort(origin);
		
		comb( 0, 0, new int[M], bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb(int idx, int k, int[] sel, BufferedWriter bw )  throws IOException {
		if (sel.length == k) {
			for (int i = 0; i < k; i++) {
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
