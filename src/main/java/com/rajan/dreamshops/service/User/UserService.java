package com.rajan.dreamshops.service.User;

import com.rajan.dreamshops.dto.UserDto;
import com.rajan.dreamshops.entity.User;
import com.rajan.dreamshops.request.CreateUserRequest;
import com.rajan.dreamshops.request.UserUpdateRequest;

public interface UserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertToDto(User user);

    User getAuthenticatedUser();
}
