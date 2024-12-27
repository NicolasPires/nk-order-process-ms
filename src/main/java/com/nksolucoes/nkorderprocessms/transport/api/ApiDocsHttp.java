package com.nksolucoes.nkorderprocessms.transport.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docs")
public class ApiDocsHttp {

	@GetMapping(value = "/openapi.yaml", produces = "application/x-yaml")
	public ResponseEntity<ClassPathResource> getApiYaml() {
		return ResponseEntity.ok(new ClassPathResource("static/openapi/api.yaml"));
	}
}

