import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] arr;
	static int[] op;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(sts.nextToken());
			}
		}
		
		//연산 입력
		op = new int[R];
		String[] inputs = br.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			op[i] = Integer.parseInt(inputs[i]);
		}

		int[][] result = turn();
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				bw.write(result[i][j] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int[][] turn() {
		int[][] copyArr = new int[N][M];
		for (int i = 0; i < copyArr.length; i++) {
			copyArr[i] = arr[i].clone();
		}
		
		for (int r = 0; r < R; r++) {
			switch(op[r]) {
			case 1:
				copyArr = updown(copyArr);
				break;
			case 2:
				copyArr = leftright(copyArr);
				break;
			case 3:
				copyArr = rightTurn(copyArr);
				break;
			case 4:
				copyArr = leftTurn(copyArr);
				break;
			case 5: 
				copyArr = splitRight(copyArr);
				break;
			case 6: 
				copyArr = splitLeft(copyArr);
				break;
			default: break;
			}
		}
		
		return copyArr;
	}

	private static int[][] splitLeft(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//1사분면
				if (i < width / 2 && j < height / 2)  newArr[i+width/2][j] = copyArr[i][j];
				//2사분면
				else if (i < width / 2 && j >= height/2)	newArr[i][j - height/2] = copyArr[i][j];
				//3사분면
				else if (i >= width/2 && j >= height/2) newArr[i - width/2][j] = copyArr[i][j];
				//4사분면
				else newArr[i][j+height/2] = copyArr[i][j];
			}
		}
		
		int[][] resultArr = new int[width][height];
		for (int i = 0; i < newArr.length; i++) {
			resultArr[i] = newArr[i].clone();
		}
		
		return resultArr;
	}

	private static int[][] splitRight(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//1사분면
				if (i < width / 2 && j < height / 2)  newArr[i][j+height/2] = copyArr[i][j];
				//2사분면
				else if (i < width / 2 && j >= height/2)	newArr[i+width/2][j] = copyArr[i][j];
				//3사분면
				else if (i >= width/2 && j >= height/2) newArr[i][j - height/2] = copyArr[i][j];
				//4사분면
				else newArr[i - width/2][j] = copyArr[i][j];
			}
		}
		
		int[][] resultArr = new int[width][height];
		for (int i = 0; i < newArr.length; i++) {
			resultArr[i] = newArr[i].clone();
		}
		
		return resultArr;
	}

	//왼쪽 회전
	private static int[][] leftTurn(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[height][width];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				newArr[height-1-j][i] = copyArr[i][j];
			}
		}
		
		int[][] resultArr = new int[height][width];
		for (int i = 0; i < newArr.length; i++) {
			resultArr[i] = newArr[i].clone();
		}
		
		return resultArr;
	}

	//오른쪽 90도
	private static int[][] rightTurn(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[height][width];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				newArr[j][width-1-i] = copyArr[i][j];
			}
		}
		
		int[][] resultArr = new int[height][width];
		for (int i = 0; i < newArr.length; i++) {
			resultArr[i] = newArr[i].clone();
		}
		
		return resultArr;
	}

	//좌우 반전
	private static int[][] leftright(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height/2; j++) {
				newArr[i][j] = copyArr[i][height-1-j];
				newArr[i][height-1-j] = copyArr[i][j];
			}
		}
		
		for (int i = 0; i < width; i++) {
			copyArr[i] = newArr[i].clone();
		}
		
		return copyArr;
	}

	//상하반전
	private static int[][] updown(int[][] copyArr) {
		int width = copyArr.length;
		int height = copyArr[0].length;
		
		int[][] newArr = new int[width][height];
		for (int i = 0; i < width/2; i++) {
			newArr[i] = copyArr[width-1-i];
			newArr[width-1-i] = copyArr[i];
		}
		
		for (int i = 0; i < width; i++) {
			copyArr[i] = newArr[i].clone();
		}
		
		return copyArr;
	}
}