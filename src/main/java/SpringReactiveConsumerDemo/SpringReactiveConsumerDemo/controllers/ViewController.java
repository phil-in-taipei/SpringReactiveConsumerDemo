package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.controllers;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.SearchKeyword;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services.UnsplashService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ViewController {


    @Autowired
    UnsplashService unsplashService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchKeyword", new SearchKeyword());
        return "index";
    }

    @PostMapping("/")
    public String performSearch(@ModelAttribute("searchKeyword") SearchKeyword searchKeyword, Model model) {
        System.out.println("****************Trying to get the count********************");
        //unsplashService.getTotal(searchKeyword.getText());
        //int count = unsplashService.getTotal(searchKeyword.getText());
        //System.out.println(count);
        ReactiveDataDriverContextVariable reactiveData =
                new ReactiveDataDriverContextVariable(unsplashService.getPhotos(searchKeyword.getText()), 1);
        model.addAttribute("photos", reactiveData);
        //model.addAttribute("size", unsplashService.getTotal(searchKeyword.getText()));
        model.addAttribute("searchText", searchKeyword.getText());
        return "index";
    }

}
