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

第二步就是获取next数组.

1.首先,我们回顾一下next数组的作用,用来确定j的数量或者说模式串右移的数量,同时我们也由此得到了next数组的计算方法.就是拥有相同前缀后缀的数量.

![](https://i.imgur.com/PtyPwok.png)

元素右移位数 = 当前元素的index - 前一元素的最大公共元素长度

元素右移位数 = 当前元素的index - 当前元素的next值.

index要大于0,当index=0时,要右移一位,所以next[0]=-1;

因为index为0,所以没有前一元素,所以说next数组的话,就是除首项之外,将最大公共元素右移一位就得到了next数组,并且因为index为0时,匹配失败也要右移,所以认为设置next[0] = -1;

next数组最直观的表现已经出来了,那么接下来就是具体实现了.

2.求next数组

这里使用的是递归.

当我们知道next[j] = k时,也就是说,前j-1个字符中有k个相同字符,这时,我们要求next[j+1].

我们假设这k个字符是p0,p1,...pk-1.对应于pj-k,pj-k+1,....pj-1

分为两种情况:
	
1. 第一种很简单,就是当pk == pj,这样很简单,next[j+1] = next[j]+1 = k + 1;
2. 重点还是在于第二种情况,当pk != pj,则next[j+1] < next[j];因为next表示的是j+1前连续相同前缀后缀,并且j位不相等,所以做不到k+1位相等,更做不到k位相等.那问题是,我们下一步应该从哪里开始去寻找更短的相同前缀后缀.这里确实又要用到next数组的概念.
![](https://i.imgur.com/OtUI5NK.png)
3. 可以看到,或者说得出,p0到pk-1和Pj-k到pj-1是一一对应的.这里还是得纠正一下next的概念,指的准确的说应该是从第一个字符开始进行匹配,而不是从中间,所以新的相同前缀后缀也之后从头开始,这样的话,我们要找到最长的相同前缀后缀,就要充分利用已知,在图片中,1到2和3到4一一对应,同时在内部,1和2也是一一对应的,1和3,2和4同样,那我们要判断的是最理想情况下就是p0到pnext[k]和4加pj一一对应.为什么不能再大一点?因为4前一index和p0不相等的,如果相等,那next[k]就不是在那个地方了.不能再小的原因很明显,next[k]和pj还是可能相等的.这样我们就比较这两个数,如果相等,那next[j+1] = next[k] + 1;如果不相等,那就领k = next[next[k]];就是将范围进一步缩小,但是还是依照这样的规律.

java实现:

	public void getNext(String p, int[] next){
	    int length = p.length();
	    next[0] = -1;
	    int k = -1;
	    int j = 0;
	    while(j < length-1){
	        if(k==-1 || p.charAt(j)==p.charAt(k)){
	            ++j;
	            ++k;
	            next[j] = k;
	        }else{
	            k = next[k];
	        }
	    }
	}

python实现:
    
	def getNext(self, p, nnext):
        pLen = len(p)
        j = 0
        k = -1
        nnext.append(-1)
        while j < pLen - 1:
            if k == -1 or p[j] == p[k]:
                k += 1
                j += 1
                nnext.append(k)
            else:
                k = nnext[k]

目前来说似乎是问题已经解决了,但是,还是有一个特例需要处理.

![](https://i.imgur.com/4X4af0W.png)

如图所示的模式串,我们通过代码得到的next数组的值为:[-1,0,0,1]

于是在我们匹配过程中,到了j=3时,匹配失败,此时next[j] = 1;所以我们需要右移3-1位,得到:

![](https://i.imgur.com/kqsG2rJ.png)

我们在右移之后,应该是从第二位也就是index为1的字符开始匹配,结果是在图一种b和c不匹配,在图二中当然也不匹配.因为我们第一次是比较的p[j]和s[i],第二次是p[next[j]]和s[i],而p[next[j]]和p[j]也就是p[3]=b和p[next[3]]=p[1]=b是相等的,那么第一次不成功,第二次必然不成功.所以不能允许p[j] = p[next[j]].如果这样的情况出现,我们就再次递归,next[j] = next[next[j]] = next[1] = 0,直接右移三位.至于原因,可以理解为一下移动两次,第一次是正常移动,j-next[j],这时必匹配失败,继续右移next[j] - next[next[j]],两者相加得到j - next[next[j]],就是相当于再次递归.

改进后:

java:
	
	public void getNext(String p, int[] next){
	    int length = p.length();
	    next[0] = -1;
	    int k = -1;
	    int j = 0;
	    while(j < length-1){
	        if(k==-1 || p.charAt(j)==p.charAt(k)){
	            ++j;
	            ++k;
	            if(p.charAt(j)!=p.charAt(k)){
	                next[j] = k;
	            }else{
	                next[j] = next[k];
	            }
	        }else{
	            k = next[k];
	        }
	    }
	}

python:

    def getNext(self, p, nnext):
        pLen = len(p)
        j = 0
        k = -1
        nnext.append(-1)
        while j < pLen - 1:
            if k == -1 or p[j] == p[k]:
                k += 1
                j += 1
                if p[j] != p[k]:
                    nnext.append(k)
                else:
                    nnext.append(nnext[k])
            else:
                k = nnext[k]

KMP:

java:

	public int KMP(String s, String p){
	    int sLen = s.length();
	    int pLen = p.length();
	    int i = 0;
	    int j = 0;
	    int[] next = new int[pLen];
	    getNext(p, next);
	    while(i<sLen && j<pLen){
	        if(j==-1 || s.charAt(i)==p.charAt(j)){
	            i++;
	            j++;
	        }else{
	            j=next[j];
	        }
	    }
	    if(j==pLen){
	        return i-j;
	    }else{
	        return -1;
	    }
	}
	public void getNext(String p, int[] next){
	    int len = p.length();
	    int j = 0;
	    int k = -1;
	    next[0] = -1;
	    while(j < len - 1){
	        if(k==-1 || p.charAt(j)==p.charAt(k)){
	            ++k;
	            ++j;
	            if(p.charAt(j)!=p.charAt(k)){
	                next[j] = k;
	            }else{
	                next[j] = next[k];
	            }
	        }else{
	            k = next[k];
	        }
	    }
	}

python:

    def KMP(self,s,p):
        sLen = len(s)
        pLen = len(p)
        i = j = 0
        next = []
        self.getNext(p,next)
        while(i<sLen and j<pLen):
            if(j==-1 or s[i]==p[j]):
                i += 1
                j += 1
            else:
                j = next[j]
        if j==pLen:
            return i - j
        else:
            return -1

    def getNext(self,p,next):
        leng = len(p)
        j = 0
        next.append(-1)
        k = -1
        while j < leng-1:
            if k==-1 or p[j]==p[k]:
                k += 1
                j += 1
                if p[j] != p[k]:
                    next.append(k)
                else:
                    next.append(next[k])
            else:
                k = next[k]

3.时间复杂度:文本串长度为n,模式串长度为m,则复杂度为O(m+n).

###BM算法

KMP算法的匹配是从模式串的开头开始匹配的,而1977年,萨克斯大学的Robert S.Boyer教授和J Strother Moore教授发明了一种新的字符串匹配算法:Boyer-Moore算法,简称BM算法.该算法从模式串的尾部开始匹配,并且拥有在最坏情况下O(N)的时间复杂度.在实践中,比KMP算法的实际效能高.

BM算法定义了两个规则:
	
- 坏字符规则:当文本串中的某个字符跟模式串中的某个字符不匹配时,我们称文本串中的这个失配字符为坏字符,此时模式串需要向右移动,移动的位数 = 坏字符在模式串中的位置 - 坏字符在模式串中最右出现的位置.此外,如果"坏字符"不包含在模式串之中,则最右出现位置为-1.

-好后缀规则:当字符失配时,后移位数 = 好后缀在模式串中的位置 - 好后缀在模式串上一次出现的位置,且如果好后缀在模式串中没有再次出现,则为-1

 举例说明:文本串为:"HERE IS A SIMPLE EXAMPLE",和模式串为"EXAMPLE".

![](https://i.imgur.com/NmVZLIA.png)

1. 首先,文本串和模式串头部进行对其,然后从模式串尾部开始进行比较,因为一个是S,而另一个为E,不匹配,且S不包含在模式串之中,所以由坏后缀规则,将模式串向右移动6-(-1)=7位.得到下图.
![](https://i.imgur.com/DQ0flUd.png)

2. 在右移之后,继续比较最后一位,依旧不匹配,但是这一次坏后缀存在于模式串之中,所以向右移动6-4=2位.这个时候,模式串的后四位和文本串一一对应,形成好后缀.因为只要匹配就是好后缀,所以好后缀共有:"MPLE","PLE","LE","E".

3. 此时,出现了好后缀和坏字符共存的情况下,就要去两者之中移动最大的情况.坏字符:2-(-1)=3;好后缀:好后缀中只有E在index为0处出现,所以值为6-0=6.
![](https://i.imgur.com/t7lpPAm.png)
![](https://i.imgur.com/fjewrO8.png)
4. 右移之后,继续从尾部进行比较,根据坏字符规则,向右移动6-4=2个字符

5. 继续从后向前匹配,匹配成功
![](https://i.imgur.com/WTyJWSZ.png)
时间复杂度:最坏为O(n)

###Sunday算法

比BM算法更快的算法.

Sunday算法是由Daniel M.Sunday在1990年提出的,思路和和BM类似

只不过,Sunday算法是从前向后匹配,在匹配失败时关注的是文本串中参加匹配的最末位字符的下一个字符.

- 如果该字符没有在模式串中出现则直接跳过,移动位数 = 匹配串长度+1
- 否则,移动位数 = 模式串中最右端的该字符到末尾的距离+1

文本串:"substring searching algorithm";

模式串:"search"

1. 左对齐:

		substring searching algorithm
		search

2. 在第二位匹配失败,这是关注参加匹配的文本串的最末位字符的下一个字符,为i,在模式串中不存在,则向右移动6+1=7位

		substring searching algorithm
		       search

3. 第一位就匹配失败,这时,参加匹配的文本串的最后一个字符的下一个字符为r,则移动2+1=3位

		substring searching algorithm
		          search

4. 匹配成功.回去写一下具体实现,来搞清楚匹配串是什么