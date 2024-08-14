import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
		comb(0, sel, visited, bw);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb( int k, int[] sel, boolean[] visited, BufferedWriter bw )  throws IOException {
		if (k == sel.length) {
			for (int j  = 0; j < sel.length; j++) {
				bw.write(sel[j] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i]= true;
				sel[k] = origin[i];
				comb(k+1, sel, visited, bw);
				visited[i] = false;
			}
		
		}
	}

}
