package com.example.javabootcamphw28;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repository.OrderRepository;
import com.example.javabootcamphw28.Repository.ProductRepository;
import com.example.javabootcamphw28.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;

    MyOrder myOrder1,myOrder2,myOrder3;
    Product product1,product2,product3;

    List<Product> productList;
    MyUser myUser;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"majd","1234","ADMIN",null);

        product1=new Product(null,"product",10,null,null);
        product2=new Product(null,"product",10,null,null);


        myOrder1=new MyOrder(null,1,50,"2023/05/10","new",null,null);
        myOrder2=new MyOrder(null,1,30,"2023/05/10","new",null,null);
        myOrder3=new MyOrder(null,1,70,"2023/05/10","new",null,null);


        productList =new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }
    @Test
    public void getProducts(){
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> productList1 = productService.getProducts();
        Assertions.assertEquals(productList1,productList);
        Assertions.assertEquals(productList1.size(),productList.size());

        Mockito.verify(productRepository, times(1)).findAll();
    }
}
