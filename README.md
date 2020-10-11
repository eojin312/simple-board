# simple-board

심플 게시판 프로젝트 향후에 다른 프로젝트 개발할 때 이 프로젝트의 기술스택 예제들을 보며 참고하면서 개발하기 위한 템플릿 프로젝트

# 기술스택
  - jdk11
  - maven
  - springboot 2.2.7
  - spring security
  - DBMS
    - h2
  - jpa
    - Spring Data JPA
    - JPA Interface 사용
    - JPQL
  - FrontEnd
    - bootstrap 5.x
    - jquery 3.5.1
    - thymeleaf
  - junit5

# SpringBoot

**package 구조**
- web
  - dto : entity converting 용 toEntity 구현
  - controller
    - BaseApiController - Api 추상클래스
    - *Controller - 화면
    - *ApiController - api
    
- service
  - business 로직처리
- domain
  - entity
  - repositoty
  
**기본 정책**
- entity 는 domain 내에 격리되어야한다
- setter 는 사용하지않는다
- 모든 객체는 생성자로 생성한다.
  - 그래서 @Builder를 활용해 귀차니즘 및 실수가능성을 극복
  
## Spring Security
  - 로그인/로그아웃 + 권한제어 기능을 추가하려고 spring security 를 추가했습니다.
  
  
**SpringSecurityConfig**

```java
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .mvcMatchers("/users/**").hasRole("ADMIN")
            .mvcMatchers("/posts/**").hasRole("MEMBER")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .csrf()
            .disable();
    }
}
```
|메소드|설명|
|------|---|
|authorizeRequests()|HttpServletRequest 를 이용해서 인증 처리
|mvcMatchers()|mvcMatcher(String mvcPatter) - 제공된 Spring MVC 패턴과 일치하는 경우에만 HttpSecurity를 호출하도록 설정할 수 있습니다.
|antMatchers()|AntMatcher(String AntPatter) - 제공된 ant 패턴과 일치하는 경우에만 HttpSecurity를 호출하도록 설정할 수 있습니다
|hasRole()|특정 권한 있는 사람만 접근 가능합니다.
|permitAll()| 모든 사용자가 접근 가능하다는 것을 의미합니다.
|anyRequest().permitAll()|그 외 모든 요청은 접근 가능
|csrf()|Token 정보를 Header 정보에 포함하여 서버 요청을 시도하는 것 (쉽게 말해 보안 강화)

**UserAuthService**
```java
public class UserAuthService implements UserDetailsService {
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지않는 회원입니다"));
    List<GrantedAuthority> authorities = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    authorities.add(authority);
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
```

|코드|설명|
|------|---|
|findByUserName|로그인 처리를 하기 전 username (=loginId) 를 가져옵니다.
|UserDetails|Spring Security에서 사용자의 정보를 담는 인터페이스는 UserDetails 인터페이스우리가 이 인터페이스를 구현하게 되면 Spring Security에서 구현한 클래스를 사용자 정보로 인식하고 인증 작업을 합니다
|UserDetailsService|DB에서 유저 정보를 직접 가져오는 인터페이스를 구현
|GrantedAuthority|권한 있는 사용자인지 판단해주는 타입
|UsernameNotFoundException|UsernameNotFoundException 은 Spring Security 에서 제공하는 Exception 입니다

![Jun-27-2020 14-41-57](https://user-images.githubusercontent.com/45488643/85915987-22abc680-b887-11ea-9eeb-e97cb3f5437d.gif)

더 자세한 spring security 설명은 제 블로그를 참조해주세요 -> [Spring Security 도전기](https://eojin312.github.io/%EA%B3%B5%EB%B6%80/springsecurity/)


## Front 구현
  tymeleaf 최소로 사용 -> 
  가급적 jquery 로 UI 렌더링 -> 향후 Vue.js 로 전환 계획
  
## Unit Test
- Junit5
- repository Test
- service Test
  - mock Test
  - service Test
- mvcmock Test

# 목표
- 단순 CRUD를 넘어선 정말 사용가능한 게시판을 만들어야
- 다른 프로젝트의 샘플로써의 역할 (템플릿)
- 기존 토이프로젝트에서 못했던 파일(혹은 이미지)첨부 기능은 필수로
- JPA를 정석으로 잘 활용해야
- 테스트 자동화 + 안정적인 테스트

# CRUD 외의 기능
- 로그인 기능
- 로그인 후 글 좋아요
- 페이지네이션
- 검색 기능

# 회고
  - 전 프로젝트에선 http session 만 이용해서 로그인 기능을 만들었지만 지금 springboot 를 사용하는 시점에서 spring security 를 처음으로 사용하니 처음엔 많이 어렵고 흔하지않은 체이닝 식 코드라 적응하기 힘들었지만 많이 알아보고 연습하니 익숙해지고 더 많은 기술을 사용하게 되었다
  - spring-jpa 를 처음 사용했던 프로젝트입니다.날쿼리를 사용하지않는 수준으로 jpa 의 장점을 익혔습니다. 
  - 간단한 기능이고, 프리티어의 AWS라 별도 비용도 지불하지는 않고 있지만,  실제 서비스를 제공한 첫번째 프로젝트였습니다.
