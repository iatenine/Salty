package controllers;

import repositories.OrderRepo;
import services.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Orders extends HttpServlet {
    OrderRepo or = new OrderRepo();
    OrderServiceImpl os = new OrderServiceImpl(or);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("orders.jsp");
        view.forward(req, resp);
    }
}
