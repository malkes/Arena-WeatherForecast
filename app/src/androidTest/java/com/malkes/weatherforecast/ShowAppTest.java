package com.malkes.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.Until.findObject;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class ShowAppTest {

    private static final String LAUNCHER_PACKAGE = "com.malkes.weatherforecast";

    private static final int LAUNCH_TIMEOUT = 10000;

    private UiDevice mDevice;

    @Test
    public void checkPreconditions() {
        assertThat(mDevice, notNullValue());
    }

    @Before
    public void startMainActivityFromHomeScreen() throws InterruptedException, UiObjectNotFoundException {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(LAUNCHER_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(LAUNCHER_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        Assert.assertNotNull(testView("spCity"));
        Assert.assertNotNull(testView("ivBackground"));
        Assert.assertNotNull(testView("tvTemperature"));
        Assert.assertNotNull(testView("ivIcon"));
        Assert.assertNotNull(testView("tvSummary"));
        UiObject2 recycler = testView("recycler");

        Assert.assertNotNull(recycler);
        waiting(10000);
        Assert.assertNotNull(recycler.getChildren());
        Assert.assertTrue(recycler.getChildren().size() > 0);
        for(UiObject2 item : recycler.getChildren()){
            Assert.assertNotNull(item);
        }
    }

    private UiObject2 testView(String viewName) {
        return  mDevice.findObject(By.res(LAUNCHER_PACKAGE, viewName));
    }


    private String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    public void waiting(long t) throws InterruptedException{
        try {
            synchronized (mDevice){
                mDevice.wait(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
