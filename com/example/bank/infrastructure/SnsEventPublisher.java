package com.example.bank.infrastructure;

import com.example.bank.service.EventPublisher;
import com.example.bank.domain.WithdrawalEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class SnsEventPublisher implements EventPublisher
{

  private final SnsClient snsClient;
  private final ObjectMapper objectMapper;
  private final String snsTopicArn = "arn:aws:sns:af-south-1:123456:SanlamFintech";

  public SnsEventPublisher()
  {
    this.snsClient = SnsClient.builder()
            .region(Region.AF_SOUTH_1)
            .build();
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void publish(WithdrawalEvent event)
  {
    try
    {
      String message = objectMapper.writeValueAsString(event);
      PublishRequest request = PublishRequest.builder()
              .message(message)
              .topicArn(snsTopicArn)
              .build();
      snsClient.publish(request);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to publish SNS event", e);
    }
  }
}
