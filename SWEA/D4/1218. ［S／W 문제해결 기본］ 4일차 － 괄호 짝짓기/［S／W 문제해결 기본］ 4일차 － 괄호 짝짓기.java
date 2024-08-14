
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int size = sc.nextInt();

			String input = sc.next();
			Stack<Character> st = new Stack<>();

			for (char c : input.toCharArray()) {
				if (c == ')' && st.peek() == '(') {
					st.pop();
				} else if (c == ']' && st.peek() == '[') {
					st.pop();
				} else if (c == '}' && st.peek() == '{') {
					st.pop();
				} else if (c == '>' && st.peek() == '<') {
					st.pop();
				} else {
					st.push(c);
				}

			}
			int result = st.isEmpty() ? 1 : 0;
			System.out.println("#" + tc + " " + result);

		}

	}

}
