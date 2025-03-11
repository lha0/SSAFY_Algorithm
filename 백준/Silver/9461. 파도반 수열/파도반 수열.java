// 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12
//f(1) = 1, f(2) = 1, f(3) = 1
//f(n) = f(n-2) + f(n-3)

import java.io.*;

public class Main {
    static long[] numberArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numberArr = new long[105];
        numberArr[1] = 1;
        numberArr[2] = 1;
        numberArr[3] = 1;

        int T = Integer.parseInt(br.readLine().split(" ")[0]);
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().split(" ")[0]);

            for (int i = 4; i <= N; i++) {
                if (numberArr[i] == 0)
                {
                    numberArr[i] = numberArr[i-2] + numberArr[i-3];
                 }
            }

            bw.write(numberArr[N] + "\n");
        }

        br.close();
        bw.close();

    }
}
