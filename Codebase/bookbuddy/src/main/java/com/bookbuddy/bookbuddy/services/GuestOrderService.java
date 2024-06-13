package com.bookbuddy.bookbuddy.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookbuddy.bookbuddy.entities.Book;
import com.bookbuddy.bookbuddy.entities.GuestOrder;
import com.bookbuddy.bookbuddy.entityDTOS.CreateGuestOrderDTO;
import com.bookbuddy.bookbuddy.exceptions.BookNotFoundException;
import com.bookbuddy.bookbuddy.repositories.BookRepository;
import com.bookbuddy.bookbuddy.repositories.GuestOrderRepository;

public class GuestOrderService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GuestOrderRepository guestOrderRepository;

    public Long createNewGuestOrder(CreateGuestOrderDTO newGuestOrderDTO) {
        Book book = bookRepository.findById(newGuestOrderDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException(newGuestOrderDTO.getBookId()));
        GuestOrder newGuestOrder = new GuestOrder(
                newGuestOrderDTO.getGuestName(),
                newGuestOrderDTO.getGuestEmail(),
                newGuestOrderDTO.getTotalOrderAmount(),
                newGuestOrderDTO.getGuestShippingAddress(),
                newGuestOrderDTO.getPaymentIntentId(),
                book);

        newGuestOrder = guestOrderRepository.save(newGuestOrder);
        return newGuestOrder.getOrderId();
    }

}
