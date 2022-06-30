/*
 * SWEA 01873. 상호의 배틀필드
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_01873_상호의배틀필드 {

	// 탱크의 좌표와 바라보는 방향
	static int x;
	static int y;
	static int head;
	
	// 탱크의 이동 방향
	static char[] directions = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty03/input_01873.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];
			
			// 맵 배열 입력
			for (int r = 0; r < map.length; r++) {
				map[r] = in.readLine().toCharArray();
				for(int c = 0; c < map[r].length; c++) {
					for(int k = 0; k < directions.length; k++) {
						// 탱크를 발견하면 위치 및 방향 파악
						if(map[r][c] == directions[k]) {
							y = r;
							x = c;
							head = k;
						}
					}
				}
			}
			
			// 문자 동작의 개수와 문자 동작 배열 입력
			int N = Integer.parseInt(in.readLine());
			char[] inputs = in.readLine().toCharArray();
			
			// 동작 수행
			for(int i = 0; i < N; i++) {
				switch(inputs[i]) {
				case 'U':
					move(map, 0);
					break;
				case 'D':
					move(map, 1);
					break;
				case 'L':
					move(map, 2);
					break;
				case 'R':
					move(map, 3);
					break;
				case 'S':
					shoot(map);
					break;
				}
			}
			System.out.printf("#%d ", test_case);
			System.out.print(mapString(map));
		}
		in.close();
	}
	
	//                  상      하      좌     우
	static int[] dr = {-1,  1,  0,  0};
	static int[] dc = { 0,  0, -1,  1};
	
	public static void move(char[][] map, int h) {
		int nr = y + dr[h];
		int nc = x + dc[h];
		
		if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[nr].length) {
			switch(map[nr][nc]) {
			case '.': // 평지
				map[nr][nc] = directions[h];
				map[y][x] = '.';
				y = nr;
				x = nc;
				head = h;
				break;
			case '-': // 물
			case '*': // 벽돌
			case '#': // 강철
				map[y][x] = directions[h];
				head = h;
				break;
			}
		}
		else {
			map[y][x] = directions[h];
			head = h;
			return;
		}
	}
	
	public static void shoot(char[][] map) {
		boolean endFlag = false;
		int nr = y;
		int nc = x;
		
		while(!endFlag) {
			nr += dr[head];
			nc += dc[head];
			
			if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[nr].length) {
				switch(map[nr][nc]) {
				case '.': // 평지
				case '-': // 물
					continue; // 포탄 진행
				case '*': // 벽돌
					map[nr][nc] = '.'; // 벽돌 제거
					endFlag = true;
					break;
				case '#': // 강철
					endFlag = true;
					break;
				}
			}
			else {
				return;
			}
		}
	}
	
	public static StringBuilder mapString(char[][] map) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		return sb;
	}
}