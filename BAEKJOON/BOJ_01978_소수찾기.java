/*
 * BAEKJOON 01978. 소수 찾기
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01978_소수찾기 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_01978.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        boolean[] isPrime = new boolean[1001];
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i < (int) Math.sqrt(isPrime.length); i++) {
            for (int j = i + 1; j < isPrime.length; j++) {
                if(j % i == 0) isPrime[j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            if(isPrime[input[i]]) ans++;
        }

        System.out.println(ans);
        br.close();
    }
}
