/*
 * SWEA 01263. 사람 네트워크2
 */

package difficulty06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01263_사람네트워크2 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty06/input_01263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int INF = 987654321;
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] adjMat = new int[N][N]; // 인접행렬
			int[] cc = new int[N]; // Closeness Centrality [CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리]
			int ans = INF;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					adjMat[r][c] = Integer.parseInt(st.nextToken());
					
					// 자기 자신이 아니거나, 해당 노드끼리 인접해있지 않으면 무한대로 설정
					if(r != c && adjMat[r][c] == 0) {
						adjMat[r][c] = INF;
					}
				}
			}
			
			// Floyd-Warshall
			// k: 경유지, i: 출발지, j: 도착지
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) continue; // 출발지와 경유지가 같다면 다음 출발지 확인
					
					for(int j = 0; j < N; j++) {
						if(i == j || k == j) continue; // 출발지와 도착지가 같거나, 경유지와 목적지가 같으면 패스
						if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j]) {
							adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						}
					}
				}
			}
			
			// 각 노드의 Closeness Centrality 계산
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(adjMat[r][c] != INF) {
						cc[r] += adjMat[r][c];
					}
				}
			}
			
			// Closeness Centrality의 최소값 찾기
			for(int i = 0; i < N; i++) {
				ans = Math.min(ans, cc[i]);
			}
			
			sb.append(String.format("#%d %d\n", test_case, ans));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
