# 레이어드 아키텍처 학습
<img src="https://img.shields.io/badge/openjdk_17-000000?style=for-the-badge&logo=openjdk&logoColor=white" alt="openjdk-17">
<img src="https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="spring-boot">
<img src="https://img.shields.io/badge/h2-4479A1?style=for-the-badge&logo=wikidata&logoColor=white" alt="h2">

---
## 프로젝트 주요 기능과 목적
레이어드 아키텍처를 학습하기 위함입니다.  
최종적으로 ***레이어드 아키텍처 Template*** 을 만드는 것이 목표입니다.
---
## 설치 방법
- JDK 17 로 프로젝트를 실행하면 됩니다.

## 테스트 방법
1. LayeredArchitectureApplication 을 실행합니다.
2. 아래 API 를 호출합니다.
```
http://localhost:8080/member?id=1
http://localhost:8080/member?id=2
http://localhost:8080/member?id=3
```
---
## 라이브러리 정보
- spring-boot-starter
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-test
- lombok
- h2
- junit-platform-launcher
