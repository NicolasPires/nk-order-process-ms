package com.nksolucoes.nkorderprocessms.transport.mapper;

import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.Item;
import com.nksolucoes.nkorderprocessms.transportlayer.documentacao.model.ItemInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

	Item mapItemInputToItem(ItemInput itemInput);
}
