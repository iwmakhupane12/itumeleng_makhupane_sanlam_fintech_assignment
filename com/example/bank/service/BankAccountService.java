package com.example.bank.service;

import com.example.bank.domain.WithdrawalEvent;
import com.example.bank.domain.InsufficientFundsException;
import com.example.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankAccountService
{
  private final AccountRepository accountRepository;
  private final EventPublisher eventPublisher;

  public BankAccountService(AccountRepository accountRepository, EventPublisher eventPublisher)
  {
    this.accountRepository = accountRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  public void withdraw(Long accountId, BigDecimal amount)
  {
    BigDecimal balance = accountRepository.getBalance(accountId);

    if (balance.compareTo(amount) < 0)
    {
      throw new InsufficientFundsException();
    }

    accountRepository.updateBalance(accountId, balance.subtract(amount));

    WithdrawalEvent event = new WithdrawalEvent(amount, accountId, "SUCCESSFUL");
    eventPublisher.publish(event);
  }
}
