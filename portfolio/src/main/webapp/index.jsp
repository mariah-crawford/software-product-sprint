<%-- The Java code in this JSP file runs on the server when the user navigates
     to the homepage. This allows us to insert the Blobstore upload URL into the
     form without building the HTML using print statements in a servlet. --%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
   String uploadUrl = blobstoreService.createUploadUrl("/data"); %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>My Portfolio</title>
    <link rel="stylesheet" href="style.css">
    <script src="script.js"></script>
  </head>
  <body onload="getDataJson()">
    <div id="content1">
      <h1>Mariah Crawford's Portfolio</h1>
      <img src="images/me.jpg" style="width:450px;height:300px;"/>
      <p>A Princeton University student pursuing a Bachelor of Science in Engineering (BSE) 
          in Computer Science and certificates in Entrepreneurship and Theater. Passionate 
          about using her creativity to explore the intersections of technology and entrepreneurship. 
          Exhibits strong computer programming skills in Java and Python languages and is a highly 
          motivated individual and a quick learner. Demonstrates great leadership skills through her 
          involvement as an officer in the diSiac Dance Company, Student Government (USG), and the 
          Entrepreneurship Club.
        </p>

      <p>Click here to get a random fun fact about Mariah:</p>
      <button onclick="addRandomFunFact()">Generate Fun Fact</button>
      <div id="fun-fact-container"></div>

      <p>View Comments Below:</p>
      <ul id="messages-container"></ul>

    </div>

    <div id="content2">
      <h1>More Information</h1>

      <h2>Projects </h2>
      <p>Software Product Print Portfolio Project, Sonorines Research Project</p>

      <h2> External Links </h2>
      <p>Click <a href="http://linkedin.com/in/mariah-crawford-38b296179">here</a> to visit my LinkedIn Page</p>
      <p>Click <a href="https://github.com/mariah-crawford">here</a> to visit my GitHub Profile</p>

      <h2>About Me: Hobbies and Interests</h2>
      <p>I'm passionate about volunteering and offering guidance and leadership to younger students.
          Through Student Government I am able to coordinate with other organizations to organize 
          and participate in volunteer events. Also, I was a mentor to many incoming college freshmen as 
          a leader in Princeton University's Freshmen Orienation Program, Community Action, in which we engaged
          in community service while building a sense of community and belonging among the students.
      </p>
      <img src="images/volunteer.jpg" style="width:500px;height:300px;"/>
      <img src="images/ca.jpg" style="width:500px;height:300px;"/>

      <p> Another interest of mine is dancing. I can often be found in the dance studio, working 
            on new choregraphy or practicing for the upcoming dance performance. I always knew I wanted
            to try a new activity in college, so I decided to audition for a dance company despite having had no 
            prior dance experience. However, I was accepted into the company and continued to grow as a 
            leader and dancer over the years. For example, when I was a freshman, former officers encouraged
            me to run for an officer position and I was granted opportunities for growth, 
            such as the chance to co-choreograph an audition piece and choreograph workshops.
      </p>
      <img src= "images/disi.png" style="width:500px;height:300px;"/>
      <img src= "images/disi_reunion.jpg" style="width:500px;height:300px;"/>

    </div>

    <form method="POST" action="<%= uploadUrl %>">

      <h2>Add a Comment:</h2>

      <p>Enter your name</p>
      <textarea name="name-input">Your name here</textarea>
      <br/><br/>

      <p>Enter a comment</p>
      <textarea name="comment-input">Enter your comment here</textarea>
      <br/><br/>

      <p>Upload an image:</p>
      <input type="file" name="image">
      <br/><br/>

      <input type="submit" />
    </form>
  </body>
</html>
