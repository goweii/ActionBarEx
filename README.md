# ActionBarEx

**高拓展高自定义性ActionBar，完美替代Android系统默认。**

[GitHub主页](https://github.com/goweii/ActionBarEx)

[Demo下载](https://github.com/goweii/ActionBarEx/raw/master/app/release/app-release.apk)



## 项目简介

## 为什么要写一个ActionBar？官方的和网上开源的那么多。

第一，先说说官方的。官方的在不同系统上的风格并不统一，自定义项太少，不能满足开发需求，所以才涌现了这么多的开源项目。

第二，网上开源的。网上的已有的，我发现的，用过的大部分都是直接封装一个固定样式的布局，或控制控件的显示隐藏，或动态添加需要的控件。但也仅限于自定义左右图标的和中间标题的个数和样式。如果要自定义ActionBar的背景呢？要让背景显示一个图片呢？要让图片延伸到状态栏下面呢？要让背景实现半透明高斯模糊效果呢？要限定按钮图标的大小呢？要自定义中间标题的背景呢？要显示一个搜索框呢？要让搜索框带有清空和搜索图标呢？要自定义搜索框样式呢？要在网络请求时显示一个Loading效果呢？等等这些需求都是无法满足的。而且我相信这样的需求不在少数。

**也可能有可以实现的框架，只是我还没发现。**



## GitHub上Star那么多的框架都无法实现，这个就可以？

要说这个绝对可以实现，肯定是不可能的，但我相信可以实现市面上99%以上的需求。

**哪里来的这么大的自信？还是先看看整个项目的层级结构吧。**

整个ActionBarEx分为3个基本层级，具体负责功能和命名约定如下：

| 根层级              | 子层级     | 说明   | 备注         |
| ------------------- | ---------- | ------ | :----------- |
| **BackgroundLayer** |            | 背景层 | 可自定义布局 |
| **ActionBarLayer**  |            | 主体层 | 垂直线性布局 |
|                     | StatusBar  | 状态栏 |              |
|                     | TitleBar   | 标题栏 | 可自定义布局 |
|                     | BottomLine | 分割线 |              |
| **ForegroundLayer** |            | 前景层 | 可自定义布局 |

- **BackgroundLayer** 背景层，负责整个ActionBar背景的绘制，如实现半透明背景或图片背景或高斯模糊背景
- **ActionBarLayer** 主体层，是以下3个不分的统称：
  - StatusBar 系统状态栏占位，可控制单独显示一个背景色，可实现状态栏图片暗色和亮色模式切换
  - TitleBar 导航栏占位，用于显示标题图标按钮等，支持自定义布局样式，以实现复杂需求
  - BottomLine 底部分割线，可控制分割线颜色和高度
- **ForegroundLayer** 前景层，用于显示一些特殊的状态，如加载状态LoadingBar，支持自定义布局



## 别整这么多虚的，先看看疗效

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_demo.gif?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_1.png?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_2.png?raw=true)

![](https://github.com/goweii/ActionBarEx/blob/master/picture/action_bar_3.png?raw=true)



## 呦吼，看着还不错呀，快告诉我怎么用？

别急呀，我再说说实现细节。

好好，别动手，有话好好说，你把这个规则长方体放下。。。

要说使用方式，还得再明确几个类

- ActionBarEx 这个就是上面说的封装好的分3个层级的基类
- SimpleActionBar 这个继承自ActionBarEx，可在导航栏显示【图标-文字-标题文字-文字-图标】这样一个布局
- SearchActionBar 这个也继承自ActionBarEx，可在导航栏显示【图标-文字-搜索框-文字-图标】这样一个布局



# 集成方式

## 添加依赖

1. 在Project的**build.gradle**添加仓库地址

```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

2. 在Model:app的**build.gradle**添加框架依赖

最新版本是多少，看下[Releases](https://github.com/goweii/ActionBarEx/releases)

从2.2.9以后版本去除版本号前的v，引用时需要注意下。

从3.0.0版本开始分离了ActionBarEx和ActionBarCommon/Search，

```
dependencies {
	// 完全引入
	implementation 'com.github.goweii:ActionBarEx:3.0.0'
	
	// 只引入ActionBarEx
	implementation 'com.github.goweii.ActionBarEx:actionbarex:3.0.0'
	
	// 引入ActionBarCommon/Search，依赖于ActionBarEx
	implementation 'com.github.goweii.ActionBarEx:actionbarex-common:3.0.0'
}
```



## 简单需求只需要下面2步就可以了

这里用SimpleActionBar举个例子

1. 布局文件引用

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

2. 代码中绑定事件

```java
simpleActionBar.setOnLeftImageClickListener(new OnLeftImageClickListener() {
    @Override
    public void onClick() {
        finish();
    }
});
```



## 复杂一点的需求，需要自定义导航栏布局

使用起来是这个样子的

1. 自定义布局文件 title_bar_custom.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="48dp">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/iv_back"
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:padding="15dp"
            android:scaleType="fitCenter"
            android:src="@mipmap/back" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:gravity="center"
            android:text="自定义布局"
            android:textColor="@color/white"
            android:textSize="17sp" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="right"
        android:orientation="horizontal">

        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:padding="15dp"
            android:scaleType="fitCenter"
            android:src="@mipmap/download" />

        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:padding="15dp"
            android:scaleType="fitCenter"
            android:src="@mipmap/delete" />

        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:padding="15dp"
            android:scaleType="fitCenter"
            android:src="@mipmap/more" />

    </LinearLayout>

</RelativeLayout>
```

2. 引用布局

```xml
<per.goweii.actionbarex.ActionBarEx
    android:id="@+id/action_bar_ex"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    android:background="@drawable/title_bar_custom_bg"
    app:ab_bottom_line_color="@color/line"
    app:ab_bottom_line_height="0dp"
    app:ab_foreground_layer_layout="@layout/loading_bar"
    app:ab_status_bar_color="@color/transparent"
    app:ab_status_bar_mode="dark"
    app:ab_title_bar_height="48dp"
    app:ab_title_bar_layout="@layout/title_bar_custom" />
```

3. 代码中绑定事件

```java
actionBarEx.getView(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
		finish();
    }
});
```



## 更加复杂的需求，可能需要自定义子类了

自定义子类需要下面三步，复写2个方法

1. **initAttrs(AttributeSet attrs)**：获取子类的自定义属性，注意不要忘了调用**super.initAttrs(attrs);**

2. **inflateTitleBar()**：初始化TitleBar导航栏布局
3. 自定义点击事件回调

举个例子吧

```java
@Override
protected void initAttrs(AttributeSet attrs) {
    super.initAttrs(attrs);

    TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SimpleActionBar);

    float titleTextMaxWidthDef = mContext.getResources().getDimension(R.dimen.title_bar_title_text_max_width_def);
    float iconPaddingDef = mContext.getResources().getDimension(R.dimen.title_bar_icon_padding_def);
    float textSizeDef = mContext.getResources().getDimension(R.dimen.title_bar_text_size_def);
    float textPaddingLeftDef = mContext.getResources().getDimension(R.dimen.title_bar_text_padding_left_def);
    float textPaddingRightDef = mContext.getResources().getDimension(R.dimen.title_bar_text_padding_right_def);
    float titleTextSizeDef = mContext.getResources().getDimension(R.dimen.title_bar_title_text_size_def);
    int iconColorDef = ContextCompat.getColor(mContext, R.color.icon_color_def);
    int textColorDef = ContextCompat.getColor(mContext, R.color.text_color_def);
    int titleTextColorDef = ContextCompat.getColor(mContext, R.color.title_text_color_def);

    leftText = typedArray.getString(R.styleable.SimpleActionBar_simple_left_text);
    leftTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_size, textSizeDef));
    leftTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_left_text_color, textColorDef);
    leftTextPaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_padding_left, textPaddingLeftDef);
    leftTextPaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_text_padding_right, textPaddingRightDef);
    leftImageRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_left_image_res, 0);
    leftImageColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_left_image_color, iconColorDef);
    leftImagePadding = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_left_image_padding, iconPaddingDef);

    rightText = typedArray.getString(R.styleable.SimpleActionBar_simple_right_text);
    rightTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_size, textSizeDef));
    rightTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_right_text_color, textColorDef);
    rightTextPaddingLeft = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_padding_left, textPaddingLeftDef);
    rightTextPaddingRight = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_text_padding_right, textPaddingRightDef);
    rightImageRes = typedArray.getResourceId(R.styleable.SimpleActionBar_simple_right_image_res, 0);
    rightImageColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_right_image_color, iconColorDef);
    rightImagePadding = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_right_image_padding, iconPaddingDef);

    titleText = typedArray.getString(R.styleable.SimpleActionBar_simple_title_text);
    titleTextSize = mDisplayInfoUtils.px2sp(typedArray.getDimension(R.styleable.SimpleActionBar_simple_title_text_size, titleTextSizeDef));
    titleTextColor = typedArray.getColor(R.styleable.SimpleActionBar_simple_title_text_color, titleTextColorDef);
    titleTextMaxWidth = (int) typedArray.getDimension(R.styleable.SimpleActionBar_simple_title_text_max_width, titleTextMaxWidthDef);

    typedArray.recycle();
}

@Override
protected View inflateTitleBar() {
    titleBarChild = (RelativeLayout) inflate(getContext(), R.layout.title_bar_simple, null);

    leftImageView = titleBarChild.findViewById(R.id.iv_left);
    leftTextView = titleBarChild.findViewById(R.id.tv_left);
    titleTextView = titleBarChild.findViewById(R.id.tv_title);
    rightTextView = titleBarChild.findViewById(R.id.tv_right);
    rightImageView = titleBarChild.findViewById(R.id.iv_right);

    if (leftImageRes > 0) {
        leftImageView.setVisibility(VISIBLE);
        leftImageView.setPadding(leftImagePadding, leftImagePadding, leftImagePadding, leftImagePadding);
        leftImageView.setImageResource(leftImageRes);
        leftImageView.setColorFilter(leftImageColor);
    } else {
        leftImageView.setVisibility(GONE);
    }

    if (!TextUtils.isEmpty(leftText)) {
        leftTextView.setVisibility(VISIBLE);
        leftTextView.setText(leftText);
        leftTextView.setTextColor(leftTextColor);
        leftTextView.setTextSize(leftTextSize);
        leftTextView.setPadding(leftTextPaddingLeft, 0, leftTextPaddingRight, 0);
    } else {
        leftTextView.setVisibility(GONE);
    }

    titleTextView.setVisibility(VISIBLE);
    titleTextView.setText(titleText);
    titleTextView.setTextColor(titleTextColor);
    titleTextView.setTextSize(titleTextSize);
    titleTextView.setMaxWidth(titleTextMaxWidth);

    if (rightImageRes > 0) {
        rightImageView.setVisibility(VISIBLE);
        leftImageView.setPadding(rightImagePadding, rightImagePadding, rightImagePadding, rightImagePadding);
        rightImageView.setImageResource(rightImageRes);
        rightImageView.setColorFilter(rightImageColor);
    } else {
        rightImageView.setVisibility(GONE);
    }

    if (!TextUtils.isEmpty(rightText)) {
        rightTextView.setVisibility(VISIBLE);
        rightTextView.setText(rightText);
        rightTextView.setTextColor(rightTextColor);
        rightTextView.setTextSize(rightTextSize);
        rightTextView.setPadding(rightTextPaddingLeft, 0, rightTextPaddingRight, 0);
    } else {
        rightTextView.setVisibility(GONE);
    }
    return titleBarChild;
}
```

好吧我承认，我只是复制了下SimpleActionBar的实现代码，不过流程就是这么个流程，还是很简单的。



## 哎，等等，前景层怎么用呢？

厉害了，想跳过去，都被你发现了。

看下前面自定义导航栏布局中是不是有这么一句

```xml
app:ab_foreground_layer_layout="@layout/loading_bar"
```

那这个布局长什么样子呢？

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout  xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:visibility="gone"
    android:layout_height="50dp">

    <me.zhanghai.android.materialprogressbar.MaterialProgressBar
        android:id="@+id/loading_bar"
        style="@style/loading_bar"
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_gravity="bottom"
        android:indeterminate="true"
        app:mpb_progressStyle="horizontal"
        app:mpb_useIntrinsicPadding="false" />

</FrameLayout>
```

这个其实就是一个1dp高的ProgressBar，用来显示加载中效果

在代码中这样使用就可以了

```java
actionBarEx.getForegroundLayer().setVisibility(View.VISIBLE);
actionBarEx.getForegroundLayer().setVisibility(View.GONE);
```

是不是很简单？



## 既然都说到这了，就把一些常用方法和属性都说了吧

### 常用方法

#### ActionBarEx

- **getBackgroundLayer()**：获取背景层布局
- **getActionBar()**：获取主体层布局
- **getForegroundLayer()**：获取前景层布局
- **getStatusBar()**：获取状态栏视图
- **getTitleBar()**：获取导航栏布局
- **getTitleBarChild()**：获取导航栏子视图
- **getBottomLine()**：获取底部分割线视图
- **getView(@IdRes int id)**：获取子控件
- **getActionBarHeight()**：获取整个ActionBar的高度
- **getStatusBarHeight()**：获取状态栏高度
- **getTitleBarHeight()**：获取标题栏高度
- **getBottomHeight()**：获取分割线高度
- **initAttrs(AttributeSet attrs)**：获取布局属性
- **inflateTitleBar()**：初始化标题栏布局

#### SimpleActionBar

- **getLeftImageView()**：获取左侧图标视图
- **getLeftTextView()**：获取左侧文本视图
- **getTitleTextView()**：获取中间标题视图
- **getRightTextView()**：获取右侧文本视图
- **getRightImageView()**：获取右侧图标视图
- **setOnLeftImageClickListener(final OnLeftImageClickListener onLeftIconClickListener)**：左侧图标点击事件
- **setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener)**：左侧文本点击事件
- **setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener)**：右侧文本点击事件
- **setOnRightImageClickListener(final OnRightImageClickListener onRightIconClickListener)**：右侧图标点击事件

#### SearchActionBar

- **getLeftImageView()**：获取左侧图标视图
- **getLeftTextView()**：获取左侧文本视图
- **getEditTextView()**：获取中间输入框视图
- **getRightTextView()**：获取右侧文本视图
- **getRightImageView()**：获取右侧图标视图
- **setOnLeftImageClickListener(final OnLeftImageClickListener onLeftIconClickListener)**：左侧图标点击事件
- **setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener)**：左侧文本点击事件
- **setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener)**：右侧文本点击事件
- **setOnRightImageClickListener(final OnRightImageClickListener onRightIconClickListener)**：右侧图标点击事件



### 属性介绍

#### ActionBarEx

```xml
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
```

#### SimpleActionBar

```xml
<!--这些属性大家应该看名字就能理解是什么意思了，好吧是因为我懒-->
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
```

#### SearchActionBar

```xml
<!--这些属性大家应该看名字就能理解是什么意思了，好吧是因为我懒-->
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
```



# 实现细节

好了，下面上干货。还是从整个层级划分开始说起吧。

### 先从布局结构重新看下层级划分

- FrameLayout（ActionBarEx本身，继承自FrameLayout）
  - ImageView或者自定义布局（背景层BackgroundLayer）
  - LinearLayout（主体层ActionBar）
    - View（状态栏StatusBar）
    - FrameLayout（标题栏TitleBar）
    - View（底部分割线BottomLine）
  - 自定义布局（前景层ForegroundLayer）

这样是不是对整个结构有了一个更清晰的认识。

### 从代码上看主要的两个功能

- ActionBarEx（自定义控价，负责显示布局）
- StatusBarUtils（负责系统状态栏的沉浸和图标亮色暗色模式切换）



### 来先看下ActionBarEx的自定义过程

#### 先看下构造方法都干了那些事

```java
public ActionBarEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.mContext = context;
    mDisplayInfoUtils = DisplayInfoUtils.getInstance(context);
    mStatusBarHeight = mDisplayInfoUtils.getStatusBarHeight();
    initAttrs(attrs);
    makeImmersion();
    initView();
}
```

可以看到初始化了一个DisplayInfoUtils对象，获取了下系统状态栏的高度。

然后调用了3个方法，下面一个一个看下：

#### initAttrs(AttributeSet attrs)

主要为获取了自定义属性，设置默认值。这都是自定义View的常规操作。

```java
protected void initAttrs(AttributeSet attrs) {
    TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ActionBarEx);

    float titleBarHeightDef = mContext.getResources().getDimension(R.dimen.title_bar_height_def);
    float bottomLineHeightDef = mContext.getResources().getDimension(R.dimen.bottom_line_height_def);
    int bottomLineColorDef = ContextCompat.getColor(mContext, R.color.bottom_line_color_def);
    int statusBarColorDef = ContextCompat.getColor(mContext, R.color.status_bar_color_def);

    mAutoImmersion = typedArray.getBoolean(R.styleable.ActionBarEx_ab_auto_immersion, true);
    mBackgroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_background_layer_layout, 0);
    mBackgroundLayerImageRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_background_layer_image_res, 0);
    mStatusBarVisible = typedArray.getBoolean(R.styleable.ActionBarEx_ab_status_bar_visible, true);
    mStatusBarDarkMode = typedArray.getInt(R.styleable.ActionBarEx_ab_status_bar_mode, STATUS_BAR_MODE_LIGHT) == STATUS_BAR_MODE_DARK;
    mStatusBarColor = typedArray.getColor(R.styleable.ActionBarEx_ab_status_bar_color, statusBarColorDef);
    mTitleBarLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_title_bar_layout, 0);
    mTitleBarHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_title_bar_height, titleBarHeightDef);
    mBottomLineHeight = (int) typedArray.getDimension(R.styleable.ActionBarEx_ab_bottom_line_height, bottomLineHeightDef);
    mBottomLineColor = typedArray.getColor(R.styleable.ActionBarEx_ab_bottom_line_color, bottomLineColorDef);
    mForegroundLayerLayoutRes = typedArray.getResourceId(R.styleable.ActionBarEx_ab_foreground_layer_layout, 0);

    typedArray.recycle();
}
```

#### makeImmersion()

看名字可以知道是设置沉浸模式，先贴代码：

```java
private void makeImmersion() {
    if (!mAutoImmersion) {
        return;
    }
    Activity activity = getActivity();
    if (activity == null) {
        return;
    }
    setSystemStatusBar(activity);
    hintSystemActionBar(activity);
}
```

看最下面2行，分别设置了系统沉浸状态栏和隐藏一同自带的导航栏。

**具体如何实现状态栏沉浸的，我就不多介绍了，都是百度的一些零零散散的方法，和看GitHub上一些大神的代码，针对小米魅族VIVO做了适配。并不能保证所有系统都有效。如果你有更好的适配方案，欢迎讨论，或者你可以让mAutoImmersion=false来关闭自动适配，去Activity实现自己的方法**

#### initView()

这个方法是本项目的重点，但很简单，没什么难的。

```java
private void initView() {
    // 1 初始化BackgroundLayer
    // 1.1 初始化背景图
    if (mBackgroundLayerLayoutRes > 0){
        mBackgroundLayer = inflate(getContext(), mBackgroundLayerLayoutRes, null);
        mBackgroundLayer.setLayoutParams(makeLayerLayoutParams());
        addView(mBackgroundLayer);
    } else {
        if (mBackgroundLayerImageRes > 0) {
            ImageView actionBarImageView = new ImageView(mContext);
            actionBarImageView.setLayoutParams(makeLayerLayoutParams());
            actionBarImageView.setImageResource(mBackgroundLayerImageRes);
            actionBarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            addView(actionBarImageView);
        }
    }

    // 2 初始ContentLayer（ActionBar）
    mActionBar = (LinearLayout) inflate(getContext(), R.layout.action_bar, null);
    mActionBar.setLayoutParams(makeLayoutParamsWithHeight(getActionBarHeight()));
    // 2.1 初始StatusBar
    mStatusBar = mActionBar.findViewById(R.id.status_bar);
    mStatusBar.setLayoutParams(makeLayoutParamsWithHeight(mStatusBarHeight));
    mStatusBar.setBackgroundColor(mStatusBarColor);
    mStatusBar.setVisibility(mStatusBarVisible ? VISIBLE : GONE);
    // 2.2 初始TitleBar
    mTitleBar = mActionBar.findViewById(R.id.title_bar);
    mTitleBar.setClickable(true);
    mTitleBar.setFocusable(true);
    mTitleBar.setFocusableInTouchMode(true);
    mTitleBar.setLayoutParams(makeLayoutParamsWithHeight(mTitleBarHeight));
    mTitleBarChild = inflateTitleBar();
    if (mTitleBarChild != null) {
        mTitleBar.addView(mTitleBarChild);
    }
    // 2.3 初始BottomLine
    mBottomLine = mActionBar.findViewById(R.id.bottom_line);
    mBottomLine.setLayoutParams(makeLayoutParamsWithHeight(mBottomLineHeight));
    mBottomLine.setBackgroundColor(mBottomLineColor);
    addView(mActionBar);

    // 3 初始ForegroundLayer
    if (mForegroundLayerLayoutRes > 0) {
        mForegroundLayer = inflate(getContext(), mForegroundLayerLayoutRes, null);
        mForegroundLayer.setLayoutParams(makeLayerLayoutParams());
        addView(mForegroundLayer);
    }
}
```

这里又有一个inflateTitleBar()方法，就是为了继承自定义而加的，它是这样实现的

```java
protected View inflateTitleBar() {
    if (mTitleBarLayoutRes > 0) {
        return inflate(getContext(), mTitleBarLayoutRes, null);
    }
    return null;
}
```

#### 然后是。？还有然后？哦，没了

一共就干了这么点事，没有实现任何效果，完全是一个空架子。是的，任何效果都需要自定义。

这个ActionBarEx只是划分了下导航栏的层级，限定了下各层级的功能。

当然每个项目肯定都有一个固定风格的设计，不可能每次使用都在布局中添加一个ActionBarEx，再引用一个自定义布局，再在代码中getView拿到控件初始化和绑定事件。稍微复杂点的肯定都是要自定义子类封装的，具体可以参考上面的自定义子类或者直接参考SimpleActionBar或者SearchActionBar的实现。



# 写在最后

这个完全是个人项目，有几点有必要说明下：

1. 觉得好用的欢迎给个Star（[GitHub](https://github.com/goweii/ActionBarEx)）

2. 发现任何BUG欢迎留言或者留个Issues（[Issues](https://github.com/goweii/ActionBarEx/issues)）

3. 任何转载请注明出处

4. 还没想到，未完待续

