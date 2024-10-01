import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> list;
	static Node root;
	
	static class Node {
		int data;
		Node leftNode;
		Node rightNode;
		
		Node(int data, Node leftNode, Node rightNode) {
			this.data = data;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", leftNode=" + leftNode + ", rightNode=" + rightNode + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line;
		list = new ArrayList<>();
		while((line = br.readLine()) != null) {
			list.add(Integer.parseInt(line));
		}
		
		root = new Node(list.get(0), null, null);
		makeTree(root, list.get(0), 1, list.size()-1);
		
		postOrder(root);
		
		br.close();
		bw.close();
	}

	private static void postOrder(Node cur) {
//		System.out.println(cur);
		if(cur.leftNode != null) {
			postOrder(cur.leftNode);
		}
		
		if(cur.rightNode != null) {
			postOrder(cur.rightNode);
		}
		
		System.out.println(cur.data);	
	}

	private static void makeTree(Node parent, int rootVal, int start, int end) {
//		System.out.println("root is " + rootVal);
//		System.out.println("start " + start);
//		System.err.println("end " + end);
		if (start > end) return;
		
		//서브트리 찾기
		boolean leftFind = false;
		boolean rightFind = false;
		int leftRootVal = -1;
		int leftRootIdx = 0;
		int rightRootVal = -1;
		int rightRootIdx = 0;
		
		for (int i = start; i <= end && (!leftFind || !rightFind); i++) {
			int cur = list.get(i);
			
			if (cur < rootVal && !leftFind) {
				leftRootIdx = i;
				leftRootVal = cur;
				leftFind = true;
			}
			
			else if (cur > rootVal && !rightFind) {
				rightRootIdx = i;
				rightRootVal = cur;
				rightFind = true;
			}
		}
		
		if (leftRootVal != -1) {
			parent.leftNode = new Node(leftRootVal, null, null);
		} else {
			leftRootIdx = start;
		}
			
		if (rightRootVal != -1) {
			parent.rightNode = new Node(rightRootVal, null, null);
		} else { //right가 없으면
			rightRootIdx = end+1;
		}
			
		
//		System.out.println("parent " + parent);
//		System.out.println("left " + parent.leftNode);
//		System.out.println("right " + parent.rightNode);
		
		makeTree(parent.leftNode, leftRootVal, leftRootIdx+1, rightRootIdx-1);
		
		//if (parent.data != root.data) return;
		
		makeTree(parent.rightNode, rightRootVal, rightRootIdx+1, end);
	}

}
