import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int maxTemp = Math.max(a, b);
			int max = Math.max(maxTemp, c);
			
			if (a == 0 && b == 0 && c == 0) break;
			
			if ((a == max && b+c <= a) || (b == max && a+c <= b) || (c == max && a+b <= c)) {
					bw.write("Invalid\n");
					continue;
			}
			else if (a == b && b == c && c == a) {
				bw.write("Equilateral\n");
				continue;
			} else if ((a == b && b != c) || (a == c && a != b) || (b == c && a != b)) {
				bw.write("Isosceles\n");
				continue;
			} else if ((a == max && b+c > a) || (b == max && a+c > b) || (c == max && a+b > c)) {
				bw.write("Scalene\n");
				continue;
			} else {
				bw.write("Invalid\n");
				continue;
			}
			
		}
		
		bw.close();
		br.close();
	}

}
