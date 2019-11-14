import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumFirstSteps extends BaseTest {

    @Test
    public static void main() {
        ThreadGroup tg = new ThreadGroup ("subgroup 1");
        Thread t1 = new Thread (tg, "thread 1");
        Thread t2 = new Thread (tg, "thread 2");
        Thread t3 = new Thread (tg, "thread 3");
        tg = new ThreadGroup ("subgroup 2");
        Thread t4 = new Thread (tg, "my thread");
        tg = Thread.currentThread ().getThreadGroup ();
        int agc = tg.activeGroupCount ();
        System.out.println ("Active thread groups in " + tg.getName () +
                " thread group: " + agc);
        tg.list ();

    }
}
