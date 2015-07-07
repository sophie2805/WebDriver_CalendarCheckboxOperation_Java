### WebDriver_CalendarCheckboxOperation_Java
- Calendar is an iFrame on the 12306 page, first locate this iFrame, then WebDriver.switchTo().frame(calFrame). Switch back to the original window after operation: WebDriver.switchTo().defaultContent()
- Checkbox is very common on the web page. It's also quite simple to operate: find it then click.
