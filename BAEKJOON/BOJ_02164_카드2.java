/*
 * BAEKJOON 02164. 카드2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_02164_카드2 {

	public static void main(String[] args) throws Exception {
		// File I/O
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 카드 수
		Queue<Integer> q = new LinkedList<Integer>();
		
		// 1부터 입력한 카드의 개수까지 Queue q에 저장
		for(int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		while(q.size() != 1) {
			// 가장 위의 카드 버림
			q.poll();
			
			// 가장 위의 카드를 가장 뒤로 보냄
			q.offer(q.poll());
		}

		sb.append(String.format("%d\n", q.poll()));
			
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
