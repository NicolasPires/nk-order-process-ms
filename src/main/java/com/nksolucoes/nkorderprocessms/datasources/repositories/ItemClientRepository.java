package com.nksolucoes.nkorderprocessms.datasources.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemClientRepository extends MongoRepository<Item, String> {
	List<Item> findByDescriptionAndBrandName(String description, String brandName);
}
