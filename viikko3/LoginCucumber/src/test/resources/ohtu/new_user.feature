Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "jakke" and password "jakke3ekkaj" are entered
        Then  system will respond "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "akkep" are entered
        Then  system will respond "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "ka" and password "kake1kake" are entered
        Then  system will respond "new user not registered"

