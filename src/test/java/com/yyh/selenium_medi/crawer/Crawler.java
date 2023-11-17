package com.yyh.selenium_medi.crawer;

import com.yyh.selenium_medi.entity.InputEntity;
import com.yyh.selenium_medi.entity.OutputEntity;
import com.yyh.selenium_medi.service.InputEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yyh.selenium_medi.service.OutputEntityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class Crawler {

    @Autowired
    private InputEntityService inputEntityService;
    @Autowired
    private OutputEntityService outputEntityService;

    private WebDriver driver;

    /**
     * Constructor
     * 初始化driver
     */
    public Crawler() {
        System.setProperty("webdriver.chrome.driver", "D:\\yyhfile\\IDM Download\\files\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //设置用户代理为一个pc端的浏览器，您可以根据您的需求修改这个值
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0");
        options.addArguments("accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        options.addArguments("accept-encoding=gzip, deflate, br");
        options.addArguments("accept-language=zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        options.addArguments("sec-fetch-dest=document");
        options.addArguments("sec-fetch-mode=navigate");
        options.addArguments("sec-fetch-site=none");
        options.addArguments("sec-fetch-user=?1");
        options.addArguments("upgrade-insecure-requests=1");
        options.addArguments("sec-ch-ua-mobile=?0");
        options.addArguments("sec-ch-ua-platform=\"Windows\"");
        // 创建一个WebDriver对象，用来控制chrome浏览器
        driver = new ChromeDriver(options);
    }

    public WebDriver getChromeDriver() {
        return driver;
    }

    /**
     * 使用cookie打开页面
     *
     * @param driver
     */
    public void openSearchPage(WebDriver driver) {
        driver.get("https://www.yixieyun.com/ZbDataAllFitPrice/index.html?CodeName=%E4%BB%B7%E6%A0%BC%E6%9F%A5%E8%AF%A2");
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(new Cookie("ASP.NET_SessionId", "v2jktb0ja0na2fywborptve5", "www.yixieyun.com", "/", null));
        driver.manage().addCookie(new Cookie("USER_NAME", "UserName=%e5%b9%bf%e5%b7%9e%e5%b8%82%e5%a6%87%e5%a5%b3%e5%84%bf%e7%ab%a5%e5%8c%bb%e7%96%97%e4%b8%ad%e5%bf%83", "www.yixieyun.com", "/", null));
        driver.get("https://www.yixieyun.com/ZbDataAllFitPrice/index.html?CodeName=%E4%BB%B7%E6%A0%BC%E6%9F%A5%E8%AF%A2");
    }

    public List<InputEntity> getInputEntities() {
        return inputEntityService.list();
    }

    public List<OutputEntity> getFromWeb(WebDriver driver, String searchHandler, InputEntity inputEntity, boolean useModel, int limit) throws InterruptedException {
        driver.manage().window().maximize();

        driver.switchTo().window(searchHandler);
        //设备名
        driver.findElement(By.id("ContentPlaceHolder1_txtQuery")).sendKeys(inputEntity.getDeviceName());
        //时间
        driver.findElement(By.id("ContentPlaceHolder1_ddlStartYear")).sendKeys("2002");
        driver.findElement(By.id("ContentPlaceHolder1_ddlEndYear")).sendKeys("2020");
        //单价范围设置
//        driver.findElement(By.id("ContentPlaceHolder1_txtPrciceMin")).sendKeys(inputEntity.getPrice().toString());
        driver.findElement(By.id("ContentPlaceHolder1_txtPrciceMin")).sendKeys("0");
        driver.findElement(By.id("ContentPlaceHolder1_txtPrciceMax")).sendKeys("99999999999");
        //型号规格
        if (inputEntity.getModel() != null && useModel) {
            driver.findElement(By.id("ContentPlaceHolder1_txtspec")).sendKeys(inputEntity.getModel());
        }

        //智能搜索
//        driver.findElement(By.id("btnSearch")).click();
//        Thread.sleep(1000);
        //精确搜索
        driver.findElement(By.id("btnDeepSearch")).click();
        Thread.sleep(1000);
        //单价降序
        //driver.findElement(By.id("ContentPlaceHolder1_sacSort_btnPrice")).click();
        //Thread.sleep(2000);

        //获取表格
        WebElement table = driver.findElement(By.id("ContentPlaceHolder1_GridViewMain"));
        // 获取表格中的所有行
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows.remove(0);

        // 创建一个新的列表来存储文本
        ArrayList<OutputEntity> outputEntities = new ArrayList<>();

        ArrayList<OutputEntity> outputCathe = new ArrayList<>();
        // 遍历每一行，获取每个单元格的文本，并添加到列表中

        List<WebElement> limitRows = null;
        if (rows.size() > limit) {
            limitRows = rows.subList(0, crawlerLimit);
        } else {
            limitRows = rows;
        }

        for (WebElement row : limitRows) {
            OutputEntity outputEntity = new OutputEntity();

            List<WebElement> tds = row.findElements(By.tagName("td"));
            String name = tds.get(3).findElement(By.tagName("span")).getText();
            String model = tds.get(4).findElement(By.tagName("span")).getText();
            String manufacturer = tds.get(6).findElement(By.tagName("span")).getText();
            String suppliers = tds.get(7).findElement(By.tagName("span")).getText();
            String strPrice = tds.get(8).findElement(By.tagName("span")).getText();
            Integer price = -1;

            String buyer = null;
            String date = null;


            try {
                double span = Double.parseDouble(strPrice);
                price = (int) span;
            } catch (Exception e) {

            }
            WebElement website = tds.get(11).findElement(By.tagName("span"));
            WebElement url = null;
            String websiteTitle = "";
            String websiteUrl = null;

            String windowHandle1 = null;

            String windowHandle2 = null;
            List<WebElement> as = website.findElements(By.tagName("a"));
            if (as.size() == 1) {
                websiteTitle = as.get(0).getText();
            } else if (as.size() == 0) {
                websiteTitle = website.getText();
            } else if (as.size() == 2) {
                websiteTitle = as.get(1).getText();
            }

            if (as.size() == 1) {
                url = as.get(0);
            } else if (as.size() == 2) {
                url = as.get(1);
            }
            if (url != null) {
                try {
                    url.click();
                    Thread.sleep(300);
                    windowHandle1 = driver.getWindowHandle();

                    //跳转到下一个页面
                    Set<String> windowHandles1 = driver.getWindowHandles();
                    for (String handle : windowHandles1) {
                        if (!windowHandles1.equals(handle)) {
                            driver.switchTo().window(handle);
                        }
                    }

                    driver.findElement(By.id("ContentPlaceHolder1_lblZBPtitle")).click();
                    Thread.sleep(300);
                    windowHandle2 = driver.getWindowHandle();

                    //跳转到下一个页面
                    Set<String> windowHandles2 = driver.getWindowHandles();
                    for (String handle : windowHandles2) {
                        if (!windowHandles2.equals(handle)) {
                            driver.switchTo().window(handle);
                        }
                    }
                    date = driver.findElement(By.id("ContentPlaceHolder1_ProDate")).getText();
                    buyer = driver.findElement(By.id("ContentPlaceHolder1_InformationTitle")).getText();


                    websiteUrl = driver.findElement(By.id("ContentPlaceHolder1_hplSourceURL")).getAttribute("href");

                } catch (Exception e) {

                } finally {
                    driver.switchTo().window(searchHandler);
                }
            }
            outputEntity.setInputId(inputEntity.getId());
            outputEntity.setDeviceName(name);
            outputEntity.setModel(model);
            outputEntity.setManufacturer(manufacturer);
            outputEntity.setSuppliers(suppliers);
            outputEntity.setPrice(price);
            outputEntity.setQuantity(0);
            outputEntity.setSourceWebsite(websiteTitle);
            outputEntity.setSourceUrl(websiteUrl);
            outputEntity.setBuyer(buyer);
            outputEntity.setDate(date);
            outputEntity.setUsedModel(useModel);
            log.info(outputEntity.toString());
            boolean add = outputEntities.add(outputEntity);
            boolean cathe = outputCathe.add(outputEntity);

            //关闭页面
            Set<String> windowHandles1 = driver.getWindowHandles();
            for (String handle : windowHandles1) {
                if (!searchHandler.equals(handle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
                driver.switchTo().window(searchHandler);
            }

        }
        //写入数据
        outputEntityService.saveBatch(outputCathe);
        return outputEntities;
    }

    /**
     * 每个设备爬多少条
     */
    private final int crawlerLimit = 5;

    @Test
    void crawlerWithModel() throws InterruptedException {
        WebDriver chromeDriver = getChromeDriver();
        List<InputEntity> inputEntities = getInputEntities();
        for (int i = 162; i < 164; i++) {
            //每循环一次重新打开页面，刷新输入框
            openSearchPage(chromeDriver);
            //抓取
            List<OutputEntity> fromWeb = getFromWeb(chromeDriver, chromeDriver.getWindowHandle(), inputEntities.get(i), true, crawlerLimit);
        }
    }

    @Test
    void crawlerWithoutModel() throws InterruptedException {
        WebDriver chromeDriver = getChromeDriver();
        List<InputEntity> inputEntities = getInputEntities();
        for (int i = 162; i < 164; i++) {
            //每循环一次重新打开页面，刷新输入框
            openSearchPage(chromeDriver);
            //抓取
            List<OutputEntity> fromWeb = getFromWeb(chromeDriver, chromeDriver.getWindowHandle(), inputEntities.get(i), false, crawlerLimit);
        }
    }
}
