import java.io.*;
import java.util.*;

public class Main
{
    static int w, h;
    static int[][] land;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] inputs = br.readLine().split(" ");
            w = Integer.parseInt(inputs[0]);
            h = Integer.parseInt(inputs[1]);
            if (w == 0 && h == 0) break;

            else {
                land = new int[h][w];
                for (int i = 0; i < h; i++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < w; j++) {
                        land[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int answer = 0;
                boolean[][] visited = new boolean[h][w];
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (!visited[i][j] && land[i][j] == 1) {
                            visited[i][j] = true;
                            dfs(i, j, visited);                   
                            answer++;
                        }

                    }
                }

                bw.write(answer + "\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int x, int y, boolean[][] visited){
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny] && land[nx][ny] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny, visited);
            }
        }

    }
}
