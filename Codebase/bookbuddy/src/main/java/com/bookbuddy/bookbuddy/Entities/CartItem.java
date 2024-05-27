package com.bookbuddy.bookbuddy.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Schema(name = "CartItem", description = "An item present in the cart")
@Table(name = "cart_items")
public class CartItem {

	@Id
	@Column(name = "cart_item_id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique ID generated by database corresponding to a user", example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;

	@ManyToOne
	@JsonIgnore
	@Schema(description = "id of the cart", example = "000056")
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@Schema(description = "id of the book", example = "00034")
	@JoinColumn(name = "book_id")
	private Book book;

	@Schema(description = "price of the item", example = "$10")
	@Column(name = "item_price")
	private BigDecimal itemPrice;

	@ManyToOne
	@JoinColumn(name = "user_checkout_id")
	private UserCheckout userCheckout;

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public CartItem(Long cartItemId, Cart cart, Book book, BigDecimal itemPrice) {
		super();
		this.cartItemId = cartItemId;
		this.cart = cart;
		this.book = book;
		this.itemPrice = itemPrice;
	}

	public CartItem(Cart cart, Book book, BigDecimal itemPrice) {
		this.cart = cart;
		this.book = book;
		this.itemPrice = itemPrice;
	}

	public CartItem() {

	}

	public UserCheckout getUserCheckout() {
		return userCheckout;
	}

	public void setUserCheckout(UserCheckout userCheckout) {
		this.userCheckout = userCheckout;
	}

}