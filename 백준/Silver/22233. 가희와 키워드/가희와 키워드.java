import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> sets = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String inputs = br.readLine();

            sets.add(inputs);
        }

        for (int j = 0; j < M; j++) {
            String[] inputs = br.readLine().split(",");

            for (int k = 0; k < inputs.length; k++) {
                String cur = inputs[k];
                sets.remove(cur);
            }

            bw.write(sets.size() + "\n");
        }



        br.close();
        bw.close();
    }
}
