package com.dallab.cattoy.application;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProdcutServiceTest {

    private ProdcutService prodcutService;

    @Before
    public void setUp() {

        prodcutService = new ProdcutService();
    }

    @Test
    public void getProducts() throws Exception {

        assertThat(prodcutService.getProducts()).isEmpty();
    }

    @Test
    public void addProdcut() throws Exception {

        prodcutService.addProduct("쥐돌이");

        assertThat(prodcutService.getProducts()).isNotEmpty();
    }
}
