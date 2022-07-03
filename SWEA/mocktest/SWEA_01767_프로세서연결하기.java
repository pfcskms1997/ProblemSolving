/*
 * SWEA 01767. 프로세서 연결하기
 */

package mocktest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_01767_프로세서연결하기 {
	
	static int N, maxCore, coreCnt, minLength;
	static int[][] mexynos;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<int[]> list;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/mocktest/input_01767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 셀의 크기
			mexynos = new int[N][N];
			list = new ArrayList<int[]>();
			maxCore = 0; // 최대 연결 코어 수
			minLength = 987654321; // 전선 길이 합의 최소
			coreCnt = 0; // 가장자리가 아닌 코어의 개수
			
			for(int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < N; c++) {
					mexynos[r][c] = Integer.parseInt(st.nextToken());
					// 가장자리에 있는 코어는 skip
					if((r == 0 || r == N-1 || c == 0 || c == N-1) && mexynos[r][c] == 1) continue;
					
					// 나머지 코어만 list에 넣음
					if(mexynos[r][c] == 1) {
						list.add(new int[] {r, c});
						coreCnt++;
					}
				}
			}
			
			solve(0, 0);
			
			bw.write(String.format("#%d %d\n", test_case, minLength));
			bw.flush();
		}

		br.close();
		bw.close();
	}
	
	// 부분집합으로 코어 전선 놓기 시도
	private static void solve(int index, int cCnt) { // cCnt: 전원과 연결된 코어 수
		if(index == coreCnt) {
			int res = getLength();
			
			if(maxCore < cCnt) {
				maxCore = cCnt;
				minLength = res;
			} else if(maxCore == cCnt) { // 최대 연결 코어 수가 같다면
				minLength = Math.min(minLength, res);
			}
			return;
		}
		
		if(cCnt >= maxCore && minLength <= getLength()) return;
			
		int[] core = list.get(index);
		int r = core[0];
		int c = core[1];
		
		// 전선을 놓기(4방향)
		for(int d = 0; d < dr.length; d++) {
			// 현재 코어의 r, c 위치에서 d방향으로 전선을 놓을 수 있다면
			if(isAvailable(r, c, d)) {
				setStatus(r, c, d, 2); // 전선 놓기
				solve(index+1, cCnt+1);
				setStatus(r, c, d, 0); // 전선 지우기
			}
		}
		
		// 전선을 놓지 않기
		solve(index+1, cCnt);
	}
	
	// r, c 위치에서 d 방향으로 전선을 놓을 수 있는지 확인
	private static boolean isAvailable(int r, int c, int d) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			
			// 다른 코어나 전선을 만나는 경우
			if(mexynos[nr][nc] >= 1) return false;
		}
		return true;
	}
	
	// r, c 위치에서 d 방향으로 전선을 놓거나(2) 지우기(0)
	private static void setStatus(int r, int c, int d, int s) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			mexynos[nr][nc] = s;
		}
	}
	
	// 놓인 전선의 길이 합 계산
	private static int getLength() {
		int lCnt = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(mexynos[r][c] == 2) lCnt++;
			}
		}
		return lCnt;
	}
}
