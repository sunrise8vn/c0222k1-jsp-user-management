package com.cg.controller;

import com.cg.dto.OutputDTO;
import com.cg.dto.UserDTO;
import com.cg.dto.UserDobDTO;
import com.cg.model.City;
import com.cg.model.User;
import com.cg.service.CityService;
import com.cg.service.CityServiceImpl;
import com.cg.service.UserService;
import com.cg.service.UserServiceImpl;
import com.cg.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    UserService userService;
    CityService cityService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        cityService = new CityServiceImpl();
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
            case "search":
                showSearchPage(req, resp);
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

        List<City> cityList = cityService.findAll();
        req.setAttribute("cityList", cityList);

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

    private void showSearchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/search.jsp");

        List<String> errors = new ArrayList<>();

        String dob = req.getParameter("dob");

        if (dob != null) {
            if (!ValidateUtils.isDateValid(dob)) {
                errors.add("Date input invalid");
            }
            else {
//                List<UserDobDTO> dtoList = userService.searchByDate(dob);

                Map<String, List<?>> result = userService.searchByDateMap(dob);
                List<?> userDobDTOS = result.get("userList");
                List<?> outputDTOS =  result.get("output");

                OutputDTO outputDTO = (OutputDTO) outputDTOS.get(0);

                if (!outputDTO.isSuccess()) {
                    errors.add(outputDTO.getMessage());
                }

                req.setAttribute("dtoList", userDobDTOS);
            }
        }

//        List<UserDTO> userList = userService.findAllUserDTO();
//
//        req.setAttribute("userList", userList);

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }

        dispatcher.forward(req, resp);
    }

    private void showUploadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/upload.jsp");

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
        String strAge = req.getParameter("age");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String strDob = req.getParameter("dob");
        int cityId = Integer.parseInt(req.getParameter("cityId"));

        List<String> errors = new ArrayList<>();

        boolean ageIsNumber = ValidateUtils.isNumberValid(strAge);
//        int ageLength = strAge.length();

        if (!ageIsNumber) {
            errors.add("Age is not valid");
        }

        if (errors.size() == 0) {
            int age = Integer.parseInt(strAge);

            User user = new User(fullName, phone, cityId, address, age, LocalDateTime.parse(strDob));

            boolean exist = cityService.existById(cityId);

            boolean success = false;

            if (exist) {
//                success = userService.create(user);
                Map<String, String> result = userService.doCreate(user);

                success = Boolean.parseBoolean(result.get("success"));
                String message = result.get("message");

                if (!success) {
                    errors.add(message);
                }
            }
            else {
                errors.add("City not found");
            }

            if (success) {
                req.setAttribute("success", true);
            }
            else {
                req.setAttribute("error", true);
                errors.add("Invalid data, please check data again!");
            }
        }

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }

        List<City> cityList = cityService.findAll();
        req.setAttribute("cityList", cityList);

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
