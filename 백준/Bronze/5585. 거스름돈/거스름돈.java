import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		int left = 1000 - N;
		
		int[] money = new int[] {500, 100, 50, 10, 5,1 };
		
		int answer = 0;
		for (int i = 0; i < 6 && left > 0; i++) {
			
			if (left / money[i] > 0) {
				int amount  = left / money[i];
				answer += amount ;
				left -= money[i] * amount;
			}
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
