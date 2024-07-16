import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		T = Integer.parseInt(input[0]);
		
		for (int t = 1; t <= T; t++) {
			String[] inputs = br.readLine().split(" ");
			int in = Integer.parseInt(inputs[0]);
			int[] num = {0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int i = 0; i < money.length; i++) {
				if (in / money[i] > 0) {
					num[i] = in / money[i];
					in = in % money[i];
				} else {
					continue;
				}
			}
			
			
			System.out.println("#" + t );
			for (int i : num) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		

	}
	

}
