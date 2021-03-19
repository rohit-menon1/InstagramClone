package com.codepath.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8hh9ajJteNeFpB531hGCiGEnc8Qr92U58Rf2dhrQ")
                .clientKey("r3e3rb2T89kpgO5ioJFY488fXFx72Xkw5p0FqMTF")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
