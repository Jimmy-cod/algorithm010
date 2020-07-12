  用 add first 或 add last 这套新的 API 改写 Deque 的代码
  
        Deque<String> stack = new LinkedList<String>();
        stack.addFirst("a");
        stack.addFirst("b");
        stack.addFirst("c");
        System.out.println(stack);

        String str = stack.peek();
        System.out.println(str);
        System.out.println(stack);
        while (stack.size()>0){
            System.out.println(stack.removeFirst());
        }
        System.out.println(stack);

