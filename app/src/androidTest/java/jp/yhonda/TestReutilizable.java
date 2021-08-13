package jp.yhonda;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.web.assertion.WebAssertion;
import android.support.test.espresso.web.assertion.WebViewAssertions;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.espresso.web.sugar.Web.WebInteraction;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.espresso.web.matcher.DomMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed; //assertions
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;//assertions
import static android.support.test.espresso.matcher.ViewMatchers.withText;//assertions
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webContent;
import static android.support.test.espresso.web.matcher.DomMatchers.elementById;
import static android.support.test.espresso.web.matcher.DomMatchers.withTextContent;

public class TestReutilizable {

    @Rule
    public ActivityTestRule<MaximaOnAndroidActivity> mActivityTestRule = new ActivityTestRule<>(MaximaOnAndroidActivity.class);

    @Test
    public void testReuse() {

        ViewInteraction textWrite = onView(
                allOf(withId(R.id.editText1), isDisplayed()));
        textWrite.perform(replaceText("38*990/20145;"), closeSoftKeyboard());
        ViewInteraction pressButton = onView(
                allOf(withId(R.id.enterB), withText("Enter"), isDisplayed()));

        pressButton.perform(click());
        waitFor(1000);
        //  el elemento de salida en el que se puede hacer clic
        WebInteraction calcResult = onWebView().withElement(findElement(Locator.ID, "moa1"));

        waitFor(5000);
        textWrite.perform(replaceText("sqrt(25*99*256);"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(2000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-2"));

        onWebView().withElement(findElement(Locator.ID, "38*990/20145;")).perform(webClick());
        textWrite.check(matches(withText("38*990/20145;")));

        calcResult.perform(webClick());
        textWrite.check(matches(withText("2508/1343")));

    }
    private static void waitFor(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void waitForStartup() {
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Maxima")));
    }
}
