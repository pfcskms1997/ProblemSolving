package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01865_동철이의일분배 {
	
	static int N;
	static double ans;
	static double[][] P;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty04/input_01865.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 직원의 수
			P = new double[N][N];
			ans = 0; // 모든 일이 성공할 확률
			
			// 성공 확률 입력
			StringTokenizer st = null;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					P[i][j] = Integer.parseInt(st.nextToken()) / 100.0;
				}
			}
			
			DFS(new boolean[N+1], 0, 1);
					
			sb.append(String.format("#%d %.6f\n", test_case, ans));
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}
	
	public static void DFS(boolean[] sel, int cnt, double earlyRes) {
		if(ans >= earlyRes*100) return;
		
		if(cnt == N) {
			ans = Math.max(ans, earlyRes*100);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(sel[i]) continue;
			
			sel[i] = true;
			DFS(sel, cnt+1, earlyRes*P[cnt][i]);
			sel[i] = false;
		}
	}
}
