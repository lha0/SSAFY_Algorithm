import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(stringTokenizer.nextToken());
		int W = Integer.parseInt(stringTokenizer.nextToken());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		
		int height = (int) Math.ceil((double)H / (N+1));
		int width = (int) Math.ceil((double)W / (M+1));
		
		bw.write((height * width) + " \n");
		br.close();
		bw.close();
	}

}
