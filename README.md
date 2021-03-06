# gitskills
## 常用Git命令

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
13. 回退到某一版本:`git reset --hard 90f890f`
14. 删除文件:`git rm README.md`
15. 关联远程库:`git remote add origin git@github.com:xujinhui1995/gitskills.git`
16. 第一次推送:`git push -u origin master`
17. 以后的推送:`git push origin master`
18. 将远程库克隆到本地:`git clone git@github.com:xujinhui1995/gitskills.git`
19. 创建新的分支:`git branch dev`
20. 转到新的分支:`git checkout dev`
21. 创建并转到新的分支:`git checkout -b dev`
22. 将某分支合并到当前分支:`git merge dev`
23. 删除分支:`git branch -d dev`
24. 查看分支:`git branch`
25. 查看分支合并图:`git log --graph`
26. 储存当前工作现场:`git stash`
27. 查看存储的工作现场列表:`git stash list`
28. 恢复工作现场:`git stash apply	`
29. 删除stash:`git stash drop`
30. 恢复工作现场并删除stash:`git stash pop`
31. 恢复某一工作现场:`git stash apply stash@{0}`
32. 强制删除分支:`git checkout -D feature-vulcan`

### 多人合作模式
1. 直接提交:`git push origin branch-name`
2. 推送失败,则更新本地,尝试合并:`git pull`
3. 无冲突之后,继续推送:`git push origin branch-name`
4. 在本地创建和远程分支的对应:`git checkout -b branch-name origin/branch-name`
5. "no tracking information":本地分支和远程分支没有连接关系`git branch --set-upstream dev origin/dev`

### 标签
1. 新建标签,默认为HEAD:`git tag <name>`
2. 指定标签信息:`git tag -a <tagname> -m "tag information"`
3. PGP签名标签:`git tag -s <tagname> -m "tag information"`
4. 查看标签:`git tag`
5. 将本地标签推送到远程:`git push origin <tagname>`
6. 推送全部未推送的标签:`git push origin --tags`
7. 删除本地标签:`git tag -d <tagname>`
8. 删除远程标签:`git push origin :refs/tags/<tagname>`

## 其他
1. 忽略文件:将文件加入.gitignore文件,可以用*.后缀
2. 强制添加被忽略文件:`git add -f file.class` 