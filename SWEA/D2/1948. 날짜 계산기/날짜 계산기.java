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
			int month1 = Integer.parseInt(inputN[0]);
			int day1 = Integer.parseInt(inputN[1]);
			int month2 = Integer.parseInt(inputN[2]);
			int day2 = Integer.parseInt(inputN[3]);
			
			int[] monthday = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			
			int diffDay1 = 0;
			int diffDay2 = 0;
			if (month1 == month2) {
				diffDay1 = day2 - day1 + 1;
			} else {
				diffDay1 = monthday[month1-1] - day1 + 1;
				diffDay2 = day2;
			}
			
			
			
			int diffMonth = 0;
			for (int i = month1 + 1; i < month2; i++) {
				diffMonth += monthday[i-1];
			}
			
			
			int answer = diffDay1 + diffDay2 + diffMonth;
			
			bw.write("#" + t + " " + answer + '\n');
		}
		bw.flush();
		br.close();
		bw.close();
		

	}
	

}
