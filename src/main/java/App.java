
import  models.Hero;
import models.Squad;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> allHeroes = Hero.getAllHeroes();
            ArrayList<Squad> allSquads = Squad.getAllSquads();
            model.put("allHeroes", allHeroes);
            model.put("allSquads", allSquads);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/postNewHero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String heroName = request.queryParams("heroName");
            Hero newHero = new Hero(heroName);
            model.put("heroname", heroName);
            return new ModelAndView(model, "SuccessHero.hbs");
        }, new HandlebarsTemplateEngine());

        get("/showHerosquad", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero thisHero = Hero.findHero(Integer.parseInt(request.params(":age")));
            List<Squad> heroSquads = thisHero.getAllThisHeroSquads();
            model.put("thisHero", thisHero);
            model.put("heroSquads", heroSquads);
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        post("/postNewSquad", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squadName = request.queryParams("heroName");
            String cause = request.queryParams("cause");
            int maxSize = Integer.parseInt(request.queryParams("maxSize"));
            String availability= request.queryParams("availability");
            String heroId = request.queryParams("SquadHero");
            boolean isAvailable;
            if (availability == "yes"){isAvailable=true;}else{isAvailable=false;}
            Squad newSquad =new Squad(squadName,cause,maxSize,isAvailable);
            Squad squad = new Squad(squadName, cause, maxSize, true);
            model.put("squad", squad);
            return new ModelAndView(model, "successSquad.hbs");
        }, new HandlebarsTemplateEngine());



    }
}