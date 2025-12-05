package com.pro.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({"", "/"})
public class HomeApi {

	@GetMapping({"", "/"})
	public String index() {
		return "Api iText PDF está acessível!";
	}

	@GetMapping("/user-agent")
    public String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return "User-Agent recebido: " + userAgent;
    }
}