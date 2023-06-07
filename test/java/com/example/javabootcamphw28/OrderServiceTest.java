package com.example.javabootcamphw28;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repository.AuthRepository;
import com.example.javabootcamphw28.Repository.OrderRepository;
import com.example.javabootcamphw28.Repository.ProductRepository;
import com.example.javabootcamphw28.Service.OrderService;
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

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    AuthRepository authRepository;

    MyOrder myOrder1,myOrder2,myOrder3;

    MyUser myUser;
    Product product;

    List<MyOrder> myOrderList;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"majd","1234","ADMIN",null);

        product=new Product(null,"product",10,null,null);
        myOrder1=new MyOrder(null,1,50,"2023/05/10","new",null,null);
        myOrder2=new MyOrder(null,1,30,"2023/05/10","new",null,null);
        myOrder3=new MyOrder(null,1,70,"2023/05/10","new",null,null);


        myOrderList =new ArrayList<>();
        myOrderList.add(myOrder1);
        myOrderList.add(myOrder2);
        myOrderList.add(myOrder3);

    }

    @Test
    public void getOrders(){
        when(orderRepository.findAll()).thenReturn(myOrderList);

        List<MyOrder> orders = orderService.getOrders();
        Assertions.assertEquals(orders,myOrderList);
        Assertions.assertEquals(3,myOrderList.size());

        Mockito.verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void updateOrderTest(){
        when(orderRepository.findMyOrderById(myOrder1.getId())).thenReturn(myOrder1);
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        orderService.updateOrder(myOrder1.getId(),myOrder2,myUser.getId());

        verify(orderRepository,times(1)).findMyOrderById(myOrder1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
    }
    @Test
    public void deleteOrderTest(){
        when(orderRepository.findMyOrderById(myOrder1.getId())).thenReturn(myOrder1);
        orderService.deleteOrder(myOrder1.getId(),myUser.getId());
        verify(orderRepository,times(1)).findMyOrderById(myOrder1.getId());

    }

    @Test
    public void getOrderByIdTest(){
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        when(orderRepository.findAllByMyUser(myUser)).thenReturn(myOrderList);
        List<MyOrder> orders = orderService.getOrders();
        Assertions.assertEquals(orders, myOrderList);
        Mockito.verify(authRepository, times(1)).findMyUserById(myUser.getId());
        Mockito.verify(orderRepository, times(1)).findAllByMyUser(myUser);

    }

}
