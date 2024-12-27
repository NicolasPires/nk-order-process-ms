package com.nksolucoes.nkorderprocessms.core.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

	@Id
	private String id;

	private String description;
	private String brandName;
	private BigDecimal unitPrice;

}
