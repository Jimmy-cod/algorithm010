学习笔记

## Trie (字典树)的几点性质：

    1. Trie 的形状和单词的插入或删除顺序无关，也就是说对于任意给定的一组单词，Trie 的形状都是唯一的。  
    2. 查找或插入一个长度为 L 的单词，访问 next 数组的次数最多为 L+1，和 Trie 中包含多少个单词无关。  
    3. Trie 的每个结点中都保留着一个字母表，这是很耗费空间的。如果 Trie 的高度为 n，字母表的大小为 m，
       最坏的情况是 Trie 中还不存在前缀相同的单词，那空间复杂度就为 O(m^n)。  
    关于 Trie 的应用场景，记住 8 个字：一次建树，多次查询。  
    
## 并查集代码模板  
```Java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) {
            //压缩路径 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```
  
## Self-balancing binary search tree  
2–3 tree  
AA tree  
AVL tree  
B-tree  
Red–black tree  
Scapegoat tree  
Splay tree  
Treap  
Weight-balanced tree  

## AVL tree
每个node 要有个int记录balance factor
Balance Factor:
    它的右子树高度减去它的左子树高度  
    balance factor = {-1,0,1}
旋转操作：  
左旋  （右右子树）  

右旋  （左左子树）  
    右旋时，挂在在左右两边之间的子树要换父节点  
　　　　　5　　　　　　　　　4  
　　　　/　\　　　　　　　　/　\  
　　　4　　6　　　　=>　　2　　5  
　　/　\　　　　　　　　　/　　　/　\　  
　2　　3　　　　　　　　1　　　3　　6　　　　(3 要换父节点)  
　/  
1
 
左右旋 （左右子树）  
    先下面的右子树左旋，变成了左左子树，再做整个子树右旋    
右左旋 （右左子树）  
    先下面的左子树右旋，变成了右右子树，再做整个子树左旋

## Red-black Tree  
红黑树是一种**近似平衡**的二叉树(Binary Search Tree),  
它能够保证任何一个节点的左右子树**高度差小于两倍**。  
 1.每个节点要么是红或黑  
 2.根结点是黑  
 3.每个叶结点(NIL结点)是黑色的  
 4.不能有相邻接的两个红节点  
 5.任何一节点到其每个叶子的所有路径都包含相同数目的黑色结点  
 
 ## AVL Tree vs  Red-black Tree  
 1.AVL trees provide faster lookups than Red Black Trees bacause they are more strictly balanced.  
 
 2.Red Black Trees provide faster insertion and removal operations than AVL trees as fewer rotations are done due to relatively relaxed balancing.  
 
 3.AML trees store balance factors or heights with each node, thus requires storeage for an integer pernode whereas Red Black Tree requires only 1 bit of information per node.  
 
 4.Red Black Trees are used in most of the language libraries, like map, multimap, multisetin C++ whereas AVL trees are used in databases where faster retrievals are required.
