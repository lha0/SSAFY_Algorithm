
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str1 = br.readLine().split("");
		String[] str2 = br.readLine().split("");
		int[][] match = new int[str1.length+1][str2.length+1];
		
		for (int i = 1; i < str1.length+1; i++) {
			for (int j = 1; j < str2.length+1; j++) {
				if (str1[i-1].equals(str2[j-1])) {
					match[i][j] = match[i-1][j-1] + 1;
				} else {
					match[i][j] = Math.max(match[i-1][j], match[i][j-1]);
				}
			}
		}
		
		bw.write(match[str1.length][str2.length] + "\n");
		bw.close();
		br.close();
	}

}
