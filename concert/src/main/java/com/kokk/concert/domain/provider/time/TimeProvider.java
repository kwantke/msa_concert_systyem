package com.kokk.concert.domain.provider.time;

import java.time.LocalDateTime;

public interface TimeProvider {
  LocalDateTime now();
}
