# MovieApp – AI Movie Description Generator

## What This App Does
This is a simple Spring Boot web application where users enter a movie title and rating, and the app uses the Gemini AI API to generate a short description for the movie. All submitted movies are displayed on a list page.

## Steps I Followed to Build This App

- Set up a new Spring Boot project with Maven and necessary dependencies.
- Verified Gemini API setup by printing a "hello" message to the console.
- Hardcoded a movie title and tested if Gemini API could return a valid description to the console.
- Created a basic controller to return the description via a web browser using raw HTML.
- Created a simple Movie model class with `title`, `description`, and later added `rating`.
- Created a web form using a plain HTML string that takes movie title and rating input.
- Used a POST endpoint in the controller to accept form data.
- Passed the movie title into the Gemini API prompt and received the generated description.
- Created a `Movie` object with the title, description, and rating and stored it in a list.
- Built a list page that loops through all movie entries and shows their details.
- Used `toString()` in the Movie class to render each movie's data on the list page.
- Styled the HTML output simply with line breaks for better readability.

## Note
- All movie data is stored in memory (in a list), so it resets on server restart.
- No database or frontend framework was used—just core Java and Spring Boot.
