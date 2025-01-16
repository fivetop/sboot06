package com.example.sboot.service;

import com.example.sboot.entity.Customer;
import com.example.sboot.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    //@Autowired
    private final CustomerRepository customerRepository;

    // 회원등록
    @Transactional
    public Customer register(Customer customer){
        return customerRepository.save(customer); // save() -> insert SQL
    }

    // 회원목록 가져오기( + 리뷰:List<Review>, + 장바구니:List<Cart>
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer(){
        //return customerRepository.findAll(); // select SQL~
        List<Customer> coustomers=customerRepository.findAll();
        coustomers.forEach(customer -> Hibernate.initialize(customer.getReviews()));
        return customerRepository.findAllWithReviews();
    }


    // 특정회원 한명의 정보 가져오기
    @Transactional(readOnly = true)
    public Optional<Customer> getById(Long id){ // PK(id)
        return customerRepository.findById(id);
        // select * from Customer where idx=
    }

    // 특정회원의 정보를 수정하기
    @Transactional
    public void cusUpdate(Long id, Customer customer){
        Optional<Customer> optional=customerRepository.findById(id);
        Customer dbCustomer;
        if(optional.isPresent()){
            dbCustomer=optional.get();
        }else{
            throw new IllegalArgumentException("error");
        }
        dbCustomer.setUsername(customer.getUsername()); // 아이디
        dbCustomer.setAge(customer.getAge()); // 나이
        customerRepository.save(dbCustomer); // update SQL
    }

    // 회원삭제
    @Transactional
    public void cusDelete(Long id){ // id
        customerRepository.deleteById(id); // delete ~ where id=id
    }

    // 아이디와 패스워드가 일치하는 회원을 가져오기
    @Transactional(readOnly = true)
    public  Customer login(String username, String password){
        // Optional<Customer> optional=customerRepository.findByUsernameAndPassword(username,password);
        Optional<Customer> optional=customerRepository.usernameAndPassword(username,password);
        Customer customer;
        if(optional.isPresent()){
            customer=optional.get();
        }else{
            throw new IllegalArgumentException("error");
        }
        return customer;
    }

    @Transactional(readOnly = true)
    public List<Customer> getAge(int age){
        //return customerRepository.findByAgeGreaterThanEqual(age);
        return customerRepository.ageGreaterThanEqual(age);
    }

}
