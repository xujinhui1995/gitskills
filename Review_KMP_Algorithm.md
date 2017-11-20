##简介
KMP算法全称是Knuth-Morris-Pratt algorithm.是由Donald Knuth, Vaughan Pratt, James H.Morris三人于1977年联合发表,算法名字也由此而来.

##暴力匹配算法
在处理字符串匹配问题时,第一个大家普遍能够想到的解决方案就是暴力匹配.具体思想就是字符串s和模式串p从第一个字母开始进行匹配,如果匹配成功,则s和p的索引i,j分别++,并继续循环,直到全部匹配成功或匹配失败.匹配失败后,i=i+1;j重置为0.具体的实现如下:

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

既然也在学习Python,就用Python再实现一下.
