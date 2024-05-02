package com.bookbuddy.bookbuddy.Entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Schema(name = "orders", description = "order given by the user")
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "order_id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique ID generated by database corresponding to a user", example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@OneToOne
	@Schema(name = "user_id", description = "id of the user")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Schema(name = "total_price", description = "total price of the order")
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	
	@Schema(name = "order_date", description = "order given on a particular date")
	@Column(name = "order_date")
	private Date orderDate;
	
	@Schema(name = "payment_type", description = "type of the payment")
	@Column(name = "payment_type")
	private String paymentType;
 
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();



	 public Order() {
	}
	
	 public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Order(Long orderId, User user, BigDecimal totalPrice, Date orderDate, String paymentType,
			List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.paymentType = paymentType;
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", user=" + user + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", paymentType=" + paymentType + ", orderItems=" + orderItems + "]";
	}


	

}
