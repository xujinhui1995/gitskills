# gitskills
##常用Git命令

1. 设置全局的用户名:`git config --global user.name "xujinhui1995"`
2. 设置全局的邮箱:`git config --global user.email "xujinhui1995@gmail.com"`
3. 上传文件到stage暂存区:`git add README.md`
4. 进行stage暂存区文件的提交:`git commit -m "upload readme.md"`
5. 提交后回退到上一个版本:`git resest -hard HEAD^`
6. 提交后回退到上2个版本:`git reset -hard HEAD^^`或`git reset -hard HEAD~2`
7. 检查当前的git提交状态:`git status`
8. 查看暂存区和工作区的区别:`git diff`
9. 查看本地的提交记录:`git log`
10. 查看历史命令以及操作序列号:`git reflog`
11. 舍弃修改,使得工作区与暂存区一致:`git checkout -- README.md`
12. 舍弃add,使得暂存区与版本库一致:`git reset HEAD README.md`
13. 回退到某一版本:`git reset 097897 -- RAEDME.md`