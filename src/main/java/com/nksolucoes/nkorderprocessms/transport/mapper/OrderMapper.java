package com.nksolucoes.nkorderprocessms.transport.mapper;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListOrders200Response;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.OrderInput;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	Order mapToOrder(OrderInput orderInput);

	Order mapToOrderResponse(Order order);

	default List<Order> mapToOrderResponseList(List<Order> orders) {
		return orders.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
	}

	default ListOrders200Response toListOrder200Response(Page<Order> orderPage) {
		ListOrders200Response response = new ListOrders200Response();
		response.setContent(mapToOrderResponseList(orderPage.getContent()));
		response.setNumber(orderPage.getNumber());
		response.setSize(orderPage.getSize());
		response.setTotalElements((int) orderPage.getTotalElements());
		response.setTotalPages(orderPage.getTotalPages());
		return response;
	}
}
