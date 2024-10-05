import java.io.*;
import java.util.*;

public class Main {
	static String S, T;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		S = br.readLine();
		T = br.readLine();
		run(T);
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}
	private static void run(String cur) {
		if (cur.equals(S)) {
			answer = 1;
			return;
		}
		
		else if (cur.length() == S.length()) {
			return;
		}
		
		char last = cur.charAt(cur.length() - 1);
		//마지막이 A일 경우 A빼고
		if (last == 'A') {
			run(cur.substring(0, cur.length() - 1));
		} 
		
		//첫번째가 B일경우, 뒤집고 B 빼기 -> B는 넣고 뒤집기 때문에 가장 앞에 위치한 것이 넣은 경우
		if (cur.charAt(0) == 'B'){
			StringBuffer sb = new StringBuffer(cur);
			String newStr = sb.reverse().toString();
			newStr = newStr.substring(0, cur.length() - 1);

			run(newStr);
		}
		
	}
}
