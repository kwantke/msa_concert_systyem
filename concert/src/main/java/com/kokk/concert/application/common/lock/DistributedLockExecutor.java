package com.kokk.concert.application.common.lock;

import java.util.function.Supplier;

public interface DistributedLockExecutor {
  <T> T executeWithLock(String key, long waitTime, long leaseTime, Supplier<T> task);
}
