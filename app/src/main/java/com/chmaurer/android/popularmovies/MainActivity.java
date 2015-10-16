package com.chmaurer.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * All Sources go here:
 * ====================
 * <p/>
 * For project setup and implementation hints:
 * https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true (udacity implementation guide)
 * https://www.udacity.com/course/viewer#!/c-nd801/l-4256658707/m-4283743583 (udacity requirements page)
 * Using the sunshine project done in Udacity Lessons for various implementation hints (Lessons 1-3) and the websites stated in Sunshine Project
 * https://jsonformatter.curiousconcept.com/ (for json parsing)
 * <p/>
 * For storing the API Key
 * https://developer.android.com/samples/MediaRouter/res/values/arrays.html for Arrays.xml (I store my api key there and do not add the arrays.xml to git)
 * <p/>
 * For the UI parts
 * http://developer.android.com/guide/topics/ui/layout/gridview.html (For the grid view)
 * <p/>
 * For the data retrieval
 * https://www.themoviedb.org/documentation/api
 * https://www.themoviedb.org/documentation/api/discover
 * https://gist.github.com/baderj/7414775
 * Using the sunshine project done in Udacity Lessons for various implementation hints (Lessons 1-3)
 * from http://developer.android.com/guide/topics/resources/more-resources.html (Api key)
 * http://stackoverflow.com/questions/18280194/using-themoviedb-to-display-image-poster-with-php
 * how to parse JSON to List of Movies taken from sunshine app
 * <p/>
 * Other stuff:
 * Reflection from http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
 * CollectionUtils in android http://stackoverflow.com/questions/30259141/how-to-add-apache-commons-collections-in-android-studio-gradle
 * List conversion http://stackoverflow.com/questions/10975913/how-to-make-a-new-list-with-a-property-of-an-object-which-is-in-another-list
 * Beanutils https://commons.apache.org/proper/commons-beanutils/apidocs/org/apache/commons/beanutils/BeanToPropertyValueTransformer.html and http://mvnrepository.com/artifact/commons-beanutils/commons-beanutils/1.8.3#gradle
 * Movies Thumbnail taken from http://de.freeimages.com/photo/film-1568846
 * Internet permission: http://developer.android.com/reference/android/Manifest.permission.html and http://stackoverflow.com/questions/2169294/how-to-add-manifest-permission-to-android-application
 * NetworkOnMainThread: http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * Image from URL https://forums.xamarin.com/discussion/4323/image-from-url-in-imageview
 * StringUtils http://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.0
 * Hint from Android Studio to exclude 'META-INF/NOTICE.txt' and 'META-INF/LICENSE.txt' in gradle.build File
 * Parcelable intent http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
 * Parcelling from http://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-be-parcelable and http://techdroid.kbeanie.com/2010/06/parcelable-how-to-do-that-in-android.html
 * Image Adapter from https://github.com/isbjorn/udacity-Popular-Movies-App/blob/master/app/src/main/java/io/maritimus/sofaexpert/ImageAdapter.java
 * Layout Span: http://stackoverflow.com/questions/2710793/what-is-the-equivalent-of-colspan-in-an-android-tablelayout
 * Textview Multiline / Line Breaks: http://stackoverflow.com/questions/6674578/multiline-textview-in-android and http://stackoverflow.com/questions/2197744/android-textview-text-not-getting-wrapped and http://stackoverflow.com/questions/5230290/android-and-displaying-multi-lined-text-in-a-textview-in-a-tablerow
 */


