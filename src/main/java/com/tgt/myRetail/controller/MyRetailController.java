package com.tgt.myRetail.controller;

import com.tgt.myRetail.response.ProductResponse;
import com.tgt.myRetail.service.MyRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class MyRetailController {

    @Autowired
    private MyRetailService myRetailService;

    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductResponse getItemData(@PathVariable("id") String id) {

        ProductResponse productResponse = null;
        try {
            // Invoke the MyRetail Service
            productResponse = myRetailService.getItemData(id);
        }
        catch(Exception ex){

            throw new ItemNotFoundException();
        }
        return productResponse;
    }


    /**
     * Return Updated Pricing
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody

    public ProductResponse updateItemPricing(@PathVariable("id") String id, @RequestBody ProductResponse item) throws Exception {
        if(id.equals(item.getId())){
            return myRetailService.update(item);

        } else {
            throw new IdNotFoundException();
        }
    }

    // Custom Runtime exception class for ItemController
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product Not found")  // 404
    protected class ItemNotFoundException extends RuntimeException {
        // ...
    }

    // Custom Runtime exception class for ItemController
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "Path Variable does not match ID in Request Body")  // 404
    protected class IdNotFoundException extends RuntimeException {
        // ...
    }


}