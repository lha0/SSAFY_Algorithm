import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static String[][] board;
    static String[][] blind_board;

    static boolean[][] visited;
    static boolean[][] blind_visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new String[N][N];
        blind_board = new String[N][N];

        visited = new boolean[N][N];
        blind_visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");

            for (int j = 0; j < N; j++) {
                board[i][j] = inputs[j];

                if (inputs[j].equals("G")) {
                    blind_board[i][j] = "R";
                } else {
                    blind_board[i][j] = inputs[j];
                }
            }
        }

        int answer = 0;
        int blind_answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j, board[i][j], false);
                    answer++;
                }

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!blind_visited[i][j]) {
                    blind_visited[i][j] = true;
                    bfs(i, j, blind_board[i][j], true);
                    blind_answer++;
                }

            }
        }

        bw.write(answer + " " + blind_answer + "\n");

        br.close();
        bw.close();
    }

    static public void bfs(int x, int y, String curType, boolean type) {
        Queue<int[]> needVisit = new ArrayDeque<>();

        needVisit.add(new int[]{x, y});

        while (!needVisit.isEmpty()) {
            int[] cur = needVisit.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!type && nx >= 0 && nx < N && ny >= 0 && ny < N
                && !visited[nx][ny]
                && board[nx][ny].equals(curType)) {
                    visited[nx][ny] = true;
                    needVisit.offer(new int[]{nx, ny});
                }

                //색약
                else if (type && nx >= 0 && nx < N && ny >= 0 && ny < N
                        && !blind_visited[nx][ny]
                        && blind_board[nx][ny].equals(curType)) {
                    blind_visited[nx][ny] = true;
                    needVisit.offer(new int[]{nx, ny});
                }
            }
        }

    }
}