package com.emailServer.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "file-generator", path = "file-generator-api/v1/")
public interface PdfGeneratorFeingClients {
	
}
