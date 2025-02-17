// https://leetcode.com/problems/find-the-highest-altitude/

// Time complexity: O(N) Space complexity: O(1)

import java.util.*;

class Solution19 {
    public int largestAltitude(int[] gain) {
        int answer = 0, altitude = 0;

        // For Each Loop
        for (int val : gain) {
            altitude += val;
            answer = Math.max(answer, altitude);

        }
        return answer;
    }
}
