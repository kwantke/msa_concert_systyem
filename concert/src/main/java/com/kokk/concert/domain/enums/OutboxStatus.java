package com.kokk.concert.domain.enums;

import com.kokk.concert.domain.exception.ConcertErrorCode;
import com.kokk.concert.domain.exception.CoreException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum OutboxStatus {
  INIT("대기",1),
  PUBLISHED("발행 완료",2)
  ;

  private final String statusName;
  private final int statusCode;

  private static Map<Integer, OutboxStatus> MAP =
          Stream.of(values()).collect(Collectors.toMap(OutboxStatus::getStatusCode, outboxStatus -> outboxStatus));

  public static OutboxStatus fromDbValue(int dbValue) {
    OutboxStatus outboxStatus = MAP.get(dbValue);
    if (outboxStatus == null) {
      throw new CoreException(ConcertErrorCode.INVALID_OUTBOX_STATUS);
    }
    return outboxStatus;
  }
}
