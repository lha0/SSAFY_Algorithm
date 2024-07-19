import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static String[] op = {"+", "-", "*" , "/"};
	static boolean[] isSelect;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        
        String[] numStr = br.readLine().split(" ");
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
        	num[i] = Integer.parseInt(numStr[i]);
        }
        
        String[] opStr = br.readLine().split(" ");
        int[] operator = new int[4];
        
        for (int i = 0; i < 4; i++) {
        	operator[i] = Integer.parseInt(opStr[i]);
        }
        
        ArrayList<String> opArr = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
        	for (int j = 1; j <= operator[i]; j++) {
        		opArr.add(op[i]);
        	}
        }
        String[] opStrArr = opArr.toArray(new String[N-1]);
        String[] sel =new String[N-1];
        isSelect = new boolean[N-1];
        
        permutation(num, opStrArr, sel, 0);
		
		/* 출력 */
		bw.write(max + "\n");
		bw.write(min + "\n");
        bw.flush();
        br.close();
        bw.close();

	}
	
	public static void permutation(int[] num, String[] opStrArr, String[] sel, int depth) {
		if (depth == sel.length) {
			run(num, sel);
			return;
		}
		
		for (int i = 0; i < opStrArr.length; i++) {
			if (isSelect[i] != true) {
				isSelect[i] = true;
				sel[depth] = opStrArr[i];
				permutation(num, opStrArr, sel, depth+1);
				isSelect[i] = false;
			}
		}
	}

	public static void run(int[] num, String[] sel) {
		int sum = num[0];
		
		//System.out.println(Arrays.toString(sel) + " " + sum);
		for (int i = 0; i < sel.length; i++) {
			switch(sel[i]) {
				case "+":
					sum = sum + num[i+1];
					break;
				case "-":
					sum = sum - num[i+1];
					break;
				case "*":
					sum = sum * num[i+1];
					break;
				default:
					sum = sum / num[i+1];
					break;
			}	
			//System.out.println(sum);
		}
		
		
		min = Math.min(min, sum);
		max = Math.max(max, sum);
	}
	
	
}