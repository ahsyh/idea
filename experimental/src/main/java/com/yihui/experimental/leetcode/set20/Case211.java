package com.yihui.experimental.leetcode.set20;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Case211 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {

    }

    public static class WordDictionary {
        private static class TrieNode {
            public boolean hasFullWord = false;
            public Map<Character, TrieNode> brothers = new HashMap<>();
        }

        private TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.brothers.get(c) == null) {
                    curr.brothers.put(c, new TrieNode());
                }
                curr = curr.brothers.get(c);
            }
            curr.hasFullWord = true;
        }

        public boolean search(String word) {
            return search(root, 0, word);
        }

        private boolean search(TrieNode curr, int index, String word) {
            if (index >= word.length()) {
                return curr.hasFullWord;
            }

            char c = word.charAt(index);
            if (c == '.') {
                for (TrieNode n : curr.brothers.values()) {
                    if (search(n, index + 1, word)) {
                        return true;
                    }
                }
                return false;
            }
            else if (curr.brothers.get(c) == null) {
                return false;
            } else {
                return search(curr.brothers.get(c), index + 1, word);
            }
        }
    }
}

