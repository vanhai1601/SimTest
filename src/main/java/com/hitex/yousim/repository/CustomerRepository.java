package com.hitex.yousim.repository;

import com.hitex.yousim.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c from Customer c where c.cusName = ?1 and c.status = 1")
    Customer findCustomerByCusName(String cusName);

    @Query(value = "select c from Customer c where c.cusName = ?1 and c.password =?2 and c.status = 1")
    Customer findCustomer(String cusName, String password);

    @Query(value = "select c from Customer c where c.custId = :custId")
    Customer findCustomerById(int custId);




  @Query(value = "select cus from Customer cus where cus.provider =?1 and cus.provider_id =?2")
  Customer findCustomerByProviderAndProvider_id(String provider, String provider_id);

    @Query(value = "select u from Customer u where u.email= ?1")
    Customer findCustomerByCustomerEmail(String email);
}
