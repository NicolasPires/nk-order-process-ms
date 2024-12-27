package com.nksolucoes.nkorderprocessms.transport.mapper;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Customer;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ItemInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListCustomers200Response;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListItems200Response;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	Item mapToItem(ItemInput itemInput);

	Item mapToItemResponse(Item item);

	default List<Item> mapToItemResponseList(List<Item> items) {
		return items.stream().map(this::mapToItemResponse).collect(Collectors.toList());
	}

	default ListItems200Response toListItem200Response(Page<Item> itemPage) {
		ListItems200Response response = new ListItems200Response();
		response.setContent(mapToItemResponseList(itemPage.getContent()));
		response.setNumber(itemPage.getNumber());
		response.setSize(itemPage.getSize());
		response.setTotalElements((int) itemPage.getTotalElements());
		response.setTotalPages(itemPage.getTotalPages());
		return response;
	}
}
