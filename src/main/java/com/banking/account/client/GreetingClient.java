package com.banking.account.client;

import com.banking.account.service.AccountService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-service")
public interface GreetingClient {
    @RequestMapping("/users/version")
    ResponseEntity<String> test();

    @RequestMapping(method = RequestMethod.GET, value = "/users/by-email/{email}")
    AccountService.User getUserByEmail(@PathVariable("email") String email);
}