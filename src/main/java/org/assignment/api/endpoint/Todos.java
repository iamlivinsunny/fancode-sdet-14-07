package org.assignment.api.endpoint;

import org.assignment.request.GetRequest;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class Todos extends BaseAPI {
    final static String endpoint = "/todos";
    public Todos(){
        super();
        responsePath += this.getClass().getSimpleName() + ".txt";
    }

    /**
     *  Returns a HashMap with the user id as key and a double array having completed task count,
     *  total task count, and percentage of completed task as items
     *
     * @return  Map<Integer, Double[]> HashMap containing todos completion status
     */
    public Map<Integer, Double[]> getMapOfUserTaskCompletion() {
        try {
            List listOfAllTodos = new GetRequest(environment, endpoint).getResponse(responsePath).extractDataAsList("");
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

    /**
     *  This method is used for verifying the users belongs to the city meets the todos completion criteria
     *  Details of users not meeting completing criteria will be captured in a SoftAssert and will assert
     *  after complete verification
     *
     * @param userCompletionMap HashMap which contains the userId as key and completion details as a double array
     * @param users Set of integer which contains the userId of users belonging to the city
     * @param criteria  String value for comparison, Currently supports value "Greater"
     * @param percentage    integer percentage which will be used to for comparison
     */
    public void verifyUsersBelongsToTheCityMeetsCompletionCriteria(Map<Integer, Double[]> userCompletionMap, Set<Integer> users, String criteria, int percentage) {
        try {
            Iterator<Integer> userIterator = users.iterator();
            SoftAssert softAssert = new SoftAssert();
            while (userIterator.hasNext()) {
                if (criteria.equals("Greater")) {
                    int userId = userIterator.next();
                    if (!(userCompletionMap.get(userId)[2] > percentage)) {
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
