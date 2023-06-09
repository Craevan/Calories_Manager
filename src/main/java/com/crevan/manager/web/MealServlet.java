package com.crevan.manager.web;

import com.crevan.manager.repository.inmemory.InMemoryMealRepository;
import com.crevan.manager.repository.MealRepository;
import com.crevan.manager.model.Meal;
import com.crevan.manager.util.MealsUtil;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealRepository database;

    @Override
    public void init() {
        database = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        switch (action == null ? "" : action) {
            case ("create"), ("edit") -> {
                Meal meal = "create".equals(action) ?
                        new Meal(MealsUtil.DEFAULT_CALORIES_COUNT, "", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)) :
                        database.get(getInteger(req.getParameter("id")));
                log.info("Create/Edit meal={}", meal);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
            }
            case ("delete") -> {
                int id = getInteger(req.getParameter("id"));
                log.info("Delete meal with id={}", id);
                database.delete(id);
                resp.sendRedirect("meals");
            }
            default -> {
                log.info("Get all meals");
                req.setAttribute("meals", MealsUtil.getTos(database.getAll(), MealsUtil.DEFAULT_CALORIES_COUNT));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String description = req.getParameter("description");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("date"));
        int calories = getInteger(req.getParameter("calories"));
        if (id.isEmpty()) {
            Meal meal = new Meal(calories, description, ldt);
            log.info("Saving new meal={}", meal);
            database.save(meal);
        } else {
            Meal meal = new Meal(getInteger(id), calories, description, ldt);
            log.info("Updating meal={}", meal);
            database.update(meal);
        }
        resp.sendRedirect("meals");
    }

    private int getInteger(final String param) {
        return Integer.parseInt(param);
    }
}
