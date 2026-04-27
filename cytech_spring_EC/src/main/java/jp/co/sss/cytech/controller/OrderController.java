package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Order;
import jp.co.sss.cytech.entity.OrderItem;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.form.OrderDetailForm;
import jp.co.sss.cytech.repository.CartRepository;
import jp.co.sss.cytech.repository.OrderItemRepository;
import jp.co.sss.cytech.repository.OrderRepository;
import jp.co.sss.cytech.repository.ProductRepository;
import jp.co.sss.cytech.repository.UserRepository;

@Controller
public class OrderController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@RequestMapping(path = "/order/detail", method = RequestMethod.POST)
	public String detailOrder(Integer cartId, Model model) {
		
		Cart cart = cartRepository.findById(cartId).orElse(null);
		
		User user = userRepository.findById(cart.getUserId()).orElse(null);
		
		OrderDetailForm orderDetailForm = new OrderDetailForm();
		orderDetailForm.setCartId(cartId);
		
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
		
		
		return "order/detail";
	}
	
	@RequestMapping(path = "/order/detail", method = RequestMethod.GET)
	public String getDetailOrder(Integer cartId, Model model) {
		
		Cart cart = cartRepository.findById(cartId).orElse(null);
		User user = userRepository.findById(cart.getUserId()).orElse(null);
		
		OrderDetailForm orderDetailForm = new OrderDetailForm();
		orderDetailForm.setCartId(cartId);
		
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
		
		return "order/detail";
	}
	
	@RequestMapping(path = "/order/detail/back", method = RequestMethod.POST)
	public String backDetailOrder(@ModelAttribute OrderDetailForm orderDetailForm, Model model) {
		
		Cart cart = cartRepository.findById(orderDetailForm.getCartId()).orElse(null);
		User user = userRepository.findById(cart.getUserId()).orElse(null);
		
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
		
		return "order/detail";
	}
	
	@RequestMapping(path = "/order/confirm", method = RequestMethod.POST)
	public String confirmOrder(@Valid @ModelAttribute OrderDetailForm orderDetailForm, BindingResult result, Model model) {
		
		Cart cart = cartRepository.findById(orderDetailForm.getCartId()).orElse(null);
		User user = userRepository.findById(cart.getUserId()).orElse(null);
		
		if (result.hasErrors()) {
			model.addAttribute("cart", cart);
			model.addAttribute("user", user);
			model.addAttribute("orderDetailForm", orderDetailForm);
			return "order/detail";
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
		
		return "order/confirm";
	}
	
	@Transactional
	@RequestMapping(path = "/order/complete", method = RequestMethod.POST)
	public String completeOrder(@ModelAttribute OrderDetailForm orderDetailForm, BindingResult result, Model model) {
		
		Cart cart = cartRepository.findById(orderDetailForm.getCartId()).orElse(null);
		
		User user = userRepository.findById(cart.getUserId()).orElse(null);
		
		Product product = productRepository.findById(cart.getProductId()).orElse(null);

		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
			
		if (product.getStock() < cart.getQuantity()) {
			result.reject("order.stock.error");
			model.addAttribute("globalError", "在庫が不足しています");
			return "order/confirm";
		}
		
		user.setUserAddress(orderDetailForm.getUserAddress());
		userRepository.save(user);
		
		Order order = new Order();
		order.setUserId(user.getUserId());
		order.setTotalAmount(product.getPrice() * cart.getQuantity());
		order.setStatus("注文完了");
		order = orderRepository.save(order);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getOrderId());
		orderItem.setProductId(product.getProductId());
		orderItem.setQuantity(cart.getQuantity());
		orderItem.setPrice(product.getPrice());
		orderItemRepository.save(orderItem);
		
		product.setStock(product.getStock() - cart.getQuantity());
		productRepository.save(product);
		
		cartRepository.delete(cart);
		
		model.addAttribute("product", product);
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("cart", cart);
		model.addAttribute("user", user);
		model.addAttribute("orderDetailForm", orderDetailForm);
		
		return "order/complete";
	}
}








































