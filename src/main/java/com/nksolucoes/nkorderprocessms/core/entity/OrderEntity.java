package com.nksolucoes.nkorderprocessms.core.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

	@Id
	private String id;

	private String orderId;
	private String orderStatus;
	private String notes;
	private BigDecimal totalAmount;
	private int totalItems;

	@DBRef
	private CustomerEntity customer;

	@DBRef
	private List<OrderItemEntity> items;
}

