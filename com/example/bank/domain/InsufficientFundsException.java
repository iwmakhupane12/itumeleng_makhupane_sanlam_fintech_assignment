package com.example.bank.domain;

public class InsufficientFundsException extends RuntimeException
{
  public InsufficientFundsException()
  {
    super("Insufficient funds for withdrawal");
  }
}
