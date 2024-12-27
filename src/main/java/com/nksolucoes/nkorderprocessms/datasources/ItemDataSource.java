package com.nksolucoes.nkorderprocessms.datasources;

import com.nksolucoes.nkorderprocessms.core.repositories.ItemRepository;
import com.nksolucoes.nkorderprocessms.datasources.repositories.ItemClientRepository;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemDataSource implements ItemRepository {

	private final ItemClientRepository itemClientRepository;

	@Override
	public Item createItem(Item item) {
		return this.itemClientRepository.save(item);
	}

	@Override
	public Page<Item> listAllItems(Pageable pageable) {
		return itemClientRepository.findAll(pageable);
	}

	@Override
	public Optional<Item> findById(String itemId) {
		return itemClientRepository.findById(itemId);
	}

	@Override
	public Item updateItem(Item item) {
		return this.itemClientRepository.save(item);
	}

	@Override
	public void deleteItem(String itemId) {
		this.itemClientRepository.deleteById(itemId);
	}

	@Override
	public List<Item> findByDescriptionAndBrandName(String description, String brandName) {
		return itemClientRepository.findByDescriptionAndBrandName(description, brandName);
	}
}
