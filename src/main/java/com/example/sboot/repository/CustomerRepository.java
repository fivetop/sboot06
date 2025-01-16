package com.example.sboot.repository;

import com.example.sboot.entity.Book;
import com.example.sboot.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 1. JpaRepository에서 제공해주는 기본 CRUD Method
    // save() -> insert SQL
    // findAll()-> select SQL
    // 2. 쿼리메서드
    public Optional<Customer> findByUsernameAndPassword(String username, String password);

    //@Query("select c from  Customer c where c.username=:username and c.password=:password")
    @Query(value = "select * from  customer where username=?1 and password=?2", nativeQuery = true)
    public Optional<Customer> usernameAndPassword(String username, String password);

    // 사용자가 입력한 나이보다 큰 회원정보를 출력?
    public List<Customer> findByAgeGreaterThanEqual(int age);
    // 3. JPQL(Entity, Table)
    //@Query("select c from Customer c where c.age >=:age")
    @Query(value = "select * from customer where age>=?1", nativeQuery = true)
    public List<Customer> ageGreaterThanEqual(int age);

    //@Query("SELECT c FROM Customer c LEFT JOIN FETCH c.reviews")
    //public List<Customer> findAllWithReviews();

    @EntityGraph(attributePaths = {"reviews"})
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllWithReviews();
}
