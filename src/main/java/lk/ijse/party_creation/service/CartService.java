package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.CartDTO;

import java.util.List;


public interface CartService {
    int saveCart(CartDTO cartDTO);

    int delete(CartDTO cartDTO);

    List<CartDTO> getAll(String email);
}
