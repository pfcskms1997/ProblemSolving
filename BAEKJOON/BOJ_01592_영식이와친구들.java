/*
 * BAEKJOON 01592. 영식이와 친구들
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_01592_영식이와친구들 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01592.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 자리의 개수
			int M = Integer.parseInt(st.nextToken()); // 게임 종료 조건
			int L = Integer.parseInt(st.nextToken()); // 목표 지점과의 거리
			int cnt = 0;
			
			// List의 앞 뒤로 삽입/제거를 하기 위해 Deque 자료구조 이용
			Deque<int[]> cycle = new LinkedList<int[]>();
			
			// 자리 순서대로 Deque 입력(Deque의 원소는 1차원 배열로 {자리 번호, 공을 받은 횟수}임)
			for(int i = 1; i <= N; i++) {
				// 처음 공을 받는 첫째 자리에만 받은 횟수 1을 입력, 나머지 자리는 0
				cycle.addLast(new int[] {i,  i == 1 ? 1 : 0});
			}
			
			while(true) {
				int[] cur = cycle.peekFirst();
				
				if(cur[1] == M) break;
				
				// 받은 횟수가 짝수인 경우
				if(cur[1] % 2 == 0) {
					// 반시계 방향으로 공 던지기
					for(int i = 0; i < L; i++) {
						cycle.offerFirst(cycle.pollLast());
					}
					cycle.peekFirst()[1]++;
					cnt++;
				}
				// 받은 횟수가 홀수인 경우
				else {
					// 시계 방향으로 공 던지기
					for(int i = 0; i < L; i++) {
						cycle.offerLast(cycle.pollFirst());
					}
					cycle.peekFirst()[1]++;
					cnt++;
				}
			}
			sb.append(String.format("#%d %d\n", test_case, cnt));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
