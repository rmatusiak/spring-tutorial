package com.acme.order;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.acme.order.application.SpringAnnotationBasedApplication;
import com.acme.order.pizza.PizzaOrderService;
import com.acme.order.pizza.PizzaOrderServiceImpl;
import com.acme.order.pizza.PizzaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAnnotationBasedApplication.class})
public class SpringTest {
	
	public void test(){
		Customer customer1 = new Customer("John Smith", "john@smith.com", "Lodz, Jaracza 74");
		Customer customer2 = new Customer("Jan Kowalski", "jan@kowalski.pl", "Lodz, Piotrkowska 100");
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		PizzaOrderService orderService = ctx.getBean(PizzaOrderServiceImpl.class);
		
		String orderId1 = orderService.createOrder(customer1, PizzaType.LARGE);
		String orderId2 = orderService.createOrder(customer2, PizzaType.SMALL);

		log.info("Unprocessed orders:{}", orderService.fetchUnprocessed());
		log.info("Delivered orders:{}", orderService.fetchDelivered());

		orderService.deliverOrder(orderId1);
		log.info("Delivered orders:{}", orderService.fetchDelivered());
		orderService.cancelOrder(orderId2);
		log.info("Delivered orders:{}", orderService.fetchDelivered());
		log.info("Cancelled orders:{}", orderService.fetchCancelled());
		log.info("Unprocessed orders:{}", orderService.fetchUnprocessed());
	}
	
}