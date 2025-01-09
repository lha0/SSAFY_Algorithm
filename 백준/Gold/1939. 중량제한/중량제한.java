import java.io.*;
import java.util.*;

public class Main {
    static int N, M, START, END;
    static List<Node>[] adjList;

    static class Node implements Comparable<Node>{
        int elem, weight;

        Node (int elem, int weight) {
            this.elem = elem;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return o.weight - this.weight;
        }

        @Override
        public String toString() {
            return "elem " + this.elem + " weight " + this.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, weight));
            adjList[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        int answer = run();

        System.out.println(answer);

        br.close();
        bw.close();
    }

    public static int run() {
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[START] = Integer.MAX_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(START, Integer.MAX_VALUE));

//        System.out.println("초기 dist " + Arrays.toString(dist));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

//            System.out.println("pq poll 시작 " + cur);

            if (cur.elem == END) break;

            if (visited[cur.elem]) continue;

            visited[cur.elem] = true;

            for (Node next : adjList[cur.elem]) {

//                System.out.println("다음 인접 값 " + next);

                if (cur.elem != START && next.weight < dist[cur.elem] && next.weight > dist[next.elem]) {
                    dist[next.elem] = next.weight;
                    pq.add(new Node(next.elem, next.weight));
                }

                else if (cur.elem != START && next.weight >= dist[cur.elem]) {
                    dist[next.elem] = dist[cur.elem];
                    pq.add(new Node(next.elem, next.weight));
                }

                else if (cur.elem == START && next.weight > dist[next.elem]) {
                    dist[next.elem] = next.weight;
                    pq.add(new Node(next.elem, next.weight));
                }

//                System.out.println("dist 끝난 값 " + Arrays.toString(dist));
            }
        }

        return dist[END];

    }

}
