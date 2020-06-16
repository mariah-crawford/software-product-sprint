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
    messages.add("Good Morning!");
    messages.add("Good Afternoon!");
    messages.add("Good Evening!");
    messages.add("Good Night!");
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Convert the ArrayList to JSON
    Gson gson = new Gson();
    String json = gson.toJson(messages);  
    
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);

    // response.setContentType("text/html;");
    //why doesnt the headers in html format work and only show up as chars?
    //response.getWriter().println("Hello Mariah! How are you today?");
  }

  /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJson(ArrayList<String> messages) {
    String json = "{";
    json += "\"morning\": ";
    json += "\"" + messages.get(0) + "\"";
    json += ", ";
    json += "\"afternoon\": ";
    json += "\"" +  messages.get(1) + "\"";
    json += ", ";
    json += "\"evening\": ";
    json += "\"" + messages.get(2) + "\"";
    json += ", ";
    json += "\"night\": ";
    json += "\"" + messages.get(3) + "\"";
    json += "}";
    return json;
  }
}
