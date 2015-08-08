Twitter Stream Reader
=====================

Displays the geolocation of the tweets coming from the Twitter Stream.


## Setting up the stream reader


After cloning the repository, create a Twitter application at [https://apps.twitter.com/](https://apps.twitter.com/). Obtain the Twitter API keys and Access tokens of your application and set them up in the Java main file.

Once the API keys and tokens are set up, run the following commands:

	mvn compile
    mvn exc

This will run Twitter stream reader daemon and write each tweet's latitude, longitude, and text in the **tweets.csv** file.

## Displaying the tweets in a map

In order to display the tweets in a map, an HTTP server must run at the project's directory with the next command:

	python -m SimpleHTTPServer


Finally, open a browser at [http://0.0.0.0:8000/gis.html](http://0.0.0.0:8000/gis.html) and it will display a map refresh it every 20 seconds.