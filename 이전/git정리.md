# Git & Git hub 특강

## [ MD파일 작성 방법 ]

* 마크다운(Markdown)

  ## 1. 문법 
  
  ### 1.1 Header
  
  - `<h1>` 부터 `<h6>` 까지 표현 가능합니다.
  - `#` 의 개수로 표현하거나 `<h1></h1>` 의 형태로 표현 가능합니다.

  ### 	1.2 List

  - 순서가 있는 목록 
    - `1.` 을 누르고 스페이스바를 누르면 생성할 수 있습니다.
    - `tab` 키를 눌러서 하위 항목을 생성할 수 있고 `shift + tab` 키를 눌러서 상위 항목으로 이동 할 수 있습니다.
  - 순서가 없는 목록
    - `-` (하이픈)을 쓰고 스페이스바를 누르면 생성할 수 있습니다
    - `tab` 키를 눌러서 하위 항목을 생성할 수 있고 `shift + tab` 키를 눌러서 상위 항목으로 이동 할 수 있습니다.

  ### 	1.3 Code Block

  - Inline
    - 인라인 블럭으로 처리하고 싶은 부분을 **`** (백틱) 
  - Block
    - **`** (백틱) 을 3번 입력하고 `Enter` 를 눌러 생성

  

  `add` 한 요소를 remote 저장소에 올리려면 `$ git push origin master` 를 터미널에 입력

  ```bash
  $ git add .
  $ git commit -m "first commit"
  $ git push origin master
  ```

  

  ### 1.4 Image

  - `![]()` 을 작성하고 `()` 안에 이미지 주소를 입력하고 `[]` 안에는 이미지 파일의 이름을 작성하기
  - 로컬에 이미파일을 저장한 경우 절대 경로가 아닌 상대 경로를 사용하여 이미지를 저장하기

  ![git-github-image](https://miro.medium.com/max/3586/1*mtsk3fQ_BRemFidhkel3dA.png)

  ### 1.5 Link

  - `[]()` 을 작성하고 `()` 안에 링크 주소를 작성하고 `[]` 안에 어떤 링크 주소인지 작성하기

  

  [git 공식문서](https://git-scm.com/)

  [github 공식문서](https://github.com/)

  

  ### 1.6 Table

  - `|` (파이프) 사이에 컬럼을 작성하고 `enter` 를 입력
  - 마지막 컬럼을 작성하고 뒤에 `|` 를 쓰기

  | working directory | statging area | remoe repo |
  | ----------------- | ------------- | ---------- |
  | working tree      | index         | history    |
  | working copy      | cache         | tree       |
  |                   |               |            |

  

  ### 1.7 기타

  **인용문** 

  - `>` 을 입력하고 `enter` 

  > git은 컴퓨터 파일의 변경사항을 추적하고 여러 명의 사용자들 간에 해당 파일들의 작업을 조율하기 위한 분산 버전 관리 시스템이다.

  - 인용문 안에 인용문을 작성하면 중첩해서 사용 가능

  > $ git add .
  >
  > > $ git commit -m "first commit"
  > >
  > > > $ git push origin master

  

  **수평선**

  - `---` , `***` , `___` 을 입력하여 작성

  Working Directory

---

  Staging Area

***

  Remote Repository

___

  

  **강조**

  - 이탤릭체는 해당 부분을 `*` 혹은 `_` (언더바) 로 감싸기
  - 보드체는 해당 부분을 `**` 혹은 `__` (언더바 2개)로 감싸기
  - 취소선은 `~~` 표시를 사용

  이것은 *이탤릭체*입니다.

  이것은 **보드체**입니다.

  이것은 ~~취소선~~입니다.



## [Git 기초 ]

### 1. **Git 초기 설정**

> **최초 한번만 설정**

- 누가 기록을 남겼는지 확인하도록 이름과 이메일을 설정

​	```git config --global user.name "깃허브 이름"```

​	```git config --global user.email "깃허브 메일 주소"```

* 올바르게 설정되었는지 확인 

  git config --global -l

### 2. Git 기본 명령어

> (1) git init

* 현재 작업 중인 디렉토리를 Git 으로 관리한다는 명령어
* ❗❗ 이미 git 저장소인 폴더에 또 다른 git 만들지  ❌
* ❗❗ 절대로 홈 디렉토리에서 git init ❌ 경로가 그 폴더인지 ~ 인지 확인하기

> (2) git status

* 현재 파일의 상태를 알려주는 명려어

* `Untracked` : Git이 관리하지 않는 파일 (한번도 Staging Area에 올라간 적 없는 파일)

* ```
  Tracked
  ```

   : Git이 관리하는 파일

  1. `Unmodified` : 최신 상태
  2. `Modified` : 수정되었지만 아직 Staging Area에는 반영하지 않은 상태
  3. `Staged` : Staging Area에 올라간 상태

> (3) git add

- 특정 파일

​	`$ git add a.txt `

- 특정 폴더

​	 ```$ git add my_folder```

* 현재 디렉토리에 속한 파일/폴더 전부

  ```$ git add .```

- Working Directory에 있는 파일을 Staging Area로 올리는 명령어
- Git이 해당 파일을 추적(관리)할 수 있도록 만듭니다.
- `Untracked, Modified → Staged` 로 상태를 변경합니다.

>  (4) git commit

* git commit -m "메시지"

> (5) git log

- 커밋의 내역을 조회할 수 있는 명령어
  - `--oneline` : 한 줄로 축약해서 보여줍니다.
  - `--graph` : 브랜치와 머지 내역을 그래프로 보여줍니다.
  - `--all` : 현재 브랜치를 포함한 모든 브랜치의 내역을 보여줍니다.
  - `--reverse` : 커밋 내역의 순서를 반대로 보여줍니다. (최신이 가장 아래)
  - `-p` : 파일의 변경 내용도 같이 보여줍니다.
  - `-2` : 원하는 갯수 만큼의 내역을 보여줍니다. (2 말고 임의의 숫자 사용 가능)

  

 ### 3. Git 의 올리기

####  로컬 저장소와 원격 저장소 연결

1. 원격 저장소가 잘 생성되었는지 확인 후, 저장소의 주소를 복사
   
    ![image-20220411174637390](git정리.assets/image-20220411174637390.png)
    
2. 기존에 실습 때 만들었던 홈 디렉토리의 TIL 폴더로 가서 vscode를 열기

1. git init을 통해 TIL 폴더를 로컬 저장소로 만들기
   
    ```bash
    kyle@KYLE MINGW64 ~/TIL
    $ git init
    Initialized empty Git repository in C:/Users/kyle/TIL/.git/
    ```
    
1. **git remote**
    - 로컬 저장소에 원격 저장소를 `등록, 조회, 삭제`할 수 있는 명령어
    1. **원격 저장소 등록**
       
        `git remote add <이름> <주소>` 형식으로 작성
        
        ```bash
        $ git remote add origin https://github.com/edukyle/TIL.git
        
        [풀이]
        git 명령어를 작성할건데, remote(원격 저장소)에 add(추가) 한다.
        origin이라는 이름으로 https://github.com/edukyle/TIL.git라는 주소의 원격 저장소를
        ```
        
    2. **원격 저장소 조회**
       
        `git remote -v` 로 작성
        
        ```bash
        $ git remote -v
        origin  https://github.com/edukyle/TIL.git (fetch)
        origin  https://github.com/edukyle/TIL.git (push)
        
        add를 이용해 추가했던 원격 저장소의 이름과 주소가 출력됩니다.
        ```
        
    3. **원격 저장소 연결 삭제**
       
        `git remote rm <이름>` 혹은 `git remote remove <이름>` 으로 작성
        
        > 로컬과 원격 저장소의 연결을 끊는 것이지, 원격 저장소 자체를 삭제하는 게 X
        > 
        
        ```bash
        $ git remote rm origin
        $ git remote remove origin
        
        [풀이]
        git 명령어를 작성할건데, remote(원격 저장소)와의 연결을 rm(remove, 삭제) 한다.
        그 원격 저장소의 이름은 origin이다.
        ```
        

##### (3) 원격 저장소에 업로드

- **커밋을 업로드!**
- 따라서 먼저 로컬 저장소에서 커밋을 생성해야 원격 저장소에 업로드 가능

1. **로컬 저장소에서 커밋 생성**
   
    ```bash
    ### 현재 상태 확인
    
    $ git status
    On branch master
    
    No commits yet
    
    Untracked files:
      (use "git add <file>..." to include in what will be committed)
            day1.md
    
    nothing added to commit but untracked files present (use "git add" to track)
    ```
    
    ```bash
    $ git add day1.md
    ```
    
    ```bash
    $ git commit -m "Upload TIL Day1"
    [master (root-commit) f3d6d42] Upload TIL Day1
     1 file changed, 0 insertions(+), 0 deletions(-)
     create mode 100644 day1.md
    ```
    
    ```bash
    ### 커밋 확인
    
    $ git log --oneline
    f3d6d42 (HEAD -> master) Upload TIL Day1
    ```
    
1. **git push**
    - 로컬 저장소의 커밋을 원격 저장소에 업로드하는 명령어
    - `git push <저장소 이름> <브랜치 이름>` 형식으로 작성
    - `-u` 옵션을 사용하면, 두 번째 커밋부터는 `저장소 이름, 브랜치 이름`을 생략 가능
    
    ```bash
    $ git push origin master
    
    [풀이]
    git 명령어를 사용할건데, origin이라는 이름의 원격 저장소의 master 브랜치에 push 한다.
    
    ------------------------------------------------
    
    $ git push -u origin master
    이후에는 $ git push 라고만 작성해도 push가 됩니다.
    ```
    
1. **vscode 자격 증명**
   
    - Sign in with your browser를 클릭
    
    - Authorize GitCredentialManager를 클릭
    
    * 정상적으로 자격 증명이 완료
    
    * 이후 git push 완료
    
    ```bash
    $ git push -u origin master
    info: please complete authentication in your browser...
    Enumerating objects: 3, done.
    Counting objects: 100% (3/3), done.
    Writing objects: 100% (3/3), 218 bytes | 218.00 KiB/s, done.
    Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
    To https://github.com/edukyle/TIL.git
     * [new branch]      master -> master
    Branch 'master' set up to track remote branch 'master' from 'origin'.
    ```
    
2. **원격 저장소에서 정상 업로드 확인**
   
    ![image-20220411174745666](git정리.assets/image-20220411174745666.png)
    

<aside>
❗ **(주의) Github 원격 저장소에 절대로 파일을 드래그해서 업로드 ❌!!!!**


 반드시 git add → git commit → git push 의 단계로만 업로드하기

그 이유는 로컬 저장소와 원격 저장소의 동기화 때문입니다.

로컬 저장소에서 변경이 먼저 일어나고, 그 변경 사항을 원격 저장소에 반영하는 형태여야 합니다. 하지만, Github에 드래그를 해서 파일을 업로드하면 원격 저장소에 변경이 먼저 일어나는 형태가 되기 때문에 이러한 행동을 지양해야 합니다.

</aside>

1. `git push`를 그림으로 이해하기

![image-20220411174806638](git정리.assets/image-20220411174806638-16496668878351.png)

로컬 저장소의 commit 이력이 원격 저장소에 그대로 반영

#### [3] 실습

##### (1) README.md 파일 push 해보기

- `README.md`는 원격 저장소의 소개와 설명이 담겨있는 역할
- 반드시 파일 이름을 `README.md`로 지정
- 홈 디렉토리에 있는 TIL 폴더에 `README.md` 파일을 생성하고, 자유롭게 `설명, 각오, 분류 등`을 마크다운 문법으로 작성하고 자신의 Github TIL 원격 저장소에 push를 해봅니다.