# BookSearchApplication
## 기술스택
### Mvvm + Hilt + Coroutine + Paging3 + Unit test + Clean architecture

### 아키텍쳐 (MVVM , Clean Architecture)

좀더 가독성이 좋고 Testable한 코드를 작성하기 위해 클린아키텍쳐로 관심사를 분리하여 폴더를 구성하였습니다.

### Hilt 사용

IOC를 통한 제어의 역전으로 객체를 외부에서 제어하게 하여 의존성을 느슨하게 만들어 확장성에 용이하고 Testable한 코드를 작성하기위해 IOC 의 방법중에 코틀린을 사용하는 간단한 프로젝트에서 쉽게 적용가능하고 컴파일 속도도 빠른 Koin(Service Locator Pattern) 사용할까 하다가 하다가 Koin은 Run Time에서 주입한다는 문제도 있고 Locator에 의존한다는 단점이 있습니다. Hilt(DI) 같은경우는 Scope 관리도 쉽고 구글이 밀어주기도하고 이번 프로젝트에서는 Hilt를 적용해보고자해서 Hilt를 채택하게 되었습니다.

### Coroutine 사용

원래는 Rxjava를 썼었지만 kotlin 안에 Coroutine이 포함되어있기도 하고 요새 구글이 Coroutine을 통한 예제를 많이 보여주고있고 또한 Rxjava 같은경우는 Retrofit을 사용할때 Rxjava용 Adpater를 넣어줘야하지만 Retrofit 자체적으로 코루틴을 지원해주기때문에 이제 대세는 Rxjava보다 Coroutine 이구나 싶어서 적용하게 되었습니다. 

### Paging3 사용

페이징된 데이터를 메모리에 캐싱도 해주고 라이브러리 없이 개발한다면 Recyclerview의 스크롤이 하단에 도달했을때 데이터를 요청해 줘야하는데 페이징 라이브러리에서는 이것을 지원해 줍니다. 또한 Network Error 처리도 직접해주고 PagingAdpater를 사용하게 되는데 ListAdpater로 구현되어있어 효율적이게 사용할수있습니다.
