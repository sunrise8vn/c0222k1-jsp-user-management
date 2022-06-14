package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.model.User;

import java.util.List;

public interface UserService extends IGeneralService<User> {

    User findById(int userId);

    List<UserDTO> findAllUserDTO();
}
