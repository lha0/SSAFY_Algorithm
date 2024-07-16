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
			String[] inputs = br.readLine().split(" ");
			int fH = Integer.parseInt(inputs[0]);
			int fM = Integer.parseInt(inputs[1]);
			int sH = Integer.parseInt(inputs[2]);
			int sM = Integer.parseInt(inputs[3]);
			
			int resultH = fH + sH;
			int resultM = fM + sM;
			
			if (resultM > 59) {
				resultM -= 60;
				resultH += 1;
			}
			
			if (resultH > 12) {
				resultH -= 12;
			}
			
			System.out.println("#" + t + " " + resultH + " " + resultM);
		}
		

	}
	

}
