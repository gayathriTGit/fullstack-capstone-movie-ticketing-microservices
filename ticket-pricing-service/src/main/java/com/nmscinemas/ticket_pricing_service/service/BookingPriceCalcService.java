package com.nmscinemas.ticket_pricing_service.service;

import com.nmscinemas.ticket_pricing_service.dto.BookingPriceCalcRequest;
import com.nmscinemas.ticket_pricing_service.dto.BookingPriceCalcResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingPriceCalcService {

    public BookingPriceCalcResponse calculateBookingPrice(BookingPriceCalcRequest request){

        final double taxRate = 0.08;
        final double oneDrinkPrice = 10.0;
        final double onePopcornPrice = 8.0;
        final double oneIceCreamPrice = 11.0;
        final double oneCandyPacketPrice = 5.0;

        System.out.println("Calling movie ticket pricing service...");
        BookingPriceCalcResponse response = new BookingPriceCalcResponse();
        double totalFoodCost = (request.getNoOfCandy() * oneCandyPacketPrice) +
                (request.getNoOfDrinks() * oneDrinkPrice) + (request.getNoOfPopcorns() * onePopcornPrice) +
                (request.getNoOfIceCream() * oneIceCreamPrice);
        double totalPrice = (request.getBaseTicketPrice() * request.getNoOfTickets()) + totalFoodCost;
        totalPrice = totalPrice + (taxRate * totalPrice);

        response.setTotalFoodCost(totalFoodCost);
        response.setTotalPrice(totalPrice);

        return response;
    }
}
