package net.sb.userservice.service.impl;

import lombok.AllArgsConstructor;

import net.sb.userservice.dto.DepartmentDto;
import net.sb.userservice.dto.ResponseDto;
import net.sb.userservice.dto.UserDto;
import net.sb.userservice.entity.User;
import net.sb.userservice.repository.UserRepository;
import net.sb.userservice.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private WebClient webClient;

    /**
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).orElseThrow();
        UserDto userDto = mapToUser(user);

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8081/api/departments/" + user.getDepartmentId())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
