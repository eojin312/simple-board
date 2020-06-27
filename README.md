# simple-board
심플 게시판 프로젝트

# 계기

# 기술스택
  - jdk11
  - maven
  - springboot 2.2.7
  - DBMS
    - h2
    - MySQL
  - jpa
    - Spring Data JPA
    - JPA Interface 사용
    - JPQL
    - queryDSL
  - FrontEnd
    - bootstrap 5.x
    - jquery 3.5.1
    - thymeleaf
  - junit5

# SpringBoot
## package 구조
**web**
  - DTO - entity converting 용 toEntity 구현
  - Controller
    - BaseApiController - Api 추상클래스
    - *Controller - 화면
    - *ApiController - api
**Service**
**domain**
  - entity
  - repositoty

# 목표
- 단순 CRUD를 넘어선 정말 사용가능한 게시판을 만들어야
- 다른 프로젝트의 샘플로써의 역할 (템플릿)
- 기존 토이프로젝트에서 못했던 파일(혹은 이미지)첨부 기능은 필수로
- JPA를 정석으로 잘 활용해야
- 테스트 자동화 + 안정적인 테스트
