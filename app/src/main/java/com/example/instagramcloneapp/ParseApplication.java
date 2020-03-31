/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.instagramcloneapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

           //Bitnami password : uu2nmjZp3Z4V
    // Enable Local Datastore.
      try {
          Parse.enableLocalDatastore(this);

          // Add your initialization code here
          Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                  .applicationId("5eec1554ff55d195b8bf9a7554cf5bf0fcd110f1")
                  .clientKey("167a58d67307112cab0ceaa269b029e1b8a28790")
                  .server("http://3.19.76.248:80/parse/")
                  .build()
          );
/*
          ParseObject object = new ParseObject("Score");
          object.put("username", "rawzerin");
          object.put("score", 50);
          object.saveInBackground();
          object.put("username", "Arafat");
          object.put("score", 20);
          object.saveInBackground(new SaveCallback() {
              @Override
              public void done(ParseException e) {
                  if (e == null) {
                      Toast.makeText(StarterApplication.this, "Parsing successful", Toast.LENGTH_LONG).show();
                  }
              }
          });
          /*
          ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
          query.whereEqualTo("score", 65);
          query.findInBackground(new FindCallback<ParseObject>() {
              @Override
              public void done(List<ParseObject> objects, ParseException e) {
                  if (objects.isEmpty() != true && e == null) {
                      for (ParseObject object : objects) {
                          Log.i("USERNAME ::#:: ", object.getString("username"));
                          Log.i("SCORE ::#:: ",Integer.toString(object.getInt("score")));
                      }
                      Log.i("Parse Result :", "Successfully Parsed");
                  } else {
                      e.printStackTrace();
                  }
              }
          });


           */
         // ParseUser.enableAutomaticUser();

          ParseACL defaultACL = new ParseACL();
          defaultACL.setPublicReadAccess(true);
          defaultACL.setPublicWriteAccess(true);
          ParseACL.setDefaultACL(defaultACL, true);
      } catch (Exception e) {
          e.printStackTrace();
      }

  }
}
