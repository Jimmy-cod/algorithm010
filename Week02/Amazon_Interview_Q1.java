package Week02;

import java.util.*;

public class Amazon_Interview_Q1 {

    class Trie {

        Trie[] children = new Trie[100];
        boolean isEnd;
        String value;

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie tmp = this;
            for(char c: word.toCharArray()){
                if (tmp.children[c-'a']==null){
                    tmp.children[c-'a'] = new Trie();
                }
                tmp = tmp.children[c-'a'];
            }
            tmp.isEnd = true;
            tmp.value = word;
        }

        public boolean search(String word) {
            Trie tmp = this;

            for(char c: word.toCharArray()){
                if (tmp.children[c-'a'] == null){
                    return false;
                }
                tmp = tmp.children[c-'a'];
            }
            return tmp.isEnd;
        }

    }

    private HashSet<String> searchReview( Trie cmptTrie, String review){
        Trie tmp = cmptTrie;
        HashSet<String> cmptSet = new HashSet<>();
        for(char c: review.toCharArray()){
            if(c == ' '){
                continue;
            }
            if (tmp.children[c-'a'] == null){
                tmp = cmptTrie;
            }
            else if(tmp.isEnd){
                cmptSet.add(tmp.value);
                tmp = cmptTrie;
            }
            else {
                tmp = tmp.children[c-'a'];
            }
        }
        return cmptSet;
    }


    public List<String> finTopNcompetitors(int numCompetitors, int topNCompetitors,
                                           List<String>compentitors, int numReviews, List<String>reviews){

        Trie cmptTrie = new Trie();
        for (String cpts: compentitors){
            cmptTrie.insert(cpts.toLowerCase());
        }
        HashMap<String, Integer> cmpMap = new HashMap<>();

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for (String review: reviews){
            HashSet<String> cmptSet = searchReview(cmptTrie,review.toLowerCase());
            for (String s: cmptSet){
                cmpMap.put(s,cmpMap.getOrDefault(s,0)+1);
            }
        }

        for(Map.Entry<String, Integer> entry: cmpMap.entrySet())
        {
            pq.offer(entry);
            if(pq.size()>numReviews)
                pq.poll();
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }
}


class Amazon_Interview_Q1_test {
    public static void main(String[] args) {
        Amazon_Interview_Q1 solution = new Amazon_Interview_Q1();
        int numCompetitors=5;
        int topNCompetitors=2;
        List<String>compentitors = Arrays.asList("anacell",
                "betacellular",
                "cetracular",
                "deltacellular",
                "eurocell");
         int numReviews =3;
        List<String> reviews= Arrays.asList("best services provided by anacell",
                "betacellular has great services",
                "anacell provides much better services than all other");

        List<String> result = solution.finTopNcompetitors( numCompetitors,  topNCompetitors,
            compentitors, numReviews, reviews);

    }
}
