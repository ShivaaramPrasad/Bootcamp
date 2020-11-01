Feature:  Automation For Scorecard using Cumcumber BDD Test in salseforce Application
Scenario Outline:  Automation For Scorecard

Given Launch Browser 
And Login with the credentials username <userName>
And Login with the credentials password <password>
Then Click Login 
When Click on the App Launcher Icon left to Setup 
Then Click on View All 
When Click on Scorecards 
And Click on New 
And Enter Individual Assessment for Name <scoreName>
And Enter the Description as <Description>
And Click on Saves 
Then Verify the success message <scoreName>
And Click on Clone 
And Click on CloneSave 
Then Verify the Clone success message <scoreName>
And Click on Delete 
And Click on DeleteAgain 
Then Verify the success message for deleted 
And close Browser 


Examples:
|userName|password|scoreName|Description|
|nupela@testleaf.com|Bootcamp$123|Shivaaram|Assessment for an individual employee|

