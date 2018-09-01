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
4. Click Run.

If step 3 fails, some things to try:
* Restart Eclipse
* Project > Clean... > Clean

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
