package com.example.bank.domain;

import java.math.BigDecimal;

public class WithdrawalEvent
{
  private final BigDecimal amount;
  private final Long accountId;
  private final String status;

  public WithdrawalEvent(BigDecimal amount, Long accountId, String status)
  {
    this.amount = amount;
    this.accountId = accountId;
    this.status = status;
  }

  public BigDecimal getAmount()
  {
    return amount;
  }

  public Long getAccountId()
  {
    return accountId;
  }

  public String getStatus()
  {
    return status;
  }
}
