/*
 * BAEKJOON 02493. 탑
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_02493_탑 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/input_02493.txt")));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		
		// 시간 초과되는 코드
//		int[] towers = new int[N];
//		int[] result = new int[N];
		
//		// 배열 초기화
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//		for(int i = 0; i < N; i++) {
//			towers[i] = Integer.parseInt(st.nextToken());
//			result[i] = 0;
//		}
		
//		for(int i = N - 1; i > 0; i--) {
//			boolean flag = false;
//			int c = i - 1;
//
//			while(!flag) {
//				if(towers[i] <= towers[c]) {
//					result[i] = c + 1;
//					flag = true;
//				}
//				else {
//					c--;
//					if(c < 0) {
//						result[i] = 0;
//						break;
//					}
//				}
//			}
//		}
		
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				if(stack.peek()[0] >= n) {
					sb.append(stack.peek()[1] + " ");
					break;
				}
				else stack.pop();
			}
			
			// stack이 비어있으면 sb에 0을 append
			if(stack.isEmpty()) sb.append("0 ");
			stack.push(new int[] {n, i + 1}); // {탑의 높이, idx}
		}

		System.out.println(sb);
		in.close();
	}
}
