package org.assignment.step_definition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assignment.pages.Todos;
import org.assignment.pages.Users;

import java.util.Map;
import java.util.Set;


public class TodoTaskCompletion {
    Map<Integer, Double[]> todoCompletionPercentage;
    Set<Integer> UsersBelongsToTheCity;

    @Given("User has the todo tasks")
    public void userHasTodoTasks() {
        this.todoCompletionPercentage = new Todos().getMapOfUserTaskCompletion();
    }

    @And("User belongs to the city {string} with altitude {string} and longitude {string}")
    public void userBelongsToTheCity(String city, String altitude, String longitude) {
        this.UsersBelongsToTheCity = new Users().getUsersBasedOnLocation(city, altitude, longitude);
    }

    @Then("User Completed task percentage should be {string} than {int}%")
    public void userCompletedTaskPercentageValidation(String criteria, int cutOff) {
        new Todos().verifyUsersBelongsToTheCityMeetsCompletionCriteria(todoCompletionPercentage, UsersBelongsToTheCity, criteria, cutOff);
    }
}
