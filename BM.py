class BM(object):
	def searchSubstring(self, s, p):
		sLen = len(s)
		pLen = len(p)
		i = j = pLen - 1
		while i<sLen and j>=0:
			if s[i] == p[j]:
				i -= 1
				j -= 1
			else:
				if j == sLen-1:
					i = i + j - p.rfind(s[i])
				else:
					i1 = i + j - p.rfind(s[i])
					index = p.find(p[pLen-1]) if p.find(p[pLen-1])==pLen-1 else -1
					i2 = i + pLen - 1 - index
					i = pLen - 1 - j + i1 if i1 > i2 else i2
					j = pLen - 1
		if j<0:
			return i + 1
		else:
			return -1