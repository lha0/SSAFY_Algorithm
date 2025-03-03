import java.io.*;
import java.util.*;

public class Main {
    static int T, n, ans1, ans2;
    static boolean[] isPrime, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        visited = new boolean[10005];
        //소수 배열 초기화
        isPrime = new boolean[10005];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            ans1 = 0;
            ans2 = 0;

            find_prime();

            find_sum();

            bw.write(ans1 + " " + ans2 + "\n");
        }

        br.close();
        bw.close();
    }

    public static void find_prime() {
//        System.out.println("n is " + n);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i] && !visited[i]) {
                visited[i] = true;

                //배수들 모두 제거
                for (int mult = 2; mult * i <= 10000; mult++) {
                    isPrime[mult * i] = false;
                    visited[mult* i] = true;
                }
            }
        }

//        System.out.println("is Prime is ");
//        for (int i = 0; i <= n; i++) {
//            System.out.print(isPrime[i] + " ");
//        }
//        System.out.println("");

    }

    public static void find_sum() {
        int i = n/2;
        int j = n/2;

        while(true) {
            if (isPrime[i] && isPrime[j]) {
                ans1 = Math.min(i, j);
                ans2 = Math.max(i, j);
                break;
            }
            i--;
            j++;
        }
    }
}
