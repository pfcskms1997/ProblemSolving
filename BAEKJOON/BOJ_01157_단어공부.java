/*
 * BAEKJOON 01157. 단어 공부
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_01157_단어공부 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_01157.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String input = br.readLine().toLowerCase();
            int[] alphaCnt = new int[26];

            for (int i = 0; i < input.length(); i++) {
                char alpha = input.charAt(i);
                alphaCnt[alpha - 'a']++;
            }

            int maxValue = 0;
            char maxAlpha = ' ';
            for (int i = 0; i < alphaCnt.length; i++) {
                if(alphaCnt[i] >= maxValue) {
                    if(alphaCnt[i] == maxValue) {
                        maxAlpha = '?';
                    } else {
                        maxAlpha = (char) (i + 'A');
                    }
                    maxValue = alphaCnt[i];
                }
            }

            System.out.printf("#%d %c\n", test_case, maxAlpha);
        }

        br.close();
    }
}
