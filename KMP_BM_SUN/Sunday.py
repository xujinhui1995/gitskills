def searchSubstring(self, s, p):
    sLen = len(s)
    pLen = len(p)
    i = j = 0
    while i<sLen and j<pLen:
        if s[i]==p[j]:
            i += 1
            j += 1
        else:
            if sLen==pLen or i + pLen - j>=sLen:
                return -1
            index = p.find(s[i-j+pLen])
            if index == -1:
                i = i - j + pLen + 1
            else:
                i = i + pLen - index - j
            j = 0
    if j==pLen:
        return i - j
    else:
        return -1