package com.nksolucoes.nkorderprocessms.datasources;

import com.nksolucoes.nkorderprocessms.core.repositories.OrderRepository;
import com.nksolucoes.nkorderprocessms.datasources.repositories.OrderClientRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDataSource implements OrderRepository {

	private final OrderClientRepository orderClientRepository;

	@Override
	public Order createOrder(Order order) {
		return this.orderClientRepository.save(order);
	}

	@Override
	public Page<Order> listAllOrders(Pageable pageable) {
		return orderClientRepository.findAll(pageable);
	}

	@Override
	public Optional<Order> findById(String orderId) {
		return orderClientRepository.findById(orderId);
	}

	@Override
	public Order updateOrder(Order order) {
		return this.orderClientRepository.save(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		this.orderClientRepository.deleteById(orderId);
	}

}
