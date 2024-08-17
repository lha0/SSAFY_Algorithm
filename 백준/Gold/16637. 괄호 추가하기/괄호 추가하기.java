import java.io.*;
import java.util.*;

public class Main {
	static int N, max;
	static List numbers;
	static List operation;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		numbers = new ArrayList<Integer>();
		operation = new ArrayList<String>();
		String[] input = br.readLine().split("");
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				numbers.add(Integer.parseInt(input[i]));

			} else {
				operation.add(input[i]);
			}
		}

		max = Integer.MIN_VALUE;
		int start = (int) numbers.get(0);
		dfs(0, start);

		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int idx, int sum) {
		if (idx >= operation.size()) {
			max = Math.max(sum, max);
			return;
		}
		// 하나의 식을 괄호 안 하고 계산 , 괄호 하고 계산 두 갈래로 나누기
		// 괄호 안 하고
		int result = cal(sum, (int) numbers.get(idx + 1), (String) operation.get(idx));
		dfs(idx + 1, result);

		// 괄호 하고
		if (idx + 1 < operation.size()) {
			// 지금 idx 다음 애들을 괄호 묶기
			int parenResult = cal((int) numbers.get(idx + 1), (int) numbers.get(idx + 2),
					(String) operation.get(idx + 1));
			// 괄호 계산한거랑 지금까지의 sum이랑 계산
			int sumResult = cal(sum, parenResult, (String) operation.get(idx));
			dfs(idx + 2, sumResult);
		}

	}

	public static int cal(int a, int b, String op) {
		int result = 0;
		switch (op) {
		case "+":
			result = a + b;
			break;
		case "-":
			result = a - b;
			break;
		case "*":
			result = a * b;
			break;

		}
		return result;
	}

}
