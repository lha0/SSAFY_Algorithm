
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static Node root;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        String key;
        TreeMap<String, Node> childMaps = new TreeMap<>();

        Node() {
        }

        void insertChild(String childKey) {
            if (!this.childMaps.containsKey(childKey)) {
                this.childMaps.put(childKey, new Node());
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        root = new Node();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            Node cur = root;

            for (int j = 0; j < K; j++) {
                String inputs = st.nextToken();

                cur.insertChild(inputs);

                cur = cur.childMaps.get(inputs);
            }
        }

        print(root, "");

        bw.write(sb + "\n");

        br.close();
        bw.close();
    }

    public static void print(Node cur, String curKey) {
        for (String key : cur.childMaps.keySet()) {
            sb.append(curKey).append(key).append("\n");
            print(cur.childMaps.get(key), curKey+"--");
        }
    }
}
