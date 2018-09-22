# ActionBarEx

**高拓展高自定义性ActionBar，完美替代Android系统默认**

[Demo下载](https://github.com/goweii/ActionBarEx/raw/master/app/release/app-release.apk)

## 项目简介

整个ActionBar分为3层：**BackgroundLayer** / **ActionBarLayer** / **ForegroundLayer**

根层级 | 子层级 | 说明 | 备注
---- | ---- | ---- | ----
**BackgroundLayer** |  | 背景层 | 可自定义布局
**ActionBarLayer** |  | 主体层 | 垂直线性布局
 | StatusBar | 状态栏 | 
 | TitleBar | 导航栏 | 可自定义布局
 | BottomLine | 分割线 | 
**ForegroundLayer** |  | 前景层 | 可自定义布局



## 截图

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_demo.gif?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_1.png?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_2.png?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_3.png?raw=true)



## 集成方式

### 添加依赖

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.goweii:ActionBarEx:v2.2.0'
	}

### 布局文件引用

```xml
<per.goweii.actionbarex.SimpleActionBar
    android:id="@+id/simple_action_bar_3"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    android:background="#ccffffff"
    app:ab_bottom_line_color="@color/line"
    app:ab_bottom_line_height="0dp"
    app:ab_foreground_layer_layout="@layout/loading_bar"
    app:ab_status_bar_color="@color/transparent"
    app:ab_status_bar_mode="dark"
    app:ab_title_bar_height="48dp"
    app:simple_left_text="返回"
    app:simple_left_text_padding_left="0dp"
    app:simple_left_text_padding_right="0dp"
    app:simple_left_image_color="@color/black"
    app:simple_left_image_res="@mipmap/back"
    app:simple_right_image_color="@color/black"
    app:simple_right_image_res="@mipmap/search"
    app:simple_right_text="确定"
    app:simple_right_text_padding_left="0dp"
    app:simple_right_text_padding_right="0dp"
    app:simple_title_text="这个标题有点长我试试长标题什么样子"
    app:simple_title_text_max_width="180dp" />
```

## 属性介绍

### ActionBarEx属性介绍

    <!--是否开启自动沉浸状态栏，默认为true-->
    <attr name="ab_auto_immersion" format="boolean" />
    <!--背景层布局，优先级高于背景层为图片-->
    <attr name="ab_background_layer_layout" format="reference" />
    <!--背景层为图片-->
    <attr name="ab_background_layer_image_res" format="reference" />
    <!--是否显示状态栏，默认为true-->
    <attr name="ab_status_bar_visible" format="boolean" />
    <!--状态栏颜色，默认透明-->
    <attr name="ab_status_bar_color" format="color|reference" />
    <!--状态栏图标颜色模式，默认light-->
    <attr name="ab_status_bar_mode" format="enum">
        <enum name="light" value="0" />
        <enum name="dark" value="1" />
    </attr>
    <!--主体层布局-->
    <attr name="ab_title_bar_layout" format="reference" />
    <!--主体层高度，默认为48dp-->
    <attr name="ab_title_bar_height" format="dimension|reference" />
    <!--底部分割线高度，默认为0dp-->
    <attr name="ab_bottom_line_height" format="dimension|reference" />
    <!--底部分割线颜色，默认透明-->
    <attr name="ab_bottom_line_color" format="color|reference" />
    <!--前景层布局-->
    <attr name="ab_foreground_layer_layout" format="reference" />

### SimpleActionBar属性介绍

    <attr name="simple_left_text" format="string|dimension" />
    <attr name="simple_left_text_size" format="dimension|reference" />
    <attr name="simple_left_text_color" format="color|reference" />
    <attr name="simple_left_text_padding_left" format="dimension|reference" />
    <attr name="simple_left_text_padding_right" format="dimension|reference" />
    <attr name="simple_left_image_res" format="reference" />
    <attr name="simple_left_image_color" format="color|reference" />
    <attr name="simple_left_image_padding" format="dimension|reference" />
    <attr name="simple_right_text" format="string|dimension" />
    <attr name="simple_right_text_size" format="dimension|reference" />
    <attr name="simple_right_text_color" format="color|reference" />
    <attr name="simple_right_text_padding_left" format="dimension|reference" />
    <attr name="simple_right_text_padding_right" format="dimension|reference" />
    <attr name="simple_right_image_res" format="reference" />
    <attr name="simple_right_image_color" format="color|reference" />
    <attr name="simple_right_image_padding" format="dimension|reference" />
    <attr name="simple_title_text" format="string|reference" />
    <attr name="simple_title_text_size" format="dimension|reference" />
    <attr name="simple_title_text_color" format="color|reference" />
    <attr name="simple_title_text_max_width" format="dimension|reference" />

### SearchActionBar属性介绍

    <attr name="search_left_text" format="string|dimension" />
    <attr name="search_left_text_size" format="dimension|reference" />
    <attr name="search_left_text_color" format="color|reference" />
    <attr name="search_left_text_padding_left" format="dimension|reference" />
    <attr name="search_left_text_padding_right" format="dimension|reference" />
    <attr name="search_left_image_res" format="reference" />
    <attr name="search_left_image_color" format="color|reference" />
    <attr name="search_left_image_padding" format="dimension|reference" />
    <attr name="search_right_text" format="string|dimension" />
    <attr name="search_right_text_size" format="dimension|reference" />
    <attr name="search_right_text_color" format="color|reference" />
    <attr name="search_right_text_padding_left" format="dimension|reference" />
    <attr name="search_right_text_padding_right" format="dimension|reference" />
    <attr name="search_right_image_res" format="reference" />
    <attr name="search_right_image_color" format="color|reference" />
    <attr name="search_right_image_padding" format="dimension|reference" />
    <attr name="search_title_hint_text" format="string|reference" />
    <attr name="search_title_text_size" format="dimension|reference" />
    <attr name="search_title_text_color" format="color|reference" />
    <attr name="search_title_hint_color" format="color|reference" />