import java.io.*;
import java.util.*;

public class Main {
    static int N, M, F, answer;
    static int[][] maps;
    static Taxi taxi;
    static List<Customer> cList;
    static int[] dx = new int[] {0, 0, 1, -1};
    static int[] dy = new int[] {1, -1, 0, 0};

    static class Taxi {
        int x, y, fuel;

        Taxi(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }
    }

    static class Customer implements Comparable<Customer> {
        int sx, sy, ex, ey, startDist, endDist;

        Customer(int sx, int sy, int ex, int ey, int startDist, int endDist) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.startDist = startDist;
            this.endDist = endDist;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.startDist == o.startDist) {
                if (this.sx == o.sx) {
                    return this.sy - o.sy;
                }
                return this.sx - o.sx;
            }
            return this.startDist - o.startDist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int ts = Integer.parseInt(st.nextToken()) - 1;
        int te = Integer.parseInt(st.nextToken()) - 1;

        taxi = new Taxi(ts, te, F);
        
        cList = new ArrayList<Customer>();
        for (int i =0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            
            Customer customer= new Customer(sx, sy, ex, ey, -1, -1);
            cList.add(customer);
        }
        
        run();
        

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void run() {
        while (!cList.isEmpty()) {
            //최단경로 구하기
//            System.out.println("최단 경로 구하기");
        	int[][] dist = new int[N][N];
        	bfsToStart(dist);
        	
            for (int i =0 ; i < cList.size(); i++) {
                Customer c = cList.get(i);
                
                c.startDist = dist[c.sx][c.sy] - 1;
            }
            
            Collections.sort(cList);
            
            //가장 가까운 손님
            Customer closest = cList.get(0);
  
//            System.out.println("가장 가까운 손님 출발지까지 거리 " + closest.startDist);
//            System.out.println("택시 출발 전 위치 " + taxi.x + " " + taxi.y);
            
            if (closest.startDist < 0) {
            	answer = -1;
            	return;
            }
            
            //이동
            if (move(closest)) {
                cList.remove(0);
                answer = taxi.fuel;
            } else {
            	answer = -1;
                return;
            }
            
//            System.out.println("다 끝난 후 택시 연료 " + taxi.fuel);
            
        }
    }

    private static boolean move(Customer closest) {
        // 연료가 충분한지 체크
        // 승객의 출발지 까지의 연료 = c.dist
        if (taxi.fuel - closest.startDist < 0) return false;
        
        //갈 수 있으면 연료 차감
        taxi.fuel -= closest.startDist;
        
        // taxi 위치를 승객의 위치로 이동
        taxi.x = closest.sx;
        taxi.y = closest.sy;
        
//        System.out.println("출발지까지 이동 후 남은 연료 " + taxi.fuel);
//        System.out.println("출발지까지 이동 후 택시 위치 " + taxi.x + " " + taxi.y);
        
        
        // 해당 위치에서 목적지로의 최단거리
        bfsToEnd(closest);
        
//        System.out.println("가장 가까운 손님 목적지까지 거리 " + closest.endDist);
        
        // 연료가 충분한가
        if (taxi.fuel - closest.endDist < 0) return false;
        
        // 갈 수 있으면 연료 차감
        taxi.fuel -= closest.endDist;
        
        // taxi 위치를 승객의 목적지로 이동
        taxi.x = closest.ex;
        taxi.y = closest.ey;
        
        // 연료 업데이트
        taxi.fuel += closest.endDist * 2;
        
        return true;
    }

    private static void bfsToEnd(Customer c) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        //택시의 지금 위치에서 시작
        queue.offer(new int[] {taxi.x, taxi.y, 0});
        
        int[][] visit = new int[N][N];
        for (int i =0 ; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit[i][j] = maps[i][j];
            }
        }
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[0] == c.ex && cur[1] == c.ey) {
                c.endDist = cur[2];
                return;
            }
            
            for (int i =0 ; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && maps[nx][ny] != 1 && visit[nx][ny] == 0) {
                    queue.offer(new int[] {nx, ny, cur[2] + 1});
                    visit[nx][ny] = 1;
                }
            }
        }
    }

    private static void bfsToStart(int[][] dist) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        //택시의 지금 위치에서 시작
        queue.offer(new int[] {taxi.x, taxi.y, 0});
        dist[taxi.x][taxi.y] = 1;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int i =0 ; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && maps[nx][ny] != 1 && dist[nx][ny] == 0) {
                    queue.offer(new int[] {nx, ny, cur[2] + 1});
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                }
            }
        }
    }

        
    

}
