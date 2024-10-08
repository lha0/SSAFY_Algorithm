import java.io.*;

public class Main {
	static int T, num;
	static int [] mem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for (int tc = 1; tc <= T; tc++) {
			num = Integer.parseInt(br.readLine().split(" ")[0]);
			
			mem = new int[15];
			mem[1] = 1;
			mem[2] = 2;
			mem[3] = 4;
			
			for (int i = 4; i <= num; i++) {
				mem[i] = mem[i-3] + mem[i-2] + mem[i-1];
			}
			
			bw.write(mem[num] + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
