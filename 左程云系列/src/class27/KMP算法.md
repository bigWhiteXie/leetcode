# 一、认识next数组
假设str = "abcefabcde"，求str对应的next[] 
其中next[i]表示在i之前的子串，前缀和后缀相等的最大长度，
例如next[8] = 3, str[8]='d',前缀abc和后缀abc相对应

# 二、KMP算法
> KMP的核心思想便是利用next数组加速匹配

s1 = "abcefabaabbdabc",s2 = "abaabb"
1. 第一步：求出s2的next[] = {-1,0,0,1,1,2}
2. 设置x用来遍历s1， y遍历s2
    + 当s1[x] == s2[y]时继续向后遍历：x++; y++;
    + 当s1[x] != s2[y]时，需要借助next数组
        + 若next[y] >= 0，将y=next[y]与x对齐，表达的实际意义就是从x-next[y]开始与s2比较，跳过中间注定失败的循环
        + 若next[y] < 0，说明从x处开始和s2比都失败了，直接将x++;
3. 退出循环，return y == s2.length ? x-y : -1;

