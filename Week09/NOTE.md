学习笔记

### 63. 不同路径 II 状态转移方程  
dp[i][j]: i 表示往右移一步； j表示往下移一步  
状态转移方程：  
```
if (obstacleGrid[i][j]==0){ //没障碍物 
    dp[i][j] = dp[i-1][j] + dp[i][j-1];
}else { //有障碍物, 不能去到这，所以路径为0，可省略，default值为0    
    dp[i][j] = 0;  
}
```

```reverseString
for (int i=0, j=s.length -1;i<j; ++i, --j){
    char tmp = s[i];s[i]=s[j];s[j]=tmp;
}
```
```aidl
for (int i=0; i< a.lenght-1;++i)
    for (int j=i+1;j<a.lenght; ++j)
```

```aidl
public String reverseWords(String s){
    String[] words = s.trim().split(" +");
    Collections.reverse(Arrays.asList(words));
    return String.join(" ", words);
}
```