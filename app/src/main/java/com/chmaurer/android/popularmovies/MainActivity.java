package com.chmaurer.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.chmaurer.android.popularmovies.data.Movie;
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

 * Settings Activity:
 * used from https://gist.github.com/udacityandroid/41aca2eb9ff6942e769b
 * used http://stackoverflow.com/questions/19517417/opening-android-settings-programmatically as reference and http://stackoverflow
 * .com/questions/19248607/settings-preference-activity-is-not-starting and http://viralpatel.net/blogs/android-preferences-activity-example/
 https://www.udacity.com/course/viewer#!/c-ud853-nd/l-1474559101/e-1643578589/m-1643578590
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
 * http://stackoverflow.com/questions/18368748/android-studio-module-wont-show-up-in-edit-configuration (problems after checkout of sunshine)
 * http://stackoverflow.com/questions/25104392/android-studio-no-module (problems after checkout of sunshine)
 * http://stackoverflow.com/questions/19272127/sdk-location-not-found-android-studio-gradle
 Problems importing project to android studio
 * https://teamtreehouse.com/community/inserting-the-parsecom-files-in-android-studio
 * http://stackoverflow.com/questions/24298896/android-studio-error-8-0-plugin-with-id-android-not-found
 * https://www.google.com/search?q=plugin+with+id+com.parse+not+found&ie=utf-8&oe=utf-8
 * http://stackoverflow.com/questions/19272127/sdk-location-not-found-android-studio-gradle
 * https://www.google.com/search?q=sdk+location+not+found&ie=utf-8&oe=utf-8
 * http://stackoverflow.com/questions/25172006/android-studio-build-fails-with-task-not-found-in-root-project-myproject
 * https://www.google.com/search?q=cannot+get+property+compileSdkVersion&ie=utf-8&oe=utf-8#q=task+compile+debug+source+not+found
 * https://www.google.com/search?q=cannot+get+property+compileSdkVersion&ie=utf-8&oe=utf-8#q=task+compliledebugsources+not+found
 * http://parse-android.s3.amazonaws.com/fb48e439390e00760cb88b07285f79ba/Parse-Starter-Project-1.10.3.zip
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858/8
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858/7
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858/6
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858/4
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858/3
 * https://discuss.gradle.org/t/getting-android-compilesdkversion-is-missing-error-gradle-build/9858
 * http://stackoverflow.com/questions/31045058/error-cannot-get-property-compilesdkversion-on-extra-properties-extension-as
 * http://android.techjaffa.info/tag/exist-error-cannot-get-property-compilesdkversion-on-extra-properties-extension-as-it-does-not/
 * https://github.com/ParsePlatform/ParseUI-Android/issues/8
 * http://stackoverflow.com/questions/28319365/error-package-android-support-v7-app-does-not-exist-android-studio
 * http://stackoverflow.com/questions/18299898/the-import-android-support-cannot-be-resolved
 * Parts from the sunshine project in all lessons up until now and parts from the github repo
 * http://developer.android.com/reference/android/content/UriMatcher.html
 * http://developer.android.com/guide/components/loaders.html
 * http://developer.android.com/reference/android/content/CursorLoader.html
 * http://developer.android.com/reference/android/provider/MediaStore.Audio.Media.html
 * http://stackoverflow.com/questions/26983905/android-programming-making-a-uri-to-get-audio-location
 * https://developer.android.com/design/material/index.html
 * http://www.google.com/design/spec/material-design/introduction.html#introduction-goals
 * http://developer.android.com/guide/topics/ui/layout/linear.html#Weight
 * https://gist.github.com/udacityandroid/a86d966f3f4105a22ac3#file-strings-xml
 * http://developer.android.com/guide/components/fragments.html
 * http://developer.android.com/reference/android/app/FragmentManager.html/
 * http://developer.android.com/reference/android/app/FragmentTransaction.html
 * https://github.com/udacity/Sunshine-Version-2/blob/5.09_two_pane_ui/app/src/main/res/layout-sw600dp/activity_main.xml
 * https://github.com/udacity/Sunshine-Version-2/blob/5.09_two_pane_ui/app/src/main/res/layout/activity_main.xml
 * https://github.com/udacity/Sunshine-Version-2/blob/5.09_two_pane_ui/app/src/main/res/layout/activity_detail.xml
 * https://github.com/udacity/Sunshine-Version-2/compare/5.08_images...5.09_two_pane_ui
 * https://gist.github.com/udacityandroid/41f9e52a36e88388624d
 * http://developer.android.com/reference/android/app/Fragment.html
 * http://developer.android.com/reference/android/widget/AbsListView.html#setChoiceMode%28int%29
 * https://gist.github.com/udacityandroid/0c906a3bdb9f518bab8f
 * http://developer.android.com/guide/topics/ui/themes.html
 * http://developer.android.com/training/multiscreen/screensizes.html#TaskUseAliasFilters
 * https://github.com/udacity/Sunshine-Version-2/compare/5.14_today_item_tablet...5.15_action_bar
 * https://gist.github.com/udacityandroid/1c799806f0e519015125
 * https://github.com/udacity/Sunshine-Version-2/tree/5.17_redlines_list_item/app/src/main/res/layout
 * https://github.com/udacity/Sunshine-Version-2/compare/5.16_settings_action_bar...5.17_redlines_list_item
 * http://stackoverflow.com/questions/11692162/android-change-background-color-of-fragment
 * http://stackoverflow.com/questions/5350624/set-icon-for-android-application
 * https://github.com/udacity/Sunshine-Version-2/compare/5.17_redlines_list_item...5.18_redlines_finish
 * http://stackoverflow.com/questions/23330816/error-package-android-support-v7-app-does-not-exist
 * https://blog.xamarin.com/android-tips-hello-appcompatactivity-goodbye-actionbaractivity/
 * http://developer.android.com/tools/support-library/features.html#v7-appcompat
 * http://developer.android.com/tools/support-library/index.html
 * http://developer.android.com/tools/revisions/build-tools.html
 * http://stackoverflow.com/questions/17954596/how-to-draw-circle-by-canvas-in-android
 * https://gist.github.com/qihnus/1909616
 * For Javadoc Comments http://stackoverflow.com/questions/17291785/how-to-generate-javadoc-comments-in-android-studio
 * http://stackoverflow.com/questions/29138760/retrofit-android-gson-array-content-deserialization
 * http://square.github.io/retrofit/
 * http://stackoverflow.com/questions/24745236/restrofit-deserializing-json-response
 * http://stackoverflow.com/questions/32269064/unable-to-create-call-adapter-for-class-example-simple
 * http://stackoverflow.com/questions/29323095/retrofit-call-inside-asynctask
 * http://stackoverflow.com/questions/33077292/abstractmethoderror-when-using-rxjavacalladapterfactory-on-retrofit-2
 * http://stackoverflow.com/questions/32367469/unable-to-create-converter-for-my-class-in-android-retrofit-library
 * http://stackoverflow.com/questions/24154917/retrofit-expected-begin-object-but-was-begin-array
 * http://stackoverflow.com/questions/25089339/retrofit-returns-an-empty-array
 * https://www.reddit.com/r/androiddev/comments/2cdgc8/retrofit_returns_an_empty_array/
 * http://stackoverflow.com/questions/12348627/bad-parcelable-exception
 * http://stackoverflow.com/questions/7037630/how-to-create-a-video-preview-in-android
 * http://stackoverflow.com/questions/9739498/android-action-bar-not-showing-overflow
 * http://stackoverflow.com/questions/6300608/how-to-pass-a-parcelable-object-that-contains-a-list-of-objects
 * http://stackoverflow.com/questions/7037630/how-to-create-a-video-preview-in-android
 * http://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent
 * http://developer.android.com/guide/components/intents-filters.html
 * http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
 * http://stackoverflow.com/questions/16333754/how-to-customize-listview-using-baseadapter
 * http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
 * http://stackoverflow.com/questions/5796611/dialog-throwing-unable-to-add-window-token-null-is-not-for-an-application-wi
 * http://stackoverflow.com/questions/1996294/problem-unmarshalling-parcelables
 * http://stackoverflow.com/questions/10552062/badparcelableexception-classnotfoundexception-when-unmarshalling-empty-classn
 * http://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
 * http://stackoverflow.com/questions/1851633/how-to-add-a-button-dynamically-in-android
 * http://stackoverflow.com/questions/8438778/how-to-load-youtube-video-thumbnails-in-android
 * http://stackoverflow.com/questions/26572048/elevation-on-android-lollipop-not-working
 * http://stackoverflow.com/questions/11411421/separation-between-rows-in-table-layout
 * http://stackoverflow.com/questions/14020859/change-height-of-a-listview-dynamicallyandroid
 * http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
 * http://stackoverflow.com/questions/9458258/return-value-from-async-task-in-android
 * http://stackoverflow.com/questions/12580742/dynamically-filling-a-table-layout-with-table-rows
 * http://stackoverflow.com/questions/5255184/android-and-setting-width-and-height-programmatically-in-dp-units
 * http://stackoverflow.com/questions/14354279/call-parents-activity-from-a-fragment
 * http://stackoverflow.com/questions/18013912/selectionargs-in-sqlitequerybuilder-doesnt-work-with-integer-values-in-columns
 * http://stackoverflow.com/questions/7374785/getstring-from-strings-xml-in-appwidgetprovider
 * http://developer.android.com/guide/topics/ui/controls/togglebutton.html
 * http://stackoverflow.com/questions/1741334/sqliteopenhelper-getwriteabledatabase-null-pointer-exception-on-android
 * http://stackoverflow.com/questions/16128636/sqliteopenhelper-null-pointer-exception
 * http://stackoverflow.com/questions/7930139/android-database-locked
 * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabaseLockedException.html
 * http://developer.android.com/reference/android/database/AbstractCursor.html#moveToFirst%28%29
 * http://stackoverflow.com/questions/20777533/sqlite-cannot-bind-argument-at-index-1-because-the-index-is-out-of-range-the-s
 */

