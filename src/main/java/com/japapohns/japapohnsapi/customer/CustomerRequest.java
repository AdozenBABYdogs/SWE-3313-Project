package com.japapohns.japapohnsapi.customer;

public record CustomerRequest(String firstName, String lastName, String phoneNumber, String address, String subdivision, String nearestIntersection, String cardType) {
}
