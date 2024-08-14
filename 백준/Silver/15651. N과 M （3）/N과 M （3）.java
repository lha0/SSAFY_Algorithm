import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] origin;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		origin = new int[N];
		for (int i = 0; i < N; i++) {
			origin[i] = i+1;
		}
		
		br.close();
		
		int[] sel = new int[M];
		comb(0, sel, bw);
		
		bw.flush();
		bw.close();
	}
	
	public static void comb(int k, int[] sel, BufferedWriter bw) throws IOException {
		
		if (k == sel.length ) {
			for (int i = 0; i < sel.length; i++) {
				bw.write(sel[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			sel[k] = origin[i];
			comb(k+1, sel, bw);
		}
	}
}
