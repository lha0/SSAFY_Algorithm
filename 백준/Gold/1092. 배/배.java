import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer = -1;
	static List<Integer> crane, box;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().split(" ")[0]);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        crane = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
        	crane.add(Integer.parseInt(st.nextToken())); 
        }
        
        M = Integer.parseInt(br.readLine().split(" ")[0]);
        box = new ArrayList<Integer>();
        StringTokenizer sts = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
        	box.add(Integer.parseInt(sts.nextToken())); 
        }
        
        //reverse 정렬
        Collections.sort(crane, Comparator.reverseOrder());
        Collections.sort(box, Comparator.reverseOrder());
        
        run();
        
        if (answer != -1) answer++;
        
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
	}

	private static void run() {
		// 박스 최대값이 크레인 최대값보다 크면, 바로 return
		if (crane.get(0) < box.get(0)) return;
		
		int craneIdx = 0;
		int boxIdx = 0;
		
		// 박스를 전체 다 제거할 때까지 반복
		while (box.size() != 0) {
			while (craneIdx < crane.size()) {
				// 들 수 있으면, 해당 박스 제거 / 다음 크레인으로 이동
				if (crane.get(craneIdx) >= box.get(boxIdx)) {
					box.remove(boxIdx);
					craneIdx++;
				}
				//들 수 없으면, 다음 박스로 이동
				else {
					boxIdx++;
				}
				
				if (box.size() == 0) break;
				if (boxIdx >= box.size()) {
					boxIdx = 0;
					break;
				}	
			}
			
			// 크레인 전체를 다 돌았으면 1분 끝, 다시 처음부터 진행
			craneIdx = 0;
			boxIdx = 0;
			answer++;
		}
	}
}
