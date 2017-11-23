class Sunday{
	public int searchSubstring(String s, String p){
		int sLen = s.length();
		int pLen = p.length();
		if(sLen < pLen) 
			return -1;
		int i = 0;
		int j = 0;
		while(i<sLen && j<pLen){
			if(s.charAt(i) == p.charAt(j)){
				++i;
				++j;
			}else{
				if(sLen == pLen || i+pLen-j>=sLen){
					return -1;
				}
				index = p.indexOf(s.charAt(i - j + pLen));
				if(index == -1){
					i = i - j + pLen + 1;
				}else{
					i = i + pLen - index - j;
				}
				j = 0;
			}
		}
		if(j == pLen){
			return i - j;
		}else{
			return -1;
		}
	}
}