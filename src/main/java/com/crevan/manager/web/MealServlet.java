package com.crevan.manager.web;

import com.crevan.manager.model.Meal;
import com.crevan.manager.util.MealsUtil;
import com.crevan.manager.web.meal.MealRestController;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.crevan.manager.util.DateTimeUtil.parseLocalDate;
import static com.crevan.manager.util.DateTimeUtil.parseLocalTime;

public class MealServlet extends HttpServlet {

    private MealRestController mealController;

    @Override
    public void init() {
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        mealController = springContext.getBean(MealRestController.class);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        switch (action == null ? "" : action) {
            case ("create"), ("edit") -> {
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", MealsUtil.DEFAULT_CALORIES_COUNT) :
                        mealController.get(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
            }
            case ("delete") -> {
                int id = getId(req);
                mealController.delete(id);
                resp.sendRedirect("meals");
            }
            case ("filter") -> {
                LocalDate startDate = parseLocalDate(req.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(req.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(req.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(req.getParameter("endTime"));
                req.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            }
            default -> {
                req.setAttribute("meals", mealController.getAll());
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(req.getParameter("date")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))
        );
        if (StringUtils.hasLength(req.getParameter("id"))) {
            mealController.update(meal, getId(req));
        } else {
            mealController.create(meal);
        }
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
