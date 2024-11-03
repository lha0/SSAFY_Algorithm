import java.io.*;
import java.util.*;

public class Main {
	static int  T, N, M;
	static long answer;
	static long[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		A = new long[N];
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(stringTokenizer.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new long[M];
		stringTokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Long.parseLong(stringTokenizer.nextToken());
		}
		
		run();
		
		bw.write(answer + "\n");
		
		br.close();
		bw.close();
		
	}

	private static void run() {
		//ASum
		List<Long> Asum = new ArrayList<>();
		long before = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (i == j) Asum.add((long) A[i]);
				else Asum.add(before +  (long)A[j]);
				
				before = Asum.get(Asum.size() - 1);
			}
		}
		
		List<Long> Bsum = new ArrayList<>();
		before = 0;
		for (int i = 0; i < M; i++) {
			for (int j = i; j < M; j++) {
				if (i == j) Bsum.add((long) B[i]);
				else Bsum.add(before +  (long)B[j]);
				
				before = Bsum.get(Bsum.size() - 1);
			}
		}
		
		Collections.sort(Asum);
		Collections.sort(Bsum);
		
//		System.out.println(Asum);
//		System.out.println(Bsum);
//		
		//two pointer
		int Astart = 0;
		int Bend = Bsum.size() - 1;
		
		
		while(Astart < Asum.size() && Bend > -1) {
			
//			System.out.println("Astart " + Astart);
//			System.out.println("Bend " + Bend);

			long sum = 0;
			long aElem = Asum.get(Astart);
			long bElem = Bsum.get(Bend);
			sum = aElem + bElem;
			
//			System.out.println("sum " + sum);
			
			if (sum == T) {
				long AElemNum = 0;
				long BElemNum = 0;
				
				while(Astart < Asum.size() && Asum.get(Astart) == aElem) {
					Astart++;
					AElemNum++;	
				}
				
				while(Bend > -1 && Bsum.get(Bend) == bElem) {
					Bend--;
					BElemNum++;
				}

//				System.out.println("AElem " + AElemNum);
//				System.out.println("BElem " + BElemNum);
				answer += AElemNum * BElemNum;
			}
			
			if (sum > T) {
				Bend--;
			}
			else if (sum < T) {
				Astart++;
			}
		}
		
	}

}
