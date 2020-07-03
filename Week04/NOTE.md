学习笔记

DFS 代码模板  
递归状态树的理解：dfs会马上进入到下一层，不用等到for循环，等dfs遇到return才会进入for循环下一个  
```$xslt
visited = set() 
def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 
	visited.add(node) 
	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```
非递归写法:  
```
def DFS(self, tree): 
   	if tree.root is None: 
   		return [] 
   	visited, stack = [], [tree.root]
   	while stack: 
   		node = stack.pop() 
   		visited.add(node)
   		process (node) 
   		nodes = generate_related_nodes(node) 
   		stack.push(nodes) 
   	# other processing work 
   	...
```

## BFS 代码模板
```$xslt
# Python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 
	while queue: 
		node = queue.pop() 
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
	# other processing work 
	...

```
![](.NOTE_images/bfs-dfs.png)

#二分查找
二分查找的前提  
1. 目标函数单调性 （单调递增或递减）
2. 存在上下界 （bounded)
3. 能够通过索引访问 （index accessible)
```$xslt
 //用二分查找
    //1.如果左<右，表示这部分是排序的,另一部分没有排序
    //2.在排序这部分找,如果target在左右值间，说明target在有序这边
    //3.否则在无序这边，据继续递归查找
    public int search(int[] nums, int target) {
        int mid,left=0;
        int right=nums.length-1;
        while (left <= right){
            mid = left+(right-left)/2;
            if (nums[mid]==target){
                return mid;
            }
            //有序在前半部分,
            if (nums[left]<=nums[mid]){
                //必须考虑左右边界值等于target情况
                //这里因为right = mid-1;加入target=左边界值
                if (nums[left]<=target && target<nums[mid]){
                    right = mid-1;
                }
                else {
                    left = mid+1;
                }
            }
            else {
                //这里也有两种情况：target在后半部有序处，或前半部无序处
                if (nums[mid]<target && target<=nums[right]){
                    left = mid +1;
                }
                else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }
```