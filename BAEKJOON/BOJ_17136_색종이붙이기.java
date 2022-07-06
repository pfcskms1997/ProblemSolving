/*
 * BAEKJOON 17136. 색종이 붙이기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	
	static int N;
	static int[][] paper;
	static int ans;
	static int[] colorPaper;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17136.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			N = 10;
			paper = new int[N][N];
			colorPaper = new int[] {0, 5, 5, 5, 5, 5};
			ans = 987654321;
			
			StringTokenizer st = null;
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < N; c++) {
					paper[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve(0);
			
			bw.write(String.format("#%d %d\n", test_case, ans == 987654321 ? -1 : ans));
			bw.flush();
		}
		// 결과 출력 및 프로그램 종료
		br.close();
		bw.close();
	}
	
	public static void solve(int cnt) {
		// 색종이를 붙힐 위치 탐색
		int sr = -1, sc = -1;
		
		L: for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(paper[r][c] == 1) {
					sr = r;
					sc = c;
					break L;
				}
			}
		}
		
		if(sr == -1 && sc == -1) {
			// 지도에 색종이를 더이상 붙일 곳이 없다.
			ans = Math.min(ans, cnt);
			return;
		}
		
		int size = getPaperSize(sr, sc);
		
		// 사이즈 1부터 가능한 크기까지 모든 색종이를 붙여보기
		for(int i = 1; i <= size; i++) {
			// 붙일 수 있는 색종이가 남아있다면 붙이기
			if(colorPaper[i] > 0) {
				colorPaper[i]--;
				
				for(int r = 0; r < i; r++) {
					for(int c = 0; c < i; c++) {
						paper[sr + r][sc + c] = 0;
					}
				}
				
				solve(cnt+1);
				
				// BackTracking(붙여 보았던 색종이를 다시 떼기)
				colorPaper[i]++;
				
				for(int r = 0; r < i; r++) {
					for(int c = 0; c < i; c++) {
						paper[sr + r][sc + c] = 1;
					}
				}
			}
		}
	}
	
	public static int getPaperSize(int sr, int sc) {
		// 현재 시작점에서 붙일 수 있는 최대 paperSize를 구함
		int size = 5;
		while(size > 1) {
			boolean flag = true;
			L: for(int r = sr; r < sr+size; r++) {
				for(int c = sc; c < sc+size; c++) {
					if(r < 0 || r >= 10 || c < 0 || c >= 10 || paper[r][c] != 1) {
						flag = false;
						break L;
					}
				}
			}
			if(flag) return size; // L for문을 끝까지 수행하는 경우
			size--;
		}
		return size;
	}
	
	public static boolean checkRange(int N, int w, int h) {
		if(w < N && h < N) return true;
		else return false;
	}
	
	public static void printPaper(int[][] paper) {
		for(int r = 0; r < paper.length; r++) {
			for(int c = 0; c < paper[r].length; c++) {
				System.out.print(paper[r][c] + " ");
			}
			System.out.println();
		}
	}
}
