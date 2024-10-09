import java.io.*;
import java.util.*;

//자주 나오는거, 단어 길이 긴거, 길이 같으면 사전 순
public class Main {
	static int N, M;
	static Map<String, Integer> maps;
	
	static class Word implements Comparable<Word> {
		String word;
		int count;
		
		Word(String word, int count) {
			this.word = word;
			this.count = count;
		}
		
		@Override
		public int compareTo(Word o) {
			if (this.count == o.count) {
				if (this.word.length() == o.word.length()) {
					return this.word.compareTo(o.word);
				}
				return o.word.length() - this.word.length();
			}
			return o.count - this.count;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maps = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine().split(" ")[0];
			
			if (str.length() < M) continue;
			
			else {
				if (maps.containsKey(str)) maps.put(str, maps.get(str)+1);
				else maps.put(str, 1);
			}
		}
		
		Queue<Word> pq = new PriorityQueue<>();
		maps.forEach((key, value) -> {
			pq.offer(new Word(key, value));
		});
		
		for (int i = 0; i < pq.size(); i++) {
			bw.write(pq.poll().word + "\n");
			i--;
		}
		
		
		br.close();
		bw.close();
	}
}
