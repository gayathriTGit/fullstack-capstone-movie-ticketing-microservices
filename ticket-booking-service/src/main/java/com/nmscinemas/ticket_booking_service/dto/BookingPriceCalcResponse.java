package com.nmscinemas.ticket_booking_service.dto;

public class BookingPriceCalcResponse {

    private Double totalPrice;
    private Double totalFoodCost;

    public BookingPriceCalcResponse() {
    }

    public BookingPriceCalcResponse(Double totalPrice, Double totalFoodCost) {
        this.totalPrice = totalPrice;
        this.totalFoodCost = totalFoodCost;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalFoodCost() {
        return totalFoodCost;
    }

    public void setTotalFoodCost(Double totalFoodCost) {
        this.totalFoodCost = totalFoodCost;
    }
}
