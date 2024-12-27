package com.nksolucoes.nkorderprocessms.transport.api;

import com.nksolucoes.nkorderprocessms.core.interactors.ItemInteractor;
import com.nksolucoes.nkorderprocessms.transport.mapper.ItemMapper;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ItemInput;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.openapi.ItemApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemHttp implements ItemApi {

	private final ItemInteractor itemInteractor;

	@Override
	public ResponseEntity<Item> createItem(ItemInput itemInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemInteractor.createItem(ItemMapper.INSTANCE.mapItemInputToItem(itemInput)));
	}

	@Override
	public ResponseEntity<List<Item>> listItem() {
		return ResponseEntity.ok(itemInteractor.listAllItems());
	}

	@Override
	public ResponseEntity<Item> getItemById(String itemId) {
		return ResponseEntity.ok(itemInteractor.getItemById(itemId));
	}

	@Override
	public ResponseEntity<Item> updateItem(String itemId, ItemInput itemInput) {
		Item item = itemInteractor.updateItem(ItemMapper.INSTANCE.mapItemInputToItem(itemInput), itemId);
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}

	@Override
	public ResponseEntity<Void> deleteItem(String itemId) {
		return this.itemInteractor.deleteItem(itemId);
	}
}
