package com.nksolucoes.nkorderprocessms.core.interactors;

import com.nksolucoes.nkorderprocessms.core.exceptions.order.NotFoundOrderException;
import com.nksolucoes.nkorderprocessms.core.repositories.OrderRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInteractor {

	private final OrderRepository orderRepository;

	public Order createOrder(Order order) {
		return this.orderRepository.createOrder(order);
	}

	public Page<Order> listAllOrders(Pageable pageable) {
		return this.orderRepository.listAllOrders(pageable);
	}

	public Order getOrderById(String orderId) {
		AtomicReference<Order> order = new AtomicReference<>();

		this.orderRepository.findById(orderId).ifPresentOrElse(
				order::set,
				() -> { throw new NotFoundOrderException(String.format("Order with id '%s' is not found.", orderId));
				}
		);

		return order.get();
	}

	public Order updateOrder(Order order, String orderId) {
		Optional<Order> orderOptional = this.orderRepository.findById(orderId);

		if (orderOptional.isPresent()) {
			Order orderToUpdate = new Order();
			orderToUpdate.setId(orderId);
			orderToUpdate.setOrderId(order.getOrderId());
			orderToUpdate.setNotes(order.getNotes());
			orderToUpdate.setCustomer(order.getCustomer());
			orderToUpdate.setItems(order.getItems());
			orderToUpdate.setTotalAmount(order.getTotalAmount());
			orderToUpdate.setTotalAmount(order.getTotalAmount());

			return this.orderRepository.updateOrder(orderToUpdate);
		} else {
			throw new NotFoundOrderException(String.format("Order with id '%s' is not found.", orderId));
		}
	}

	public ResponseEntity<Void> deleteOrder(String orderId) {
		if (!this.orderRepository.findById(orderId).isPresent()) {
			throw new NotFoundOrderException("Unable to delete order with id '" + orderId + "'.");
		}
		this.orderRepository.deleteOrder(orderId);
		return ResponseEntity.noContent().build();
	}
}
