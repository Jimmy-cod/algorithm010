#学习笔记
----
###Java 递归模板  
• 递归终结条件  
• 处理当前层逻辑  
• 下探到下一层  
• 清理当前层  
``` Java
    public void recur(int level, int param) {
        //terminator
        if (level > MAX_LEVEL) {
            //process result
            return;
        }
        
        //process current logic
        process(level, param);
        
        //drill down
        recur(level: level+1,newParam);
        
        //restore current status
        
    }
```    
###递归思维要点  
• 不要人肉递归（最大误区）<br/>
• 找到最近最简方法，将其拆解成可以重复解决的问题（重复子问题）<br/>
• 数学归纳法<br/>
    
分治的递归模板  Didide & Conquer template:
 
        //1. terminator
        //2. process (split your big problem)
        //3. drill down (sub-problems)
        //4. merge(sub-result)
        //5. reverse states
        
    # Python
    def divide_conquer(problem, param1, param2, ...): 
      # recursion terminator 
      if problem is None: 
        print_result 
        return 
      # prepare data 
      data = prepare_data(problem) 
      subproblems = split_problem(problem, data) 
      # conquer subproblems 
      subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
      subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
      subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
      …
      # process and generate the final result 
      result = process_result(subresult1, subresult2, subresult3, …)
    	
      # revert the current level states
      
      
###回溯   
    每一层不断尝试，以获取可能的解
    只需要思考 3 个问题：      
    1、路径：也就是已经做出的选择。     
    2、选择列表：也就是你当前可以做的选择。     
    3、结束条件：也就是到达决策树底层，无法再做选择的条件。  
    
    回溯模板：  
   
    ```
    result = [];
    public void backtrack(路径, 选择列表){
        if 满足结束条件:
            result.add(路径);
            return;
        
        for 选择 in 选择列表:
            做选择;
            backtrack(路径, 选择列表);
            撤销选择;
    }
    ```
    其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
