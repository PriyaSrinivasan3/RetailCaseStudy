package com.tgt.myRetail.service;

import com.tgt.myRetail.MyRetailApplication;
import com.tgt.myRetail.response.ProductPricing;
import com.tgt.myRetail.response.ProductResponse;
import com.tgt.myRetail.response.ProductDescResponse;
import com.tgt.myRetail.domain.Pricing;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyRetailApplication.class)
@ComponentScan
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)


public class MyRetailServiceTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyRetailService myRetailService;

    private MockRestServiceServer mockServer;

    private String API_URL = "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=dtaxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statisticsstion_answer_statistics";

    private String SUCCESS_JSON = "{\n" +
            "  \"product\": {\n" +
            "    \"question_answer_statistics\": {\n" +
            "      \"questionCount\": 0,\n" +
            "      \"answerCount\": 0\n" +
            "    },\n" +
            "    \"deep_red_labels\": {\n" +
            "      \"total_count\": 2,\n" +
            "      \"labels\": [\n" +
            "        {\n" +
            "          \"id\": \"gqwm8i\",\n" +
            "          \"name\": \"TAC\",\n" +
            "          \"type\": \"relationship type\",\n" +
            "          \"priority\": 0,\n" +
            "          \"count\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"twbl94\",\n" +
            "          \"name\": \"Movies\",\n" +
            "          \"type\": \"merchandise type\",\n" +
            "          \"priority\": 0,\n" +
            "          \"count\": 1\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"available_to_promise_network\": {\n" +
            "      \"product_id\": \"13860428\",\n" +
            "      \"id_type\": \"TCIN\",\n" +
            "      \"available_to_promise_quantity\": 10,\n" +
            "      \"street_date\": \"2011-11-15T06:00:00.000Z\",\n" +
            "      \"availability\": \"AVAILABLE\",\n" +
            "      \"online_available_to_promise_quantity\": 10,\n" +
            "      \"stores_available_to_promise_quantity\": 0,\n" +
            "      \"availability_status\": \"LIMITED_STOCK\",\n" +
            "      \"multichannel_options\": [\n" +
            "        \"HOLD\",\n" +
            "        \"SHIPGUEST\"\n" +
            "      ],\n" +
            "      \"is_infinite_inventory\": false,\n" +
            "      \"loyalty_availability_status\": \"LIMITED_STOCK\",\n" +
            "      \"loyalty_purchase_start_date_time\": \"1970-01-01T00:00:00.000Z\",\n" +
            "      \"is_loyalty_purchase_enabled\": false,\n" +
            "      \"is_out_of_stock_in_all_store_locations\": false,\n" +
            "      \"is_out_of_stock_in_all_online_locations\": false\n" +
            "    },\n" +
            "    \"item\": {\n" +
            "      \"tcin\": \"13860428\",\n" +
            "      \"bundle_components\": {\n" +
            "        \"is_assortment\": false,\n" +
            "        \"is_kit_master\": false,\n" +
            "        \"is_standard_item\": true,\n" +
            "        \"is_component\": false\n" +
            "      },\n" +
            "      \"dpci\": \"058-34-0436\",\n" +
            "      \"upc\": \"025192110306\",\n" +
            "      \"product_description\": {\n" +
            "        \"title\": \"The Big Lebowski (Blu-ray)\",\n" +
            "        \"bullet_description\": [\n" +
            "          \"<B>Movie Studio:</B> Universal Studios\",\n" +
            "          \"<B>Movie Genre:</B> Comedy\",\n" +
            "          \"<B>Software Format:</B> Blu-ray\"\n" +
            "        ],\n" +
            "        \"general_description\": \"Blu-ray BIG LEBOWSKI, THE Movies\"\n" +
            "      },\n" +
            "      \"parent_items\": \"46767107\",\n" +
            "      \"buy_url\": \"https://www.target.com/p/the-big-lebowski-blu-ray/-/A-13860428\",\n" +
            "      \"variation\": {},\n" +
            "      \"enrichment\": {\n" +
            "        \"images\": [\n" +
            "          {\n" +
            "            \"base_url\": \"https://target.scene7.com/is/image/Target/\",\n" +
            "            \"primary\": \"13860428\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"sales_classification_nodes\": [\n" +
            "          {\n" +
            "            \"node_id\": \"5xswx\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"node_id\": \"yzuww\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"node_id\": \"ieagq\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"node_id\": \"55ayu\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"node_id\": \"5t0ak\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"return_method\": \"This item can be returned to any Target store or Target.com.\",\n" +
            "      \"handling\": {},\n" +
            "      \"recall_compliance\": {\n" +
            "        \"is_product_recalled\": false\n" +
            "      },\n" +
            "      \"tax_category\": {\n" +
            "        \"tax_class\": \"G\",\n" +
            "        \"tax_code_id\": 99999,\n" +
            "        \"tax_code\": \"99999\"\n" +
            "      },\n" +
            "      \"display_option\": {\n" +
            "        \"is_size_chart\": false,\n" +
            "        \"is_warranty\": false\n" +
            "      },\n" +
            "      \"fulfillment\": {\n" +
            "        \"is_po_box_prohibited\": true,\n" +
            "        \"po_box_prohibited_message\": \"We regret that this item cannot be shipped to PO Boxes.\"\n" +
            "      },\n" +
            "      \"package_dimensions\": {\n" +
            "        \"weight\": \"0.18\",\n" +
            "        \"weight_unit_of_measure\": \"POUND\",\n" +
            "        \"width\": \"5.33\",\n" +
            "        \"depth\": \"6.65\",\n" +
            "        \"height\": \"0.46\",\n" +
            "        \"dimension_unit_of_measure\": \"INCH\"\n" +
            "      },\n" +
            "      \"environmental_segmentation\": {\n" +
            "        \"is_lead_disclosure\": false\n" +
            "      },\n" +
            "      \"manufacturer\": {},\n" +
            "      \"product_vendors\": [\n" +
            "        {\n" +
            "          \"id\": \"4667999\",\n" +
            "          \"manufacturer_style\": \"61119422\",\n" +
            "          \"vendor_name\": \"UNIVERSAL HOME VIDEO\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"1258738\",\n" +
            "          \"manufacturer_style\": \"025192110306\",\n" +
            "          \"vendor_name\": \"BAKER AND TAYLOR\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"1979650\",\n" +
            "          \"manufacturer_style\": \"61119422\",\n" +
            "          \"vendor_name\": \"Universal Home Ent PFS\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"product_classification\": {\n" +
            "        \"product_type\": \"542\",\n" +
            "        \"product_type_name\": \"ELECTRONICS\",\n" +
            "        \"item_type_name\": \"Movies\",\n" +
            "        \"item_type\": {\n" +
            "          \"category_type\": \"Item Type: MMBV\",\n" +
            "          \"type\": 300752,\n" +
            "          \"name\": \"Movies\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"product_brand\": {\n" +
            "        \"brand\": \"Universal Home Video\"\n" +
            "      },\n" +
            "      \"item_state\": \"READY_FOR_LAUNCH\",\n" +
            "      \"specifications\": [],\n" +
            "      \"attributes\": {\n" +
            "        \"gift_wrapable\": \"N\",\n" +
            "        \"has_prop65\": \"N\",\n" +
            "        \"is_hazmat\": \"N\",\n" +
            "        \"max_order_qty\": 10,\n" +
            "        \"street_date\": \"2011-11-15\",\n" +
            "        \"media_format\": \"Blu-ray\",\n" +
            "        \"merch_class\": \"MOVIES\",\n" +
            "        \"merch_subclass\": 34,\n" +
            "        \"return_method\": \"This item can be returned to any Target store or Target.com.\"\n" +
            "      },\n" +
            "      \"country_of_origin\": \"US\",\n" +
            "      \"relationship_type_code\": \"Title Authority Child\",\n" +
            "      \"subscription_eligible\": false,\n" +
            "      \"ribbons\": [],\n" +
            "      \"tags\": [],\n" +
            "      \"estore_item_status_code\": \"A\",\n" +
            "      \"eligibility_rules\": {\n" +
            "        \"hold\": {\n" +
            "          \"is_active\": true\n" +
            "        },\n" +
            "        \"ship_to_guest\": {\n" +
            "          \"is_active\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"return_policies\": {\n" +
            "        \"user\": \"Regular Guest\",\n" +
            "        \"policyDays\": \"30\",\n" +
            "        \"guestMessage\": \"This item must be returned within 30 days of the ship date. See return policy for details.\"\n" +
            "      },\n" +
            "      \"gifting_enabled\": false\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Before
    public void setup() {
        //create a mock Server instance for RestTemplate@Test
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    //Test to verify getPricingbyItem service method
    @Test
    public void testgetPricingByItem() throws IOException {

        Pricing pricing = myRetailService.getPricingByItem("13860428");
        assertEquals("13860428", pricing.getItem());

    }

    //Test to verify getPricingbyItem service method
    @Test
    public void testgetProductGeneralDesc() throws IOException {


        mockServer
                .expect(requestTo(API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(SUCCESS_JSON,
                                MediaType.TEXT_PLAIN));

        ProductDescResponse productDescResponse  = myRetailService.getProductGeneralDesc("13860428");
        mockServer.verify();

        assertEquals("The Big Lebowski (Blu-ray)",productDescResponse.getProductName());
    }

    @Test
    public void testupdate() throws Exception{

        ProductPricing productPricing = new ProductPricing();
        productPricing.setValue((float) 40);
        productPricing.setCurrency("USD");

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId("13860429");
        productResponse.setName("The Big Lebowski (Blu-ray)");
        productResponse.setProductPricing(productPricing);

        ProductResponse pdtResponse = myRetailService.update(productResponse);

        assertNotNull(pdtResponse);
        assertEquals(40.0,pdtResponse.getProductPricing().getValue(),0.01);

    }

}
