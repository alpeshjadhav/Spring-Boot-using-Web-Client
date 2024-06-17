package net.sb.userservice.service;

import net.sb.userservice.dto.ResponseDto;
import net.sb.userservice.entity.User;

public interface UserService {

    User saveUser(User user);
    ResponseDto getUser(Long userId);
}
