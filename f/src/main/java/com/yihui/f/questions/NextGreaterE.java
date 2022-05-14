package com.yihui.f.questions;

import com.yihui.f.util.Log;
import lombok.AllArgsConstructor;

import java.util.Stack;

public class NextGreaterE {

    private static NextGreaterE s = new NextGreaterE();

    public static void test () {
        testOne(new int[]{2,1,2,4,3});
    }

    private static void testOne(int[] list) {
        int[] r = s.nextGreaterElement(list);
        Log.e("result is " + r);
    }

    @AllArgsConstructor
    private static class Node {
        int val;
    }

    int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Node> st = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek().val <= nums[i]) {
                st.pop();
            }

            result[i] = st.isEmpty() ? -1 : st.peek().val;

            st.push(new Node(nums[i]));
        }

        return result;
    }
}
