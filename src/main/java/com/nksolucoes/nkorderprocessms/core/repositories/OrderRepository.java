package com.nksolucoes.nkorderprocessms.core.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

	Order createOrder(Order order);

	Page<Order> listAllOrders(Pageable pageable);

	Optional<Order> findById(String orderId);

	Order updateOrder(Order item);

	void deleteOrder(String itemId);

}
