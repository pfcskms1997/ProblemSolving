/*
 * BAEKJOON 10813. 공 바꾸기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10813_공바꾸기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_10813.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 바구니의 수
		int M = Integer.parseInt(st.nextToken()); // 바구니 교환 횟수
		int[] basket = new int[N+1]; // 바구니 번호: 1 ~ N
		
		// 바구니 배열 생성
		for(int i = 1; i < basket.length; i++) {
			basket[i] = i;
		}
		
		// 바구니 교환
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			swap(basket, a, b);
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(getResult(basket).toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void swap(int[] basket, int a, int b) {
		int temp = basket[a];
		basket[a] = basket[b];
		basket[b] = temp;
	}

	private static StringBuilder getResult(int[] basket) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < basket.length; i++) {
			sb.append(basket[i] + " ");
		}
		return sb;
	}
}
