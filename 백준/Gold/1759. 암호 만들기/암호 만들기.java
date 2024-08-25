import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static String[] letter;
	static List<String> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		letter = new String[C];
		StringTokenizer str = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			letter[i] = str.nextToken();
		}
		Arrays.sort(letter);

		run(0, 0, new boolean[C]);

		Collections.sort(answer);

		for (int i = 0; i < answer.size(); i++) {
			bw.write(answer.get(i) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int idx, int k, boolean[] visited) {
		if (k == L) {

			String str = "";
			int com = 0;
			int vow = 0;

			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					str += letter[i];

					if (letter[i].equals("a") || letter[i].equals("e") || letter[i].equals("i") || letter[i].equals("o")
							|| letter[i].equals("u")) {
						vow++;
					} else {
						com++;
					}
				}

			}
			

			// 자음, 모음 개수 확인
			if (com >= 2 && vow >= 1)
				answer.add(str);

			return;
		}

		for (int i = idx; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				run(i + 1, k + 1, visited);
				visited[i] = false;
			}
		}
	}

}
