/*
 * SWEA 01225. 암호생성기
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_01225_암호생성기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_01225.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		int N = 8;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int tc = Integer.parseInt(in.readLine());
			Queue<Integer> q = new LinkedList<Integer>();
			boolean endFlag = false;
			
			// Queue q에 input 입력
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i = 0; i < N; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(!endFlag) {
				// Cycle
				for(int i = 1; i <= 5; i++) {
					int front = q.poll() - i;
					if(front <= 0) {
						front = 0;
						q.offer(front);
						endFlag = true;
						break;
					}
					q.offer(front);
				}
			}
			
			// 결과 출력
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("#%d ", tc));
			for(int i = 0; i < N; i++) {
				sb.append(q.poll() + " ");
			}
			
			System.out.println(sb);
		}
		in.close();
	}
}
