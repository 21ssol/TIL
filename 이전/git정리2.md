# Git 특강 2

---

## [1] git clone - 복제하기(협업할 때)

> git clone은 맨 처음 한번만 실행해야 한다.

* ```git clone <원격 저장 주소>``` 
  - ![image-20220412142908408](git정리2.assets/image-20220412142908408.png)
  - git clone을 통해 생성된 로컬 저장소에 'git init' 과 'git remote add'가 수행되어 있다.
* `git pull <저장소 이름> <브랜치 이름>`
  * 로컬 저장소랑 원격 저장소의 내용이 일치하면 git pull을 해도 변화x
  * ![image-20220412143155280](git정리2.assets/image-20220412143155280.png)

![image-20220412143210815](git정리2.assets/image-20220412143210815.png)

### (1) 규칙

* git clone -> git push -> git pull

  * git push : 

    - 수정 > 저장 (git add .) > 커밋(git commit -m '') > git push origin master

  * 주의❗

    ![image-20220412143712819](git정리2.assets/image-20220412143712819.png)

​						![image-20220412143730061](git정리2.assets/image-20220412143730061.png)



## [2] git branch - 테스트 해보는 공간

> 나뭇가지처럼 하나의 프로젝트를 작업공간을 나누어 독립적으로 작업할 수 있도록 하는 도구

> branch 에서의 작업은 master 에는 영향을 받지 않는다.

* git branch - 관련 명령어

  * git branch : 브랜치 목록 확인
  * git branch -r : 원격 저장소의 브랜치 목록 확인
  * git branch <브랜치 이름> : 새로운 브랜치 생성
  * git branch <브랜치 이름> <커밋 ID> : 특정 커밋 기준으로 브랜치 생성
  * git branch -d <브랜치 이름> : 브랜치 삭제  # 병합된 브랜치만 삭제 가능 
  *  git branch -D <브랜치 이름> : 브랜치 삭제 # (주의) 강제 삭제 (병합되지 않은 브랜치도 삭제 가능

* git swtich

  * git switch <다른 브랜치 이름> : 다른 브랜치로 이동
  * git switch -c <브랜치 이름> : 브랜치를 새로 생성과 동시에 이동
  * git switch -c <브랜치 이름> <커밋 ID> : 특정 커밋 기준으로 브랜치 생성과 동시에 이동

  

### [2]-1 git branch merge - 브랜치 결합

* git merge <합칠 브랜치 이름> : 브랜치 합치기

### [2]-2 git branch conflict - 브랜치 충돌

1. `hotfix` 브랜치를 생성 및 이동합니다.

   ```bash
   $ git switch -c hotfix
   ```

2. 특정 작업 완료 후 커밋합니다.

   ```bash
   # test.txt 수정
   
   master test 1
   **이건 hotfix에서 작성한 문장입니다.**
   ```

   ```bash
   $ git add .
   $ git commit -m "hotfix test 1"
   
   $ git log --oneline --graph --all
   * ad045fa (HEAD -> hotfix) hotfix test 1
   *   ac0e971 (master) Merge branch 'signout'
   |\\
   | * bcade83 signout test 1
   * | 48bd5a6 master test 2
   |/
   * df231d0 login test 1
   * 1e62b4c master test 1
   ```

3. `master` 브랜치로 이동합니다.

   ```bash
   $ git switch master
   ```

4. 특정 작업(`hotfix` 와 동일 파일의 동일 부분 수정) 완료 후 커밋합니다.

   ```bash
   # text.txt 수정
   
   master test 1
   **이건 master에서 작성한 문장입니다.**
   ```

   ```bash
   $ git add .
   $ git commit -m "master test 3"
   
   $ git log --oneline --all --graph
   * 3170247 (HEAD -> master) master test 3
   | * ad045fa (hotfix) hotfix test 1
   |/
   *   ac0e971 Merge branch 'signout'
   |\\
   | * bcade83 signout test 1
   * | 48bd5a6 master test 2
   |/
   * df231d0 login test 1
   * 1e62b4c master test 1
   ```

5. `master`에 `hotfix`를 병합합니다.

   ```bash
   $ git merge hotfix
   ```

6. 결과 → merge conflict 발생 (**같은 파일의 같은 문장을 수정했기 때문입니다!**)

   ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/072a8eb0-4fa6-4759-806e-6ae38069eb8e/Untitled.png)

7. 충돌 확인 및 해결

   - Merge Conflict가 일어났을 때 Git이 어떤 파일을 Merge 할 수 없었는지 살펴보려면 `git status` 명령을 이용합니다.

   ```bash
   $ git status
   On branch master
   You have unmerged paths.
     (fix conflicts and run "git commit")
     (use "git merge --abort" to abort the merge)
   
   Unmerged paths:
     (use "git add <file>..." to mark resolution)
           both modified:   test.txt
   
   no changes added to commit (use "git add" and/or "git commit -a")
   ```

   ```
   master test 1
   <<<<<<< HEAD
   이건 master에서 작성한 문장입니다.
   =======
   이건 hotfix에서 작성한 문장입니다.
   >>>>>>> hotfix
   ```

   - `=======` 위쪽의 내용은 HEAD 버전(merge 명령을 실행할 때 작업하던 `master` 브랜치)의 내용이고 아래쪽은 `hotfix` 브랜치의 내용입니다. 충돌을 해결하려면 위쪽이나 아래쪽 내용 중에서 고르거나 새로 작성하여 Merge 해야 합니다. (`<<<<<<<, =======, >>>>>>>` 가 포함된 행은 삭제)

   ```bash
   # test.txt 최종본
   
   master test 1
   이건 충돌을 해결한 후의 문장입니다.
   ```

8. merge commit 진행합니다.

   ```bash
   $ git add .
   $ git commit
   ```

   - vim 편집기 등장

     ```bash
     Merge branch 'hotfix'
     
     # Conflicts:
     #       test.txt
     #
     # It looks like you may be committing a merge.
     # If this is not correct, please run
     #       git update-ref -d MERGE_HEAD
     # and try again.
     
     # Please enter the commit message for your changes. Lines starting
     # with '#' will be ignored, and an empty message aborts the commit.
     #
     # On branch master
     # All conflicts fixed but you are still merging.
     #
     ```

   - 작성된 커밋 메세지를 확인하고 `esc` 를 누른후 `:wq` 를 입력하여 저장 & 종료합니다.

     ```bash
     $ git commit
     [master 8ef1443] Merge branch 'hotfix'
     ```

9. log 확인

   ```bash
   $ git log --oneline --all --graph
   *   8ef1443 (HEAD -> master) Merge branch 'hotfix'
   |\\
   | * ad045fa (hotfix) hotfix test 1
   * | 3170247 master test 3
   |/
   *   ac0e971 Merge branch 'signout'
   |\\
   | * bcade83 signout test 1
   * | 48bd5a6 master test 2
   |/
   * df231d0 login test 1
   * 1e62b4c master test 1
   ```

10. `hotfix` 브랜치를 삭제합니다.



