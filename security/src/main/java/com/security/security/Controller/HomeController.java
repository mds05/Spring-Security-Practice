package com.security.security.Controller;

import java.util.ArrayList;
import java.util.List;
import com.security.security.model.Car;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private List<Car> c=new ArrayList<>(List.of(new Car("maruti","yyy",500000),new Car("sui","jjajaj",4445556)));
    @GetMapping("/")
    public String yo(){
        return "hello monosshita. wassup";
    }
    @GetMapping("/cars")
    private List<Car> getStudents(){
        return c;
    }
    @PostMapping("/car")
    private void addStudents(@RequestBody Car d){
        c.add(d);
    }
    @GetMapping("/csrf_token")
    private CsrfToken getcsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