public class MainActivity extends AppCompatActivity {
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    public boolean ismTwoPane () {
        return mTwoPane;
    }

    public void setmTwoPane (boolean mTwoPane) {
        this.mTwoPane = mTwoPane;
    }

    @Override protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        if (findViewById (R.id.movieDetailContainer) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager ().beginTransaction ().replace (R.id.movieDetailContainer, new DetailActivity.PlaceholderFragment (), DETAILFRAGMENT_TAG).commit ();
            }
        } else {
            mTwoPane = false;
        }

    }

    @Override public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        getMenuInflater ().inflate (R.menu.settings, menu);
        return true;
    }


    @Override public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity (new Intent (this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override protected void onResume () {
        super.onResume ();
    }

    public void onMovieSelectedCallback (Movie movie) {
        if (ismTwoPane ()) {
            Bundle args = new Bundle ();
            args.putParcelable (DetailActivity.PlaceholderFragment.DETAIL_URI, movie);
            DetailActivity.PlaceholderFragment fragment = new DetailActivity.PlaceholderFragment ();
            fragment.setArguments (args);
            getSupportFragmentManager ().beginTransaction ().replace (R.id.movieDetailContainer, fragment, DETAILFRAGMENT_TAG).commit ();
        } else {
            Intent detailIntent = new Intent (this, DetailActivity.class);
            detailIntent.putExtra ("Movie", movie);
            startActivity (detailIntent);
        }
    }

}
