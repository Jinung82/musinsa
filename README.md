# 이진웅 무신사 과제 제출

이 프로젝트는 springboot + JPA + H2BASE를 기본으로 언어는 kotlin을 이용하여 restful API를 구현 하였습니다. 

## 프로젝트 환경 
 1.java version : java-temulin-17 

 2.spring boot version : 3.1.5

 3.kotlin version : 1.8.22
 
 4.그외 라이브러리 참조 : build.gradle.kts

### `프로젝트 실행 방법`

1. gradle build를 이용하여 프로젝트를 빌드

2. 2.kotlin/com/musinsa/MusinsaApplication.kt 를 이용하여 intellij에서 spring boot 컨포넌트를 이용하여 서버를 실행한다.


### `API 목록`

1. 전체 과제 API는  kotlin/com/musinsa/controller/ProductController.kt 안에 존재 한다.
2. 1번 문제 API (method = get) : http://localhost:8080/api/products/all
3. 2번 문제 API (method = get): http://localhost:8080/api/products/brand
4. 3번 문제 API (method = get): http://localhost:8080/api/products/category/{categoryName}
5. 4번 문제 API (method = post): 추가, 업데이트, 삭제
6.전체 requestBody 예제: <code> {
      "category": "상의",
      "brand": "J",
      "price": "100"}</code>
   
7. 카테고리 추가 API (method = post): http://localhost:8080/api/product/save 
     
8. 카테고리 가격 업데이트 API (method = post): http://localhost:8080/api/product/update

9. 카테고리 삭제 API (method = post) : http://localhost:8080/api/product/delete

### `TEST CODE 작성`

1. src/test 폴더 밑으로 repository, service layer 에 대해서 테스트 코드 구성 
2. repository TEST : 직접 persistence layer 이므로 springBootTest 어노테이션을 이용하여 실제 데이터를 가져오도록 구성
3. service TEST : repository 객체 및 그외 인스턴스는 mock 객체로 구성하여 테스트 코드 구성

### ExceptionHandler 를 CommonController에 구현

