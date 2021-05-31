# SpringBoot와 AWS로 혼자 구현하는 웹 서비스
---
- 참고문헌: [스프링 부트와 AWS로 혼자 구현하는 웹 서비스](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788965402602&orderClick=LEa&Kc=)

`@RestController`
    - 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
`@GetMapping`
    - HTTP Method인 Get의 요청을 받을 수 있는 API 생성
`@RunWith(SpringRunner.class)`
    - 테스트 진행시 JUnit에 내장된 실행자 외 다른 실행자 실행
    - SpringRunner라는 스프링 실행자 사용
    - 스프링 부트 테스트와 JUnit 사이에 연결자 역할
`@WebMvcTest`
    - 여러 스프링 테스트 어노테이션 중, Web에 집중할 수 있는 어노테이션
    - 선언시 `@Controller`, `@ControllerAdvice` 사용 가능
`@Autowired`
    - 스프링이 관리하는 `Bean`을 주입받음
`private MockMvc mvc`
    - 웹 API 테스트 시 사용
    - 스프링 MVC 테스트 시작점
    - HTTP GET, POST등에 대한 API 테스트 가능
`mvc.perform(get("/hello"))`
    - MockMvc를 통해 /hello 주소로 HTTP GET 요청
    - 체이닝이 지원되어 여러 검증 기능을 이어서 선언 가능
`.andExpect(status().isOk())`
    - mvc.perform 결과 검증
    - HTTP Header의 Stuatus 검증
    - 200, 404, 500 등의 상태 검증
    - 여기서는 200인지 아닌지 검증
`.andExpect(content().string(hello))`
    - mvc.perform의 결과 검증
    - 응답 본문 내용 검증
`@Getter`
    - 선언된 모든 필드의 get 메소드 생성
`@RequireArgsConstructor`
    - 선언된 모든 final 필드가 포함된 생성자를 생성
    - final이 없는 필드는 생성자에 미포함
`asserThat`
    - assertj라는 테스트 검증 라이브러리의 검증 메소드
    - 검증하고자 하는 대상을 메소드 인자로 받음
`isEqualTo`
    - assertj의 동등 비교 메소드
    - assertThat에 있는 값과 isEqualTo 값을 비교해서 같을 때만 성공