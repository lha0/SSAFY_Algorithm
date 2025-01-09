import java.io.*;
import java.util.*;

public class Main {
    static int N, M, totalSum, maxElem;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        totalSum = 0;
        maxElem = 0;
        for (int i = 0; i < N; i++) {
            totalSum += arr[i];
            maxElem = Math.max(maxElem, arr[i]);
        }

        int answer = 0;
        if (M == 1) {
            answer = totalSum;
        } else if (M == N) {
            answer = maxElem;
        } else {
            answer = bs();
        }

        bw.write(answer + "\n");

        br.close();
        bw.close();
    }

    public static int bs() {
        int left = maxElem; //제일 큰 값이 용량 최솟값
        int right = totalSum; // 전체 합이 용량 최댓값
        int storage = 0;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            storage = (left + right) / 2;

            int cnt = 0;
            int sum = 0;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < arr.length; i++) {
                //블루레이에 더 들어갈 수 있으면
                if (sum + arr[i] <= storage) {
                    sum += arr[i];
                } else { //못 들어가면
                    max = Math.max(max, sum); //최댓값 저장
                    sum = arr[i];
                    cnt++;
                }

                if (i == arr.length - 1) {
                    max = Math.max(max, sum); //최댓값 저장
                    cnt++;
                }
            }

            if (cnt > M) { //블루레이 갯수가 많다면, 용량을 더 늘린다
                left = storage + 1;
            } else {
                right = storage - 1;
                min = Math.min(min, storage);
            }
        }

        return min;
    }
}
