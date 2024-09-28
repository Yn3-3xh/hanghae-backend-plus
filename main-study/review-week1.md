1. 문제
    
    과제, 프로젝트를 진행하면서 부딪혔던 기술적인 문제
    
    - TDD 적용
    - 단위/통합 테스트 작성
    - Mock, Stub 개념
    - 서비스단 책임 분리
    - 동시성 제어
    - 동시성 순서 제어
    
2. 시도
    
    문제를 해결하기 위해 어떤 시도를 하셨나요?
    
    - Error Log 확인
    - Googling + GPT
    - 멘토링
    - 네트워킹
    
3. 해결
    
    문제를 어떻게 해결하셨나요?
    
    - Red > Green > Refactor 단계를 적용해서 TDD 적응
    - 멘토링을 통해 목적에 맞게 정말 필요한 테스트만 명확하게 작성
    - 서비스에 있던 검증들을 @Component로 책임 전가
    - 서비스에 있던 add, sub같은 UserPoint가 행동하는 메서드는 UserPoint record로 책임 전가
    - 동시성과 순서를 제어하를 하기위해 ReentrantLock + CompletableFuture를 도입
    
4. 알게된 것
    
    문제를 해결하기 위해 시도하며 새롭게 알게된 것은 무엇인가요? 
    
    - TDD를 적용한 개발
    - 무분별하지 않은 Mock과 Stub 설정
    - 목적에 맞춰 정말 필요한 테스트만을 명확하게 단위/통합 테스트
    - 서비스에서 역할에 따른 책임을 @Component를 사용하여 테스트할 수 있도록 public하게 분리
    - 동시성 제어에 대한 다양한 알고리즘과 사용방법
    
- Keep
    
    현재 만족하고 계속 유지할 부분
    
    - TDD 적용
    - 단위/통합 테스트 적용
    - 서비스 레벨에서 서비스만의 책임이 아닌 것을 public하게 책임 분리
    
- Problem
    
    개선이 필요하다고 생각하는 문제점
    
    - @Component vs Comman Pattern 어떤 상황에 어떤 것이 좋을지
    - CAS 이론
    - ConcurrentHashMap 개념 학습 및 적용
    - AOP를 적용한 동시성(ConcurrentHashMap) 책임 분리
    - 책임을 분리한 후 애노테이션으로 동시성 처리
    
- Try
    
    문제점을 해결하기 위해 시도해야 할 것
    
    - 개인 학습
    - 기존 프로젝트에 적용하여 개선
