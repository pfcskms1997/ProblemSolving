/*
 * BAEKJOON 02562. 최댓값
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_02562_최댓값 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_02562.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        int maxValue = 0;

        for (int i = 1; i <= 9; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input > maxValue) {
                maxValue = input;
                idx = i;
            }
        }

        System.out.printf("%d\n%d\n", maxValue, idx);
        br.close();
    }
}
