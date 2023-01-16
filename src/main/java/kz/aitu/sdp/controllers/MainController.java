package kz.aitu.sdp.controllers;

import kz.aitu.sdp.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    ShopSystem shopSystem = new ShopSystem();
    Bouquet bouquet = new Bouquet();

    @GetMapping("/")
    public String home(Model model) {
        bouquet.getBouquet_fillings().clear();
        model.addAttribute("title", "Home page");
        return "home";
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("title", "Order page");
        return "order";
    }

    @GetMapping("/order/add-flower")
    public String orderFlower(Model model) {
        model.addAttribute("flowers", shopSystem.getFlowers());
        model.addAttribute("title", "Flower page");
        return "order_add_flower";
    }

    @PostMapping("/order/add-flower")
    public String orderNewFlower(@RequestParam int flower_id, Model model) {
        model.addAttribute("flowers", shopSystem.getFlowers());
        shopSystem.addFlower(bouquet, flower_id);
        model.addAttribute("bouquet", bouquet);
        model.addAttribute("title", "Flower page");
        return "order_add_flower";
    }

    @GetMapping("/order/add-accessory")
    public String orderAccessory(Model model) {
        model.addAttribute("accessories", shopSystem.getAccessories());
        model.addAttribute("title", "Accessory page");
        return "order_add_accessory";
    }

    @PostMapping("/order/add-accessory")
    public String orderNewAccessory(@RequestParam int accessory_id, Model model) {
        shopSystem.getAccessories();
        shopSystem.addAccessory(bouquet, accessory_id);
        model.addAttribute("bouquet", bouquet);
        model.addAttribute("accessories", shopSystem.getAccessories());
        model.addAttribute("title", "Accessory page");
        return "order_add_accessory";
    }

    @GetMapping("/order/client")
    public String client(Model model) {
        model.addAttribute("title", "Client page");
        return "client";
    }

    @GetMapping("/order/client/old")
    public String clientOld(Model model) {
        model.addAttribute("title", "Client page");
        return "client_old";
    }

    @PostMapping("/order/client/old")
    public String clientOld(@RequestParam String customer_phone, Model model) {
        try {
            if (!shopSystem.ifCustomerExists(customer_phone)) {
                CustomerNotFound e = new CustomerNotFound();
                throw e;
            } else {
                Customer customer = shopSystem.getCustomerByPhone(customer_phone);
                shopSystem.makeOrder(bouquet, customer);
                model.addAttribute("message", "Order completed and will be delivered soon!");
            }
        } catch (CustomerNotFound e) {
            model.addAttribute("message", e.getMessage());
        }

        model.addAttribute("title", "Client page");
        return "client_old";
    }

    @GetMapping("/order/client/new")
    public String clientNew(Model model) {
        model.addAttribute("title", "Client page");
        return "client_new";
    }

    @PostMapping("/order/client/new")
    public String clientNew(@RequestParam String customer_phone, @RequestParam String customer_fname, @RequestParam String customer_lname, @RequestParam String customer_address, @RequestParam String customer_email, Model model) {
        Customer customer = new Customer(customer_fname, customer_lname, customer_address, customer_phone, customer_email);
        shopSystem.addCustomerToDB(customer);
        customer = shopSystem.getCustomerByPhone(customer_phone);
        shopSystem.makeOrder(bouquet, customer);
        model.addAttribute("message", "Order completed and will be delivered soon!");
        model.addAttribute("title", "Client page");
        return "client_new";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        model.addAttribute("title", "Manage page");
        return "manage";
    }

    @GetMapping("/manage/update-shipping")
    public String updateShipping(Model model) {
        model.addAttribute("title", "Update shipping page");
        return "update_shipping";
    }

    @PostMapping("/manage/update-shipping")
    public String updateShippingById(@RequestParam int id, Model model) {
        try {
            if (!shopSystem.ifShippingExists(id)) {
                ShippingNotFound e = new ShippingNotFound();
                throw e;
            } else {
                shopSystem.updateShippingStatus(id);
                model.addAttribute("message", "Successfully updated");
            }
        } catch (ShippingNotFound e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("title", "Update shipping page");
        return "update_shipping";
    }

    @GetMapping("/manage/update-payment")
    public String updatePayment(Model model) {
        model.addAttribute("title", "Update payment page");
        return "update_payment";
    }

    @PostMapping("/manage/update-payment")
    public String updatePaymentById(@RequestParam int id, Model model) {
        try {
            if (!shopSystem.ifPaymentExists(id)) {
                PaymentNotFound e = new PaymentNotFound();
                throw e;
            } else {
                shopSystem.updatePaymentStatus(id);
                model.addAttribute("message", "Successfully updated");
            }
        } catch (PaymentNotFound e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("title", "Update payment page");
        return "update_payment";
    }

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("title", "View orders page");
        return "view";
    }

    @PostMapping("/view")
    public String viewByPhone(@RequestParam String customer_phone, Model model) {
        try {
            if (!shopSystem.ifCustomerExists(customer_phone)) {
                CustomerNotFound e = new CustomerNotFound();
                throw e;
            } else {
                model.addAttribute("orders", shopSystem.getCustomersOrders(customer_phone));
                model.addAttribute("message", "Your orders:");
            }
        } catch (CustomerNotFound e) {
            model.addAttribute("message", e.getMessage());
        }

        model.addAttribute("title", "View orders page");
        return "view";
    }
}