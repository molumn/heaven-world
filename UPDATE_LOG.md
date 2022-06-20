# UPDATE

## Version

### 0.1.0

- HWPlayer: Player 클래스의 바인딩
- HWPlayerSystem: HWPlayer 기능 집합
- traits 추가: interfaces

### 0.2.0

- 개발환경 변경 (Windows -> Ubuntu)
- HWPlayerManager: HWPlayer 집합체 (관리 기능 추가)
- HWPlayer 의 traits(AbleToPatch, AbleToStorage) test 완료 (추후 추가 기능 구현 예정)

### 0.3.0-tested

- HWInventory DSL : [**InvFX**]를 내 스타일로 확장해보았다. (`gui`, `inv`, `shower`, `craft`, `exchange` 등의 **Maker** 추가 예정)
  - (구조 상에서 약간 차이나는데 기분 탓인가..?)
- **HWInvMaker**로 여러 가지 확장 만들기가 가능해짐?
- :closed_book: added `README`!!!!!!!!!!

- #### TODO
  - 나머지 인벤토리 이벤트 **DSL**를 **HWInvMaker**에 추
  - **HWInventory**에 children 같이 `menu`를 만들기 : react-router-dom 같은 것
  - **HWPlayer**와의 연동 구현
  - **HWInventoryStorage**로 기본 구현체 제공 및 신규 인벤토리 저장
  - 마인크래프트 내의 컨텐츠중 실시간 또는 주기적인 데이터 동기화가 필요한 미니게임의 인벤토리를 위한 갱신 메소드 구현

---

## Version TODO

### 0.4.0

- 스킬 연출을 위한 파티클 생성을 추상화한 클래스
  - 서버에서 파티클 연출의 성능 개선 (`알고리즘 최적화`, `가상화`, `HWPlayerManager를 이용한 부분적 송출` or `...`)
  - 미사일 같은 자동 추적 연출을 위한 추상화 클래스 구현 (`선형 보간` 혹은 `물리 상호작용`)
- Particle Event Binding -> 안할 가능성이 높음

---

## Future

- ### 가능성 !
  - Bungee plugin library (waterfall)
    - 서버간 메시지 전달 간편화
    - **HWPlayer**의 yml 데이터 전송 또는 동기화
    - HWPlayerManager `patchAll` 메소드의 멀티쓰레드 가능성 살펴보기
    - (가능하면) Microsoft 계정을 이용한 보안 관련 메소드. 안되면 말고ㅋ

- ### 가능성 _
  - 능력, 스킬을 추상화한 클래스. (인터페이스만 만들고 따로 플러그인 만들 수도, 아님 `lib` -> `lib` -> `plugin`)
  - Packet 관련 확장 (귀찮음)



[**InvFx**]: https://github.com/monun/invfx
