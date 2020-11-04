Feature: Assessement using Cumcumber BDD Test in salseforce Application

Scenario Outline: Assessement

Given Login to the application
And Login with the username <userName>
And Login with the password <password>
When Click on Community under Most Recently Used
Then Print the Application Name and Developer Name for the App Type <appTypen> 
And Click on View Profile icon 
Then Click on Switch to Salesforce Classic
When Click on Create New and Select Event
And Enter the Subject as Call Email Meeting
And Enter Start date as tomorrow 
And Enter End date as tomorrow+one
Then Add invitees as Contact: Click on the LookUp icon
And Attach a file to the event Choose File, Attach File and Done
Then Verify that the file is attached to the file
When Click on Save

Examples:
|userName|password|appTypen|
|nupela@testleaf.com|Bootcamp$123|Lightning|
