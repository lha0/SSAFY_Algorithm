import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String> hashmap = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String site = st.nextToken();
            String pwd = st.nextToken();

            hashmap.put(site, pwd);
        }

        for (int i = 0; i < M; i++) {
            String findSite = br.readLine().split(" ")[0];

            String answer = hashmap.get(findSite).toString();

            bw.write(answer + "\n");
        }

        br.close();
        bw.close();
    }
}
