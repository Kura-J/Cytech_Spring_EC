package jp.co.sss.cytech.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.cytech.entity.Cart;
import jp.co.sss.cytech.entity.Product;
import jp.co.sss.cytech.repository.CartRepository;
import jp.co.sss.cytech.repository.ProductRepository;

@Controller
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(path = "/cart/add", method = RequestMethod.POST)
	public String addCart(Integer productId, Integer quantity, 
			HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer userId = (Integer) session.getAttribute("loginUserId");
		
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cart.setQuantity(quantity);
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		cart.setCreatedAt(now);
		cart.setUpdatedAt(now);
		
		cartRepository.save(cart);
		
		redirectAttributes.addFlashAttribute("message", "カートに追加しました");
		
		return "redirect:/cart/add?productId=" + productId + "&quantity=" + quantity + "&cartId=" + cart.getCartId();
	}
	
	@RequestMapping(path = "/cart/add", method = RequestMethod.GET)
	public String showCartAdd(Integer productId, Integer quantity, Integer cartId, Model model) {
		
		Product product = productRepository.findById(productId).orElse(null);
		Cart cart = cartRepository.findById(cartId).orElse(null);
		
		Integer totalPrice = product.getTaxPrice() * quantity;
		
		model.addAttribute("product", product);
		model.addAttribute("quantity", quantity);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("cart", cart);
		
		return "cart/add";
	}
	
	@RequestMapping("/cart/detail")
	public String detailCart(HttpSession session, Model model) {
		
		Integer userId = (Integer) session.getAttribute("loginUserId");
		List<Cart> cartList = cartRepository.findByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart/detail";
	}
	
	@RequestMapping(path = "/cart/delete", method = RequestMethod.POST)
	public String deleteCart(Integer cartId) {
		cartRepository.deleteById(cartId);
		return "redirect:/cart/detail";
	}
	
	@RequestMapping(path = "/cart/buy", method = RequestMethod.POST)
	public String singleBuy(Integer productId, HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("loginUserId");
		
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cart.setQuantity(1);
		
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		cart.setCreatedAt(now);
		cart.setUpdatedAt(now);
		
		cart = cartRepository.save(cart);
		
		return "redirect:/order/detail?cartId=" + cart.getCartId();
	}
}
























