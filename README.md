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
    - `mvc.perform`의 결과 검증
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
    - assertThat에 있는 값과 `isEqualTo` 값을 비교해서 같을 때만 성공

`@RequestParam`
    - 외부에서 API로 넘긴 파라미터를 가져옴

`param`
    - API테스트할 때 사용될 요청 파라미터 설정
    - String 값만 허용

`jsonPath`
    - JSON 응답값을 필드별로 검증할 수 있는 메소드
    - $를 기준으로 필드명 명시 

`spring-boot-starter-data-jpa`
    - 스프링 부트용 `Spring Data Jpa` 추상화 라이브러리
    - 스프링 부트 버전에 맞게 자동으로 JPA관련 라이브러리 버전 관리

`h2`
    - 인메모리 관계형 데이터베이스
    - 프로젝트 의존성만으로 관리 가능
    - 애플리케이션 재시작시 초기화 -> 테스트용

`@Entity`
    - 테이블과 링크될 클래스
    - Entity 클래스에서는 절대 `Setter` 메소드를 만들지 않음
    - 클래스의 camelCase 이름을 Snake_Case로 매칭

`@Id`
    - 해당 테이블의 PK필드

`@GeneratedValue`
    - PK 생성 규칙
    - 스프링 부트 2.0에서는 `Generation Type.IDENTITY` 옵션을 추가해야만 `auto_increment`

`@Column`
    - 테이블의 칼럼

`@NoArgsConstructor`
    - 기본 생성자 자동 추가
    - `public Posts() {}`와 같은 효과

`@Getter`
    - 클래스 내 모든 필드의 Getter 메소드를 자동생성

`@Builder`
    - 해당 클래스의 빌더 패턴 클래스 생성

`@After`
    - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    - 배포 전 전체 테스트를 수행할 떄 테스트 간 데이터 침범을 막기 위해 사용
    - 여러 테스트가 동시에 실행되면 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 테스트가 실패할 수 있음

`postsRepository.save`
    - 테이블 posts에 insert/update 쿼리 실행
    - id 값이 있다면 update가, 없다면 insert 쿼리 실행

`postsRepository.findAll`
    - 테이블 posts에 있는 모든 데이터 조회

`@MappedSuperclass`
- JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식

`@EntityListeners(AuditingEntityListener.class)`
- BaseTimeEntity 클래스에 Auditing 기능 포함

`@CreatedDate`
- Entity가 생성되어 저장될 때 시간이 자동 저장

`@LastModifiedDate`
- 조회한 Entity의 값을 변경할 때 시간이 자동 저장

### Spring 웹 계층

##### __Web Layer__
- `@Controller`와 `JSP/Freemarker`등의 뷰 템플릿 영역
- 외부 요청과 응답에 대한 전반적인 영역

##### __Service Layer__
- `@Service`에 사용되는 서비스 영역
- 일반적으로 `Controller`와 `Dao`의 중간 영역에서 사용
- `@Transactional`이 사용되어야 하는 영역

##### __Repository Layer__
- `Database`와 같이 데이터 저장소에 접근하는 영역
- `Dao(Data Access Object)`영역

##### __Dtos__
- Dto(Data Transfer Object)는 계층 간 데이터 교환을 위한 객체
- 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체

##### __Domain Model__
- 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것을 도메인 모델이라고 함
- `@Entity`가 사용된 영역 역시 도메인 모델
- 무조건 DB Tabler과 관계가 있는 것은 아님
- `VO`처럼 값 객체들도 도메인 모델 영역

### CI의 4가지 규칙
1. 모든 소스 코드가 살아있고 누구든 현재의 소스에 접근할 수 있는 단일 지점 유지
2. 빌드 프로세스를 자동화하여 누구든 소스로부터 시스템을 빌드하는 단일 명령어 사용할 수 있게 하기
3. 테스팅을 자동화하여 단일 명령어로 언제든지 시스템에 대한 건전한 테스트 수트를 실행할 수 있게 할 것
4. 누구나 현재 실행 파일을 얻으면 지금까지 가장 완전한 실행 파일을 얻었다는 확신읋 하게 할 것