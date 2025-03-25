import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int cnt = Integer.bitCount(N);
        int ans = 0;
        while (cnt > K) {
            N++;
            ans++;
            cnt = Integer.bitCount(N);

        }
        bw.write(ans + "\n");

        br.close();
        bw.close();
    }
}
