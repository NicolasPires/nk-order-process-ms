package com.nksolucoes.nkorderprocessms.core.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

	Item createItem(Item item);

	List<Item> listAllItems();

	Optional<Item> findById(String itemId);

	Item updateItem(Item item);

	void deleteItem(String itemId);

	List<Item> findByDescriptionAndBrandName(String description, String brandName);
}
