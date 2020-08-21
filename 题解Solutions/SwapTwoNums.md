
```
  int x,y;
  x = x + y;
  y = x - y;
  x = x - y;
 ```

```
    public void swap(int x, int y)
    {
        if (x == y) // Check if the two addresses are same
            return;
        x = x + y;
        y = x - y;
        x = x - y;

       // a = (a + b) - (b = a);
    }
 ```