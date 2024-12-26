package com.nksolucoes.nkorderprocessms.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

	@Id
	private String id;

	private String document;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
}
