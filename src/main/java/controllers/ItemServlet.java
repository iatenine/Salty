package controllers;

import com.google.gson.Gson;
import lombok.SneakyThrows;
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
import java.sql.SQLException;
import java.util.LinkedList;

public class ItemServlet extends HttpServlet {
    final private ItemRepo ir = new ItemRepo();
    final private ItemServiceImpl is = new ItemServiceImpl(ir);
    LinkedList<Item> list = new LinkedList<>();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        LinkedList<Item> list = is.getItems();
        Item item1 = new Item("Steak", 405, false);
        Item item2 = new Item("Fries", 2500, true);

        list.add(item1);
        list.add(item2);

        req.setAttribute("list", list);
        RequestDispatcher view = req.getRequestDispatcher("items.jsp");
        view.forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.getWriter().append(saveItem(req));
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        resp.getWriter().append(saveItem(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Can't get past 403 errors on delete paths
        int id = Integer.parseInt(req.getQueryString());
        boolean success = ir.delete(id, "items");
        if(success)
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        else
            resp.sendError(404, "No item found");
    }

    protected String saveItem(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Item i = gson.fromJson(reader, Item.class);
        list.add(i);
//        return gson.toJson(is.saveItem(i));
        return gson.toJson(i);

    }
}
