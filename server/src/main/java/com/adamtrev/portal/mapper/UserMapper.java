package com.adamtrev.portal.mapper;

import com.adamtrev.portal.apimodel.UserDto;
import com.adamtrev.portal.data.UserPojo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(final UserPojo userPojo);

    UserPojo toPojo(final UserDto dto);
}
