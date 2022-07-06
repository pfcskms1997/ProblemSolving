/*
 * BAEKJOON 11399. ATM
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399_ATM {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_11399.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 고객의 수
		int[] waitTime = new int[N];
		int minTime = 0;
		
		// 1차원 배열에 각 고객의 ATM 인출 시간을 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			waitTime[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가장 시간이 적게 걸리는 고객부터 ATM을 이용할 수 있도록 배열을 오름차순 정렬
		Arrays.sort(waitTime);
		
		// 앞에서부터 대기시간을 고려하여 더함
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				minTime += waitTime[j];
			}
		}
		
		System.out.println(minTime);
		br.close();
	}
}
