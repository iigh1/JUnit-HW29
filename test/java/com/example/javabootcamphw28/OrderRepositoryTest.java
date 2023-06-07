package com.example.javabootcamphw28;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {



    @Autowired
    OrderRepository orderRepository;


    MyUser myUser;

    MyOrder myOrder1,myOrder2,myOrder3;

    List<MyOrder> myOrderList;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null,"majd","1234","ADMIN",null);
        myOrder1=new MyOrder(null,1,50,"2023/05/10","new",null,null);
        myOrder2=new MyOrder(null,1,30,"2023/05/10","new",null,null);
        myOrder3=new MyOrder(null,1,70,"2023/05/10","new",null,null);
    }
    @Test
    public void findAllByMyUser(){
        orderRepository.save(myOrder1);
        orderRepository.save(myOrder2);
        orderRepository.save(myOrder3);
        myOrderList=orderRepository.findAllByMyUser(myUser);
        Assertions.assertThat(myOrderList.get(0).getMyUser().getId()).isEqualTo(myUser.getId());
    }

    @Test
    public void  findMyOrderById(){
        orderRepository.save(myOrder1);
        myOrder2=orderRepository.findMyOrderById(myOrder1.getId());
        Assertions.assertThat(myOrder2).isEqualTo(myOrder1);
    }

}
