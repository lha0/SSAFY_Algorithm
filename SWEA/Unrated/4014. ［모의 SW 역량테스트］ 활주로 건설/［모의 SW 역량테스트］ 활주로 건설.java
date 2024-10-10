import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, X, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int i = 0; i < N; i++) {
                if (canBuildRunway(map[i])) answer++; // Row check

                int[] col = new int[N];
                for (int j = 0; j < N; j++) {
                    col[j] = map[j][i];
                }
                if (canBuildRunway(col)) answer++; // Column check
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean canBuildRunway(int[] line) {
        boolean[] slope = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) continue;

            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            if (line[i] > line[i + 1]) { // Downhill
                for (int j = 1; j <= X; j++) {
                    if (i + j >= N || line[i + 1] != line[i + j] || slope[i + j]) return false;
                    slope[i + j] = true;
                }
            } else { // Uphill
                for (int j = 0; j < X; j++) {
                    if (i - j < 0 || line[i] != line[i - j] || slope[i - j]) return false;
                    slope[i - j] = true;
                }
            }
        }

        return true;
    }
}