# 고양이마켓 프로젝트 REST API, 도메인 주도 설계, TDD
# TDD
1. 의존성
   : domain -> Application(Service) -> UI(Controlloer)으로 의존
   : domain이 바뀌면 App이 바뀌고, App이 바뀌면 UI가 바뀜.
   : domain -> 말 그대로 domain, 가장 깊은 내용을 다루고 있음
   : Application -> 비즈니스 로직을 다루고 있음.
   : UI -> UI, Web과 가장 가까운 부분

2. 기본 순서로 진행 된다.
   : RED -> 실패하는 단계
   : GREEN -> 성공하는 단계
   : Refactoring -> 기능은 변하지 않고 클린코드 작성을 위함.
   
3. given, when, then ?????       