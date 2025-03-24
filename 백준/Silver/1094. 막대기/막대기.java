import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());

        int count = 1;
        int bar = 64;
        int min = 64;

        while(bar > X) {
            min = min >> 1;
            count++;
            bar -= min;

            if (bar >= X) {
                count--;
            } else {
                bar += min;
            }
        }

        bw.write(count + "\n");


        br.close();
        bw.close();
    }
}
