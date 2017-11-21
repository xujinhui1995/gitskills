##简介
KMP算法全称是Knuth-Morris-Pratt algorithm.是由Donald Knuth, Vaughan Pratt, James H.Morris三人于1977年联合发表,算法名字也由此而来.

##暴力匹配算法
在处理字符串匹配问题时,第一个大家普遍能够想到的解决方案就是暴力匹配.具体思想就是字符串s和模式串p从第一个字母开始进行匹配,如果匹配成功,则s和p的索引i,j分别++,并继续循环,直到全部匹配成功或匹配失败.匹配失败后,i=i+1;j重置为0.具体的实现如下:

1.
	
	public static int VolienceMatching(String str, String pattern){
		    int i = 0;
		    int j = 0;
		    int sLength = str.length();
		    int pLength = pattern.length();
		    int index = -1;
		    for(; i<sLength; i++){
		        j = 0;
		        int temp = i;
		        for(; j<pLength; j++){
		            char sChar = str.charAt(temp);
		            char pChar = pattern.charAt(j);
		            if(sChar == pChar){
		                temp++;
		            }else{
		                break;
		            }
		        }
		        if(j == pLength){
		            index = i;
		            break;
		        }
		    }
		    return index;
		}

2.

	public static int VolienceMatching(String str, String pattern){
			int i = 0;
		    int j = 0;
		    int sLength = str.length();
		    int pLength = pattern.length();
		    while(i<sLength && j<pLength){
		        char sChar = str.charAt(i);
		        char pChar = pattern.charAt(j);
		        if(sChar == pChar){
		            i++;
		            j++;
		        }else{
		            i = i - j + 1;
		            j = 0;
		        }
		    }
		    if(j == pLength){
		        return i - j;
		    }else{
		        return -1;
		    }

既然也在学习Python,就用Python再实现一下.
	
	class Solution(object):
	    def VolienceMatching(self,s,p):
	        i = j = 0
	        while i < len(s) and j < len(p):
	            if s[i] == p[j]:
	                i += 1
	                j += 1
	            else:
	                i = i - j + 1
	                j = 0
	        if j == len(p):
	            return i-j
	        else:
	            return -1

这么一点东西折腾了好久,就是因为main方法里面字符串和类名写重复了,一直在报错,却找不出到底是哪里错了,细心细心.

###KMP算法实现

KMP算法的具体实现就是借助了一个next数组来实现的.next数组指明了模式串在匹配失败之后,j数组开始重新进行匹配的位置.

在这里就是借鉴一下Kris老师所列举的例子,字符串S为"BBC ABCDAB ABCDABCDABDE",相应的模式串P为"ABCDABD".

还是画一下暴力匹配算法吧:
![](https://i.imgur.com/Ce6jIr7.png)

在写的时候,还是发现了又一个问题,就是在判断对i,j的更改时,j=-1时的操作.在模式串首字符不匹配的时候,j会被设置为-1,下一次比较久出现问题,因为此时我们应该将模式串右移一个,并且将i+1来进行下一次比较,这是就需要我们判断j的值,j=-1时,说明模式串首字母不一致,只能同时"右移",这样的话,就把他和相等结合起来.

接下来就是具体的实现.

	public void KMP_Search(String s, String p){
	    int i = 0;
	    int j = 0;
	    int sLength = s.length();
	    int pLength = p.length();
	    while(i<sLength && j<pLength){
	        if(j == -1 || s.charAt(i) == p.charAt(j)){
	            i++;
	            j++
	        }else{
	            j = next[j];
	        }
	    }
	    if(j == pLength){
	        return i - j;
	    }else{
	        return -1;
	    }
	}

还有python的实现:

	class Solution:
	    def KMP_Search(self, s, p):
	        i = j = 0
	        sLen = len(s)
	        pLen = len(p)
	        while i<sLen and j<pLen:
	        	if j==-1 or s[i]==p[j]:
	        		i += 1
	        		j += 1
	        	else:
	        		j = next[j]
	        if j == pLen:
	        	return i - j
	        else:
	        	return -1
