import java.util.*;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine().split(" ")[0]);
        int[] list = new int[N];
        
        for (int i = 0; i < N; i++) {
        	list[i] = Integer.parseInt(br.readLine().split(" ")[0]);
        }
        
        Arrays.sort(list);
        
        for (int i = 0; i < list.length; i++) {
        	bw.write(list[i] + "\n");
        }
        
        br.close();
        bw.close();
	}

}
