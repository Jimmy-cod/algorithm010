package Week07;

import java.util.*;

public class LC127_word_ladder {
    //Two-ended BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)){
            return 0;
        }
        Set<String> beginVisited = new HashSet<>();
        Set<String> endVisited = new HashSet<>();
        Set<String> visited = new HashSet<>();
        beginVisited.add(beginWord);
        endVisited.add(endWord);

        int len = beginWord.length();
        int step =1;
        while(!beginVisited.isEmpty() && !endVisited.isEmpty()){
            if (beginVisited.size()>endVisited.size()){
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            //用Set在遍历时不能删除元素,所以用temp临时保存下一个beginSet
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word: beginVisited){
                char[] chs = word.toCharArray();

                for (int i=0; i<len; i++){
                    char originChar = chs[i];
                    for (char c='a'; c<='z'; c++){
                        if (c== originChar) continue;
                        chs[i] = c;
                        String newWord = String.valueOf(chs);

                        if (endVisited.contains(newWord)){
                            return step+1;
                        }
                        if (wordSet.contains(newWord)&&!visited.contains(newWord)){
                            nextLevelVisited.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    //next letter, recover original char
                    chs[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }
}
