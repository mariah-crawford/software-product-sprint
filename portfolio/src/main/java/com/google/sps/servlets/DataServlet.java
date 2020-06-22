// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    // Create an arraylist object with hard-coded messages
    private ArrayList<String> messages;

  @Override
  public void init() {
    messages = new ArrayList<String>();
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Create a Query instance with the kind of entity to load
    Query query = new Query("Comment").addSort("user_comment", SortDirection.DESCENDING);

    // Gather the instances of entities in Datastore of kind "Comment"
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    // Iterate through the entities in Datastore to get their properties
    for (Entity entity : results.asIterable()) {  
        String name = (String) entity.getProperty("user_name");
        String comment = (String) entity.getProperty("user_comment");
        messages.add("\""+ comment + "\" \n - " + name);
    } 
    
    // Convert the ArrayList to JSON
    Gson gson = new Gson();
    String json = gson.toJson(messages);  
    
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String name = request.getParameter("name-input");
    String comment = request.getParameter("comment-input");

    // Create entity with name and comment properties
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("user_name", name);
    commentEntity.setProperty("user_comment", comment);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.jsp");
  }
}
