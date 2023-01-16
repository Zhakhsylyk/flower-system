package kz.aitu.sdp;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ShopSystem {
    String url = "jdbc:h2:mem:testdb";
    String username = "sa";
    String password = "password";

    List<Flower> flowersList = new ArrayList<>();
    List<Accessory> accessoriesList = new ArrayList<>();
    List<Customer> customersList = new ArrayList<>();

    Payment payment = new Payment();

    Shipping shipping = new Shipping();


    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public List<Flower> getFlowers() {


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flowers");

            while (resultSet.next()) {
                Flower flower = new Flower();
                flower.setComponent_id(resultSet.getInt("flower_id"));
                flower.setComponent_name(resultSet.getString("flower_name"));
                flower.setComponent_color(resultSet.getString("flower_color"));
                flower.setComponent_price(resultSet.getDouble("flower_price"));

                flowersList.add(flower);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flowersList;
    }

    public List<Accessory> getAccessories() {


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accessories");

            while (resultSet.next()) {
                Accessory accessory = new Accessory();
                accessory.setComponent_id(resultSet.getInt("accessory_id"));
                accessory.setComponent_name(resultSet.getString("accessory_name"));
                accessory.setComponent_color(resultSet.getString("accessory_color"));
                accessory.setComponent_price(resultSet.getDouble("accessory_price"));

                accessoriesList.add(accessory);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return accessoriesList;
    }

    public void showFlowers() {
        for (Flower flower : getFlowers()) {
            System.out.println(flower);
        }
    }


    public void showAccessories() {
        for (Accessory accessory : getAccessories()) {
            System.out.println(accessory);
        }
    }

    public void addFlower(Bouquet bouquet, int id) {
        bouquet.addComponent(flowersList.get(id - 1));
    }

    public void addAccessory(Bouquet bouquet, int id) {
        bouquet.addComponent(accessoriesList.get(id - 1));
    }

    public Bouquet getBouquetById(int bouquet_id) {
        Bouquet bouquet = new Bouquet();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bouquets WHERE bouquet_id = (?)");
            statement.setInt(1, bouquet_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bouquet.setBouquet_id(resultSet.getInt("bouquet_id"));
                bouquet.setBouquet_fillings_string(resultSet.getString("bouquet_fillings"));
                bouquet.setBouquet_price(resultSet.getDouble("bouquet_price"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bouquet;
    }

    public Payment getPaymentById(int payment_id) {
        Payment paymentById = new Payment();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments WHERE payment_id = (?)");
            statement.setInt(1, payment_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                paymentById.setPayment_id(resultSet.getInt("payment_id"));
                paymentById.setPayment_status(resultSet.getString("payment_status"));
                paymentById.setPayment_sum(resultSet.getDouble("payment_sum"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentById;
    }

    public Shipping getShippingById(int shipping_id) {
        Shipping shippingById = new Shipping();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM shippings WHERE shipping_id = (?)");
            statement.setInt(1, shipping_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                shippingById.setShipping_id(resultSet.getInt("shipping_id"));
                shippingById.setShipping_employee(getEmployeeById(resultSet.getInt("shipping_employee_id")));
                shippingById.setShipping_status(resultSet.getString("shipping_status"));
                shippingById.setShipping_date(resultSet.getString("shipping_date"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingById;
    }

    public void updateShippingStatus(int shipping_id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE shippings SET shipping_status = 'delivered', shipping_date = (?) WHERE shipping_id = (?)");
            statement.setString(1, date.format(now));
            statement.setInt(2, shipping_id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentStatus(int payment_id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE payments SET payment_status = 'payed' WHERE payment_id = (?)");
            statement.setInt(1, payment_id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employeesList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statementGetEmployees = connection.prepareStatement("SELECT * FROM employees");
            ResultSet resultSetEmployees = statementGetEmployees.executeQuery();
            while (resultSetEmployees.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(resultSetEmployees.getInt("employee_id"));
                employee.setEmployee_fname(resultSetEmployees.getString("employee_fname"));
                employee.setEmployee_lname(resultSetEmployees.getString("employee_lname"));
                employee.setEmployee_position(resultSetEmployees.getString("employee_position"));
                employee.setEmployee_phone(resultSetEmployees.getString("employee_phone"));
                employeesList.add(employee);
            }
            statementGetEmployees.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesList;
    }

    public Employee getEmployeeById(int employee_id) {
        Employee employee = new Employee();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = (?)");
            statement.setInt(1, employee_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setEmployee_fname(resultSet.getString("employee_fname"));
                employee.setEmployee_lname(resultSet.getString("employee_lname"));
                employee.setEmployee_position(resultSet.getString("employee_position"));
                employee.setEmployee_phone(resultSet.getString("employee_phone"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public Boolean ifCustomerExists(String customer_phone) {
        Customer customer = new Customer();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_phone = (?)");
            statement.setString(1, customer_phone);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() == false) return false;

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Boolean ifPaymentExists(int payment_id) {
        Payment payment = new Payment();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments WHERE payment_id = (?)");
            statement.setInt(1, payment_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() == false) return false;

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Boolean ifShippingExists(int shipping_id) {
        Shipping shipping = new Shipping();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM shippings WHERE shipping_id = (?)");
            statement.setInt(1, shipping_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() == false) return false;

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Customer getCustomerByPhone(String customer_phone) {
        Customer customer = new Customer();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_phone = (?)");
            statement.setString(1, customer_phone);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_fname(resultSet.getString("customer_fname"));
                customer.setCustomer_lname(resultSet.getString("customer_lname"));
                customer.setCustomer_address(resultSet.getString("customer_address"));
                customer.setCustomer_phone(customer_phone);
                customer.setCustomer_email(resultSet.getString("customer_email"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public Customer getCustomerById(int customer_id) {
        Customer customer = new Customer();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = (?)");
            statement.setInt(1, customer_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_fname(resultSet.getString("customer_fname"));
                customer.setCustomer_lname(resultSet.getString("customer_lname"));
                customer.setCustomer_address(resultSet.getString("customer_address"));
                customer.setCustomer_phone(resultSet.getString("customer_phone"));
                customer.setCustomer_email(resultSet.getString("customer_email"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public void addCustomerToDB(Customer new_customer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `customers` (`customer_fname`, `customer_lname`, `customer_address`, `customer_phone`, `customer_email`) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, new_customer.getCustomer_fname());
            statement.setString(2, new_customer.getCustomer_lname());
            statement.setString(3, new_customer.getCustomer_address());
            statement.setString(4, new_customer.getCustomer_phone());
            statement.setString(5, new_customer.getCustomer_email());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeOrder(Bouquet bouquet, Customer customer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statementInsertBouquet = connection.prepareStatement("INSERT INTO `bouquets` (`bouquet_fillings`, `bouquet_price`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statementInsertBouquet.setString(1, bouquet.bouquet_fillingsToString());
            statementInsertBouquet.setDouble(2, bouquet.getBouquet_price());
            statementInsertBouquet.executeUpdate();
            ResultSet resultSetBouquetId = statementInsertBouquet.getGeneratedKeys();
            resultSetBouquetId.next();
            bouquet.setBouquet_id(resultSetBouquetId.getInt(1));
            statementInsertBouquet.close();

            PreparedStatement statementInsertPayment = connection.prepareStatement("INSERT INTO `payments` (`payment_sum`, `payment_status`) VALUES (?, 'not payed')", Statement.RETURN_GENERATED_KEYS);
            statementInsertPayment.setDouble(1, bouquet.getBouquet_price());
            statementInsertPayment.executeUpdate();

            ResultSet resultSetPaymentId = statementInsertPayment.getGeneratedKeys();
            resultSetPaymentId.next();
            payment.setPayment_id(resultSetPaymentId.getInt(1));
            payment.setPayment_sum(bouquet.getBouquet_price());
            payment.setPayment_status("not payed");
            statementInsertPayment.close();


            PreparedStatement statementInsertShipping = connection.prepareStatement("INSERT INTO `shippings` (`shipping_employee_id`, `shipping_status`) VALUES (?, 'not delivered')", Statement.RETURN_GENERATED_KEYS);
            statementInsertShipping.setInt(1, getRandomEmployee("courier").getEmployee_id());
            statementInsertShipping.executeUpdate();

            ResultSet resultSetShippingId = statementInsertShipping.getGeneratedKeys();
            resultSetShippingId.next();
            shipping.setShipping_id(resultSetShippingId.getInt(1));
            shipping.setShipping_status("not delivered");
            statementInsertShipping.close();


            PreparedStatement statementInsertOrder = connection.prepareStatement("INSERT INTO `orders` (`order_customer_id`, `order_bouquet_id`, `order_payment_id`, `order_employee_id`, `order_shipping_id`, `order_date`) VALUES (?, ?, ?, ?, ?, ?)");
            statementInsertOrder.setInt(1, customer.getCustomer_id());
            statementInsertOrder.setInt(2, bouquet.getBouquet_id());
            statementInsertOrder.setInt(3, payment.getPayment_id());
            statementInsertOrder.setInt(4, getRandomEmployee("florist").getEmployee_id());
            statementInsertOrder.setInt(5, shipping.getShipping_id());
            statementInsertOrder.setString(6, date.format(now));


            statementInsertOrder.executeUpdate();
            statementInsertOrder.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Employee getRandomEmployee(String position) {
        List<Employee> neededEmployeesList = new ArrayList<>();
        for (Employee employee : getEmployees()) {
            if (employee.getEmployee_position().equals(position)) {
                neededEmployeesList.add(employee);
            }
        }
        int id = (int) (Math.random() * ((neededEmployeesList.size())));
        return neededEmployeesList.get(id);
    }

    public List<Customer> getCustomers() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_fname(resultSet.getString("customer_fname"));
                customer.setCustomer_lname(resultSet.getString("customer_lname"));
                customer.setCustomer_address(resultSet.getString("customer_address"));
                customer.setCustomer_phone(resultSet.getString("customer_phone"));
                customer.setCustomer_email(resultSet.getString("customer_email"));

                customersList.add(customer);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return customersList;
    }

    public List<Order> getOrders() {
        List<Order> ordersList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setOrder_customer(getCustomerById(resultSet.getInt("order_customer_id")));
                order.setOrder_bouquet(getBouquetById(resultSet.getInt("order_bouquet_id")));
                order.setOrder_payment(getPaymentById(resultSet.getInt("order_payment_id")));
                order.setOrder_shipping(getShippingById(resultSet.getInt("order_shipping_id")));
                order.setOrder_employee(getEmployeeById(resultSet.getInt("order_employee_id")));
                order.setOrder_date(resultSet.getString("order_date"));
                ordersList.add(order);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ordersList;
    }

    public List<Order> getCustomersOrders(String customer_phone) {
        List<Order> ordersList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            int customer_id = getCustomerByPhone(customer_phone).getCustomer_id();
            PreparedStatement statementGetOrders = connection.prepareStatement("SELECT * FROM orders WHERE order_customer_id = (?)");
            statementGetOrders.setInt(1, customer_id);
            ResultSet resultSet = statementGetOrders.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setOrder_customer(getCustomerById(resultSet.getInt("order_customer_id")));
                order.setOrder_bouquet(getBouquetById(resultSet.getInt("order_bouquet_id")));
                order.setOrder_payment(getPaymentById(resultSet.getInt("order_payment_id")));
                order.setOrder_shipping(getShippingById(resultSet.getInt("order_shipping_id")));
                order.setOrder_employee(getEmployeeById(resultSet.getInt("order_employee_id")));
                order.setOrder_date(resultSet.getString("order_date"));
                ordersList.add(order);
            }

            resultSet.close();
            statementGetOrders.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public void showCustomersOrders(String customer_phone) {
        for (Order order : getCustomersOrders(customer_phone)) {
            System.out.println(order);
        }
    }

}
