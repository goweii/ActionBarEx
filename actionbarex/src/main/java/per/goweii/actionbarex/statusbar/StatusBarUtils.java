package per.goweii.actionbarex.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StatusBarUtils {

    public static boolean supportTransparentStatusBar() {
        return OSUtils.isMiui()
                || OSUtils.isFlyme()
                || (OSUtils.isOppo() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 设置状态栏透明
     */
    public static void transparentStatusBar(Activity activity) {
        transparentStatusBar(activity.getWindow());
    }

    /**
     * 设置状态栏透明
     */
    public static void transparentStatusBar(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            transparentStatusBar(activity);
        }
    }

    /**
     * 设置状态栏透明
     */
    public static void transparentStatusBar(Window window) {
        if (OSUtils.isMiui() || OSUtils.isFlyme()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transparentStatusBarAbove21(window);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        } else if ((OSUtils.isOppo() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)) {
            transparentStatusBarAbove21(window);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            transparentStatusBarAbove21(window);
        }
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setStatusBarIconMode(Fragment fragment, boolean isDarkMode) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            setStatusBarIconMode(activity, isDarkMode);
        }
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setStatusBarIconMode(Activity activity, boolean isDarkMode) {
        setStatusBarIconMode(activity.getWindow(), isDarkMode);
    }

    /**
     * 设置状态栏图标主题
     */
    public static void setStatusBarIconMode(Window window, boolean isDarkMode) {
        if (isDarkMode) {
            setDarkMode(window);
        } else {
            setLightMode(window);
        }
    }

    /**
     * 设置状态栏图标白色主题
     *
     * @param window
     */
    private static void setLightMode(Window window) {
        if (OSUtils.isMiui()) {
            setMIUIStatusBarDarkMode(window, false);
        } else if (OSUtils.isFlyme()) {
            setFlymeStatusBarDarkMode(window, false);
        } else if (OSUtils.isOppo()) {
            setOppoStatusBarDarkMode(window, false);
        } else {
            setStatusBarDarkMode(window, false);
        }
    }

    /**
     * 设置状态栏图片黑色主题
     *
     * @param window
     */
    private static void setDarkMode(Window window) {
        if (OSUtils.isMiui()) {
            setMIUIStatusBarDarkMode(window, true);
        } else if (OSUtils.isFlyme()) {
            setFlymeStatusBarDarkMode(window, true);
        } else if (OSUtils.isOppo()) {
            setOppoStatusBarDarkMode(window, true);
        } else {
            setStatusBarDarkMode(window, true);
        }
    }

    @TargetApi(21)
    private static void transparentStatusBarAbove21(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    private static void setStatusBarDarkMode(Window window, boolean darkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkMode) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        }
    }

    private static void setMIUIStatusBarDarkMode(Window window, boolean darkMode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Class<? extends Window> clazz = window.getClass();
            try {
                Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                int darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, darkMode ? darkModeFlag : 0, darkModeFlag);
            } catch (Exception e) {
            }
        }
        setStatusBarDarkMode(window, darkMode);
    }

    private static void setFlymeStatusBarDarkMode(Window window, boolean darkMode) {
        FlymeStatusBarUtils.setStatusBarDarkIcon(window, darkMode);
    }

    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    private static void setOppoStatusBarDarkMode(Window window, boolean darkMode) {
        int vis = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkMode) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (darkMode) {
                vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }

    /**
     * 设置状态栏颜色
     *
     * @param window
     * @param color
     */
    public static void setStatusBarColor(Window window, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
