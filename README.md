# SAGA 패턴 + Outbox 패턴

## 1. 프로젝트 소개
이 프로젝트는 MSA 기반 콘서트 예매 시스템에서 Outbox 패턴으로 이벤트 전달의 신뢰성을 확보하고, Saga 패턴을 적용해 결제 실패 시 보상 트랜잭션을 통한 예약 롤백을 구현했습니다.

## 2. MSA 구성
- 회원(User)
  - 회원 정보 관리
  - 회원가입 후 로그인시 Access Token과 Refresh Token 발급
- 콘서트(Concert)
  - 콘서트 조회 및 예매 관리
- 결제(Payment)
  - 예매시 결제 처리 관리


## 2. 예매 + 결제 구조
```text
[Concert Service]
  TX:
    - Reservation 생성 (PENDING)
    - Outbox에 PENDING_PAYMENT 저장
  COMMIT
        ↓ (Outbox Relay)
Kafka: concert-reserve
        ↓
[Payment Service]
  TX:
    - 결제 시도
    - Outbox에 Success / Fail 저장
  COMMIT
        ↓
Kafka: payment-success / payment-fail
        ↓
[Concert Service]
  TX:
    - 예약 CONFIRMED or SYSTEM_CANCELED (보상)
    - Outbox에 SUCCESS_PAYMENT or SYSTEM_CANCEL_PAYMENT 업데이트
```

- Saga: 얘약 -> 결제 -> 보상
- Outbox: 각 단계의 이벤트 저장


