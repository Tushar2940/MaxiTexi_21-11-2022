package com.example.maxitexi.Model;

import java.util.List;

public class ModelData {
    public String To;
    public String pickup;
    public String drop;
    public String name;
    public int id;
    public String pessenger;
    public String ContactNo;
    public String flightno;
    public String bookingdate;
    public String time;
    public String price;
    public String payment;
    public String totaltime;
    public String distance;
    public String Remarks;

    public ModelData(String to, String pickup, String drop, String name, int id, String pessenger, String contactNo, String flightno, String bookingdate, String time, String price, String payment, String totaltime, String distance, String remarks) {
        To = to;
        this.pickup = pickup;
        this.drop = drop;
        this.name = name;
        this.id = id;
        this.pessenger = pessenger;
        ContactNo = contactNo;
        this.flightno = flightno;
        this.bookingdate = bookingdate;
        this.time = time;
        this.price = price;
        this.payment = payment;
        this.totaltime = totaltime;
        this.distance = distance;
        Remarks = remarks;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPessenger() {
        return pessenger;
    }

    public void setPessenger(String pessenger) {
        this.pessenger = pessenger;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
