package com.iss.gold.service;

import com.iss.gold.businesslogic.GoldStockExchangeBusinessLogic;
import com.iss.gold.pojo.PlaceGoldOrderRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Vikram
 */
@Path("goldStockExchangeService")
public class GoldStockExchangeService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String placeGoldOrder(PlaceGoldOrderRequest placeGoldOrderRequest) {
        System.out.println(placeGoldOrderRequest);
        GoldStockExchangeBusinessLogic goldStockExchangeBusinessLogic = new GoldStockExchangeBusinessLogic();
        return goldStockExchangeBusinessLogic.placeGoldOrder(placeGoldOrderRequest);
    }
}
