package Week04;

import java.util.*;

public class LC127_word_ladder {

    static class Solution2 {
        //建一个canConvert函数，两个单词只有一个字母不同，return true;
        public boolean canConvert(String s1, String s2) {
            // 因为题目说了单词长度相同，可以不考虑长度问题
            // if (s1.length() != s2.length()) return false;
            int count = 0;
            for (int i = 0; i < s1.length(); ++i) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    ++count;
                    if (count > 1) {
                        return false;
                    }
                }
            }
            return count == 1;
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }
            // visited修改为boolean数组
            boolean[] visited = new boolean[wordList.size()];
            int idx = wordList.indexOf(beginWord);
            if (idx != -1) {
                visited[idx] = true;
            }
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            int count = 0;
            while (queue.size() > 0) {
                int size = queue.size();
                ++count;
                while (size-- > 0) {
                    String start = queue.poll();
                    for (int i = 0; i < wordList.size(); ++i) {
                        // 通过index判断是否已经访问
                        if (visited[i]) {
                            continue;
                        }
                        String s = wordList.get(i);
                        if (!canConvert(start, s)) {
                            continue;
                        }
                        if (s.equals(endWord)) {
                            return count + 1;
                        }
                        visited[i] = true;
                        queue.offer(s);
                    }
                }
            }
            return 0;
        }

    }

    static class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0|| !wordSet.contains(endWord)){
                return 0;
            }
            Deque<String> queue = new ArrayDeque<>();
            queue.addFirst(beginWord);
            Set<String> visited = new HashSet<String>();
            visited.add(beginWord);
            int step =1;
            while (!queue.isEmpty()){
                int queSize = queue.size();
                for (int i = 0;i<queSize;i++){
                    String word = queue.pollLast();
                    char[] wordChar = word.toCharArray();
                    //build new words
                    for (int j=0;j<word.length();j++){
                        //keep original char for backtrack
                        char originChar = wordChar[j];
                        for (char k='a'; k<='z';k++){
                            if (k==originChar){
                                continue;
                            }
                            wordChar[j] = k;
                            String newWord = String.valueOf(wordChar);

                            //new word has been visited,continue
                            if (visited.contains(newWord)){
                                continue;
                            }
                            //new word in the wordList
                            if (wordSet.contains(newWord)){
                                //reach to endWord
                                if (newWord.equals(endWord)){
                                    return step +1;
                                }
                                else {
                                    queue.addFirst(newWord);
                                    visited.add(newWord);
                                }
                            }
                        }
                        //reverse back
                        wordChar[j] = originChar;
                    }
                }
                step++;
            }

            return 0;
        }

    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        String[] wordListArray = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        Collections.addAll(wordList, wordListArray);
        Solution solution = new Solution();
        int res = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }

}
