package de.gowlr.allcar.web;

/**
 * ProductTypeController
 */

import de.gowlr.allcar.entities.*;
import de.gowlr.allcar.repositories.*;
import de.gowlr.allcar.services.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products/")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository ProductTypeRepository;
    @Autowired
    private FilterService FilterService;
    @Autowired
    private CarFilterModel CarFilter;
    @Autowired
    private SearchService SearchService;
    @Autowired
    private PictureRepository PicRepository;
    @Autowired
    private SearchwordRepository SearchwordRepository;
    @Autowired
    private BrandRepository BrandRepository;
    @Autowired
    private CategoryRepository CatRepository;

    @ModelAttribute
    public void setDefaultAttributes(Model model) {
        model.addAttribute("carFilter", CarFilter);
    }

    @GetMapping("{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        EcProductTypeEntity ProductType = ProductTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("product", ProductType);
        return "products/product-detail";
    }

    @GetMapping("create")
    public String showCreateFrom(Model model) {
        model.addAttribute("cats", CatRepository.findAll());
        model.addAttribute("brands", BrandRepository.findAll());
        model.addAttribute("productType", new EcProductTypeEntity());
        return "admin/create-product";
    }

    @PostMapping("add")
    public String addProductType(@Valid EcProductTypeEntity productType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/create-product";
        }
        EcProductTypeEntity savedProduct = ProductTypeRepository.save(productType);
        return "redirect:edit/" + savedProduct.getId().toString();
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        EcProductTypeEntity productType = ProductTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        model.addAttribute("isUpdate", true);
        model.addAttribute("cats", CatRepository.findAll());
        model.addAttribute("brands", BrandRepository.findAll());
        model.addAttribute("productType", productType);
        return "admin/edit-product";
    }

    @PostMapping("update/{id}")
    public String updateProductType(@PathVariable("id") Integer id, @Valid EcProductTypeEntity productType,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            productType.setId(id);
            return "redirect:" + id.toString();
        }

        ProductTypeRepository.save(productType);

        return "redirect:/products/" + id.toString();
    }

    @GetMapping("delete/{id}")
    public String deleteProductType(@PathVariable("id") int id, Model model) {
        EcProductTypeEntity productType = ProductTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProductType Id:" + id));
        ProductTypeRepository.delete(productType);
        model.addAttribute("ProductTypes", ProductTypeRepository.findAll());
        return "redirect:/products/list";
    }

    @GetMapping("search")
    public String searchByKeyword(@RequestParam(value = "searchfor", required = false) String searchfor, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            EcUserEntity currUser = EcUserEntity.getCurrentUser();

            EcSearchWordsEntity searchWordToStore = new EcSearchWordsEntity();
            searchWordToStore.setEcUserByUserId(currUser);
            searchWordToStore.setSearchWords(searchfor);
            SearchwordRepository.save(searchWordToStore);
        }
        model.addAttribute("carFilter", CarFilter);
        model.addAttribute("productTypes", SearchService.search(searchfor));

        return "products/index";
    }

    @GetMapping("showFilter")
    public String showFilteredProducts(Model model) {

        return "products/index";
    }

    @PostMapping("filter")
    public String loginValidate(CarFilterModel carFilter, BindingResult result, Model model, RedirectAttributes red) {

        red.addFlashAttribute("productTypes", FilterService.filter(carFilter));
        red.addFlashAttribute("carFilter", CarFilter);

        return "redirect:showFilter";
    }

    @GetMapping("list")
    public String showAllProducts(Model model) {
        model.addAttribute("productTypes", ProductTypeRepository.findAll());
        return "products/index";
    }

}
