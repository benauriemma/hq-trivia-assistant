# HQ Trivia Assistant
An HQ Trivia assistant for those of us who are better with REST API's than trivia.  This application is meant to help you answer questions in the HQ Trivia mobile game.  (Well) within the 10-second time limit of a question, it will:
* Screenshot the question and answers
* Use Google's Vision API to extract the text of the question and answers
* Generate a search query
* Execute that query using Google's Custom Search API
* Analyze the results of the search
* Suggest the most likely answer

### DISCLAIMER
Using this during a live game of HQ Trivia would be a violation of the app's terms of use.  **I strongly discourage using this during a live game**.  This is only meant to be used on screenshots and recordings.

### Downloading and running
The projects dependencies are managed with Maven.  The following are the steps I use to download and run the project using Mac and Eclipse, but I expect the process will be similar with other operating systems or IDEs:
1. Download the project to your local machine using ```git clone your-url```.  ```your-url``` can be found by clicking the "Clone or download" icon at the main page of this Github repository.
2. From Eclipse, select File > Import... > Projects from Folder or Archive.  Then, click Directory... and navigate to the project folder on your local drive.
3. Maven should automatically resolve dependencies, and Eclipse should automatically recognize Driver.java as the main class.
4. You should have errors now because the file PersonalInfo.java doesn't exist.  You'll need to follow the instructions in the following section to obtain an API key and a search engine ID.  Then, create a file PersonalInfo.java that resembles:
```java
public final class PersonalInfo {
	public static final String API_KEY = "your-api-key";
	public static final String SEARCH_ENGINE_ID = "your-search-engine-id";
}
```
5. Click Run.

If step 3 fails, some things to try:
* Restart Eclipse
* Project > Clean... > Clean

### Setting up Google Cloud Platform
You'll need to take a few simple steps to get yourself access to the Google APIs used by this project.  If the information here isn't comprehensive, there's a fair amount of documentation online (both by Google and others) that might help you get set up.

##### Vision API
1. Set up an account for Google Cloud Platform if you don't already have one.
2. Create a new project.  There should be a dropdown at the top left of the GCP homepage/console.
3. Click "APIs & Services", then "Dashboard", then "ENABLE APIS AND SERVICES".  Search for the Vision API and enable it.
4. Go back to the APIs & Services screen, and click on "Credentials".  Copy the API key for the project you created.  This is the value of API_KEY in PersonalInfo.

##### Custom Search
1. Follow the instructions here to create a Custom Search Engine: https://developers.google.com/custom-search/docs/tutorial/creatingcse
2. Enter any url in the "Sites to search" section; you'll remove that later.
3. Under "Search Preferences", choose "Search the entire web but emphasize included sites".
4. Now, remove the url you added the the "Sites to search" section earlier.  This should now search the whole web.
5. Copy the search engine ID.  This is the value of SEARCH_ENGINE_ID in PersonalInfo.

### Using the assistant
Currently the project is configured specifically for my computer (a 13" Macbook Pro) and my phone (an iPhone 6 Plus), so it may take some adjustment to work for you.  That said, these are the steps I use:
1. Plug my phone into my computer via a lightning cable.
2. Open Quicktime, and select File > New Movie Recording, then from the dropdown next to the record button, select my iPhone.
3. My phone screen should now be shown on my laptop.  Make this window as tall as possible and put it against the right side of my display.  The program expects the questions and answers to be in a very specific place, which may be different for you depending on your devices/display sizes.  This would need to be adjusted in the code.
4. Now, run the application as described in the section above.  A small window should pop up; make sure this is selected so that it recognizes keyboard inputs.  The iPhone screen and the Eclipse console should both be visible, too.
5. As soon as a question becomes visible on the screen, press the space bar.  This triggers the application to take screenshots and make a guess.
6. The three answers and a score for each will be printed to the console.  The answer with the highest score is the assistant's best guess.
7. Press the space bar again each time a new question is shown.

Making this process less platform dependent is on the roadmap.

### Current state
The assistant can successfully read and google a question, but it's not very "smart" yet.  That is, the logic it uses to build a query and make a guess is fairly simplistic, and only works for some types of questions.  Though I haven't done extensive or scientific testing, I'd estimate that it makes a correct guess for about 1 in 4 questions.  It rarely guesses incorrectly, but often returns a score of 0 for all answers.

### TODO
See the Issues section of this repository for some more granular todo's, but my two main goals moving forward with this project are:
1. Create new logic for generating search queries and analyzing the results to choose a correct answer more consistently
2. Make it more friendly for others to use and contribute
