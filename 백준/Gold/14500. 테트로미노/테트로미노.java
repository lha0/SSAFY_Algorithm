import java.io.*;
import java.util.*;

public class Main {
    static int N, M, maxSum;
    static int[][] board;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    //ㅓ, ㅏ, ㅗ, ㅜ
    static int[][] exceptX = {{-1, 0, 1}, {-1, 0, 1}, {0, -1, 0}, {0, 1, 0}};
    static int[][] exceptY = {{0, -1, 0}, {0, 1, 0}, {-1, 0, 1}, {-1, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                run(i, j, visited, board[i][j], 1);
                visited[i][j] = false;

                excFun(i, j, board[i][j]);
            }
        }

        bw.write(maxSum + "\n");

        br.close();
        bw.close();
    }

    public static void run(int x, int y, boolean[][] visited, int sum, int cnt) {
        if (cnt == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextX < N && nextY < M && nextY >= 0
                    && !visited[nextX][nextY]) {

                if (cnt + 1 == 4 && maxSum >= sum + board[nextX][nextY]) {
                    continue;
                } else if (cnt+1 == 4 && maxSum < sum + board[nextX][nextY]) {
                    maxSum = sum + board[nextX][nextY];
                    continue;
                } else {
                    visited[nextX][nextY] = true;
                    run(nextX, nextY, visited, sum + board[nextX][nextY], cnt + 1);
                    visited[nextX][nextY] = false;
                }
            }
        }
    }

    public static void excFun(int x, int y, int sum) {
        //ㅏ, ㅓ, ㅗ, ㅜ
        for (int i = 0; i < 4; i++) {
            int firstX = x + exceptX[i][0];
            int firstY = y + exceptY[i][0];
            if (firstX < 0 || firstX >= N || firstY < 0 || firstY >= M) continue;

            int secondX = x + exceptX[i][1];
            int secondY = y + exceptY[i][1];
            if (secondX < 0 || secondX >= N || secondY < 0 || secondY >= M) continue;

            int thirdX = x + exceptX[i][2];
            int thirdY = y + exceptY[i][2];
            if (thirdX < 0 || thirdX >= N || thirdY < 0 || thirdY >= M) continue;

            int exceptSum = sum + board[firstX][firstY] + board[secondX][secondY] + board[thirdX][thirdY];

            maxSum = Math.max(maxSum, exceptSum);

        }
    }


}
