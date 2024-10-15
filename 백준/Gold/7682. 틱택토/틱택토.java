import java.util.*;
import java.io.*;

public class Main {
	static int Xcnt, Ocnt;
	static String[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine().split(" ")[0];
		while (!input.equals("end")) {
			String[] arr = input.split("");
			Xcnt = 0;
			Ocnt = 0;
			maps = new String[3][3];
			
			for (int i = 0; i < arr.length; i++) {
				String cur = arr[i];

				maps[i / 3][i % 3] = cur;

				if (cur.equals("X"))
					Xcnt++;
				else if (cur.equals("O"))
					Ocnt++;
			}
			
			//두 개 개수 같을 때
			 if (Xcnt == Ocnt) {
				 //아직 더 채울 수 있는 경우 invalid
				if (Xcnt <= 2 && Ocnt <= 2) {
					bw.write("invalid\n");
				}
				else {
					//X랑 O가 둘 다 3줄 채워져있는 경우 invalid
					if ((row("X") && row("O")) || (col("X") && col("O")) || (diagonal("X") && diagonal("O"))) {
						bw.write("invalid\n");
					}
					// O가 다 채워지면서 끝난 경우 valid
					else if (row("O") || col("O") || diagonal("O")) {
						bw.write("valid\n");
					}
					
					else {
						bw.write("invalid\n");
					}
				}	
			} 
			 
			// O가 놓을 차례 
			else if (Xcnt == Ocnt + 1) {
				//O가 다 채워져있는 경우
				if (row("O") || col("O") || diagonal("O")) {
					bw.write("invalid\n");
				} 
				
				//X가 3줄 완성한 경우 valid
				else if (Xcnt >= 3 && (row("X") || col("X") || diagonal("X"))) {
					bw.write("valid\n");
				}
				
				//게임판이 다 채워진 경우
				else if (Xcnt + Ocnt == 9) {
					bw.write("valid\n");
				}

				else bw.write("invalid\n");
			} 
			
			else {
				bw.write("invalid\n");
			}

			input = br.readLine().split(" ")[0];
		}

		br.close();
		bw.close();
	}
	
	//대각선 판별
	private static boolean diagonal(String ST) {
		if (maps[0][0].equals(maps[1][1]) && maps[1][1].equals(maps[2][2]) && maps[2][2].equals(ST)) {
			return true;
		} else if (maps[0][2].equals(maps[1][1]) && maps[1][1].equals(maps[2][0]) && maps[2][0].equals(ST)) {
			return true;
		}
		return false;
	}
	
	//세로 판별
	private static boolean col(String ST) {
		for (int c = 0; c < maps.length; c++) {
			if (maps[0][c].equals(maps[1][c]) && maps[1][c].equals(maps[2][c]) && maps[2][c].equals(ST)) {
				return true;
			}
		}
		return false;
	}
	
	//가로 판별
	private static boolean row(String ST) {
		for (int r = 0; r < maps.length; r++) {
			if (maps[r][0].equals(maps[r][1]) && maps[r][1].equals(maps[r][2]) && maps[r][2].equals(ST)) {
				return true;
			}
		}
		return false;
	}

}
