package controllers;

import ORM.PepperORM;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import models.Customer;
import models.CustomerData;
import repositories.CustomerDataRepo;
import repositories.CustomerRepo;
import services.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CustomerServlet extends HttpServlet {
    CustomerRepo cr = new CustomerRepo();
    CustomerDataRepo cdr = new CustomerDataRepo();
    CustomerServiceImpl cs = new CustomerServiceImpl(cr, cdr);

        //"MockDB"
    static LinkedList<Customer> list = new LinkedList<>();



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CustomerData data1 = new CustomerData("8508675309", "987654321");
        Customer customer1 = new  Customer(42, "Hank Hill", data1);
        Customer customer2 = new Customer(2, "Peggy Hill");
        list.add(customer1);
        list.add(customer2);
//        LinkedList<Customer> list = cs.getCustomers();
        req.setAttribute("list", list);
        RequestDispatcher view = req.getRequestDispatcher("customers.jsp");
        view.forward(req, resp);
    }
    // To add user_data, a user must send it as a nested object inside of the JSON. The AllArgsConstructor
    // should be able to handle this
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append(saveCustomer(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Gson gson = new Gson();
//        BufferedReader reader = req.getReader();
//        Customer newCustomer = gson.fromJson(reader, Customer.class);
//        list.removeIf((x->x.getId()==newCustomer.getId()));
        resp.getWriter().append(saveCustomer(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getQueryString());
//        resp.getWriter().append("hi" + id);
//        boolean success = cs.deleteCustomer(id);
        boolean success = list.stream().anyMatch((elem)-> elem.getId() == id);
        if(success)
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            resp.getWriter().append("No");
    }

    protected String saveCustomer(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Customer c = gson.fromJson(reader, Customer.class);
        list.add(c);
//        return gson.toJson(cs.saveCustomer(c));
        return gson.toJson(c);
    }

}
