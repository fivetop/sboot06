package com.example.sboot.controler;


import com.example.sboot.entity.Customer;
import com.example.sboot.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/register")
    public String register(){
        Customer c=new Customer();
        c.setUsername("cocom");
        c.setPassword("12345");
        c.setCustomerName("홍길동");
        c.setAge(40);
        c.setRating("GOLD");
        c.setOccupation("회사원");

        Customer cus=customerService.register(c);
        return "redirect:/";
    }

    @GetMapping("/lists")
    public String lists(Model model){
        List<Customer> cusList=customerService.getAllCustomer();
        System.out.println(cusList); // ?
        model.addAttribute("cusList", cusList);
        return "list"; // list.html(404)
    }

    @GetMapping("/detail/{id}") // /customer/detail/2
    public String getDetail(@PathVariable Long id, Model model){
        Optional<Customer> optional=customerService.getById(id);
        Customer customer;
        if(optional.isPresent()){
            customer=optional.get();
        }else{
            throw new IllegalArgumentException("error");
        }
        model.addAttribute("customer", customer);
        return "detail"; // detail.html
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id){
        Customer customer=new Customer();
        customer.setUsername("jpa");
        customer.setAge(55);
        customerService.cusUpdate(id, customer);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        customerService.cusDelete(id);
        return "redirect:/";
    }

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password, Model  model){
        Customer customer=customerService.login(username, password);
        model.addAttribute("customer", customer);
        return "result"; // result.html
    }

    @GetMapping("/ageList/{age}")
    public String ageList(@PathVariable int age, Model model){
        List<Customer> ageLists=customerService.getAge(age);
        System.out.println(ageLists.size()); // ?
        model.addAttribute("ageLists", ageLists);
        return "ageList"; // ageList.html
    }
}
