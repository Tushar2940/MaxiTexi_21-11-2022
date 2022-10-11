package com.example.maxitexi.Model;

import java.util.List;

public class BookingData {

    List<BookingDataList> ResultData = null;



    public List<BookingDataList> getResultData() {
        return ResultData;
    }

    public void setResultData(List<BookingDataList> resultData) {
        ResultData = resultData;
    }

    public class BookingDataList {

        public int id;
        public String Pick_Up_Address;
        public String Name;
        public String Email;
        public String Drop_Off_Address;
        public String ContactNo;
        public String Time;
        public String airportType;
        public String airportType2;
        public String adType;
        public String Distance;
        public String Amount;
        public String Date;
        public String Passengers;
        public String Price;
        public String PaymentType;
        public String totalTime;
        public String totalDistance;
        public String Remarks;
        public String Flightno;
        public String Status;
        public String No_off_passanger;
        public String time_to_destination;
        public String flag;

        public BookingDataList(String pick_Up_Address, String name, String email, String drop_Off_Address, String contactNo, String time, String airportType, String airportType2, String adType, String distance, String amount, String date, String passengers, String price, String paymentType, String totalTime, String totalDistance, String remarks, String flightno, String status, String no_off_passanger, String time_to_destination, String flag) {
            Pick_Up_Address = pick_Up_Address;
            Name = name;
            Email = email;
            Drop_Off_Address = drop_Off_Address;
            ContactNo = contactNo;
            Time = time;
            this.airportType = airportType;
            this.airportType2 = airportType2;
            this.adType = adType;
            Distance = distance;
            Amount = amount;
            Date = date;
            Passengers = passengers;
            Price = price;
            PaymentType = paymentType;
            this.totalTime = totalTime;
            this.totalDistance = totalDistance;
            Remarks = remarks;
            Flightno = flightno;
            Status = status;
            No_off_passanger = no_off_passanger;
            this.time_to_destination = time_to_destination;
            this.flag = flag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPick_Up_Address() {
            return Pick_Up_Address;
        }

        public void setPick_Up_Address(String pick_Up_Address) {
            Pick_Up_Address = pick_Up_Address;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDrop_Off_Address() {
            return Drop_Off_Address;
        }

        public void setDrop_Off_Address(String drop_Off_Address) {
            Drop_Off_Address = drop_Off_Address;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String contactNo) {
            ContactNo = contactNo;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getAirportType() {
            return airportType;
        }

        public void setAirportType(String airportType) {
            this.airportType = airportType;
        }

        public String getAirportType2() {
            return airportType2;
        }

        public void setAirportType2(String airportType2) {
            this.airportType2 = airportType2;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getPassengers() {
            return Passengers;
        }

        public void setPassengers(String passengers) {
            Passengers = passengers;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(String totalTime) {
            this.totalTime = totalTime;
        }

        public String getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(String totalDistance) {
            this.totalDistance = totalDistance;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getFlightno() {
            return Flightno;
        }

        public void setFlightno(String flightno) {
            Flightno = flightno;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getNo_off_passanger() {
            return No_off_passanger;
        }

        public void setNo_off_passanger(String no_off_passanger) {
            No_off_passanger = no_off_passanger;
        }

        public String getTime_to_destination() {
            return time_to_destination;
        }

        public void setTime_to_destination(String time_to_destination) {
            this.time_to_destination = time_to_destination;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }

}
