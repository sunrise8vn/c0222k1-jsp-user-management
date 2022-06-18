package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.dto.UserDobDTO;
import com.cg.model.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IGeneralService<User> {

    User findById(int userId);

    List<UserDTO> findAllUserDTO();

    Map<String, String> doCreate(User user);

    List<UserDobDTO> searchByDate(String dob);

    Map<String, List<?>> searchByDateMap(String dob);
}
