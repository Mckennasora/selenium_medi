package com.yyh.selenium_medi.testSelenium;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class TestSelenium {

    @Test
    void openChrome() throws InterruptedException {
//        System.setProperty("webdriver.edge.driver", "D:\\yyhfile\\IDM Download\\files\\edgedriver_win64\\msedgedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\yyhfile\\IDM Download\\files\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        设置用户代理为一个pc端的浏览器，您可以根据您的需求修改这个值
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
//        options.addArguments("ASP.NET_SessionId=v2jktb0ja0na2fywborptve5");
//        options.addArguments("USER_NAME=%e5%b9%bf%e5%b7%9e%e5%b8%82%e5%a6%87%e5%a5%b3%e5%84%bf%e7%ab%a5%e5%8c%bb%e7%96%97%e4%b8%ad%e5%bf%83");
// 创建浏览器对象

        // 创建一个WebDriver对象，用来控制chrome浏览器
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.yixieyun.com/ZbDataAllFitPrice/index.html?CodeName=%E4%BB%B7%E6%A0%BC%E6%9F%A5%E8%AF%A2");
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(new Cookie("ASP.NET_SessionId", "v2jktb0ja0na2fywborptve5", "www.yixieyun.com", "/", null));
        driver.manage().addCookie(new Cookie("USER_NAME", "UserName=%e5%b9%bf%e5%b7%9e%e5%b8%82%e5%a6%87%e5%a5%b3%e5%84%bf%e7%ab%a5%e5%8c%bb%e7%96%97%e4%b8%ad%e5%bf%83", "www.yixieyun.com", "/", null));
        driver.get("https://www.yixieyun.com/ZbDataAllFitPrice/index.html?CodeName=%E4%BB%B7%E6%A0%BC%E6%9F%A5%E8%AF%A2");
//        driver.get("https://www.baidu.com");
//        log.info("================================================");
//        WebElement t1 = driver.findElement(By.className("t1"));
//        log.info(t1.getText());
//        log.info("================================================");

        driver.findElement(By.id("ContentPlaceHolder1_txtQuery")).sendKeys("功能牵引网架及配件");
        //时间
        driver.findElement(By.id("ContentPlaceHolder1_ddlStartYear")).sendKeys("2002");
        driver.findElement(By.id("ContentPlaceHolder1_ddlEndYear")).sendKeys("2020");
        //单价范围设置
        driver.findElement(By.id("ContentPlaceHolder1_txtPrciceMin")).sendKeys("20");
        driver.findElement(By.id("ContentPlaceHolder1_txtPrciceMax")).sendKeys("9999999");
        //智能搜索
        driver.findElement(By.id("btnSearch")).click();
        Thread.sleep(2000);
        //精确搜索
        //单价降序
        //driver.findElement(By.id("ContentPlaceHolder1_sacSort_btnPrice")).click();
        Thread.sleep(2000);

        //获取表格
        WebElement table = driver.findElement(By.id("ContentPlaceHolder1_GridViewMain"));
        // 获取表格中的所有行
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows.remove(0);

        // 创建一个新的列表来存储文本
        ArrayList<String> names = new ArrayList<>();

        // 遍历每一行，获取每个单元格的文本，并添加到列表中
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            String name = tds.get(3).findElement(By.tagName("span")).getText();
            boolean add = names.add(name);
        }
        System.out.println("names = " + names);

    }

    @Test
    void openEdge() {
        System.setProperty("webdriver.edge.driver", "D:\\yyhfile\\IDM Download\\files\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions option = new EdgeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9527");
        option.addArguments("--remote-allow-origins=*");
        EdgeDriver driver = new EdgeDriver(option);
//        WebElement t1 = driver.findElement(By.className("t1"));
//        String text = t1.getText();
        log.info("================================================");
        log.info(driver.getTitle());
        log.info("================================================");
    }
}

