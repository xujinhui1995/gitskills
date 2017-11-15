#SSH秘钥配置
##起因
因为一次意外,进行了腾讯的软件搬家和更新了体验版的激进的系统,导致了很多软件不能用,并且,电脑也没有响应的音频驱动.所以就重装了系统.知道今天才想起来要向git上写一点东西,结果出现了SSH秘钥的问题.于是在网上搜索了一下解决方案.
##解决方案
1. 一般来说SSH Key在重装系统之后就没有了,所以直接生成一个新的SSH
		
		$ssh-keygen -t rsa -C "your email"

    之后会提示密码,可以自行设置,最后会提示.ssh目录的位置.找到这个位置,里面有两个文件:id_rsa和id_rsa.pub

2. 用记事本打开id_rsa.pub,复制里面的内容.进入到git里的account-setting,并选择SSH Keys,删除原有的,新建一个,将id_rsa.pub中的内容复制到Key中.
3. 测试
		
		$ssh -T git@github.com
4. 设置Git的账户信息
		
		git config --global user.name "username"
		git config --global user.email "your email"

###大功告成
