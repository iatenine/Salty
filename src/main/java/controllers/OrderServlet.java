package controllers;

import com.google.gson.Gson;
import models.Item;
import models.Order;
import repositories.OrderRepo;
import services.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderServlet extends HttpServlet {
    OrderRepo or = new OrderRepo();
    OrderServiceImpl os = new OrderServiceImpl(or);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("orders.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append(saveOrder(req));
        try {
            LinkedList<Order> orders = or.getAll();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("orders.jsp").forward(req, resp);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append(saveOrder(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getQueryString());
        boolean success = os.deleteOrder(id);
        if(success)
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            resp.sendError(404, "No item found");
    }

    protected String saveOrder(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Order o = gson.fromJson(reader, Order.class);
        return gson.toJson(os.saveOrder(o));
    }
}
