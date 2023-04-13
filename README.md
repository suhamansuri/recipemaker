# RecipeMaker

## An application to Help you Meal Prep

RecipeMaker will help you plan your meals by:
- **Keeping Track** of the items you have in your pantry
- **Providing** meal ideas for the time you have with the ingredients you have!
- **Inform** you of the groceries you need to purchase for meals on your meal-list

*This application cannot create new recipes but 
instead extracts them from a set of recipes you have already implemented*

This application will use a set of recipes that the user has implemented
and propose possible meal ideas for different time-slots available to the user
and different ingredients available to the user. It is ideally
catered towards the university/college student age-range, aiming to improve
the eating habits of students experiencing new life on campus.

This project is of interest to me as it is something that I personally struggled with
throughout my first semester (and even now) to properly plan out my meals with the appropriate time
I had.

## User Stories
- As a user, I want to be able to add and edit multiple recipes in my collection
- As a user, I want to be able to allocate a set time for each different recipe according to my time to prepare
- As a user, I want to be able to select a recipe that fits my current time allotted
- As a user, I want to be able to select a recipe that includes the ingredients I prefer
- As a user, I want to be able to add and remove multiple ingredients from each recipe
- As a user, I want to be able to save my recipe book to file (if I choose)
- As a user, I want to be able to load my recipe book from file (if i choose)
- As a user, I want to be reminded to save my book to file and have the option not to
- As a user, I want to be given the option to load my recipe book


Instructions for Grader:
- There are four buttons each with a different purpose pertaining to the recipeBook
- Press make a recipe to select a recipe and recipe the ingredients
- Press edit recipe book to edit a recipe by selecting the recipe you would like to edit
- Make sure when editing and adding the recipe that you select the "Enter" buttons next to each text field
- To get a list of options for meals, select options for meals and choose ingredient or time
- Enter the ingredient you wish to eat or the time that you have
- Select view Recipe book for a list of all the recipe names
- The visual component is in data labelled logo.png
- Save application by pressing the save button
- Load application by pressing the load button

Sample of events that occur when program is run, loaded and a recipe is edited:
Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Potato salad' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'French fries' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'French toast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Coleslaw' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'PattyMelt' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Milkshake' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Toast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Cheeseburger' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Chicken breast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Croissant sandwich' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Potato salad' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'French fries' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'French toast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Coleslaw' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'PattyMelt' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Milkshake' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Toast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Cheeseburger' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Chicken breast' to RecipeBook

Wed Apr 12 18:37:37 PDT 2023
Added Recipe 'Croissant sandwich' to RecipeBook

Wed Apr 12 18:38:21 PDT 2023
Changed 'Cheeseburger' cook time to 40 minutes


Process finished with exit code 0


Possible refactoring:
I would change the recipe class to include an ingredient class as its list of ingredients instead of list of string.
This would allow me to add features to ingredients such as whether I currently have them at home, or their relative price.
I would also edit my GUI to not open a new frame when an option was selected and rather a new panel was opened.
I find this makes it less of a busy application as there are not many tabs open. 
Lastly, I would potentially also add a class that produces a list of all steps required to make each recipe.
This would be lengthy and tedious, however would then pose as an actual recipeBook. I would consider this if it did not 
add too many words and make the application busy. Thus, I would attempt it before proceeding.

