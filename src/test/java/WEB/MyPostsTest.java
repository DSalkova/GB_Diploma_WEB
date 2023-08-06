package WEB;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyPostsTest extends AbstractTest{
    Logger logger = LoggerFactory.getLogger(MyPostsTest.class);
    @BeforeEach
    void goToMyPosts() {
        getWebDriver().get(getPostsUrl());
    }
    @Test
    @DisplayName("Test-case №1")
    @Description("Check user2 (without posts) doesn't have any posts")
    public void myPostsTest1() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername2(), getPassword2());
        assertThat(myPage.getNoPostsMessageText(), is(equalTo("No items for your filter")));
    }
    @Test
    @DisplayName("Test-case №2")
    @Description("Check default sort at the 1st page")
    public void myPostsTest2() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        assertTrue(myPage.checkBtnPage());
        assertThat(myPage.getTitleFirstPostFirstPage(), is(equalTo("Post11 - The Last")));
    }
    @Test
    @DisplayName("Test-case №3")
    @Description("Check next page exist")
    public void myPostsTest3() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        assertTrue(!myPage.checkBtnNextPage());
    }
    @Test
    @DisplayName("Test-case №4")
    @Description("Check going from the 1st page to the next page")
    public void myPostsTest4() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage
                .login(getUsername1(), getPassword1())
                .goToNextPage();
        getWait().until(ExpectedConditions.urlToBe(myPage.getUrlNextPage()));
        assertThat(getWebDriver().getCurrentUrl(), is(equalTo(myPage.getUrlNextPage())));
    }
    @Test
    @DisplayName("Test-case №5")
    @Description("Check going from the 1st page to the next page and back to the 1st page")
    public void myPostsTest5() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        myPage.goToNextPage();
        getWait().until(ExpectedConditions.urlToBe(myPage.getUrlNextPage()));
        myPage.goToPrevPage();
        getWait().until(ExpectedConditions.urlToBe(myPage.getUrlPrevPage()));
        assertThat(getWebDriver().getCurrentUrl(), is(equalTo(myPage.getUrlPrevPage())));
    }
    @Test
    @DisplayName("Test-case №6")
    @Description("Check the last page")
    public void myPostsTest6() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage
                .login(getUsername1(), getPassword1())
                .goToNextPage();
        getWait().until(ExpectedConditions.urlToBe(myPage.getUrlNextPage()));
        myPage.goToNextPage();
        getWait().until(ExpectedConditions.urlToBe(myPage.getUrlNextPage()));
        assertTrue(myPage.checkBtnPage());
        assertThat(myPage.getTitleFirstPostLastPage(), is(equalTo("Post1 - The First")));
    }
    @Test
    @DisplayName("Test-case №7")
    @Description("Check DESC sort (new - old)")
    public void myPostsTest7() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage
                .login(getUsername1(), getPassword1())
                .getOrderDESC();
        assertThat(getWebDriver().getCurrentUrl(), is(equalTo(myPage.getUrlDESC())));
        assertThat(myPage.getTitleFirstPostFirstPage(), is(equalTo("Post11 - The Last")));
    }
    @Test
    @DisplayName("Test-case №8")
    @Description("Check ASC sort (old - new)")
    public void myPostsTest8() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage
                .login(getUsername1(), getPassword1())
                .getOrderDESC()
                .getOrderASC();
        assertThat(getWebDriver().getCurrentUrl(), is(equalTo(myPage.getUrlASC())));
        assertThat(myPage.getTitleFirstPostFirstPage(), is(equalTo("Post1 - The First")));
    }
    @Test
    @DisplayName("Test-case №9")
    @Description("Check not my posts page")
    public void myPostsTest9() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage
                .login(getUsername1(), getPassword1())
                .goToNotMyPosts();
        assertThat(getWebDriver().getCurrentUrl(), is(equalTo(myPage.getUrlNotMyPostsPage())));
    }
    @Test
    @DisplayName("Test-case №10")
    @Description("Check the first post has title, image and description")
    public void myPostsTest10() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        assertTrue(!myPage.getTitleFirstPostFirstPage().isEmpty());
        assertThat(myPage.checkImageFirstPost(), is(not("https://test-stand.gb.ru/placeholder/800x600.gif")));
        assertTrue(!myPage.getDescriptionFirstPost().isEmpty());
    }
    @Test
    @DisplayName("Test-case №11")
    @Description("Check at the 1st page posts count = 10")
    public void myPostsTest11() {
        MyPage myPage = new MyPage(getWebDriver());
        myPage.login(getUsername1(), getPassword1());
        assertThat(myPage.countPosts(), is(equalTo(10)));
        logger.error("Test-case №11 failed! Expected 10 but was 4");
    }
}