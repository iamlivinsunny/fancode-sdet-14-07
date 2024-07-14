package org.assignment.pages;

import org.assignment.request.GetRequest;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class Todos extends BasePage {
    final static String endpoint = "/todos";

    public Map<Integer, Double[]> getMapOfUserTaskCompletion() {
        try {
            List listOfAllTodos = new GetRequest(environment, endpoint).getResponse().extractDataAsList("");
            //Create a map with key as id and value an array containing the completed, total and percentage todos count
            HashMap<Integer, Double[]> completionPercentage = new HashMap<>();
            for (Object todos : listOfAllTodos) {
                Map<String, Object> todoMap = (Map<String, Object>) todos;
                int key = (int) todoMap.get("userId");
                Double[] value;
                if (completionPercentage.containsKey(key)) {
                    value = completionPercentage.get(key);
                    value[0] += (boolean) todoMap.get("completed") ? 1 : 0;
                    value[1] += 1;
                } else {
                    value = new Double[3];
                    value[0] = (boolean) todoMap.get("completed") ? 1.0 : 0.0;
                    value[1] = 1.0;
                }
                value[2] = value[0] * 100 / value[1];
                completionPercentage.put(key, value);
            }
            return completionPercentage;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void verifyUsersBelongsToTheCityMeetsCompletionCriteria(Map<Integer, Double[]> userCompletionMap, Set<Integer> users, String criteria, int percentage) {
        try {
            Iterator<Integer> userIterator = users.iterator();
            SoftAssert softAssert = new SoftAssert();
            while (userIterator.hasNext()) {
                if (criteria.equals("Greater")) {
                    int userId = userIterator.next();
                    if (userCompletionMap.get(userId)[2] < percentage) {
                        String failureMessage = String.format("User with ID %d has %.2f%% TODO tasks pending.\n", userId, 100 - userCompletionMap.get(userId)[2]);
                        softAssert.assertTrue(false, failureMessage);
                    }
                }
            }
            softAssert.assertAll("Some users of the city does not met the Todo task completion criteria.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
