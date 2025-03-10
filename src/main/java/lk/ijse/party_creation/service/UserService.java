package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String email);
}
