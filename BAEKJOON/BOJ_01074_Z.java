/*
 * BAEKJOON 01074. Z
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_01074_Z {

	static int cnt;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_01074.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 행렬의 크기는 2^N * 2^N
			int size = (int)Math.pow(2, N);
			cnt = 0;
			
			// 목적지의 좌표
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열
			
			zSearch(size, r, c);
			
			bw.write(String.format("#%d %d\n", test_case, cnt));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static void zSearch(int size, int r, int c) {
		// Base Part
		if(size == 1) {
			return;
		}
		
		int resize = size / 2;
		
		if(r < resize && c < resize) {
			zSearch(resize, r, c); // 2사분면			
		}
		else if(r < resize && c >= resize) {
			cnt += resize * resize;
			zSearch(resize, r, c-resize); // 1사분면			
		}
		else if(r >= resize && c < resize) {
			cnt += resize * resize * 2;
			zSearch(resize, r-resize, c); // 3사분면			
		}
		else if(r >= resize && c >= resize) {
			cnt += resize * resize * 3;
			zSearch(resize, r-resize, c-resize); // 4사분면
		}
	}
}
