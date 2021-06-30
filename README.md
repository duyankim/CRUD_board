# CRUD_board
[mini project] java 게시판 만들기

## 폴더 구조
```
src
└─main
    ├─java
    │  └─kr
    │      └─ac
    │          └─kopo
    │              ├─dao
    │              │      BoardDao.java
    │              │      BoardDaoImpl.java
    │              │      BoardItemDao.java
    │              │      BoardItemDaoImpl.java
    │              │
    │              ├─domain
    │              │      Board.java
    │              │      BoardItem.java
    │              │
    │              └─service
    │                      BoardItemService.java
    │                      BoardItemServiceImpl.java
    │                      BoardService.java
    │                      BoardServiceImpl.java
    │
    └─webapp
        │  delete.jsp
        │  index.jsp
        │  insertDB.jsp
        │  postlist.jsp
        │  postview.jsp
        │  search.jsp
        │  update.jsp
        │  write.jsp
        │
        ├─assets
        │  ├─css
        │  │      search.css
        │  │      styles.css
        │  │      viewComment.css
        │  │      writeComment.css
        │  │
        │  └─js
        │          form.js
        │          scripts.js
        │          textEditor.js
        │
        ├─META-INF
        │      MANIFEST.MF
        │
        └─WEB-INF
            └─lib
                    mysql-connector-java-8.0.23.jar
```

## 구현 화면
### 게시판 리스트 조회
![1](https://user-images.githubusercontent.com/46421950/123925332-c4e41a00-d9c5-11eb-857e-b4f7ba93d827.png)

### 게시글 리스트 조회
![2](https://user-images.githubusercontent.com/46421950/123925567-01177a80-d9c6-11eb-9734-dcb4afd5448a.png)

### 게시글 조회
![3](https://user-images.githubusercontent.com/46421950/123925627-0ffe2d00-d9c6-11eb-80c1-c52f79d34bb7.png)

### 댓글 작성
![4](https://user-images.githubusercontent.com/46421950/123925709-21dfd000-d9c6-11eb-8fec-40d955f04b5f.png)

### 게시글 작성
![5](https://user-images.githubusercontent.com/46421950/123925758-2e642880-d9c6-11eb-98b5-194f6554ed33.png)

### 게시글 수정
![7](https://user-images.githubusercontent.com/46421950/123928948-51440c00-d9c9-11eb-835f-e467fe7531ba.png)

### 게시글 삭제
![8](https://user-images.githubusercontent.com/46421950/123928977-57d28380-d9c9-11eb-9022-42c7d9ca6358.png)

### 키워드 검색
![6](https://user-images.githubusercontent.com/46421950/123925840-4340bc00-d9c6-11eb-981b-3f1ffe261469.png)
