package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.CartDTO;
import lk.ijse.party_creation.entity.Cart;
import lk.ijse.party_creation.repo.CartRepo;
import lk.ijse.party_creation.service.CartService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveCart(CartDTO cartDTO){
        try{
            Cart cart = modelMapper.map(cartDTO,Cart.class);
            cartRepo.save(cart);
            return VarList.Created;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int delete(CartDTO cartDTO) {
        try {
            System.out.println("serviceccc"+cartDTO);
            List<Cart> cartItems = cartRepo.findByEmail(cartDTO.getEmail());
            System.out.println("serviceccc"+cartItems);
            if (cartItems == null || cartItems.isEmpty()) {
                return VarList.Not_Found;
            }

            for (Cart cartItem : cartItems) {
                if (cartDTO.getProductId() == cartItem.getProductId()) {
                    System.out.println("deleteeee"+cartItem);
                    cartRepo.delete(cartItem);
                    return VarList.OK;
                }
            }
            return VarList.Not_Found;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete cart item", e);
        }
    }
    @Override
    public List<CartDTO> getAll(String email){
        List<Cart> cart = cartRepo.findByEmail(email);
        return modelMapper.map(cart, new TypeToken<List<CartDTO>>() {}.getType());
    }
}
