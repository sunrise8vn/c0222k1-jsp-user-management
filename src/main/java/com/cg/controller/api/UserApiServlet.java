package com.cg.controller.api;

import com.cg.dto.UserDTO;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserApiServlet", urlPatterns = "/api/users")
public class UserApiServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "find-all":
                getFindAll(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void getFindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = null;

        List<UserDTO> userList = userService.findAllUserDTO();

        json = new Gson().toJson(userList);

        resp.getWriter().write(json);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = null;
        String strId = req.getParameter("id");

        int customerId = Integer.parseInt(strId);

        Boolean success = userService.remove(customerId);

        if (success) {
            json = new Gson().toJson("success");
        }
        else {
            json = new Gson().toJson("error");
        }

        resp.getWriter().write(json);
    }
}
