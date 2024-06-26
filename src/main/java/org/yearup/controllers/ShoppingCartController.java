package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController // convert this class to a REST controller
@RequestMapping("/api/cart")// only logged in users should have access to these actions
public class ShoppingCartController {
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @GetMapping("/get")// each method in this controller requires a Principal object as a parameter
    public ShoppingCart getCart(Principal principal) {
        try {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            List<ShoppingCart> shoppingCarts = new ArrayList<>(); // use the shoppingcartDao to get all items in the cart and return the cart
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    @RequestMapping(path = "/products", method = RequestMethod.POST)
    // add a POST method to add a product to the cart - the url should be
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productDao.create(product);
    }
    // https://localhost:8080/cart/products/15 (15 is the productId to be added

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.PUT)
    // add a PUT method to update an existing product in the cart - the url should be
    public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
        productDao.update(productId, product);
    }
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.DELETE)
    // add a DELETE method to clear all products from the current users cart
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId) {
        productDao.delete(productId);
    }
}
// https://localhost:8080/cart

