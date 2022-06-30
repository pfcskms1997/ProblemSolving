/*
 * SWEA 07699. 수지의 수지 맞는 여행
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_07699_수지의수지맞는여행 {

	static int maxVal;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_07699.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// 섬의 크기
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] island = new char[R][];
			boolean[] alphabet = new boolean[26];
			maxVal = 0;
			
			// 2차원 배열에 섬의 정보를 입력
			for(int r = 0; r < R; r++) {
				island[r] = br.readLine().toCharArray();
			}
			
			alphabet[island[0][0] - 'A'] = true;
			DFS(island, 0, 0, alphabet, 1);
			
			System.out.printf("#%d %d\n", test_case, maxVal);
		}
		br.close();
	}
	
	//                우       하      좌       상
	static int[] dr = {0,  1,  0, -1};
	static int[] dc = {1,  0, -1,  0};
	
	public static void DFS(char[][] map, int r, int c, boolean[] alphabet, int cnt) {

		maxVal = Math.max(maxVal, cnt);
		if(cnt == alphabet.length) return;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length) {
				// 방문한 적이 없는 알파벳이면
				if(!alphabet[map[nr][nc] - 'A']) {
					alphabet[map[nr][nc] - 'A'] = true;
					DFS(map, nr, nc, alphabet, cnt+1);
					alphabet[map[nr][nc] - 'A'] = false;
				}
			}
		}
	}
	
	public static void printMap(char[][] map) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
