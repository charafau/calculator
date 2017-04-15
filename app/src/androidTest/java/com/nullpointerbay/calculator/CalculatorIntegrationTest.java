package com.nullpointerbay.calculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class CalculatorIntegrationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    @Test
    public void shouldAddSevenAndThree() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSeven), withText("7"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnAdd), withText("+"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btwThree), withText("3"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnEqual), withText("="), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.txtSummary), withText("10.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("10.0")));

    }

    @Test
    public void shouldSubtractThreeFromEight() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnEight), withText("8"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnMinus), withText("-"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btwThree), withText("3"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnEqual), withText("="), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.txtSummary), withText("5.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("5.0")));

    }

    @Test
    public void shouldCleanSummaryAfterClickingClear() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnFive), withText("5"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnAdd), withText("+"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btwThree), withText("3"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnEqual), withText("="), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.txtSummary), withText("8.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("8.0")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.txtOutput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnClear), withText("C"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.txtOutput),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.txtSummary),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("")));

    }

    @Test
    public void shouldDisableButtonWhenClickOperatorButton() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSix), withText("6"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnAdd), withText("+"), isDisplayed()));
        appCompatButton2.perform(click());


        ViewInteraction button = onView(
                allOf(withId(R.id.btnEqual),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        5),
                                2),
                        isDisplayed()));
        button.check(matches(not(isEnabled())));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnSix), withText("6"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnEqual),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        5),
                                2),
                        isDisplayed()));
        button2.check(matches(isEnabled()));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnAdd), withText("+"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnClear), withText("C"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnEqual),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        5),
                                2),
                        isDisplayed()));
        button3.check(matches(isEnabled()));

    }

    @Test
    public void shouldNotPutSpaceAfterMinusWhenFirstClicked() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnMinus), withText("-"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnTwo), withText("2"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnAdd), withText("+"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.bntFour), withText("4"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnMinus), withText("-"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btnOne), withText("1"), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.txtOutput), withText(" -2 + 4 - 1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(" -2 + 4 - 1")));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btnEqual), withText("="), isDisplayed()));
        appCompatButton7.perform(click());


        ViewInteraction result = onView(
                allOf(withId(R.id.txtSummary), withText("1.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.TableLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        result.check(matches(withText("1.0")));


    }
}
