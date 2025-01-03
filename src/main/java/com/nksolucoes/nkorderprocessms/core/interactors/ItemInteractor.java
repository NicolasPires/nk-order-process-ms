package com.nksolucoes.nkorderprocessms.core.interactors;

import com.nksolucoes.nkorderprocessms.core.exceptions.item.DuplicateItemException;
import com.nksolucoes.nkorderprocessms.core.exceptions.item.NotFoundItemException;
import com.nksolucoes.nkorderprocessms.core.repositories.ItemRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemInteractor {

	private final ItemRepository itemRepository;

	public Item createItem(Item item) {
		itemRepository.findByDescriptionAndBrandName(item.getDescription(), item.getBrandName())
				.stream().findFirst().ifPresent(existingItem -> {
			throw new DuplicateItemException(
					String.format("Item with description '%s' and brand '%s' already exists.",
							item.getDescription(), item.getBrandName()));
		});

		return this.itemRepository.createItem(item);
	}

	public Page<Item> listAllItems(Pageable pageable) {
		return this.itemRepository.listAllItems(pageable);
	}

	public Item getItemById(String itemId) {
		AtomicReference<Item> item = new AtomicReference<>();

		this.itemRepository.findById(itemId).ifPresentOrElse(
				item::set,
				() -> { throw new NotFoundItemException(String.format("Item with id '%s' is not found.", itemId)); }
		);

		return item.get();
	}


	public Item updateItem(Item item, String itemId) {
		Optional<Item> itemData = this.itemRepository.findById(itemId);

		if (itemData.isPresent()) {
			Item updateItem = new Item();
			updateItem.setId(itemData.get().getId());
			updateItem.setDescription(item.getDescription());
			updateItem.setBrandName(item.getBrandName());
			updateItem.setUnitPrice(item.getUnitPrice());
			return this.itemRepository.updateItem(updateItem);
		} else {
			throw new NotFoundItemException("Item not found.");
		}
	}

	public ResponseEntity<Void> deleteItem(String itemId) {
		if (!this.itemRepository.findById(itemId).isPresent()) {
			throw new NotFoundItemException("Unable to find Item for delete.");
		}
		this.itemRepository.deleteItem(itemId);
		return ResponseEntity.noContent().build();
	}

}
