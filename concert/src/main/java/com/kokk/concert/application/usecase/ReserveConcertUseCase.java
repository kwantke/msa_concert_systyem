package com.kokk.concert.application.usecase;

import com.kokk.concert.application.common.UseCase;
import com.kokk.concert.application.common.lock.DistributedLockExecutor;
import com.kokk.concert.application.dto.request.ReserveConcertRequestDto;
import com.kokk.concert.application.dto.response.ReserveConcertResponseDto;
import com.kokk.concert.application.port.in.ReservationServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@RequiredArgsConstructor
@Slf4j
@UseCase
public class ReserveConcertUseCase {

  private final String RESERVATION_LOCK_KEY = "seat_reservation:";
  private final ReservationServicePort reservationServicePort;
  private final DistributedLockExecutor distributedLockExecutor;

  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequestDto request, Long userIdx) {

    ReserveConcertResponseDto reserveConcertResponseDto = null;
    String lockKey = RESERVATION_LOCK_KEY + request.concertSessionId();
    long waitTime = 7_000; // 락 회득까지 대기할 시간
    long leaseTime = 5_000; // 락 획득후 자동 해제되기까기 최대 점유 시간

    try{
      reserveConcertResponseDto = distributedLockExecutor.executeWithLock(lockKey, waitTime, leaseTime, ()
              -> {
        return reservationServicePort.reserveConcert(request, userIdx);
      });
    }catch (ObjectOptimisticLockingFailureException e) {
      log.warn("[낙관락 예외 발생] 좌석 예약 실패 - 사용자 {}, {}", userIdx, e.getMessage());
    }
    return reserveConcertResponseDto;
  }
}
