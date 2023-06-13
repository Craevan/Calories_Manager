package com.crevan.manager.web;

import com.crevan.manager.model.Meal;
import com.crevan.manager.util.MealsUtil;
import com.crevan.manager.web.meal.MealRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private MealRestController mealController;


    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealController = springContext.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        switch (action == null ? "" : action) {
            case ("create"), ("edit") -> {
                final Meal meal = "create".equals(action) ?
                        new Meal(MealsUtil.DEFAULT_CALORIES_COUNT, "", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)) :
                        mealController.get(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
            }
            case ("delete") -> {
                int id = getId(req);
                mealController.delete(id);
                resp.sendRedirect("meals");
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
                Integer.parseInt(req.getParameter("calories")),
                req.getParameter("description"),
                LocalDateTime.parse(req.getParameter("date"))
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
