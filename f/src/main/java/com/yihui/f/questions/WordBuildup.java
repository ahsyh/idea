package com.yihui.f.questions;

import com.yihui.f.util.Log;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBuildup {
    public static void test() {
        WordBuildup w = new WordBuildup();
        Set<String> dict = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));
        List<List<String>> r = w.findLadders("hit", "cog", dict);
        Log.e("result: " + r);
    }

    @Data
    @AllArgsConstructor
    private class Node {
        String str;
        Integer prev;
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(end);
        ArrayList<Node> q = new ArrayList<>();
        List<Integer> tails =  new ArrayList<>();

        Node root = new Node(start, -1);
        q.add(root);
        int index = 0;
        int left;
        int right;
        while (index < q.size()) {
            left = index;
            right = q.size();
            boolean findEnd = false;

            for (; index < right; index++) {
                String curr = q.get(index).str;
                Set<String> exists = getPrevs(q, index);
                Set<String> next = generateNext(curr).stream()
                        .filter(e -> !exists.contains(e))
                        .filter(dict::contains)
                        .collect(Collectors.toSet());

                findEnd = false;
                for (String e : next) {
                    q.add(new Node(e, index));
                    if (end.equals(e)) {
                        findEnd = true;
                        tails.add(q.size() - 1);
                    }
                }
            }

            if (findEnd) {
                break;
            }

        }

        List<List<String>> result = new ArrayList<List<String>>();
        for (Integer i : tails) {
            List<String> ladder = getLadder(q, i);
            result.add(ladder);
        }

        return result;
    }

    private List<String> getLadder(ArrayList<Node> all, int tail) {
        List<String> r = new ArrayList<>();

        while (tail >= 0) {
            r.add(all.get(tail).str);
            tail = all.get(tail).prev;
        }

        Collections.reverse(r);

        return r;
    }

    private Set<String> getPrevs(ArrayList<Node> all, int curr) {
        Set<String> r = new HashSet<>();

        while (curr >= 0) {
            r.add(all.get(curr).str);
            curr = all.get(curr).prev;
        }

        return r;
    }

    private Set<String> generateNext(String word) {
        char[] chars = word.toCharArray();
        Set<String> r = new HashSet<>();

        for (int pos = 0; pos < word.length(); pos++) {
            char curr = chars[pos];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[pos] = ch;
                r.add(String.valueOf(chars));
            }
            chars[pos] = curr;
        }

        return r;
    }
}
