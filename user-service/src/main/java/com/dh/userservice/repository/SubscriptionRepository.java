package com.dh.userservice.repository;

import com.dh.userservice.model.SubscriptionDTO;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public class SubscriptionRepository {
    private FeignSubscriptionRepository feignSubscriptionRepository;
    public SubscriptionRepository(FeignSubscriptionRepository feignSubscriptionRepository) {
        this.feignSubscriptionRepository = feignSubscriptionRepository;
    }
    @CircuitBreaker(name="subscription",fallbackMethod = "subsciptionFallbackMethod")
    @Retry(name = "subscription")
    public SubscriptionDTO findByUserId(Integer userId, Boolean throwError){
        System.out.println("Ejecutando...");
        ResponseEntity<SubscriptionDTO> response = feignSubscriptionRepository.findByUserId(userId,throwError);
        return response.getBody();
    }

    private SubscriptionDTO subsciptionFallbackMethod(CallNotPermittedException exception){
        return  new SubscriptionDTO(999,new Date(),new Date(),9999);
    }
}
