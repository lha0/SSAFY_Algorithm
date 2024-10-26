import java.util.*;
import java.io.*;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] tshirts = new int[6];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			tshirts[i] =  Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int tshirtAnswer = 0;
		int pencilAnswer = N / P;
		int pIndivAnswer = N % P;
		
		for (int i = 0; i < tshirts.length; i++) {
			if (tshirts[i] % T == 0) {
				tshirtAnswer += tshirts[i] / T;
			}
			
			else {
				tshirtAnswer += tshirts[i] / T + 1;
			}
		}
		
		bw.write(tshirtAnswer + "\n");
		bw.write(pencilAnswer + " " + pIndivAnswer + "\n");
		
		
		
		br.close();
		bw.close();
	}

}
