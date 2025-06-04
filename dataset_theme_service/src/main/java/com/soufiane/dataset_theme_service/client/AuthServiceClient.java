package com.soufiane.dataset_theme_service.client;

import com.soufiane.sharedlibrary.dto.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:8081") // remplace PORT par le vrai port d'auth
public interface AuthServiceClient {

    @GetMapping("/api/me")
    UserInfoResponse getCurrentUser(@RequestHeader("Authorization") String token);
}
