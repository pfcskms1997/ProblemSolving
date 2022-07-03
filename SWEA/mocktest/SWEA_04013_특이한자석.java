/*
 * SWEA 04013. 특이한 자석
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_04013_특이한자석 {
	
	static int K;
	static int[][] magnets;
	
	static int[] lr = {-1, 1}; // 좌/우로 이동하기 위한 델타 변수

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_04013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = 4; // 자석의 개수
			int M = 8; // 날(톱니)의 개수
			K = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			magnets = new int[N+1][M+1]; // 1번부터 인덱싱
			
			// 4개의 자석 정보 입력(N극: 0, S극: 1)
			StringTokenizer st = null;
			for(int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c <= M; c++) {
					magnets[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int num = Integer.parseInt(st.nextToken()); // 회전시킬 자석의 번호(1 ~ 4)
				int dir = Integer.parseInt(st.nextToken()); // 회전 방향(시계: 1, 반시계: -1)
				rotate(magnets, num, dir);
			}
			
			// 점수 계산
			int ans = 0;
			
			for (int i = 1; i <= N; i++) {
				// 첫 번째 날이 S극일 때에만 점수에 반영
				ans += magnets[i][1] == 1 ? (1 << i-1) : 0;
			}
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	// BFS 방식으로 연쇄적으로 회전시켜야 할 자석을 탐색함
	public static void rotate(int[][] magnets, int num, int dir) {
		Queue<int[]> queue = new LinkedList<int[]>(); // int[]: {자석 번호, 회전 방향}
		boolean[] v = new boolean[magnets.length];

		queue.offer(new int[] {num, dir});
		v[num] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cleft = magnets[cur[0]][7]; // 현재 자석의 왼쪽 날
			int cright = magnets[cur[0]][3]; // 현재 자석의 오른쪽 날
			
			// 자석 회전
			if(cur[1] == 1) { // 시계 방향
				int temp = magnets[cur[0]][8];
				for(int i = 8; i > 1; i--) {
					magnets[cur[0]][i] = magnets[cur[0]][i-1];
				}
				magnets[cur[0]][1] = temp;
			} else { // 반시계 방향
				int temp = magnets[cur[0]][1];
				for(int i = 1; i < 8; i++) {
					magnets[cur[0]][i] = magnets[cur[0]][i+1];
				}
				magnets[cur[0]][8] = temp;
			}
			
			for(int d = 0; d < lr.length; d++) {
				int adj = cur[0] + lr[d]; // 인접한 자석의 번호
				int nd = cur[1] * -1; // 회전 방향 전환
				
				// 유효한 범위 내에서 아직 회전이 되지 않은 자석만 확인
				if(adj >= 1 && adj <= 4 && !v[adj]) {
					if(d == 0 && (cleft != magnets[adj][3])) { // 왼쪽 자석을 확인
						queue.offer(new int[] {adj, nd});
						v[adj] = true;
					}
					else if(d == 1 && (cright != magnets[adj][7])) { // 왼쪽 자석을 확인
						queue.offer(new int[] {adj, nd});
						v[adj] = true;
					}
				}
			}
		}
	}
}