import java.io.*;
import java.util.*;

public class Main {
    static int N, M, startX, startY, answer;
    static String[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new String[N][M];

        for (int i = 0; i < N; i++) {
            String[] nextLine = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                String next = nextLine[j];
                board[i][j] = next;

                if (next.equals("I")){
                    startX = i;
                    startY = j;
                }
            }
        }

        run();

        if (answer == 0) bw.write("TT\n");
        else bw.write(answer + "\n");

        br.close();
        bw.close();
    }

    public static void run() {
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if (board[cur[0]][cur[1]].equals("P")) {
                answer++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < N && ny >=0 && ny < M
                && !visited[nx][ny]
                && !board[nx][ny].equals("X")){
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }



        }


    }
}
