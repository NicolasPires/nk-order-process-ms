package com.nksolucoes.nkorderprocessms.transport.api;

import com.nksolucoes.nkorderprocessms.core.interactors.ItemInteractor;
import com.nksolucoes.nkorderprocessms.transport.mapper.ItemMapper;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ItemInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ListItems200Response;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.openapi.ItemApi;
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
public class ItemHttp implements ItemApi {

	private final ItemInteractor itemInteractor;

	private final ItemMapper itemMapper;

	@Override
	public ResponseEntity<Item> createItem(ItemInput itemInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemInteractor.createItem(itemMapper.mapToItem(itemInput)));
	}

	@Override
	public ResponseEntity<ListItems200Response> listItems(Integer page, Integer size) {
		int currentPage = (page != null) ? page : 0;
		int pageSize = (size != null) ? size : 10;

		Pageable pageable = PageRequest.of(currentPage, pageSize);

		Page<Item> itemPage = itemInteractor.listAllItems(pageable);

		List<Item> content = itemMapper.toListItem200Response(itemPage).getContent();

		ListItems200Response response = new ListItems200Response()
				.content(content)
				.number(itemPage.getNumber())
				.size(itemPage.getSize())
				.totalElements((int) itemPage.getTotalElements())
				.totalPages(itemPage.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Item> getItemById(String itemId) {
		return ResponseEntity.ok(itemInteractor.getItemById(itemId));
	}

	@Override
	public ResponseEntity<Item> updateItem(String itemId, ItemInput itemInput) {
		Item item = itemInteractor.updateItem(itemMapper.mapToItem(itemInput), itemId);
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}

	@Override
	public ResponseEntity<Void> deleteItem(String itemId) {
		return this.itemInteractor.deleteItem(itemId);
	}
}
