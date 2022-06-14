package com.cg.controller;

import com.cg.dto.UserDTO;
import com.cg.model.User;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreatePage(req, resp);
                break;
            case "edit":
                showEditPage(req, resp);
                break;
            case "list":
                showListPage(req, resp);
                break;
            default:
                showListPage(req, resp);
                break;
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                doCreate(req, resp);
                break;
            case "edit":
                doUpdate(req, resp);
                break;
        }
    }



    public void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/create.jsp");

        dispatcher.forward(req, resp);
    }

    private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/edit.jsp");

        String id = req.getParameter("id");

        User user = userService.findById(Integer.parseInt(id));

        if (user != null) {
            req.setAttribute("user", user);
        }

        dispatcher.forward(req, resp);
    }

    public void showListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/list.jsp");

        List<UserDTO> userList = userService.findAllUserDTO();

        req.setAttribute("userList", userList);

        dispatcher.forward(req, resp);
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/create.jsp");

        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        User user = new User(fullName, phone, address);

        boolean success = userService.create(user);

        if (success) {
            req.setAttribute("success", true);
        }
        else {
            req.setAttribute("error", true);
        }

        dispatcher.forward(req, resp);
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/edit.jsp");

        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        User user = new User(Integer.parseInt(id), fullName, phone, address);

        boolean success =  userService.update(user);

        if (success) {
            req.setAttribute("success", true);
        }
        else {
            req.setAttribute("error", true);
        }

        dispatcher.forward(req, resp);
    }
}
