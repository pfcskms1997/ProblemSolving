/*
 * SWEA 01861. 정사각형 방
 */

package difficulty04;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01861_정사각형방_sol {
	
	static int T, N;
	static String Ans = new String();
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty04/input_01861.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N+2][N+2]; // 범위를 하나 더 크게 하면 테두리는 0이므로 범위 체크를 따로 할 필요가 없어짐
			
			// map 초기화
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ans = new Ans(0, 987654321);
			
			// 출발점 루프
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					// 4방 탐색을 하여 나보다 1 큰 곳으로 이동을 반복
					recursive(i, j, 1, map[i][j]);
				}
			}
			System.out.println("#" + tc + " " + ans.toString());
		}
		sc.close();
	}
	
	static class Ans implements Comparable<Ans> {
		int cnt;
		int start;
		
		public Ans(int cnt, int start){
			this.cnt = cnt;
			this.start = start;
		}

		@Override
		public int compareTo(Ans o) {
			if(this.cnt == o.cnt)
				return this.start - o.start; // 오름차순 정렬
			return o.cnt - this.cnt; // 내림차순 정렬
		}

		@Override
		public String toString() {
			return start + " " + cnt;
		}
	}
	
	static Ans ans;
	
	// 어느 방향으로 먼저 가야하는지 고려해야 함.
	// 이 문제에서는 관계 없음
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void recursive(int r, int c, int cnt, int start) {
		// base part
		
		// logic part
		// cnt가 가장 큰 값
		Ans tmp = new Ans(cnt, start);
		if(ans.compareTo(tmp) > 0) {
			ans = tmp;
		}

		// inductive part
		for(int d = 0; d < dc.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 지도의 크기를 n+2로 했기 때문에 범위 체크를 할 필요가 없음
			
			// 재귀함수 호출
			if(map[nr][nc] == map[r][c] + 1) {
				recursive(nr, nc, cnt+1, start);
			}
		}
	}
	
	public static void printMap(int[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
