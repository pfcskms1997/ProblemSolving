/*
 * Programmers Lv1. 모의고사
 */

import java.util.*;

class 모의고사 {
    static int[] ans1 = {1, 2, 3, 4, 5};
    static int[] ans2 = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] ans3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        int[] score = new int[4];
        int maxScore = 0;
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == ans1[i % ans1.length]) score[1]++;
            if(answers[i] == ans2[i % ans2.length]) score[2]++;
            if(answers[i] == ans3[i % ans3.length]) score[3]++;
        }
        
        for(int i = 1; i < score.length; i++) {
            maxScore = Math.max(maxScore, score[i]);
        }
        
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i < score.length; i++) {
            if(maxScore == score[i]) list.add(i);
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}