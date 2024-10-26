import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String first = br.readLine();
		String second = br.readLine();
		String last = br.readLine();
		
		String answer = "";
		
		if (!first.equals("Fizz") && !first.equals("Buzz") && !first.equals("FizzBuzz")) {
			int next = Integer.parseInt(first) + 3;
			if (next % 3 == 0 && next % 5 == 0) answer = "FizzBuzz";
			else if (next % 3 == 0) answer = "Fizz";
			else if (next % 5 == 0) answer = "Buzz";
			else answer = next + "";
		}
		
		else if (!second.equals("Fizz") && !second.equals("Buzz") && !second.equals("FizzBuzz"))  {
			int next = Integer.parseInt(second) + 2;
			if (next % 3 == 0 && next % 5 == 0) answer = "FizzBuzz";
			else if (next % 3 == 0) answer = "Fizz";
			else if (next % 5 == 0) answer = "Buzz";
			else answer = next + "";
		}
		
		else if (!last.equals("Fizz") && !last.equals("Buzz") && !last.equals("FizzBuzz"))  {
			int next = Integer.parseInt(last) + 1;
			if (next % 3 == 0 && next % 5 == 0) answer = "FizzBuzz";
			else if (next % 3 == 0) answer = "Fizz";
			else if (next % 5 == 0) answer = "Buzz";
			else answer = next + "";
		}
			
		bw.write(answer + "\n");
		
		br.close();
		bw.close();
	}

}
