package com.example.MovieApp.Controller;

import com.example.MovieApp.Model.Movie;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    private final Client client = new Client();

    private List<Movie> movies = new ArrayList<>();

    // Show the form for inputting movie title
    @GetMapping("/movie/form")
    public String movieForm() {
        return """
            <html>
            <body>
            <h2>Enter Movie Title& Rating</h2>
            <form action="/movie/generate" method="post">
                Movie Name: <input type="text" name="title" required /> <br />
                Rating(0(min)-5(max))<input type="text" name="rating" required /><br />
                <button type="submit">Generate Description</button>
            </form>
            <hr/>
            <a href="/movie/list">See all generated movies</a>
            </body>
            </html>
        """;
    }

    // Accept form submission, call Gemini API, save and display
    @PostMapping("/movie/generate")
    public String generateDescription(@RequestParam String title,  @RequestParam int rating) {
        String prompt = "Please generate a short movie description for " + title+ "Include all languages to check the movie title";

        try {
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", prompt, null);

            Movie movie = new Movie(title, response.text(),rating);
            movies.add(movie);

            return "<html><body>" +
                    "<h1>" + movie.getTitle() + "</h1>" +
                    "<p>" + movie.getDescription() + "</p>" +
                    "<p>" + movie.getRating() + "</p>"+
            "<a href=\"/movie/form\">Generate another movie description</a><br/>" +
                    "<a href=\"/movie/list\">See all movies generated</a>" +
                    "</body></html>";
        } catch (Exception e) {
            return "Error generating description: " + e.getMessage();
        }
    }

    // Show the list of all movies generated so far
    @GetMapping("/movie/list")
    public String listMovies() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><h2>All Generated Movies</h2><ul>");
        for (Movie movie : movies) {
            sb.append("<li>").append(movie.toString()).append("</li>");
        }
        sb.append("</ul><a href='/movie/form'>Generate new movie description</a></body></html>");
        return sb.toString();
    }
}
