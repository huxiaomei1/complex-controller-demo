package geektime.spring.springbucks.waiter.service;

import geektime.spring.springbucks.waiter.model.Coffee;
import geektime.spring.springbucks.waiter.model.CoffeeOrder;
import geektime.spring.springbucks.waiter.model.OrderState;
import geektime.spring.springbucks.waiter.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("创建订单: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }


    public List<CoffeeOrder> findall(){
        List<CoffeeOrder> findAll = orderRepository.findAll();
        log.info("咖啡订单：{}",findAll);
        return findAll;
    }

}
