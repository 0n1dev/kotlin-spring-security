## 사용자 정의 보안 기능 구현

- WebSecurityConfigurerAdapter (Deprecated)
  - 스프링 시큐리티의 웹 보안 기능 초기화 및 설정
  - HttpSecurity를 생성
    - 세부적인 보안 기능을 설정할 수 있는 API 제공
---
## Spring Security without the WebSecurityConfigurerAdapter

- Spring Security 5.7.0-M2에서는 `WebSecurityConfigurerAdapter`가 더 이상 사용되지 않음.
- `SecurityFilterChain` 빈을 등록하는 것을 권장.

[공식 문서 내용](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)