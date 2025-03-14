import java.io.*;
import java.util.*;

public class Main {
    static int N, M, sum;
    static List<Node>[] adjList;

    static class Node implements Comparable<Node> {
        int elem, weight;

        Node (int elem, int weight) {
            this.elem = elem;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        run();

        bw.write(sum + "\n");

        br.close();
        bw.close();
    }

    public static void run() {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.elem]) continue;

            visited[cur.elem] = true;
            sum += cur.weight;

            for (Node next : adjList[cur.elem]) {
                if (!visited[next.elem] && next.weight < dist[next.elem]) {
                    dist[next.elem] = next.weight;
                    pq.offer(new Node(next.elem, next.weight));
                }
            }
        }
    }
}
