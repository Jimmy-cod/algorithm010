一，HashMap的内部存储结构

Java中数据存储方式最底层的两种结构，一种是数组，另一种就是链表。
数组的特点：连续空间，寻址迅速，但是在删除或者添加元素的时候需要有较大幅度的移动，所以查询速度快，增删较慢。
链表的特点：由于空间不连续，寻址困难，增删元素只需修改指针，所以查询慢、增删快。
综合这两者的优点，摒弃缺点，哈希表就诞生了，既满足了数据查找方面的特点，占用的空间也不大。

二、底层原理

HashMap的实现主要用到了哈希表的链地址法。即使用数组+链表的方式实现。
hashMap实现了Map接口，继承了AbstractMap类。其中Map接口定义了键映射到值的规则，AbstractMap类提供了Map接口的骨干实现。
hashMap底层有一个entry数组(entry是hashMap的内部类，它包含了键key、值value、下一个节点next，以及hash值，这是非常重要的，正是由于Entry才构成了table数组的项为链表)，数组上每一项都有一个链表，如果链表长度超过阀值( TREEIFY THRESHOLD==8)，就把链表转成红黑树，链表长度低于6，就把红黑树转回链表

三、HashMap提供的三个构造函数：

1、HashMap()：
构造一个具有默认初始容量 (16) 和默认加载因子 (0.75) 的空 HashMap。

2、HashMap(int initialCapacity)：
构造一个带指定初始容量和默认加载因子 (0.75) 的空 HashMap。

3、HashMap(int initialCapacity, float loadFactor)：
构造一个带指定初始容量和加载因子的空 HashMap。

常量
static final int DEFAULT_INITIAL_CAPACITY = 16;
初始容量：16
static final int MAXIMUM_CAPACITY = 1 << 30;
最大容量：2的30次方 => 1073741824
static final float DEFAULT_LOAD_FACTOR = 0.75f;
负载因子：75%

** put(Object key,Object value)方法：

作用是存储一个键-值对

    public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        modCount++;
        addEntry(hash, key, value, i);
        return null;
    }
 
 处理步骤如下：<br/>
 （1）判断key是否为null，若为null，调用putForNullKey(value)处理。这个方法代码如下：
     /**
      * Offloaded version of put for null keys
      */
     private V putForNullKey(V value) {
         for (Entry<K,V> e = table[0]; e != null; e = e.next) {
             if (e.key == null) {
                 V oldValue = e.value;
                 e.value = value;
                 e.recordAccess(this);
                 return oldValue;
             }
         }
         modCount++;
         addEntry(0, null, value, 0);
         return null;
     }


