/*
 * Programmers Lv2. 프린터
 */

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        List<int[]> queue = new LinkedList<int[]>();

        for(int i = 0; i < priorities.length; i++) {
            queue.add(new int[] {i, priorities[i]});
        }
        
        int idx = 0;
        L: while(idx < priorities.length-1) {
            int[] cur = queue.get(idx);
            
            for(int i = idx+1; i < priorities.length; i++){
                if(cur[1] < queue.get(i)[1]) {
                    queue.remove(idx);
                    queue.add(cur);
                    continue L;
                }
            }
            idx++;
        }
        
        int answer = 0;
        for(int i = 0; i < queue.size(); i++) {
            if(queue.get(i)[0] == location) break;
            else answer++;
        }
        
        return answer+1;
    }
}