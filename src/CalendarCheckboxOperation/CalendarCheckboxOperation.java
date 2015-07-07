package CalendarCheckboxOperation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class CalendarCheckboxOperation {
    private WebDriver dr;
    private String url;

    public CalendarCheckboxOperation(){
        //System.setProperty("webdriver.chrome.driver","/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
        this.dr = new FirefoxDriver();//ChromeDriver();
        this.url = "https://kyfw.12306.cn/otn/lcxxcx/init";
        this.dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void  action(){
        //打开12306查票页面
        this.dr.get(this.url);
        //选择出发城市
        this.dr.findElement(By.id("fromStationText")).click();
        this.dr.findElement(By.cssSelector("ul.popcitylist>li.ac_even.openLi.ac_odd[title='杭州']")).click();
        //选择目的地城市
        this.dr.findElement(By.id("toStationText")).click();
        this.dr.findElement(By.cssSelector("ul.popcitylist>li.ac_even.openLi.ac_odd[title='厦门']")).click();
        this.dr.findElement(By.id("train_start_date")).click();
        //选择日期，日期是一个iFrame，先找到这个iFrame，然后切换窗口到iFrame，再选择日期并点击
        WebElement calFrame = dr.findElement(By.xpath("/html/body/div[11]/iframe"));
        this.dr.switchTo().frame(calFrame);
        this.dr.findElement(By.xpath("/html/body/div[@class='WdateDiv WdateDiv2']/div[3]/table/tbody/tr/td[2]/table/tbody/tr[2]/td[last()]")).click();
        this.dr.switchTo().defaultContent();
        this.dr.findElement(By.id("_a_search_btn1")).click();
        //车次类型先全选上
        this.dr.findElement(By.id("span_station_train_code")).click();
        //取消其他和K字头列车
        this.dr.findElement(By.xpath("//div[@id='sear-sel-bd']/div[1]/div[2]/ul/li/input[@value='QT']")).click();
        this.dr.findElement(By.xpath("//div[@id='sear-sel-bd']/div[1]/div[2]/ul/li/input[@value='K']")).click();
        //将所有checkbox的Label和是否选中打印出来
        List<WebElement> ccList = this.dr.findElements(By.xpath("//div[@id='sear-sel-bd']/div[1]/div[2]/ul/li"));
        for(int i = 0; i < ccList.size(); i ++){
            System.out.printf("%-20s   isSelected:%-20s\n",ccList.get(i).findElement(By.tagName("label")).getText(),
                    ccList.get(i).findElement(By.tagName("input")).isSelected());
        }
        this.dr.quit();
    }

    public static void main(String[] args) {
        CalendarCheckboxOperation cc = new CalendarCheckboxOperation();
        cc.action();
    }
}
