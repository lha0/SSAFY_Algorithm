import java.util.*;
import java.io.*;

public class Solution {
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		T = Integer.parseInt(input[0]);
		
		for (int t = 1; t <= T; t++) {
			String[] inputN = br.readLine().split(" ");
			int N = Integer.parseInt(inputN[0]);
			
			String[] inputArr = br.readLine().split(" ");
			int[] inputNum = new int[N];
			for (int i = 0; i < N; i++) {
				inputNum[i] = Integer.parseInt(inputArr[i]);
			}
			
			Arrays.sort(inputNum);
			
			System.out.print("#" + t + " " );
			for (int i = 0; i < N; i++) {
				System.out.print(inputNum[i] + " ");
			}
			System.out.println();
		}
		

	}
	

}
