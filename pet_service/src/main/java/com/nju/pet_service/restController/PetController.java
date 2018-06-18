package com.nju.pet_service.restController;

import com.nju.pet_service.restController.info.PetInfo;
import com.nju.pet_service.restController.info.PetListInfo;
import com.nju.pet_service.restController.vo.ResultVO;
import com.nju.pet_service.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    PetService petService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO add(@RequestBody PetInfo petInfo){
        if (petService.addPet(petInfo) == 1){
            return new ResultVO("add a pet", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    public PetListInfo getList(@PathVariable Integer cid){
        return petService.getPets(cid);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PetInfo getPet(@PathVariable Integer id){
        return petService.getPet(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVO update(@RequestBody PetInfo petInfo){
        if (petService.addPet(petInfo) == 1){
            return new ResultVO("update a pet", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/search/{petName}", method = RequestMethod.GET)
    public PetListInfo get(@PathVariable String petName){
        return petService.getPets(petName);
    }

    @RequestMapping(value = "/detail/{petName}", method = RequestMethod.GET)
    public PetInfo getPetByName(@PathVariable String petName){
        return petService.getPet(petName);
    }
}
