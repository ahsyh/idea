package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Case208 {
    public static void test(String[] args) {
        Trie trie = new Trie();

        boolean r;

        trie.insert("apple");
        r = trie.search("apple");   // return True
        Log.e("search apple" , "result is: " + r);
        r = trie.search("app");     // return False
        Log.e("search app" , "result is: " + r);
        r = trie.startsWith("app"); // return True
        Log.e("startWith app" , "result is: " + r);

        trie.insert("app");

        r = trie.search("app");     // return True
        Log.e("search app" , "result is: " + r);
    }

    private static void testOne() {


    }

    public static class Trie {

        private static class TrieNode {
            public boolean completeWord = false;
            public Map<Character, TrieNode> brothers = new HashMap<>();

            public TrieNode() {
            }

        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode n = root;
            for (char c : word.toCharArray()) {
                if (!n.brothers.containsKey(c)) {
                    n.brothers.put(c, new TrieNode());
                }
                n = n.brothers.get(c);
            }
            n.completeWord = true;
        }

        public boolean search(String word) {
            TrieNode n = root;

            for (char c : word.toCharArray()) {
                if (!n.brothers.containsKey(c)) {
                    return false;
                }
                n = n.brothers.get(c);
            }
            if (n.completeWord) {
                return true;
            }
            return false;
        }

        public boolean startsWith(String prefix) {
            TrieNode n = root;

            for (char c : prefix.toCharArray()) {
                if (!n.brothers.containsKey(c)) {
                    return false;
                }
                n = n.brothers.get(c);
            }
            return true;
        }
    }
}
