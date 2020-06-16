学习笔记
ArrayList / List 初始化：  
List<Integer> list = Arrays.asList(1,2);

Array 初始化： 
int[]{map.get(target-nums[i]),nums[i]};
                
HashMap 源码：
为啥要这么多中间变量呢？ tab, first, e; n,k 感觉诚心让我们不容易看懂源码:-)
这些中间变量的作用是存储取出的值？ tab = table 有啥作用，为啥不能直接在table上操作？

putVal时，如果hash key 相同的值超过7个时变为TreeNode(红黑树）
  if (binCount >= 7) {  this.treeifyBin(tab, hash); }
---------------------------------------------                    
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

------------------------

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        HashMap.Node[] tab;
        int n;
        if ((tab = this.table) == null || (n = tab.length) == 0) {
            n = (tab = this.resize()).length;
        }

        Object p;
        int i;
        if ((p = tab[i = n - 1 & hash]) == null) {
            tab[i] = this.newNode(hash, key, value, (HashMap.Node)null);
        } else {
            Object e;
            Object k;
            if (((HashMap.Node)p).hash == hash && ((k = ((HashMap.Node)p).key) == key || key != null && key.equals(k))) {
                e = p;
            } else if (p instanceof HashMap.TreeNode) {
                e = ((HashMap.TreeNode)p).putTreeVal(this, tab, hash, key, value);
            } else {
                int binCount = 0;

                while(true) {
                    if ((e = ((HashMap.Node)p).next) == null) {
                        ((HashMap.Node)p).next = this.newNode(hash, key, value, (HashMap.Node)null);
                        if (binCount >= 7) {
                            this.treeifyBin(tab, hash);
                        }
                        break;
                    }

                    if (((HashMap.Node)e).hash == hash && ((k = ((HashMap.Node)e).key) == key || key != null && key.equals(k))) {
                        break;
                    }

                    p = e;
                    ++binCount;
                }
            }

            if (e != null) {
                V oldValue = ((HashMap.Node)e).value;
                if (!onlyIfAbsent || oldValue == null) {
                    ((HashMap.Node)e).value = value;
                }

                this.afterNodeAccess((HashMap.Node)e);
                return oldValue;
            }
        }

        ++this.modCount;
        if (++this.size > this.threshold) {
            this.resize();
        }

        this.afterNodeInsertion(evict);
        return null;
    }
