import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, visited;
    static Deque<int[]> needVisit = new ArrayDeque<>();
    static PriorityQueue<Info> canEat = new PriorityQueue<>(new InfoComparator());

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int min = 0;

    public static class Info {
        int x, y, size, eat, time;

        public Info(int x, int y, int size, int eat, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eat = eat;
            this.time = time;
        }
    }

    public static class InfoComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            if (o1.time == o2.time) {
                if (o1.x == o2.x) return o1.y - o2.y;
                else return o1.x - o2.x;
            }
            return o1.time - o2.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        visited = new int[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
                if (map[i][j] == 9) {
                    needVisit.add(new int[]{i, j, 2, 0, 0});
                    visited[i][j] = 1;
                    map[i][j] = 0;
                }
            }
        }

        while (!needVisit.isEmpty()) {
            canEat = new PriorityQueue<>(new InfoComparator());
            bfs();

            if (!canEat.isEmpty()) {
                Info e = canEat.poll();
                int eX = e.x;
                int eY = e.y;
                int eEat = e.eat;
                int eSize = e.size;
                int eTime = e.time;

                map[eX][eY] = 0;
                eEat++;
                if (eEat == eSize) {
                    eSize++;
                    eEat = 0;
                }

                min += eTime;
                needVisit.clear();
                needVisit.add(new int[]{eX, eY, eSize, eEat, 0});
                visited = new int[N][N];
                visited[eX][eY] = 1;
            } else {
                break;
            }
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        while (!needVisit.isEmpty()) {
            int[] coo = needVisit.poll();
            int x = coo[0];
            int y = coo[1];
            int size = coo[2];
            int eat = coo[3];
            int time = coo[4];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] == 0 && map[nx][ny] <= size) {
                    visited[nx][ny] = visited[x][y] + 1;
                    if (map[nx][ny] != 0 && map[nx][ny] < size) {
                        canEat.add(new Info(nx, ny, size, eat, time + 1));
                    }
                    needVisit.add(new int[]{nx, ny, size, eat, time + 1});
                }
            }
        }
    }
}
