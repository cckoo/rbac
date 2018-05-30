package cn.cckoo.yu.steps;

import cn.cckoo.yu.Application;
import cn.cckoo.yu.driver.Driver;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@ContextConfiguration(classes = {Application.class})
public class Hooks {

    @Autowired
    Driver driver;

    @After
    public void closeUiDriver() {
        driver.close();
    }

}