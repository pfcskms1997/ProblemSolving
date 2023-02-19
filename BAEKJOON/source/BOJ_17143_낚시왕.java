/*
 * BAEKJOON 17143. 낚시왕
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	
	static class Shark {
		int r;
		int c;
		int speed;
		int direction;
		int size;
		
		public Shark(int r, int c, int speed, int direction, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
	
	static int R, C, M;
	static Shark[][] map;
	
	static int[] dr = {0, -1,  1,  0,  0};
	static int[] dc = {0,  0,  0,  1, -1};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 격자판의 세로 크기
			C = Integer.parseInt(st.nextToken()); // 격자판의 가로 크기
			M = Integer.parseInt(st.nextToken()); // 상어의 수
			map = new Shark[R+1][C+1];
			
			// Shark 객체를 생성하고, 상어의 위치에 해당하는 2차원 map에 연결
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()); // 상어의 행 좌표
				int c = Integer.parseInt(st.nextToken()); // 상어의 열 좌표
				int s = Integer.parseInt(st.nextToken()); // 상어의 속도
				int d = Integer.parseInt(st.nextToken()); // 상어의 이동 방향
				int z = Integer.parseInt(st.nextToken()); // 상어의 크기
				
				// 이동 수행시간 단축을 위한 속력을 재계산
				if(d == 1 || d == 2) { // 상, 하
					s %= (R - 1) * 2;
				} else if(d == 3 || d == 4) { // 좌, 우
					s %= (C - 1) * 2;
				}
				
				map[r][c] = new Shark(r, c, s, d, z);
			}
			
			int ans = (M == 0 ? 0 : simulation(map));
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	public static int simulation(Shark[][] map) {
		int totalSize = 0;
		
		// 낚시왕이 오른쪽 열로 한 칸 씩 이동
		for(int c = 1; c <= C; c++) {
			// 상어 잡기
			for(int r = 1; r <= R; r++) {
				if(map[r][c] != null) {
					totalSize += map[r][c].size;
					map[r][c] = null;
					break;
				}
			}
			
			// 상어 이동(경계 넘을 시 방향 반전, 겹치면 큰 상어가 작은 상어 잡아 먹음)
			moveShark(map);
		}
		return totalSize;
	}
	
	public static void moveShark(Shark[][] map) {
		Queue<Shark> queue = new LinkedList<Shark>();
		
		// 남아있는 상어를 queue에 넣고 map을 비움
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				if(map[r][c] != null) {
					queue.offer(map[r][c]);
					map[r][c] = null; // map 초기화
				}
			}
		}
		
		// 상어를 모두 배치할 때까지 반복
		while(!queue.isEmpty()) {
			Shark s = queue.poll();
			
			// 재계산된 speed만큼 상어가 이동
			for(int v = 0; v < s.speed; v++) {
				if(s.direction == 1 && s.r == 1) s.direction = 2;
				else if(s.direction == 2 && s.r == R) s.direction = 1;
				else if(s.direction == 3 && s.c == C) s.direction = 4;
				else if(s.direction == 4 && s.c == 1) s.direction = 3;
				
				s.r = s.r + dr[s.direction];
				s.c = s.c + dc[s.direction];
			}
			
			// map에 상어 배치
			if(map[s.r][s.c] == null) { // 다른 상어가 없는 경우 그 위치에 현재 상어를 넣음
				map[s.r][s.c] = s;
			} else { // 다른 상어가 있는 경우 더 큰 상어가 작은 상어를 잡아먹음
				if(map[s.r][s.c].size < s.size) {
					map[s.r][s.c] = s;
				}
			}
		}
	}
}
