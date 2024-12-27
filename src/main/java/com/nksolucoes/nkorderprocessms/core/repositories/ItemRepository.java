package com.nksolucoes.nkorderprocessms.core.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepository {

	Item createItem(Item item);

	Page<Item> listAllItems(Pageable pageable);

	Optional<Item> findById(String itemId);

	Item updateItem(Item item);

	void deleteItem(String itemId);

	List<Item> findByDescriptionAndBrandName(String description, String brandName);
}
