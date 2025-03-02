import java.util.*;
import java.io.*;

public class Main {
    static int T, x1, x2, y1, y2, r1, r2, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            answer = 0;

//            double d = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

            int d = (int)(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

            //접점이 무한대 
            if ((x1 == x2) && (y1 == y2) && (r1 == r2)) {
                answer = -1;
            }

            //접점이 2개
            else if ((Math.pow(r1-r2, 2) < d) && (Math.pow(r1 + r2, 2) > d)) {
                answer = 2;
            }

            //접점 1개 
            else if ((d == Math.pow(r1 - r2, 2)) || //내접
                    (d == Math.pow(r1 + r2, 2)) //외접
            ) {
                answer = 1;
            }

            else answer = 0;

            bw.write(answer + "\n");
        }


        br.close();
        bw.close();

    }
}


