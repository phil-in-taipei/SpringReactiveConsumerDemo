package SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.controllers;

import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.models.SearchKeyword;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services.PexelsService;
import SpringReactiveConsumerDemo.SpringReactiveConsumerDemo.services.UnsplashService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import java.util.Objects;

@Controller
public class ViewController {


    @Autowired
    UnsplashService unsplashService;

    @Autowired
    PexelsService pexelsService;

    @GetMapping("/")
    public String displayIndex(Model model) {
        model.addAttribute("searchKeyword", new SearchKeyword());
        return "index";
    }

    @PostMapping("/")
    public String performSearch(@ModelAttribute("searchKeyword") SearchKeyword searchKeyword, Model model) {
        ReactiveDataDriverContextVariable reactiveData;
        System.out.println("This is the api source: " + searchKeyword.getApiSource());
        if (Objects.equals(searchKeyword.getApiSource(), "pexel")) {
            if (Objects.equals(searchKeyword.getOrientation(), "squarish")) {
                searchKeyword.setOrientation("square");
            }
            reactiveData =
                    new ReactiveDataDriverContextVariable(
                            pexelsService.getPhotos(
                                    searchKeyword.getText(),
                                    searchKeyword.getOrientation()), 1);
        } else {
            reactiveData =
                    new ReactiveDataDriverContextVariable(
                            unsplashService.getPhotos(
                                    searchKeyword.getText(),
                                    searchKeyword.getOrientation()), 1);
        }

        //ReactiveDataDriverContextVariable countData =
        //        new ReactiveDataDriverContextVariable(unsplashService.getTotal(searchKeyword.getText()));

        model.addAttribute("photos", reactiveData);
        model.addAttribute("searchText", searchKeyword.getText());
        //model.addAttribute("count", countData);
        return "index";
    }

}
