##Sunday

Sunday算法是Daniel M.Sunday于1990年提出的字符串模式匹配.其效率在匹配随机的字符串时比其他匹配算法还要更快.Sunday算法的实现比KMP,BM实现更加容易.

文本串:"LESSONS TEARNED IN SOFTWARE TE"

模式串:"SOFTWARE"

Sunday算法和BM算法类似,区别在于比较的顺序和关注的字符.

首先在左对齐之后,开始从左到右比较:

在匹配失败后,关注模式串最后一个字符对应的文本串的字符的下一个字符,

如果这个字符在模式串中不存在,则右移的位数 = 模式串长度 + 1.

如果存在,则移动位数 = 模式串的位置到末尾的位置 + 1

		LESSONS TEARNED IN SOFTWARE TE
		SOFTWARE

不匹配,则关注T,T在模式串中存在,则右移 4 + 1 = 5.

		LESSONS TEAREND IN SOFTWARE TE
		     SOFTWARE

不匹配,则关注N,N在模式串中不存在,则右移 8 + 1 = 9

		LESSONS TEAREND IN SOFTWARE TE
		            SOFTWARE

不匹配,则关注O,O在模式串中存在,则右移 6 + 1 = 7

		LESSONS TEAREND IN SOFTWARE TE
		                   SOFTWARE

匹配.