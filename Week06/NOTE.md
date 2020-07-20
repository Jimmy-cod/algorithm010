学习笔记

### **动态规划的实现及关键点**

动态规划和递归或分治没有根本上的区别（关键看有无最优的子结构）

共性：找到重复子问题

差异性：最优子结构，中途可以淘汰次优解

动态规划，无非就是利用历史记录，来避免我们的重复计算。而这些历史记录，我们得需要一些变量来保存，一般是用一维数组或者二维数组来保存。
第一步骤：定义数组元素的含义
第二步骤：找出数组元素之间的关系式,最优子结构，把大的问题拆分成小的问题
第三步骤：找出初始值
优化: 保存的中间值是否可以覆盖，用一维数组保存中间值

DP: 解题公式：
a.分治（子问题）
b.状态数组定义
c.DP方程

LC-198：打家劫舍
a.子问题  
b.状态数组定义  
c.DP方程  

#思路：
1. 状态数组定义： a[i]: 0..i 能偷到max value : a[n-1]
2. DP方程 : a[i] = a[i-1] + nums[i], 这里有问题，不知道i-1是否偷。没办法递推
3. 升维：a[i][0,1]: 0表示 i不偷； 1表示 i偷
4. DP方程： 
    a[i][0] = Max(a[i-1][0], a[i-1][1])
    a[i][1] = Max(a[i-1][0], 0) + nums[i] => a[i-1][0] + nums[i]
5. DP要夹带更多的信息时，一般就是升维。
6. 在此基础上考虑重新定义状态数组，降维
7. 状态数组: a[i]: 0..i天，且nums[i]必偷的最大值， 能偷到max value : max(a)
8. DP方程 : a[i] = Max(a[i-1], a[i-2] + nums[i]);

```$xslt
    public int rob(int[] nums) {
        if (nums == null || nums.length==0) return 0;
        if (nums.length==1) return nums[0];

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for (int i=2;i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
```
  
```$xslt

```  
