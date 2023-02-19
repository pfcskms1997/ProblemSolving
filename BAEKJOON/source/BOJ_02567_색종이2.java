/*
 * BAEKJOON 02567. 색종이 - 2
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_02567_색종이2 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02567.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 색종이의 수
		boolean[][] paper = new boolean[100][100];
		int res = 0;
		
		// 색종이의 좌측 하단 시작 값을 입력받아 색종이의 크기(가로 10, 세로 10) 만큼 true로 마킹
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int in_c = Integer.parseInt(st.nextToken()) - 1;
			int in_r = Integer.parseInt(st.nextToken()) - 1;
			
			for(int r = in_r; r < in_r + 10; r++) {
				for(int c = in_c; c < in_c + 10; c++) {
					paper[r][c] = true;
				}
			}
		}
		
		// 색종이가 붙은 영역의 둘레 구하기
		//           좌     상    우    하
		int[] dr = { 0, -1, 0, 1};
		int[] dc = {-1,  0, 1, 0};
		
		for(int r = 0; r < paper.length; r++) {
			for(int c = 0; c < paper[r].length; c++) {
				if(!paper[r][c]) continue;
				
				// 현재 [r][c]에서 1 떨어진 네 방향을 확인하여 색종이가 아니라면 경계이므로 
				// 영역을 벗어난 수(false 또는 도화지의 경계)만큼 수를 더하여 경계를 계산함
				for(int d = 0; d < dr.length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위 체크
					if(nr >= 0 && nr < paper.length && nc >= 0 && nc < paper[0].length) {
						if(!paper[nr][nc]) res++; // 색종이와 색종이가 아닌 영역(경계)
					}
					else res++; // 도화지 경계를 벗어난 경우도 둘레에 포함시킴
				}
			}
		}
		System.out.println(res);
	}
}
