# 一、partition
## 边界：less、more
less代表比目标元素小的右边界边界
more代表比目标元素大的左边界
在遍历过程中，如果遇到比目标元素小的元素，就swap(cur++,++less)
遇到比目标大的元素，swap(cur,--more)

## 用途
### 1. 完成快排

### 2. 无序数组中查找第K小的数


