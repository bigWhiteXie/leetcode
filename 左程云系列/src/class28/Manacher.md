manacher算法是用来求字符串中最大回文子串的，步骤如下：

1. 初始化R=-1(当前最长回文的右边界) C=0(当前最长回文的中心点)
2. 生成manacher str(生成一个2*size+1的str数组，在偶数的index上插入#)
2. 遍历manacher str：
    1. 借用之前得到的回文串，初始化当前回文半径
       help[i] = R > i ? Math.min(help[2*C-i],R-i) : 1\
       原因：虽然我们可以通过C得到i对称点(2c-i)的回文半径，但此时面临三种情况：
        + 情况1：对称点的半径小于r-i，那么直接用对称点的半径初始化当前点的半径就行
        + 情况2：对称点的半径=r-i，那么初始化当前点的半径为r-i
        + 情况3：对称点的半径>r-i，还是只能初始化当前点的半径为r-i
    2. 从i位置往两边扩张(从i+help[i]和i-help[i]开始)，知道半径超过范围或两边不相等则break
       while(i+help[i] < str.length && i-help[i] >= 0){
          if(str[i+help[i]] == str[i-help[i]]){
            help[i]++;
          }else{
            breakl
          }
       }
    3. 判断当前半径是否超过之前的R，若是的话更新max、R、C 
    4. 返回的结果-1才是最大的回文串长度