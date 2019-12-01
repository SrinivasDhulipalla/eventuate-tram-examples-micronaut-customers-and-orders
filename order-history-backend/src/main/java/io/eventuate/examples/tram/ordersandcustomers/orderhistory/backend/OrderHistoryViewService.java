package io.eventuate.examples.tram.ordersandcustomers.orderhistory.backend;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;

import java.math.BigDecimal;

public class OrderHistoryViewService {

  private CustomerViewRepository customerViewRepository;
  private OrderViewRepository orderViewRepository;

  public OrderHistoryViewService(CustomerViewRepository customerViewRepository,
                                 OrderViewRepository orderViewRepository) {
    this.customerViewRepository = customerViewRepository;
    this.orderViewRepository = orderViewRepository;
  }

  public void createCustomer(Long customerId, String customerName, BigDecimal creditLimit) {
    customerViewRepository.addCustomer(customerId, customerName, creditLimit);
  }

  public void addOrder(Long customerId, Long orderId, BigDecimal orderTotal) {
    customerViewRepository.addOrder(customerId, orderId, orderTotal);
    orderViewRepository.addOrder(orderId, orderTotal);
  }

  public void approveOrder(Long customerId, Long orderId) {
    customerViewRepository.updateOrderState(customerId, orderId, OrderState.APPROVED);
    orderViewRepository.updateOrderState(orderId, OrderState.APPROVED);
  }

  public void rejectOrder(Long customerId, Long orderId) {
    customerViewRepository.updateOrderState(customerId, orderId, OrderState.REJECTED);
    orderViewRepository.updateOrderState(orderId, OrderState.REJECTED);
  }
}
