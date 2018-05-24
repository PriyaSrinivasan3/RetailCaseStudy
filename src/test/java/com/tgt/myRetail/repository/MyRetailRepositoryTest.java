package com.tgt.myRetail.repository;

import com.tgt.myRetail.MyRetailApplication;
import com.tgt.myRetail.domain.Pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyRetailApplication.class)
@ComponentScan
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class MyRetailRepositoryTest {

    @Autowired
    private MyRetailRepository myRetailRepository;

    //JUnit for  Item data fetch against item input list
    @Test
    public void testfindByitem() {

        Pricing pricing= myRetailRepository.findByitem("13860428");
        assertEquals("13860428",pricing.getItem());

    }
}
