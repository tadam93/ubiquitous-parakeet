package com.adamtrev.portal.mapper;

import com.adamtrev.portal.apimodel.BillDto;
import com.adamtrev.portal.data.BillsPojo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    BillDto toDto(final BillsPojo billsPojo);

    BillsPojo toPojo(final BillDto dto);
}
