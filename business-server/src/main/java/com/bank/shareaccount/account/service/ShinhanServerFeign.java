package com.bank.shareaccount.account.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.shareaccount.account.dto.request.CreateAccountRequestDto;
import com.bank.shareaccount.account.dto.request.ExchangeRequest;
import com.bank.shareaccount.global.config.FeignConfig;

@Component
@FeignClient(name = "shinhanserver", url = "https://shbhack.shinhan.com/v1", configuration = FeignConfig.class)
public interface ShinhanServerFeign {
	@PostMapping("/search/fx/krw-amount")
	Object getExchangeMoney(@RequestBody ExchangeRequest request);
}
