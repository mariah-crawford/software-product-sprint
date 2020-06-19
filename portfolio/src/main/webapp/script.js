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


/**
 * Fetches a quote from the server and adds it to the DOM.
 */
function getQuote() {
  console.log('Fetching data.');

  // The fetch() function returns a Promise because the request is asynchronous. (fetch the url the servlet is mapped to -look under servlets folder)
  const responsePromise = fetch('/data');

  // When the request is complete, pass the response into handleResponse().
  responsePromise.then(handleResponse);
}

/**
 * Handles response by converting it to text and passing the result to
 * addQuoteToDom().
 */
function handleResponse(response) {
  console.log('Handling the response.');
  // the consol log prints out to log as code is executing - like print statements

  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise = response.text();

  // When the response is converted to text, pass the result into the
  // addQuoteToDom() function.
  textPromise.then(addQuoteToDom);
}
/** Adds a quote to the DOM. */
function addQuoteToDom(quote) {
  console.log('Adding quote to dom: ' + quote);

  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerText = quote;
}

/**
 * Gets data by fetching JSON string from the server and prints to JS console.
 */
 function getDataJson() {
    fetch('/data') //http request
    .then(response => response.json()) //like a function that takes in response then does the following..
    .then((messages) => { // multiple line function that takes in msg and does the following: 
        console.log(messages);

        // add hard-coded comments to the page
        const msgListElement = document.getElementById('messages-container');
        msgListElement.innerHTML = '';
        for(i = 0; i < messages.length ; i++){
            msgListElement.appendChild(createListElement(messages[i]));
        }
    });
 }

 /** Creates an <li> element containing text. (makes bullet points?) */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

/**
 * Adds a random greeting to the page.
 */
function addRandomFunFact() {
  const funFacts =
      ['I love to dance', 'I\'m from New Jersey', 'I plan school events for over 1,000 students', 'I create my own jewelry'];

  // Pick a random fun fact.
  const funFact = funFacts[Math.floor(Math.random() * funFacts.length)];

  // Add it to the page.
  const funFactContainer = document.getElementById('fun-fact-container');
  funFactContainer.innerText = funFact;
}
