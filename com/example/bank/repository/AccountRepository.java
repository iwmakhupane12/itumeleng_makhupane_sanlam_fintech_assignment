package com.example.bank.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountRepository
{
  private final JdbcTemplate jdbcTemplate;

  public AccountRepository(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  public BigDecimal getBalance(Long accountId)
  {
    return jdbcTemplate.queryForObject(
            "SELECT balance FROM accounts WHERE id = ?",
            BigDecimal.class,
            accountId
    );
  }

  public void updateBalance(Long accountId, BigDecimal newBalance)
  {
    jdbcTemplate.update(
            "UPDATE accounts SET balance = ? WHERE id = ?",
            newBalance, accountId
    );
  }
}
