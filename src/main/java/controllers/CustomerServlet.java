package controllers;

import com.google.gson.Gson;
import models.Customer;
import models.CustomerData;
import models.Item;
import models.Order;
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
import java.util.LinkedList;

public class CustomerServlet extends HttpServlet {
    CustomerRepo cr = new CustomerRepo();
    CustomerDataRepo cdr = new CustomerDataRepo();
    CustomerServiceImpl cs = new CustomerServiceImpl(cr, cdr);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerData data1 = new CustomerData("8508675309", "987654321");
        Customer customer1 = new  Customer(42, "Hank Hill", data1);
        Customer customer2 = new Customer("Peggy Hill");

        LinkedList<Customer> list = new LinkedList<>();
        list.add(customer1);
        list.add(customer2);
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
        resp.getWriter().append(saveCustomer(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getQueryString());
        boolean success = cs.deleteCustomer(id);
        if(success)
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            resp.sendError(404, "No item found");
    }

    protected String saveCustomer(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Customer c = gson.fromJson(reader, Customer.class);
        return gson.toJson(cs.saveCustomer(c));
    }

}
