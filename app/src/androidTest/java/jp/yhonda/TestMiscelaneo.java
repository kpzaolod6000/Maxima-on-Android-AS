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
@RunWith(AndroidJUnit4.class)
public class TestMiscelaneo {
    @Rule
    public ActivityTestRule<MaximaOnAndroidActivity> mActivityTestRule = new ActivityTestRule<>(MaximaOnAndroidActivity.class);

    @Test
    public void testMiscelaneo() {
        waitForStartup();
        ViewInteraction textWrite = onView(
                allOf(withId(R.id.editText1), isDisplayed()));
        textWrite.perform(replaceText("askinteger(n);"), closeSoftKeyboard());

        ViewInteraction pressButton = onView(
                allOf(withId(R.id.enterB), withText("Enter"), isDisplayed()));
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-1"));
        onWebView().check(webContent(elementById("MathJax-Element-1", withTextContent(" \\mbox{ Is  }n\\mbox{  an  }{\\it integer}\\mbox{ ? }  "))));

        textWrite.perform(replaceText("y;"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-2"));

        textWrite.perform(replaceText("asksign(x);"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-3"));

        textWrite.perform(replaceText("neg;"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-4"));

        textWrite.perform(replaceText("integrate(x^n,x);"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-5"));

        textWrite.perform(replaceText("yes;"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-6"));

        textWrite.perform(replaceText("integrate(exp(n*x),x,0,inf);"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-7"));

        textWrite.perform(replaceText("neg;"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-8"));

        textWrite.perform(replaceText("file_search_maxima"), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-9"));


        textWrite.perform(replaceText("\"$$$$\""), closeSoftKeyboard());
        pressButton.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-10"));



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
