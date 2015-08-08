package com.github.gorayni;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;


public class App 
{
    public static void main( String[] args )
    {
    	
    	ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		configurationBuilder.setOAuthConsumerKey("YOUR CONSUMER KEY (API KEY)")//					
				.setOAuthConsumerSecret("YOUR CONSUMER SECRET (API SECRET)") //
				.setOAuthAccessToken("YOUR ACCESS TOKEN")//
				.setOAuthAccessTokenSecret("YOUR ACCESS TOKEN SECRET");

		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		StatusListener listener = new StatusListener() {

			@Override
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onStatus(Status status) {
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tweets.csv", true)))) {														
					if(status.getGeoLocation() != null)
					{						
						double latitude = status.getGeoLocation().getLatitude();
						double longitude = status.getGeoLocation().getLongitude();
						String text = status.getText();						
						out.println(latitude+","+longitude+","+text);
					}									
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		};
		
		twitterStream.addListener(listener);    	
                
		try {
			System.out.println( "Starting listening to tweets..." );
			twitterStream.sample();
			while (true) {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
        
    }
}
