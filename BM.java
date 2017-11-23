class BM{
	public int searchSubstring(String s, String p){
		int sLen = s.length();
		int pLen = p.length();
		if(sLen<pLen)
			return -1;
		int j = pLen - 1;
		int i = j;
		while (i < sLen && j >= 0) {
			if (s.charAt(i) == p.charAt(j)) {
				i--;
				j--;
			} else {
				if (j == pLen - 1) {
					i = i + j - p.lastIndexOf(s.charAt(i));
					j = pLen - 1;
				} else {
					int i1 = i + j - p.lastIndexOf(s.charAt(i));
					int index = p.indexOf(p.charAt(pLen - 1)) == j ? (-1) : p.indexOf(p.charAt(pLen - 1));
					int i2 = i + pLen - 1 - index;
					i = pLen - 1 - j + (i1 > i2 ? i1 : i2);
					j = pLen - 1;
				}
			}
		}
		if (j < 0) {
			return i + 1;
		} else {
			return -1;
		}
	}
}