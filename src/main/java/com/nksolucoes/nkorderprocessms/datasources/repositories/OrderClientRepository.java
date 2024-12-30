package com.nksolucoes.nkorderprocessms.datasources.repositories;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;




public interface OrderClientRepository extends MongoRepository<Order, String> {
	Page<Order> findAll(Pageable pageable);
}
