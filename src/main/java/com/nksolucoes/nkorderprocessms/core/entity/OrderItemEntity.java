package com.nksolucoes.nkorderprocessms.core.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

	@Id
	private String id;

	@DBRef
	private ItemEntity item;
	private int quantity;
	private BigDecimal subtotal;
}

