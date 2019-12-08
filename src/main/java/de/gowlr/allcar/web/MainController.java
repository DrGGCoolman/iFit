package de.gowlr.allcar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.gowlr.allcar.repositories.ProductTypeRepository;

@Controller
public class MainController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("highlights", productTypeRepository.findByHighlighted(true));
        return "index";
    }

    @RequestMapping("/impressum")
    public String Impressum() {
        return "impressum";
    }

    @RequestMapping("/new-user")
    public String newUser() {
        return "new-user";
    }

    @RequestMapping("/datenschutz")
    public String Datenschutz() {
        return "datenschutz";
    }

    @RequestMapping("/admin/")
    public String adminIndex() {
        return "admin/index";
    }

    @RequestMapping("/admin/index")
    public String adminIndexx() {
        return "admin/index";
    }

    @RequestMapping("/products/product-detail")
    public String productDetail() {
        return "products/product-detail";
    }

    @RequestMapping("/admin/products")
    public String adminProducts() {
        return "admin/Products";
    }

    @RequestMapping("/admin/product-create-edit")
    public String adminproductCreateEdit() {
        return "admin/product-create-edit";
    }

}