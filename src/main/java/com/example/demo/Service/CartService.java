package com.example.demo.Service;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.Entities.dbo.Cart;
import com.example.demo.Repo.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@Transactional
public class CartService {
    @Autowired
    CartRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    public Page<Cart> get(String username, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return repository.get(username, pageable);
    }
    public void addToCart(Cart object) {
        repository.save(object);
    }

    public void removeOne(String username, Integer productId) {
        repository.removeOne(username, productId);
    }

    public void removeAll(String username) {
        repository.removeAll(username);
    }

    public String processPay(String username) {
        List<Cart> list = repository.getAll(username);
        int price = 0;
        for (Cart cart : list) {
            if (cart.getRegularCages() != null && cart.getRegularCages().getCagePrice() != null) {
                price += cart.getRegularCages().getCagePrice();
            }
        }
        String apiEndpoint = "http://localhost:8089/payment-vnpay/pay?amount=" + price;
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(apiEndpoint, ApiResponse.class);
        log.info("priceprice|" + price);
        log.info("response|" + Objects.requireNonNull(response.getBody()).getData());
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public Page<Cart> bill(String username) {
        Pageable pageable = PageRequest.of(0, 1000);
        Page<Cart> cartPage = repository.get(username, pageable);
        repository.updateAllBought(username);
        return cartPage;
    }
}
