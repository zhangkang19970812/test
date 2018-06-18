package com.nju.category_service.restController;

import com.nju.category_service.restController.info.CategoryInfo;
import com.nju.category_service.restController.info.CategoryListInfo;
import com.nju.category_service.restController.vo.ResultVO;
import com.nju.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO add(@RequestBody CategoryInfo categoryInfo){
        if (categoryService.addCategory(categoryInfo) == 1){
            return new ResultVO("add a category", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CategoryListInfo getList(){
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public CategoryInfo getList(@PathVariable Integer id){
        return categoryService.getCategory(id);
    }

}
