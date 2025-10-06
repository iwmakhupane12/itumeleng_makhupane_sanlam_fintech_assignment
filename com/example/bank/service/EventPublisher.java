package com.example.bank.service;

import com.example.bank.domain.WithdrawalEvent;

public interface EventPublisher
{
  void publish(WithdrawalEvent event);
}