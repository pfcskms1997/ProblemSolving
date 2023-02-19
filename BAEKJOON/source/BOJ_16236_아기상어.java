/*
 * BAEKJOON 16236. 아기 상어
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int distance;
		
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.distance = d;
		}

		@Override
		public String toString() {
			return String.format("Point r: %d, c: %d, d: %d", this.r, this.c, this.distance);
		}

		@Override
		public int compareTo(Point o) {
			if(this.distance == o.distance) {
				if(this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				else return Integer.compare(this.r, o.r);
			}
			
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static class Shark extends Point {
		int level;

		public Shark(int r, int c, int level) {
			super(r, c, 0);
			this.level = level;
		}
		
		@Override
		public String toString() {
			return super.toString() + String.format(" Lv: %d", this.level);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 공간의 크기
			int[][] map = new int[N][N];
			Shark babyShark = null;
			int eatCount = 0;
			int time = 0;
			
			// 2차원 배열에 공간의 상태를 입력
			StringTokenizer st = null;
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					
					// 최초의 아기 상어의 위치를 저장
					if(map[r][c] == 9) {
						babyShark = new Shark(r, c, 2);
					}
				}
			}
			
			while(true) {
				// BFS를 수행하여 타겟 물고기의 좌표를 받아 옴
				Point target = BFS(map, babyShark);

				// 먹을 수 있는 물고기가 없는 경우는 루프 종료
				if(target == null) break;
				
				// 중간 결과 출력
//				System.out.println("=======================");
//				System.out.println(target.toString());
//				printMap(map);
//				System.out.println("Sum: " + (time + target.distance));
				
				// map의 상태 변경
				map[babyShark.r][babyShark.c] = 0;
				map[target.r][target.c] = 9;
				
				// 아기 상어의 좌표 변경
				babyShark.r = target.r;
				babyShark.c = target.c;
				
				eatCount++;
				time += target.distance;
				
				// 먹은 물고기의 수와 아기 상어의 사이즈가 같으면 size up
				if(eatCount == babyShark.level) {
					babyShark.level++;
					eatCount = 0;
				}
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, time));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	//                 상     하    좌    우
	static int[] dr = {-1, 1,  0, 0};
	static int[] dc = { 0, 0, -1, 1};
	
	public static Point BFS(int[][] map, Shark babyShark) {
		int N = map.length;
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v = new boolean[N][N];
		// int[][] distanceMap = new int[N][N];
		LinkedList<Point> smallFish = new LinkedList<Point>();
		
		int dist = 1;
		
		q.offer(babyShark);
		v[babyShark.r][babyShark.c] = true;
		// distanceMap[babyShark.r][babyShark.c] = dist++;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			for(int i = size; i > 0; i--) {
				Point p = q.poll();
				
				for(int d = 0; d < dr.length; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					
					if(checkRange(nr, nc, N) && !v[nr][nc] && map[nr][nc] <= babyShark.level) {
						v[nr][nc] = true;
						q.offer(new Point(nr, nc, dist));
						// distanceMap[nr][nc] = dist;
						
						// 상어가 먹을 수 있는 물고기인 경우에 리스트에 추가
						if(map[nr][nc] != 0 && map[nr][nc] < babyShark.level) {
							smallFish.add(new Point(nr, nc, dist));
						}
					}
				}
			}
			// 가장 가까운 거리 내에서 먹을 수 있는 물고기가 발견된 경우
			// 더 멀리 있는 위치까지의 탐색은 불필요하므로 아래의 조건문을 수행 후 함수 종료
			if(smallFish.size() > 0) {
				// 먹을 수 있는 물고기를 조건에 따라 정렬
				Collections.sort(smallFish);
				// 첫 번째 물고기를 반환
				return smallFish.get(0);
			}
			dist++;
		}
		// 먹을 수 있는 물고기가 없으면 null을 반환
		return null;
	}
	
	public static boolean checkRange(int nr, int nc, int size) {
		if(nr >= 0 && nr < size && nc >= 0 && nc < size) return true;
		else return false;
	}
	
	public static void printMap(int[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
