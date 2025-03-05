import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>() ;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int next = Integer.parseInt(st.nextToken());
            pq.add(next);
        }

        int answer = 0;
        int count = N+1;
        while(count-- > 1) {
            int now = pq.poll();
            answer += (count * now);
        }

        bw.write(answer + "\n");

        br.close();
        bw.close();
    }
}
