package Week07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC212_word_search_ii {
    /*
    * 利用字典树可以加快单词搜索
    * */
    int[][] orient = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    int m, n;
    public List<String> findWords(char[][] board, String[] words) {

        if (board == null || board.length==0){
            return null;
        }
        //build Tire for words
        MyTrie trie = new MyTrie();
        for (String w : words){
            trie.insert(w);
        }
        TireNode root = trie.root;
        //search words in board matrix
        m = board.length;
        n = board[0].length;
        int[][] visited = new int[m][n];
        //防止重复
        Set<String> set = new HashSet<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                dfs(board,i,j,root,set,visited);
            }
        }
        return  new ArrayList<String>(set);
    }

    private void dfs(char[][] board, int i, int j, TireNode root, Set<String> set , int[][] visited) {
        if (i<0 || i>=m || j<0 || j>=n || visited[i][j] == 1){
            return;
        }
        root = root.next[board[i][j]-'a'];
        if (root == null){
            return;
        }
        visited[i][j] = 1;
        if (root.isEnd){
            set.add(root.val);
        }

        //continue to search other word
        for (int[] o : orient){
            dfs(board,i+o[0],j+o[1],root,set,visited);
        }
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j]=0;
        return;
    }

    class TireNode {
        private boolean isEnd;
        public TireNode[] next;
        public String val;
        public TireNode() {
            isEnd = false;
            next = new TireNode[26];
            val ="";
        }
    }

    class MyTrie {

        private TireNode root;
        /** Initialize your data structure here. */
        public MyTrie() {
            root = new TireNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()){
                if (node.next[c-'a'] == null){
                    node.next[c-'a'] = new TireNode();
                }
                node = node.next[c-'a'];
            }
            node.isEnd = true;
            node.val = word;
        }
    }
}

class Test{
    public static void main(String[] args) {
        LC212_word_search_ii solution = new LC212_word_search_ii();
        char[][] board ={{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"} ;
        List<String> res = solution.findWords(board,words);
    }
}
