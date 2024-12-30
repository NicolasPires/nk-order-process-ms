package com.nksolucoes.nkorderprocessms.transport.api;

import com.nksolucoes.nkorderprocessms.core.interactors.OrderInteractor;
import com.nksolucoes.nkorderprocessms.transport.mapper.OrderMapper;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListOrders200Response;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.OrderInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.openapi.OrderApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderHttp implements OrderApi {

	private final OrderInteractor orderInteractor;

	private final OrderMapper orderMapper;

	@Override
	public ResponseEntity<Order> createOrder(OrderInput orderInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderInteractor.createOrder(orderMapper.mapToOrder(orderInput)));
	}

	@Override
    public ResponseEntity<ListOrders200Response> listOrders(Integer page, Integer size) {
		int currentPage = (page != null) ? page : 0;
		int pageSize = (size != null) ? size : 10;

		Pageable pageable = PageRequest.of(currentPage, pageSize);

		Page<Order> orderPage = orderInteractor.listAllOrders(pageable);

		List<Order> content = orderMapper.toListOrder200Response(orderPage).getContent();

		ListOrders200Response response = new ListOrders200Response()
				.content(content)
				.number(orderPage.getNumber())
				.size(orderPage.getSize())
				.totalElements((int) orderPage.getTotalElements())
				.totalPages(orderPage.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Order> getOrderById(String orderId) {
		return ResponseEntity.ok(orderInteractor.getOrderById(orderId));
	}

	@Override
	public ResponseEntity<Order> updateOrder(String orderId, OrderInput orderInput) {
		Order order = orderInteractor.updateOrder(orderMapper.mapToOrder(orderInput), orderId);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}

	@Override
	public ResponseEntity<Void> deleteOrder(String orderId) {
		return this.orderInteractor.deleteOrder(orderId);
	}
}
