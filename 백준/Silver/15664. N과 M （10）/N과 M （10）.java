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
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			origin[i] =  Integer.parseInt(input[i]);
		}
		Arrays.sort(origin);
		
		int[] sel = new int[M];
		boolean[] visited = new boolean[N];
		comb(0, 0, sel, visited, bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb(int idx, int k, int[] sel, boolean[] visited, BufferedWriter bw )  throws IOException {
		if (k == sel.length) {
			for (int j  = 0; j < sel.length; j++) {
				bw.write(sel[j] + " ");
			}
			bw.write("\n");
			return;
		}
		
		int before = 0;
		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				if (before != origin[i]) {
					visited[i] = true;
					sel[k] = origin[i];
					before = origin[i];
					comb(i, k+1, sel, visited, bw);
					visited[i] = false;
				}
				
			}
			
				
		}
		
	}

}
