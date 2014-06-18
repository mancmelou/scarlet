/**
 * And of course, just like everything,
 * this starts in your project namespace :)
 */
package org.example.app

/**
 * Import Scarlet's "Application" class and you're ready to go!
 */
import com.mancmelou.scarlet.Application

/**
 * Create a new class that extends the Application class.
 * Now, this class is where your application runs. How exciting!
 */
class SimpleScarletApp extends Application {

  /**
   * This will register "GET /" route.
   * Every GET request to the "/" URI will be routed to this block.
   */
  get("/") {
    "Congratulations! You're running your first Scarlet app."
  }

  get("/form") {
    /**
     * `html` block will force text/html content type
     */
    html {
      <form action="/form" method="post">
        <span>Your query:</span>
        <input type="text" name="query" />
        <input type="submit" value="Go!" />
      </form>
    }
  }

  post("/form") {
    "You submitted " + param("query") + "!"
  }

  get("/debug") {

    // text block will force text/plain content type
    text {
      "Router: " + router.toString + "\n\n" +
      "Response: " + response.toString + " " + response.get.toMap + "\n\n" +
      "Request: " + request.toString + " " + request.get.toMap + "\n\n"
    }
  }
}

// TODO
//
// make the sample app have more example urls,
// gradually show more features
//
// Fix application class - remove Request and Response shared objects
