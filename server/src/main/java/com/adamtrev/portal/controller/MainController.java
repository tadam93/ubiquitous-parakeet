package com.adamtrev.portal.controller;

import com.adamtrev.portal.apimodel.UserDto;
import com.adamtrev.portal.data.UserPojo;
import com.adamtrev.portal.mapper.UserMapper;
import com.adamtrev.portal.repository.UserPojoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class MainController {
    private static final String PREFIX_V1 = "/api/v1/";
    private static final String USER_API = PREFIX_V1 + "users";

    private UserPojoRepository userPojoRepository;
    private UserMapper userMapper;

    @PostMapping(USER_API)
    public ResponseEntity<UserDto> createUser(@RequestParam(value = "firstName", defaultValue = "Trevor") final String firstName,
                                              @RequestParam(value = "lastName", defaultValue = "Malone") final String lastName,
                                              @RequestParam(value = "age", defaultValue = "21") final Integer age) {
        final UserPojo pojo = userPojoRepository.create(UserPojo
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build());

        return ResponseEntity
                .ok()
                .body(userMapper.toDto(pojo));
    }

    @GetMapping(USER_API)
    public ResponseEntity<UserDto> getUser(@RequestParam(value = "firstName") final String firstName,
                                           @RequestParam(value = "lastName") final String lastName) {
        final UserPojo pojo = userPojoRepository.get(firstName, lastName);

        if (pojo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .body(userMapper.toDto(pojo));
    }

    @DeleteMapping(USER_API)
    public ResponseEntity<UserPojo> deleteUser(@RequestParam(value = "firstName") final String firstName,
                                               @RequestParam(value = "lastName") final String lastName) {
        final UserPojo pojo = userPojoRepository.delete(firstName, lastName);

        if (pojo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(pojo);
    }
}
