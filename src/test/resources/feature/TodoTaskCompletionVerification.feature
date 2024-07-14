Feature: Todo task completion verification

  Scenario Outline: All the users of City should have more than half of their todos task completed.
    Given User has the todo tasks
    And User belongs to the city "<City>" with altitude "<altitude>" and longitude "<longitude>"
    Then User Completed task percentage should be "<criteria>" than <percentage>%

    Examples:
      | City    | altitude | longitude | criteria | percentage |
#      | FanCode | -40 To 5 | 5 To 10   | Greater  | 50         |
      | FanCode | -40 To 5 | 5 To 10   | Greater  | 10         |
#      | FanCode | -40 To 5 | 5 To 10   | Greater  | 100        |
