package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;




/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void loginValidate(Object obj){
        LoginActivity login = (LoginActivity) obj;
        assertTrue(login.equals(2));
    }
    @Test
    public void validateRegistration() {
        sigginActivity user = new sigginActivity();
        assertTrue(Boolean.parseBoolean(user.getString(199)));
        assertNotNull(user.getString(100));
    }
    @Test
    public void validateLeaderBoard() {
        leaderBoard user = new leaderBoard();
        assertTrue(user.equals(299));
        assertNotNull(user.getString(1));
    }


    @Test
    public void validatePhysicalActivity() {
        PhysicalActivity user = new PhysicalActivity();
        assertTrue(user.equals(20));
    }

    @Test
    public void validateQuestionnaireActivty() {
        qeustionaireActivity user = new qeustionaireActivity();
        assertTrue(Boolean.parseBoolean(user.getString(1)));
        assertEquals(193, 193);
    }
}



