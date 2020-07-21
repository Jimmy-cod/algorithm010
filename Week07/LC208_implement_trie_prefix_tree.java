package Week07;

public class LC208_implement_trie_prefix_tree {
    /*
    Trie 的几点性质：

    1. Trie 的形状和单词的插入或删除顺序无关，也就是说对于任意给定的一组单词，Trie 的形状都是唯一的。
    2. 查找或插入一个长度为 L 的单词，访问 next 数组的次数最多为 L+1，和 Trie 中包含多少个单词无关。
    3. Trie 的每个结点中都保留着一个字母表，这是很耗费空间的。如果 Trie 的高度为 n，字母表的大小为 m，最坏的情况是 Trie 中还不存在前缀相同的单词，那空间复杂度就为 O(m^n)。
    最后，关于 Trie 的应用场景，希望你能记住 8 个字：一次建树，多次查询。
    * */
    class Trie {

        class TireNode {
            private boolean isEnd;
            TireNode[] next;
            public TireNode() {
                isEnd = false;
                next = new TireNode[26];
            }
        }

        private TireNode root;
        /** Initialize your data structure here. */
        public Trie() {
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
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()){
                if (node.next[c-'a'] == null){
                    return false;
                }
                node = node.next[c-'a'];
            }
            return  node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TireNode node = root;
            for (char c :  prefix.toCharArray()){
                if (node.next[c-'a'] == null){
                    return false;
                }
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
