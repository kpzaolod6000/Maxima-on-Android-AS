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
public class TestCalculoTensorial {
    @Rule
    public ActivityTestRule<MaximaOnAndroidActivity> mActivityTestRule = new ActivityTestRule<>(MaximaOnAndroidActivity.class);

    @Test
    public void testCalculoSetup() {
        waitForStartup();
        ViewInteraction loadLibraryCtensor = onView(
                allOf(withId(R.id.editText1), isDisplayed()));
        loadLibraryCtensor.perform(replaceText("load(ctensor);"), closeSoftKeyboard());
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.enterB), withText("Enter"), isDisplayed()));
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-1"));

        loadLibraryCtensor.perform(replaceText("csetup();"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-2"));

        loadLibraryCtensor.perform(replaceText("4"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-3"));

        loadLibraryCtensor.perform(replaceText("n;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-4"));//Do you want to enter matrix?

        loadLibraryCtensor.perform(replaceText("1;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Answer 1, 2, 3 or 4 :")));

        loadLibraryCtensor.perform(replaceText("1;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Row 1 Column 1:")));

        loadLibraryCtensor.perform(replaceText("a"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Row 2 Column 2:")));

        loadLibraryCtensor.perform(replaceText("x^2;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Row 3 Column 3:")));

        loadLibraryCtensor.perform(replaceText("x^2*sin(y)^2;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        Web.onWebView().check(WebViewAssertions.webContent(DomMatchers.containingTextInBody("Row 4 Column 4:")));

        loadLibraryCtensor.perform(replaceText("-d;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-5"));//Enter functional dependencies

        loadLibraryCtensor.perform(replaceText("dependes([a,d],x);"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-6"));//Do you want to see matrix?

        loadLibraryCtensor.perform(replaceText("y;"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-7"));//done


        loadLibraryCtensor.perform(replaceText("christof(mcs);"), closeSoftKeyboard());
        appCompatButton2.perform(click());
        waitFor(2000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-15"));
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
