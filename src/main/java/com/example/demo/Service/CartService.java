package com.example.demo.Service;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.Entities.dbo.Cart;
import com.example.demo.Repo.CartRepository;
import com.example.demo.Repo.RegularCagesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class CartService {
    @Autowired
    CartRepository repository;
    @Autowired
    RegularCagesRepository cagesRepository;
    @Autowired
    private RestTemplate restTemplate;
    public Page<Cart> get(String username, int pageNo, int pageSize, Integer status) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return repository.get(username, status, pageable);
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

    public String processPay(String username, Integer status) {
        List<Cart> list = repository.getAll(username, status);
        int price = 0;
        if(status == null) {
            for (Cart cart : list) {
                if (cart.getRegularCages() != null && cart.getRegularCages().getCagePrice() != null) {
                    price += cart.getRegularCages().getCagePrice();
                }
            }
        }
        else {
            for (Cart cart : list) {
                if(cart.getPrice() != null)
                    price += cart.getPrice();
            }
        }
        String apiEndpoint = "http://localhost:8089/payment-vnpay/pay?amount=" + price;
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(apiEndpoint, ApiResponse.class);
        log.info("priceprice|" + price);
        log.info("response|" + Objects.requireNonNull(response.getBody()).getData());
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public Page<Cart> bill(String username, Integer status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Integer billId = repository.countBillId();
        if(billId == null)
            billId = 1;
        else
            billId += 1;
        log.info("billId|" + billId);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(calendar.getTime());
        System.out.println("Ngày hiện tại: " + currentDate);

        // Thêm 5 ngày
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        String futureDate = dateFormat.format(calendar.getTime());
        log.info("Ngày hiện tại + 5 ngày: " + futureDate);
        repository.updateAllBought(username, status, billId, currentDate, futureDate);
        Page<Cart> cartPage = repository.getPageByBillId(billId, pageable);
        List<Cart> list = cartPage.toList();
        log.info("cartPageList|" + cartPage.toList());
        for (Cart cart : list)
            if(cart.getProductId() != null)
                cagesRepository.updateQuantity(cart.getProductId());
        return cartPage;
    }

    public Integer processPriceOrder(String shape, String material, Integer basePrice) {
        int price = 1000000;
        price = (int) (basePrice + price * processShape(shape) * processMaterial(material));
        return price;
    }

    public float processShape(String shape) {
        float rate = 1f;
        if(shape == null || shape.equals("vuông"))
            rate = 1f;
        else if (shape.equals("tròn"))
            rate = 1.05f;
        else if(shape.equals("thái"))
            rate = 1.1f;
        else // uốn vai
            rate = 1.15f;
        return rate;
    }

    public float processMaterial(String material) {
        float rate = 1f;
        if(material == null || material.equals("tre già"))
            rate = 1f;
        else if (material.equals("tre xử lý") || material.equals("mây") || material.equals("inox"))
            rate = 1.05f;
        else if(material.equals("gỗ mun"))
            rate = 1.1f;
        else // huyết hương đỏ
            rate = 1.15f;
        return rate;
    }
}
