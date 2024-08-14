import java.io.*;
import java.util.*;

public class Solution {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<String> stack = new Stack<String>();
		
		for (int tc = 1; tc <= 10; tc++) {
			N= Integer.parseInt(br.readLine().split(" ")[0]);
			String[] inputs = br.readLine().split("");
			int possible = 1;
			
			// {, (, [ => stack push
			// 닫는거면 pop 후 비교
			 for (int i = 0; i < inputs.length; i++) {

				if (inputs[i].equals("(") || inputs[i].equals("{") || inputs[i].equals("[") || inputs[i].equals("<")) {
					stack.push(inputs[i]);

				} else {
					if (stack.size() != 0) {
						String item = stack.pop();
						
						switch(inputs[i]) {
						case ")":
							if (!item.equals("(")) {
								possible = 0;
								break;
							}
							break;
						case "}":
							if (!(item.equals("{"))) {
								possible = 0;
								break;
							}
							break;
						case ">":
							if (!(item.equals("<"))) {
								possible = 0;
								break;
							}
							break;
						case "]":
							if (!(item.equals("["))) {
								possible = 0;
								break ;
							}
							break;
						default:
							break;
						}
					}
					
				}
			}
			bw.write("#" + tc + " " + possible + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}

}
