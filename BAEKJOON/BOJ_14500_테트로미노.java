/*
 * BAEKJOON 14500. 테트로미노
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	
	static int N, M, ans;
	static int[][] paper;
	
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, -1, 1, 0};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_14500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 종이의 세로 크기
			M = Integer.parseInt(st.nextToken()); // 종이의 가로 크기
			paper = new int[N][M];
			ans = 0;
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					paper[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			//print(paper);
			boolean[][] v = new boolean[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					v[r][c] = true;
					DFS(paper, v, r, c, 1, paper[r][c]);
					checkAround(paper, r, c);
					v[r][c] = false;
				}
			}
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
	
	public static void DFS(int[][] paper, boolean[][] v, int r, int c, int cnt, int score) {
		
		if(cnt == 4) {
			ans = Math.max(ans, score);
			return;
		}
		
		for(int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(checkRange(nr, nc) && !v[nr][nc]) {
				v[nr][nc] = true;
				DFS(paper, v, nr, nc, cnt+1, score + paper[nr][nc]);
				v[nr][nc] = false;
			}
		}
	}
	
	public static void checkAround(int[][] paper, int r, int c) {
        if( (r == 0 && c == 0) ||
            (r == 0 && c == M-1) ||
            (r == N-1 && c == 0) ||
            (r == N-1 && c == M-1) ) return;
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(checkRange(nr, nc)) {
				list.add(paper[nr][nc]);
			}
		}
		
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		};
		
		Collections.sort(list, com);
		
		int sum = 0;
		for(int i = 0; i < 3; i++) {
			sum += list.get(i);
		}
		
		ans = Math.max(ans, paper[r][c] + sum);
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		else return false;
	}
}
