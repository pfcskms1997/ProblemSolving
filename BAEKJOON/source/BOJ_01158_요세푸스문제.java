/*
 * BAEKJOON 01158. 요세푸스 문제
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_01158_요세푸스문제 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_01158.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> num = new LinkedList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			num.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int idx = K-1;
		int listSize = N;
		while(!num.isEmpty()) {
			sb.append(num.get(idx)).append(", ");
			num.remove(idx);
			listSize--;
			idx--;
			idx += K;
			
			if(idx >= listSize && !num.isEmpty()) {
				while(true) {
					idx = idx - listSize;
					if(idx < listSize) break;
				}
			}
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		
		System.out.println(sb);
		br.close();
	}
}
