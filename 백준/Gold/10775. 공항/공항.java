import java.io.*;
import java.util.*;

public class Main {
    static int answer, G, P;
    static int[] plane;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        G = Integer.parseInt(br.readLine().split(" ")[0]);
        P = Integer.parseInt(br.readLine().split(" ")[0]);

        plane = new int[P];
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine().split(" ")[0]);
            plane[i] = g;
        }

        run();

        bw.write(answer + "\n");

        br.close();
        bw.close();
    }

    public static void run() {
        int[] gate = new int[G+1];
        //union-find 초기화
        for (int i = 0; i < gate.length; i++) {
            gate[i] = i;
        }

        for (int i = 0; i < plane.length; i++) {
            int curPlane = plane[i];
            int parent = find(gate, curPlane);
            
            if (parent-1 < 0) return;
            
            //게이트 할당
            //원하는 게이트에 비행기가 있다면, 그 앞 게이트에 할당
            if (union(gate, parent, parent-1)) {
                answer++;
            } else {
                return;
            }
        }
    }

    public static int find(int[] gate, int a) {
        if (gate[a] == a) return a;
        return gate[a] = find(gate, gate[a]);
    }

    public static boolean union(int[] gate, int a, int b) {
        int parentA = find(gate, a);
        int parentB = find(gate, b);

        if (parentA == parentB) return false;
        else {
            if (parentA < parentB) {
                gate[parentB] = parentA;
            } else {
                gate[parentA] = parentB;
            }
            return true;
        }
    }

}
