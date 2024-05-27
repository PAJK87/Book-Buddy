package com.bookbuddy.bookbuddy.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bookbuddy.bookbuddy.entities.PaymentDTO;
import com.bookbuddy.bookbuddy.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository, @Value("${stripe.key.secret}") String secretKey) {
        this.paymentRepository = paymentRepository;
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(PaymentDTO paymentDTO) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentDTO.getAmount());
        params.put("currency", paymentDTO.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

}
