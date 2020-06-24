学习笔记
Java 递归模板
    递归终结条件
    处理当前层逻辑
    下探到下一层
    清理当前层

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
    
   递归思维要点
   不要人肉递归（最大误区）
   找到最近最简方法，将其拆解成可以重复解决的问题（重复子问题）
   数学归纳法
    
 分治的递归模板  Didide & Conquer template:
 
        //1. terminator
        //2. process (split your big problem)
        //3. drill down (sub-problems)
        //4. merge(sub-result)
        //5. reverse states
    
    回溯
    每一层不断尝试，以获取可能的解