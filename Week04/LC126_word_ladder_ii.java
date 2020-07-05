package Week04;

import java.util.*;

public class LC126_word_ladder_ii {
    static class Solution {

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            List<List<String>> res = new ArrayList<>();
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return res;
            }

            bfs(beginWord, endWord, wordSet, res);
            return res;
        }

        private void bfs(String beginWord, String endWord, Set<String> wordSet, List<List<String>> res) {
            Queue<List<String>> queue = new LinkedList<>();
            List<String> path = new ArrayList<>();
            path.add(beginWord);
            queue.offer(path);

            boolean isFound = false;
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            while (!queue.isEmpty()) {
                int size = queue.size();
                Set<String> subVisited = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    List<String> p = queue.poll();
                    //得到当前路径的末尾单词
                    String lastWord = p.get(p.size() - 1);
                    List<String> neighbors = getNeighbors(lastWord, wordSet);
                    for (String nextWord : neighbors) {
                        //只考虑之前没有出现过的单词
                        if (!visited.contains(nextWord)) {
                            //到达结束单词
                            if (nextWord.equals(endWord)) {
                                isFound = true;
                                p.add(nextWord);
                                res.add(new ArrayList<String>(p));
                                p.remove(p.size() - 1);
                            }
                            //加入当前中间单词
                            p.add(nextWord);
                            //记录到queue中
                            queue.offer(new ArrayList<String>(p));
                            //因为所有neighbors是平级的，进入下一级前remove当前nextWord
                            p.remove(p.size() - 1);
                            subVisited.add(nextWord);
                        }
                    }
                }
                visited.addAll(subVisited);
                // if (isFound) {
                //     break;
                // }
            }
        }


        private ArrayList<String> getNeighbors(String w, Set<String> dict) {
            ArrayList<String> res = new ArrayList<String>();
            char chs[] = w.toCharArray();

            for (char ch = 'a'; ch <= 'z'; ch++) {
                for (int i = 0; i < chs.length; i++) {
                    if (chs[i] == ch) {
                        continue;
                    }
                    char old_ch = chs[i];
                    chs[i] = ch;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = old_ch;
                }
            }
            return res;
        }
    }


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        String[] wordListArray = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        Collections.addAll(wordList, wordListArray);
        Solution solution = new Solution();

    }
}
