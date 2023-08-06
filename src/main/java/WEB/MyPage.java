package WEB;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyPage extends AbstractPage{
    private final String urlMyPostsPage = "https://test-stand.gb.ru/";
    private final String urlNextPage = "https://test-stand.gb.ru/?page=2";
    private final String urlPrevPage = "https://test-stand.gb.ru/?page=1";
    private final String urlDESC = "https://test-stand.gb.ru/?sort=createdAt&order=DESC";
    private final String urlASC = "https://test-stand.gb.ru/?sort=createdAt&order=ASC";
    private final String urlNotMyPostsPage = "https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC";
    public MyPage(WebDriver driver) {
        super(driver);
    }
    public String getUrlMyPostsPage() {
        return urlMyPostsPage;
    }
    public String getUrlNextPage() {
        return urlNextPage;
    }
    public String getUrlPrevPage() {
        return urlPrevPage;
    }
    public String getUrlDESC() { return urlDESC; }
    public String getUrlASC() {
        return urlASC;
    }
    public String getUrlNotMyPostsPage() {
        return urlNotMyPostsPage;
    }
    // LoginPage:
    @FindBy(xpath = "//li[3]/a")
    private WebElement userNameLink;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputUsername;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@form='login']")
    private WebElement btnLogin;
    @FindBy(xpath = "//li[3]/span[2]")
    private WebElement btnLogout;
    @FindBy(xpath = "//h2")
    private WebElement errorCode;
    @FindBy(xpath = "//p")
    private WebElement errorMessage;
    // PostsPage:
    @FindBy(css = ".post.svelte-127jg4t")
    private List<WebElement> allPosts;
    @FindBy(xpath = "//img")
    private WebElement testImage;
    @FindBy(xpath = "//a[1]/h2")
    private WebElement testTitleFirstPostFirstPage;
    @FindBy(xpath = "//a[3]/h2")
    private WebElement testTitleFirstPostLastPage;
    @FindBy(xpath = "//a/div")
    private WebElement testDescription;
    @FindBy(xpath = "//p")
    private WebElement noPostsMessage;
    @FindBy(xpath = "//div[2]/div/a[2]")
    private WebElement btnNextPage;
    @FindBy(css = ".disabled:nth-child(2)")
    private WebElement btnNextPageDisabled;
    @FindBy(xpath = "//div[2]/div/a")
    private WebElement btnPrevPage;
    @FindBy(css = ".disabled")
    private WebElement btnPageDisabled;
    @FindBy(xpath = "//i")
    private WebElement btnOrderASC;
    @FindBy(xpath = "//i[2]")
    private WebElement btnOrderDESC;
    @FindBy(xpath = "//*[@id=\"SMUI-form-field-1\"]")
    private WebElement btnNotMyPosts;
    public int countPosts() {
        return allPosts.size();
    }
    public boolean checkBtnPage() {
        try {
            return btnPageDisabled.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean checkBtnNextPage() {
        try {
            return btnNextPageDisabled.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public MyPage goToNextPage() {
        btnNextPage.click();
        return this;
    }
    public MyPage goToPrevPage() {
        btnPrevPage.click();
        return this;
    }
    public MyPage getOrderDESC() {
        btnOrderDESC.click();
        return this;
    }
    public MyPage getOrderASC() {
        btnOrderASC.click();
        return this;
    }
    public String getTitleFirstPostFirstPage() {
        return testTitleFirstPostFirstPage.getText().trim();
    }
    public String getTitleFirstPostLastPage() {
        return testTitleFirstPostLastPage.getText().trim();
    }
    public String checkImageFirstPost() {
        return testImage.getAttribute("src").trim();
    }
    public String getDescriptionFirstPost() {
       return testDescription.getText().trim();
    }
    public MyPage goToNotMyPosts() {
        btnNotMyPosts.click();
        return this;
    }
    public String getNoPostsMessageText() {
        return noPostsMessage.getText().trim();
    }
    public MyPage login(String username, String password){
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        btnLogin.click();
        return this;
    }
    public MyPage logout(){
        userNameLink.click();
        btnLogout.click();
        return this;
    }
    public boolean findErrorCode() {
        try {
            return errorCode.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getHeaderText() {
        return userNameLink.getText().trim();
    }
    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }
    public String getErrorCode() {
        return errorCode.getText().trim();
    }
}