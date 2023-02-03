package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.controllers;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.SearchKeyword;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services.UnsplashService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        ReactiveDataDriverContextVariable reactiveData =
                new ReactiveDataDriverContextVariable(
                        unsplashService.getPhotos(
                                searchKeyword.getText(),
                                searchKeyword.getOrientation()), 1);
        //ReactiveDataDriverContextVariable countData =
        //        new ReactiveDataDriverContextVariable(unsplashService.getTotal(searchKeyword.getText()));

        model.addAttribute("photos", reactiveData);
        model.addAttribute("searchText", searchKeyword.getText());
        //model.addAttribute("count", countData);
        return "index";
    }

}
