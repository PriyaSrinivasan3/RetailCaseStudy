package com.tgt.myRetail.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.myRetail.domain.Pricing;
import com.tgt.myRetail.repository.MyRetailRepository;
import com.tgt.myRetail.response.ProductResponse;
import com.tgt.myRetail.response.ProductPricing;
import com.tgt.myRetail.response.ProductDescResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class MyRetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRetailService.class);
    private final String apiUrl;
    private final String apiSearch;

    @Autowired
    private MyRetailRepository myRetailRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MyRetailService(@Value("${api.service.url}") String apiUrlInput, @Value("${api.service.search}") String apiSearchInput){
        apiUrl = apiUrlInput;
        apiSearch = apiSearchInput;
    }
    /**
     * Gets the Product Data and stores in ProductResponse
     *
     * @return Productresponse
     */

    public ProductResponse getItemData(String item_id) throws IOException {

        //Invoke the API url with item_id input and fetch the item General Description and store in ProductDescResponse class
        ProductDescResponse productDescResponse = getProductGeneralDesc(item_id);

        //Extract pricing information for item_id input from Mongodb
        Pricing pricing = getPricingByItem(item_id);

        ProductPricing productPricing =new ProductPricing();

        // Store pricing data in productPricing class
        productPricing.setValue(pricing.getValue());
        productPricing.setCurrency(pricing.getCurrency_code());

        // Store Product id, name and Product Pricing in ProductResponse class
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(item_id);
        productResponse.setName(productDescResponse.getProductName());
        productResponse.setProductPricing(productPricing);

        return productResponse;
    }

    /**
     * Gets the product description from api.target.com
     *
     * @return Productdescresponse
     */

    public ProductDescResponse getProductGeneralDesc(String item) throws IOException{

           LOGGER.debug("getGeneralDesc item input: {}", item);
           LOGGER.debug("apiurl is :- " + apiUrl + item + "?" + apiSearch);

        ResponseEntity<String> apiresponse= restTemplate.getForEntity(apiUrl + item + "?" + apiSearch , String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(apiresponse.getBody());

        JsonNode items = root.findValue("item");
        JsonNode prdDesc = items.findValue("product_description");
        String productName = prdDesc.findPath("title").textValue();
        ProductDescResponse productDescResponse = new ProductDescResponse();
        productDescResponse.setProductName(productName);
        return productDescResponse;
    }

    /**
     * Gets the Product Pricing from Mongodb and stores in ProductResponse
     *
     * @return ProductPricing
     */

    protected Pricing getPricingByItem(String item) throws IOException{

        Pricing pricing = myRetailRepository.findByitem(item);

        LOGGER.debug("itemspace={} ", pricing.toString());

        return pricing;
    }

    /**
     * Updates the pricing in mongo database.
     *
     * @param - productpricing to be updated
     *
     * @return - updated ProductResponse entity with updated pricing
     */

    public ProductResponse update(ProductResponse itemId) throws IOException{

        LOGGER.info("Update pricing with itemid = {} ",itemId.getId());


        Pricing existingpricing = myRetailRepository.findByitem(itemId.getId());

        LOGGER.debug("Read pricing with itemid = {} ",existingpricing.getItem());


        existingpricing.setValue(itemId.getProductPricing().getValue());
        existingpricing.setCurrency_code(itemId.getProductPricing().getCurrency());

        LOGGER.debug("pricing with updated values \n",existingpricing.toString());

        Pricing updatedPricing = myRetailRepository.save((existingpricing));

        LOGGER.debug("pricing updated successfully");

        // updatedProductPricing object updated with new value and currency
        ProductPricing updatedProductPricing = new ProductPricing();
        updatedProductPricing.setValue(updatedPricing.getValue());
        updatedProductPricing.setCurrency(updatedPricing.getCurrency_code());

        // updatedItem object updated with product id, name and updatedProductPricing
        ProductResponse updatedItem = new ProductResponse();
        updatedItem.setId(updatedPricing.getItem());
        updatedItem.setName(itemId.getName());
        updatedItem.setProductPricing(updatedProductPricing);

        return updatedItem;

    }

}