/**
 * From The Sunshine-Project some parts were ...
 * =============================================
 * http://developer.android.com/guide/topics/ui/settings.html and https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1643578576/m-1643578578
 * http://stackoverflow.com/questions/4233873/how-to-get-extra-data-from-intent-in-android
 * share action logic  also taken from from http://developer.android.com/training/sharing/shareaction.html and
 * from Documentation within Android studio and from coding in PlaceholderFragment and from http://developer.android.com/training/sharing/shareaction.html and http://stackoverflow.com/questions/12030631/using-string-inside-shareactionprovider-share-intent
 * and from https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808726/m-1643578595 and http://stackoverflow.com/questions/19358510/why-menuitemcompat-getactionprovider-returns-null
 * taken from http://stackoverflow.com/questions/9739498/android-action-bar-not-showing-overflow
 * using https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1643578580/m-1643578582
 * taken from http://stackoverflow.com/questions/4233873/how-to-get-extra-data-from-intent-in-android
 * taken from udacity course answer video
 * http://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing
 * taken from http://stackoverflow.com/questions/2614719/how-do-i-get-the-sharedpreferences-from-a-preferenceactivity-in-android
 * http://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing
 * http://stackoverflow.com/questions/8308695/android-options-menu-in-fragment
 * http://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing
 * http://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing
 * https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808706/m-1480808709
 * http://developer.android.com/guide/components/intents-filters.html#ExampleExplicit and https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808714/m-1480808717
 * http://stackoverflow.com/questions/3180354/regex-check-if-string-contains-at-least-one-digit
 * http://openweathermap.org/API#forecast
 * usng http://stackoverflow.com/questions/19167954/use-uri-builder-in-android-or-create-url-with-variables and http://developer.android.com/reference/android/net/Uri.Builder.html
 * typed array for appid (from http://developer.android.com/guide/topics/resources/more-resources.html)  //according to http://home.openweathermap.org/ and http://api.openweathermap.org/data/2.5/forecast/daily?q=5760,at&units=metric&mode=json&lang=de&cnt=7 and http://stackoverflow.com/questions/17121213/java-io-ioexception-no-authentication-challenges-found and https://discussions.udacity.com/t/openweathermap-now-requires-an-api-key/34486
 * URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + postalCode + ",at&units=metric&mode=json&lang=de&cnt=7");
 * getting temp taken from http://stackoverflow.com/questions/5566669/how-to-parse-a-json-object-in-android and http://developer.android.com/reference/org/json/JSONObject.html
 * help by https://jsonformatter.curiousconcept.com/
 * using code from https://www.udacity.com/ lessons
 * using initial code from the sunshine repo at github.com
 * using the open weather service as described at http://openweathermap.org/weather-conditions
 * using https://de.wikipedia.org/wiki/ISO-3166-1-Kodierliste for country code
 * using http://openweathermap.org/forecast for the api parameters
 * http://api.openweathermap.org/data/2.5/forecast/daily?q=5760,at&units=metric&mode=json&lang=de&cnt=7
 * Taking https://gist.github.com/udacityandroid/d6a7bb21904046a91695 for HTTP Request "boiler plate code"
 * for ForecastFragment http://stackoverflow.com/questions/20252727/is-not-an-enclosing-class-java
 * http://developer.android.com/reference/android/os/AsyncTask.html for asyncTask
 * using lesson2's codings
 * Menu inflater: http://developer.android.com/guide/topics/ui/menus.html and http://stackoverflow.com/questions/12424063/getmenuinflater-method-undefined-issue-in-android-context-menu-creation and http://stackoverflow.com/questions/18813367/creating-an-options-menu-in-android
 * and http://stackoverflow.com/questions/12395747/option-menu-does-not-appear-in-android and http://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing and http://developer.android.com/reference/android/app/Fragment.html
 * and http://stackoverflow.com/questions/8308695/android-options-menu-in-fragment
 * Reacting to click on options item: http://developer.android.com/guide/topics/ui/menus.html
 * For the ifRoom parameter :http://developer.android.com/guide/topics/ui/menus.html
 * For executing an asynchTask: http://www.peachpit.com/articles/article.aspx?p=2166868&seqNum=3
 * Android internet permission: http://stackoverflow.com/questions/2169294/how-to-add-manifest-permission-to-android-application
 * Using code from https://gist.github.com/udacityandroid/4ee49df1694da9129af9
 * OnClick of Listview http://developer.android.com/reference/android/widget/ListView.html
 * Replacing code according to https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/m-4393120181 ( https://github.com/udacity/Sunshine-Version-2/blob/3.02_create_detail_activity/app/src/main/res/layout/activity_detail.xml and https://github.com/udacity/Sunshine-Version-2/blob/3.02_create_detail_activity/app/src/main/java/com/example/android/sunshine/app/DetailActivity.java)
 * using http://developer.android.com/training/basics/firstapp/starting-activity.html and code replacement
 * as described in video lesson 3 (https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/m-1628289061)
 * taken from http://stackoverflow.com/questions/9739498/android-action-bar-not-showing-overflow
 * using https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1643578580/m-1643578582
 * taken from https://developer.android.com/guide/components/intents-common.html and https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808722/m-1480808725
 * from https://developer.android.com/guide/components/intents-common.html#Maps
 * how to get Package manager from http://stackoverflow.com/questions/17005713/why-would-activity-getpackagemanager-return-null (idea)
 * used from https://gist.github.com/udacityandroid/41aca2eb9ff6942e769b
 * used http://stackoverflow.com/questions/19517417/opening-android-settings-programmatically as reference and http://stackoverflow.com/questions/19248607/settings-preference-activity-is-not-starting and http://viralpatel.net/blogs/android-preferences-activity-example/
 * taking below code from https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1643578589/m-1643578590
 * from http://developer.android.com/training/sharing/shareaction.html and https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808726/m-1643578595-->
 * as in solution from https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1480808722/m-1480808725
 * list preference implementation taken from http://stackoverflow.com/questions/9880841/using-list-preference-in-android
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
