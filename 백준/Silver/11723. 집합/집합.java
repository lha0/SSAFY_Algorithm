import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int M = Integer.parseInt(br.readLine());
		 int[] S = new int[21];
		for (int i = 0; i < M; i++) {
			String[] inputs = br.readLine().split(" ");
			String op = inputs[0];
			int num = 0;
			if (!op.equals("all") && !op.equals("empty")) 
				num = Integer.parseInt(inputs[1]);
		    
		    switch(op) {
		    case "add" :
		    	if (S[num] != 0) continue;
		    	else S[num] = num;
		    	break;
		    	
		    case "remove" :
		    	if (S[num] == 0) continue;
		    	else S[num] = 0;
		    	break;
		    	
		    case "check" :
		    	if (S[num] != 0) bw.write(1 + "\n");
		    	else bw.write(0 + "\n");
		    	break;
		    	
		    case "toggle" :
		    	if (S[num] != 0) S[num] = 0;
		    	else S[num] = num;
		    	break;
		    	
		    case "all" :
		    	for (int idx = 1; idx <= 20; idx++) {
		    		S[idx] = idx;
		    	}
		    	break;
		    	
		    case "empty" :
		    	for (int idx = 1; idx <= 20; idx++) {
		    		S[idx] = 0;
		    	}
		    	break;
		    }
		}
		
		br.close();
		bw.close();
	}
}
