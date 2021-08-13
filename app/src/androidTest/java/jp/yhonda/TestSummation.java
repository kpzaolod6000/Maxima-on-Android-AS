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

//ViewMatchers = para hacer coincidir y encontrar elementos / vistas de la interfaz de usuario en la jerarquía de vistas de una pantalla de actividad de Android
//ViewActions = Una vez que onView coincide y devuelve el objeto ViewInteraction, cualquier acción se puede invocar llamando al método "perform" del objeto ViewInteraction
//ViewAssertions = para afirmar que la vista coincidente es lo que esperábamos. Una vez que onView coincide y devuelve el objeto ViewInteraction, cualquier aserción se puede comprobar mediante el método check de ViewInteraction
@RunWith(AndroidJUnit4.class)
public class TestSummation {

    @Rule
    public ActivityTestRule<MaximaOnAndroidActivity> mActivityTestRule = new ActivityTestRule<>(MaximaOnAndroidActivity.class);

    @Test
    public void testSumSimple(){
        waitForStartup();
        ViewInteraction TextViewSum = onView(
                allOf(withId(R.id.editText1), isDisplayed()));
        TextViewSum.perform(replaceText("sum(i^2,i,1,7)"), closeSoftKeyboard());

        ViewInteraction buttonEnter = onView(
                allOf(withId(R.id.enterB), withText("Enter"), isDisplayed()));
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-1"));


        TextViewSum.perform(replaceText("sum(a[i],i,1,7)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-2"));

        TextViewSum.perform(replaceText("sum(a[i],i,1,7)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-3"));


        TextViewSum.perform(replaceText("sum(a[i],i,1,n)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-4"));

        TextViewSum.perform(replaceText("sum(2^i + i^2,i,0,n)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-5"));


        TextViewSum.perform(replaceText("sum(1/3^i,i,1,inf)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-6"));

        TextViewSum.perform(replaceText("sum(1/3^i,i,1,inf),simpsum"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-7"));


        TextViewSum.perform(replaceText("sum(i^2,i,1,4) * sum(1/i^2,i,1,inf)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-8"));


        TextViewSum.perform(replaceText("sum(integrate(x^k,x,0,1),k,1,n)"), closeSoftKeyboard());
        buttonEnter.perform(click());
        waitFor(1000);
        onWebView().withElement(findElement(Locator.ID, "MathJax-Element-9"));


        TextViewSum.perform(replaceText("sum(if k <=5 then a^k else b^k,k,1,10)"), closeSoftKeyboard());
        buttonEnter.perform(click());
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
