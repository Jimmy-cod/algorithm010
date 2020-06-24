学习笔记
Java 递归模板

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
    
 分治的递归模板  Didide & Conquer template:
 
        //1. terminator
        //2. process (split your big problem)
        //3. drill down (sub-problems)
        //4. merge(sub-result)
        //5. reverse states
    
    