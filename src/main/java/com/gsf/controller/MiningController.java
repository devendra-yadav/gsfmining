package com.gsf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsf.service.MiningService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import ua_parser.Client;
import ua_parser.Parser;

@RestController
@RequestMapping("/mine")
@Slf4j
public class MiningController {
	
	@Autowired
	private MiningService miningService;
	
	@PostMapping("/{user}")
	public String startMining(HttpServletRequest request, @PathVariable("user") String user) {
		log.info("Mining session for user "+user+" starting");
		
		String userAgent = request.getHeader("user-agent");
        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);
        
		Map<String, String> clientData = new HashMap<>();
		clientData.put("host", request.getRemoteAddr());
		clientData.put("os_family", client.os.family);
        clientData.put("device_family", client.device.family);
        clientData.put("userAgent_family", client.userAgent.family);
        
		miningService.start(user, clientData);
		log.info("Mining session for user "+user+" ending");
		
		
		
		return "Mining session triggered for "+user;
		
	}
	
	@GetMapping("/{user}")
	public String startMiningNotAllowed(HttpServletRequest request, @PathVariable("user") String user) {
		log.info("Mining session for user "+user+" starting");
		
		String userAgent = request.getHeader("user-agent");
        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);
        
		Map<String, String> clientData = new HashMap<>();
		clientData.put("host", request.getRemoteAddr());
		clientData.put("os_family", client.os.family);
        clientData.put("device_family", client.device.family);
        clientData.put("userAgent_family", client.userAgent.family);
        
		log.info("Mining session for user "+user+" ending");
		
		log.info(""+clientData);
		
		return "Wrong mining request by "+user;
		
	}

}
