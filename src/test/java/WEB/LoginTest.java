package WEB;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends AbstractTest{
    Logger logger = LoggerFactory.getLogger(LoginTest.class);
    @BeforeEach
    void goToLogin() {
        getWebDriver().get(getLoginUrl());
    }

    @Test
    @DisplayName("Test-case №1")
    @Description("Authorization by created user1 (with posts) with valid credentials")
    public void loginTest1() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        assertThat(myPage.getHeaderText(), is(equalTo("Hello, " + getUsername1())));
    }
    @Test
    @DisplayName("Test-case №2")
    @Description("Authorization by created user2 (without posts) with valid credentials")
    public void loginTest2() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername2(), getPassword2());
        assertThat(myPage.getHeaderText(), is(equalTo("Hello, " + getUsername2())));
    }
    @Test
    @DisplayName("Test-case №3")
    @Description("Authorization by created user with valid credentials")
    public void loginTest3() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("1qwerty2qwerty3qwer4", "e1086c97a2");
        assertThat(myPage.getHeaderText(), is(equalTo("Hello, 1qwerty2qwerty3qwer4")));
    }
    @Test
    @DisplayName("Test-case №4")
    @Description("Authorization by created user with valid credentials")
    public void loginTest4() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("qqwweerrttyy", "af4d952d35");
        assertThat(myPage.getHeaderText(), is(equalTo("Hello, qqwweerrttyy")));
    }
    @Test
    @DisplayName("Test-case №5")
    @Description("Authorization by created user with valid credentials")
    public void loginTest5() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("123456789112", "22b691e523");
        assertThat(myPage.getHeaderText(), is(equalTo("Hello, 123456789112")));
    }
    @Test
    @DisplayName("Test-case №6")
    @Description("Authorization by created user with empty credentials")
    public void loginTest6() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("", "");
        assertThat(myPage.getErrorCode(), is(equalTo("401")));
    }
    @Test
    @DisplayName("Test-case №7")
    @Description("Authorization by created user with empty username")
    public void loginTest7() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("", getPassword1());
        assertThat(myPage.getErrorCode(), is(equalTo("401")));
    }
    @Test
    @DisplayName("Test-case №8")
    @Description("Check error message if authorization by created user with empty username")
    public void loginTest8() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("", getPassword1());
        assertThat(myPage.getErrorMessage(), is(equalTo("Поле не может быть пустым")));
        logger.error("Test-case №8 failed! Expected error 'Поле не может быть пустым' but was 'Invalid credentials.'.");
    }
    @Test
    @DisplayName("Test-case №9")
    @Description("Authorization by created user with empty password")
    public void loginTest9() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), "");
        assertThat(myPage.getErrorCode(), is(equalTo("401")));
    }
    @Test
    @DisplayName("Test-case №10")
    @Description("Authorization by not created user with valid credentials")
    public void loginTest10() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("dsalkova3", getPassword1());
        assertThat(myPage.getErrorCode(), is(equalTo("401")));
    }
    @Test
    @DisplayName("Test-case №11")
    @Description("Check error message if authorization by not created user with valid credentials")
    public void loginTest11() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("dsalkova3", getPassword1());
        assertThat(myPage.getErrorMessage(), is(equalTo("Проверьте логин и пароль")));
        logger.error("Test-case №11 failed! Expected error 'Проверьте логин и пароль' but was 'Invalid credentials.'.");
    }
    @Test
    @DisplayName("Test-case №12")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest12() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("йцукен123456", "507d3e5af1");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №12 failed!");
    }
    @Test
    @DisplayName("Test-case №13")
    @Description("Check error message if authorization by created user with invalid login and valid password")
    public void loginTest13() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("йцукен123456", "507d3e5af1");
        assertThat(myPage.getErrorMessage(), is(equalTo("Неправильный логин. " +
                "Может состоять только из латинских букв и цифр, без спецсимволов")));
        logger.error("Test-case №13 failed! Expected error 'Неправильный логин. Может состоять \" +\n" +
                "                \"только из латинских букв и цифр, без спецсимволов' but was '500: Argument is not a ByteString'.");
    }
    @Test
    @DisplayName("Test-case №14")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest14() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("w2", "62d7d5184b");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №14 failed!");
    }
    @Test
    @DisplayName("Test-case №15")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest15() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("<>\"&<>\"&<>\"&", "0421077098");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
    }
    @Test
    @DisplayName("Test-case №16")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest16() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("qwerty<>\"&<>", "9020c3b562");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №16 failed!");
    }
    @Test
    @DisplayName("Test-case №17")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest17() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("123456<>\"&<>", "f8c90b402f");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №17 failed!");
    }
    @Test
    @DisplayName("Test-case №18")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest18() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("qwer1234!<>\"", "44bc6aa428");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №18 failed!");
    }
    @Test
    @DisplayName("Test-case №19")
    @Description("Authorization by created user with invalid login and valid password")
    public void loginTest19() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login("qwertyuiop12345678901", "0421077098");
        if (myPage.findErrorCode()) assertThat(myPage.getErrorCode(), is(equalTo("401")));
        else assertTrue(myPage.findErrorCode());
        logger.error("Test-case №19 failed!");
    }
}