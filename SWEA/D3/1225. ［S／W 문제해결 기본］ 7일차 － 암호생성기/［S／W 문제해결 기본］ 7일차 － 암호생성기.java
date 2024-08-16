import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int[] number;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int tc = 1; tc <= 10; tc++) {
			T = Integer.parseInt(br.readLine().split(" ")[0]);
			
			String[] inputs = br.readLine().split(" ");
			number = new int[8];
			for (int i = 0; i < 8; i++) {
				number[i] = Integer.parseInt(inputs[i]);
			}
			
			run();
			
			bw.write("#" + tc + " ");
			for (int i = 0; i < number.length; i++) {
				bw.write(number[i] + " ");
			}
			bw.write("\n");
			
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void run() {
		
		L:for (int cycle = 0; ; cycle++) {
			for (int add = 1; add <= 5; add++) {
				number[0] -= add;
				
				int moveNum = number[0];
				for (int i = 1; i <number.length ; i++) {
					number[i-1] = number[i];
				}
				
				
				if (moveNum <= 0) {
					number[number.length-1] = 0;
					break L;
				} else {
					number[number.length-1] = moveNum;
				}
			}
		}	
	}
}
