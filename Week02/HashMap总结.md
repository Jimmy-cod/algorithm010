一，HashMap的内部存储结构

Java中数据存储方式最底层的两种结构，一种是数组，另一种就是链表。<br/>

数组的特点：连续空间，寻址迅速，但是在删除或者添加元素的时候需要有较大幅度的移动，所以查询速度快，增删较慢。<br/>
链表的特点：由于空间不连续，寻址困难，增删元素只需修改指针，所以查询慢、增删快。<br/>
综合这两者的优点，摒弃缺点，哈希表就诞生了，既满足了数据查找方面的特点，占用的空间也不大。<br/>

二、底层原理

HashMap的实现主要用到了哈希表的链地址法。即使用数组+链表的方式实现。<br/>
hashMap实现了Map接口，继承了AbstractMap类。其中Map接口定义了键映射到值的规则，AbstractMap类提供了Map接口的骨干实现。<br/>
hashMap底层有一个entry数组(entry是hashMap的内部类，它包含了键key、值value、下一个节点next，以及hash值，<br/>
正是由于Entry才构成了table数组的项为链表)，数组上每一项都有一个链表，如果链表长度超过阀值( TREEIFY THRESHOLD==8)，就把链表转成红黑树，链表长度低于6，就把红黑树转回链表

JDK 1.7 有源码分析<br/>
三、HashMap提供的三个构造函数：

1、HashMap()：<br/>
构造一个具有默认初始容量 (16) 和默认加载因子 (0.75) 的空 HashMap。

2、HashMap(int initialCapacity)：<br/>
构造一个带指定初始容量和默认加载因子 (0.75) 的空 HashMap。

3、HashMap(int initialCapacity, float loadFactor)：<br/>
构造一个带指定初始容量和加载因子的空 HashMap。<br/>

常量<br/>
static final int DEFAULT_INITIAL_CAPACITY = 16;<br/>
初始容量：16<br/>
static final int MAXIMUM_CAPACITY = 1 << 30;<br/>
最大容量：2的30次方 => 1073741824<br/>
static final float DEFAULT_LOAD_FACTOR = 0.75f;<br/>
负载因子：75%<br/>

** put(Object key,Object value)方法：

作用是存储一个键-值对<br/>

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
 （1）判断key是否为null，若为null，调用putForNullKey(value)处理。这个方法代码如下：<br/>
 
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
     
     
从代码可以看出，如果key为null的值，默认就存储到table[0]开头的链表了。然后遍历table[0]的链表的每个节点Entry，
如果发现其中存在节点Entry的key为null，就替换新的value，然后返回旧的value，
如果没发现key等于null的节点Entry，就增加新的节点。<br/>

（2）先计算key的hashcode，在使用计算的结果二次hash，使用indexFor(hash, table.length)方法找到Entry数组的索引i的位置。<br/>

（3）接着遍历以table[i]为头结点的链表，如果发现已经存在节点的hash、key值与条件相同时，
将该节点的value值替换为新的value值，然后返回旧的value值。<br/>

（4）如果未找到hash、key值均相同的节点，则调用addEntry方法增加新的节点（头插法）。代码如下：<br/>

    void addEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K,V>(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }
    

**get(Object key)方法:

作用是根据键来获取值<br/>

    public V get(Object key) {  
        if (key == null)  
            return getForNullKey();  
        int hash = hash(key.hashCode());  
        for (Entry<K,V> e = table[indexFor(hash, table.length)];  
             e != null;  
             e = e.next) {  
            Object k;  
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))//-------------------1----------------  
                return e.value;  
        }  
        return null;  
    }  
    
    
处理步骤如下：<br/>
（1）当key为null时，调用getForNullKey()，它的源码如下：<br/>

    private V getForNullKey() {  
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {  
            if (e.key == null)  
                return e.value;  
        }  
        return null;  
    }  
    
返回table[0]开头的链表的键为null的节点的值<br/>

（2）当键不为null时，依然计算hash值，然后找到具体在哪个table[indexFor(hash, table.length)]
节点开头的链表中，遍历此链表查找是否存在搜索条件中的key值，返回其value。若没有符合条件的key值，返回null。<br/>


***HashMap常考问题总结 <br/>

最后补充一些面试时候常问到的一些问题总结。

（1）HashMap和HashTable的区别？<br/>

HashMap是非线程安全的，HashTable是线程安全的<br/>
HashMap的键和值都允许有null值存在，而HashTable则不行<br/>
因为线程安全的问题，HashMap效率比HashTable的要高<br/>
哈希值的使用不同，HashMap要根据hashCode二次计算得到hash值，而HashTable直接使用对象的hashCode<br/>
继承的父类不同，HashMap继承自AbstractMap<K,V>，而HashTable继承自Dictionary<K,V> <br/>

（2）HashMap中的键可以是任何对象或数据类型吗？<br/>

可以为null，但是不能为可变对象。如果为可变对象的话，对象中的属性改变则对象的hashCode也进行了相应的改变，导致下次无法查找到已存在Map中的数据。
如果可变对象在HashMap中被当做键，那么就要小心在它的属性改变时，不要改变它的hashCode。只要保证成员变量的改变不会相应改变其hashCode即可。
<br/>
（3）HashTable如何实现线程安全？<br/>

实现原理是在对应的方法上添加了synchronized关键字进行修饰，由于在执行此方法时需要获得对象锁，
因此执行起来比较慢。如果想实现线程安全的HashMap的话，推荐使用ConcurrentHashMap。<br/>


参考资料:
链接：https://www.jianshu.com/p/2f50d21dbfdc

