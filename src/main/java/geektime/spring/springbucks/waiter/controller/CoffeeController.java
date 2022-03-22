package geektime.spring.springbucks.waiter.controller;

import geektime.spring.springbucks.waiter.model.Coffee;
import geektime.spring.springbucks.waiter.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;

import java.util.List;

@Controller
@RequestMapping("/coffee")
@ResponseBody
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/")
    @ResponseBody
    @Test
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }


    @GetMapping("/showAll")
    public ModelAndView showAll(){
        List<Coffee> coffeeList = coffeeService.getAllCoffee();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("coffeeList",coffeeList);
        modelAndView.setViewName("show-all");
        return modelAndView;
    }
}
