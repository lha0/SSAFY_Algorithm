import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		long[] mem = new long[N+2];
		
		mem[0] = 0;
		mem[1] = 1;
		
		for (int i = 2; i <= N && i > 1; i++) {
			mem[i] = mem[i-1] + mem[i-2]; 
		}
		
		bw.write(mem[N] + "\n");
		
		
		br.close();
		bw.close();
	}

}
