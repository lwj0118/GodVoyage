package org.godvoyage.godvoyage.service;

import org.godvoyage.godvoyage.dto.CartDetailDTO;
import org.godvoyage.godvoyage.dto.CartOrderDTO;
import org.godvoyage.godvoyage.repository.CartItemRepository;
import org.godvoyage.godvoyage.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
@SpringBootTest
public class CartServiceTests {
    @Autowired
    CartItemRepository cartItemRepository;



}
