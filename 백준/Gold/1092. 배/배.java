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

		if (crane.get(0) < box.get(0)) return;
		
		//시작점부터 무게 비교
		int craneIdx = 0;
		int boxIdx = 0;
		
		while (box.size() != 0) {
			
			while (craneIdx < crane.size()) {
//				System.out.println("crane " + craneIdx);
//				System.out.println("box " + boxIdx);
				if (crane.get(craneIdx) >= box.get(boxIdx)) {
					box.remove(boxIdx);
					craneIdx++;
				} else {
					boxIdx++;
				}
				
				if (box.size() == 0) break;
				if (boxIdx >= box.size()) {
					boxIdx = 0;
					break;
				}
					
				
			}
			craneIdx = 0;
			boxIdx = 0;
			answer++;
		}
		
		
	}

}
