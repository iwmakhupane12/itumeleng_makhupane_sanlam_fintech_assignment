package com.example.bank.controller;

import com.example.bank.service.BankAccountService;
import com.example.bank.domain.InsufficientFundsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankAccountController
{

  private final BankAccountService bankAccountService;

  public BankAccountController(BankAccountService bankAccountService)
  {
    this.bankAccountService = bankAccountService;
  }

  @PostMapping("/withdraw")
  public ResponseEntity<String> withdraw(
          @RequestParam Long accountId,
          @RequestParam BigDecimal amount)
  {
    try
    {
      bankAccountService.withdraw(accountId, amount);
      return ResponseEntity.ok("Withdrawal successful");
    }
    catch (InsufficientFundsException e)
    {
      return ResponseEntity.badRequest().body("Insufficient funds");
    }
    catch (Exception e)
    {
      return ResponseEntity.internalServerError().body("Withdrawal failed");
    }
  }
}
