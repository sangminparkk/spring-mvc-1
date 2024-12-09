# 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
reference : [스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1)
* 하게 된 이유 : Spring MVC 구조를 이해하지 못한 채 개발하고 있어, 나중에 현업에서 마주하게 될 문제를 정확히 정의하고 해결하기 위함. 깊이 있는 구조 이해가 필요하다고 판단했습니다.
* 프로젝트 시작일 : 11/26
* 프로젝트 종료일 : ing (mvc 2편에서 타임리프 내용 추가)

## 개발환경
* gradle
* java 17
* spring boot 3.4.0
* library
  * thymeleaf
  * spring web
  * lombok
  * h2
  * dev-tools

## Things to think about
* Spring MVC 구조 : Front-Controller / Handler-Adapter
* HTTP 요청 데이터
  * Get query-string
  * Post html form
  * HTTP API - text/json
* HTTP 응답 데이터
  * html
  * json

TBD..

## Things to do
TBD..

## Things I am doing
* 타임리프 내용 추가(12/6~) 

## Thymeleaf (내용 정리)

### 기본 기능
* text :  escaped 기본 지원 (현업 서비스 권장)
* utext : unescaped, 반드시 필요할 때만 사용할 것
* SpringEL(Expression Language) :  model에 담긴 데이터를 사용할 때 변수표현식(`${}`) 사용
  * 접근방식 : property (.fieldName op .getFieldName)
* 편의 객체
  * HTTP 요청 파라미터 접근 : `${param.xx}`
  * HTTP Session 접근 : `${session.xx}`
  * Spring bean 접근 : `@BeanName.method`
* URL
  * a 태그 + th:href
  * 경로 변수에 변수가 없으면(=치환할 대상이 없으면) query-string 자동 적용합니다.
  * Query-string Vs. Path variable
```thymeleafexpressions
Query-string : @{/hello(param1=${param1}, param2=${param2})}   /hello?param1=xxx&param2=xxx
path-variable : @{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}  /hello/param1/param2
둘의 조합 : @{/hello/{param1}(param1=${param1}, param2=${param2})  /hello/param1?param2=xxx
```
* 리터럴
  * 소스 코드 상 고정된 값
  * 문자 처리 시 공백 여부 중요
    * 공백 O : ```작은따옴표(``)``` 반드시 사용해야 리터럴 처리 가능
    * 공백 X : 작은따옴표없이 리터럴 처리 가능
  * 리터럴 대체 문법 : `||`, 편리해서 주로 사용할 것 같습니다.
* 속성
  * 설정 : `th:field`
  * 추가 : `th:classappend`
  * 체크박스 : `th:checked`
    * html은 기본적으로 checked가 있으면 무조건 표시합니다.
    * th:checked 사용하면 checked=false 시, 체크 속성값을 제외함으로 개발자가 편리하게 개발할 수 있습니다.


### 스프링 통합
통합 지원을 위한 기본 설정은 스프링 부트가 자동으로 지원해줍니다.

입력폼 처리
* `th:object` : Model에 빈 객체를 넘겨줌으로써 **커멘드 객체** 설정
* `th:field` : 커멘드 객체의 프로퍼티 접근합니다. **id, name, value 속성을 자동 완성해주며 중복 코드 제거**

