package controllers;

import com.google.gson.Gson;
import models.Item;
import repositories.ItemRepo;
import services.ItemServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ItemServlet extends HttpServlet {
    final private ItemRepo ir = new ItemRepo();
    final private ItemServiceImpl is = new ItemServiceImpl(ir);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("items.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append(saveItem(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append(saveItem(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getQueryString());
        boolean success = ir.delete(id);
        if(success)
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            resp.sendError(404, "No item found");
    }

    protected String saveItem(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Item i = gson.fromJson(reader, Item.class);
        return gson.toJson(is.saveItem(i));
    }
}
