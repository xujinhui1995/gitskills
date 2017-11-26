# 算法复杂度

算法复杂度分为时间复杂度和空间复杂度.作用:时间复杂度是指执行算法所需要的计算工作量;而空间复杂度是指执行这个算法所需要的内存空间.

在计算机科学中,算法的时间复杂度是一个函数,它定性描述了该算法的运行时间.这是一个关于代表算法输入值的字符串的长度的函数.时间复杂度常用大O符号表示,不包括这个函数的低阶项和首项系数.

### 计算方法

1. 一般情况下,算法中基本操作重复执行的次数是问题规模n的某个函数,用T(n)表示,表示某个辅助函数f(n),使得当n趋近于无穷大时,T(n)/f(n)的极限值为不等于0的常数,则称f(n)是T(n)的同量级函数.记作T(n)=O(f(n)),称O(f(n))为算法的渐进时间复杂度,简称时间复杂度.

	分析:随着模块n的增大,算法执行的时间的增长率和f(n)的增长率成正比,所以f(n)越小,算法的时间复杂度越低,算法的效率越高.

2. 在计算时间复杂度的时候,先找出算法的基本操作,然后根据相应的各语句确定他的执行次数,再找出T(n)的同量级(他的同量级有以下:1,log2n，n，n log2n ，n的平方，n的三次方，2的n次方，n!)找出后,f(n)=该数量级,若T(n)/f(n)求极限可得到一常数c,则时间复杂度T(n)=O(f(n))

		for(int i=1; i<=n; i++){
		    for(int j=0; j<=n; j++){
		        c[i][j] = 0;//该步骤属于基本操作次数:n的平方次
		        for(int k=1; k<=n; k++){
		            c[i][j] += a[i][k] * b[k][j];//该步骤属于基本操作执行次数:n的三次方
		        }
		    }
		}

则有T(n) = n^2 + n^3,根据上面括号里的同量级,我们可以确定n的三次方为T(n)的同量级.

则有f(n) = n^3,然后根据T(n)/f(n)求极限可得到常数c

该算法的时间复杂度:T(n) = O(n^3)

### 常见的增长数量级

![](https://i.imgur.com/caIrP5w.jpg)

注:在找归并算法的实现的时候,突然发现上图就是《算法》里面的原图.而我竟然没有任何印象.

二分查找:

		public static int binarySearch(Integer[] srcArray, int des){
		    int low = 0;
		    int high = srcArray.length-1;
		    while(low<=high){
		        int middle = (high+low)>>>1;
		        if(des == srcArray[middle]){
		            return middle;
		        }else if(des > srcArray[middle]){
		            low = middle + 1;
		        }else{
		            high = middle - 1;
		        }
		    }
		    return -1;
		}

二分查找要注意三个问题,越界,溢出和死循环,这个坑以后再填.

	def binarySearch(self, srcArray, des):
		low = 0
		high = len(srcArray) - 1
		while low<=high and low<len(srcArray) and high<len(srcArray):
			middle = (low+high)//2
			if des == srcArray[middle]:
				return middle
			elif des < srcArray[middle]:
				high = middle - 1
			else:
				low = middle + 1
		return -1

因为二分查找会按照n/2的方式一致查找下去,一致到最后不能二分,假设查找了k次,最坏清苦下,则2^k = n;T(n) = k = log2n,则f(n) = logN,则复杂度为O(logN).

归并排序:

	public static void mergeSort(Comparable[] arr, int high, int low){
	    if(high <= low){
	        return;
	    }
	    int middle = low + (high-low)/2;//可以有效防止溢出
	    mergeSort(arr, middle, low);
	    mergeSort(arr, high, middle+1);
	    merge(arr,low,middle,high);
	}
	public static void merge(Comparable[] arr, int low, int middle, int high){
	    int i = low;
	    int j = middle + 1;
	    int[] aux = new int[high-low+1];
	    for (int k=low; k<=high; k++){
	        aux[k] = arr[k];
	    }
	    for(int k=low; k<=high; k++){
	        if(i>middle) a[k] = aux[j++];
	        else if(j>high) a[k] = aux[i++];
	        else if(less(aux[i],aux[j])) a[k] = aux[i++];
	        else a[k] = aux[j++];
	    }
	}

