# ActionBarEx

**高拓展高自定义性ActionBar，完美替代Android系统默认**

[GitHub主页](https://github.com/goweii/ActionBarEx)

[Demo下载](https://github.com/goweii/ActionBarEx/raw/master/app/release/app-release.apk)



## 项目简介

整个ActionBar分为3层：**BackgroundLayer** / **ActionBarLayer** / **ForegroundLayer**，具体负责功能如下：

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

1. 在项目根目录的**build.gradle**添加仓库地址

```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

2. 在项目app目录的**build.gradle**添加依赖

```
dependencies {
	implementation 'com.github.goweii:ActionBarEx:v2.2.0'
}
```

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

### 代码中使用

```java
simpleActionBar.setOnLeftImageClickListener(new OnLeftImageClickListener() {
    @Override
    public void onClick() {
        finish();
    }
});
```



## 自定义子类

自定义子类只需要下面三步，复写2个方法即可

1. **initAttrs(AttributeSet attrs)**：获取子类的自定义属性，注意不要忘了调用**super.initAttrs(attrs);**

2. **inflateTitleBar()**：初始化TitleBar导航栏布局
3. 自定义点击事件回调

#### 举个例子吧

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

好吧我承认，我只是复制了下里面的SimpleActionBar的实现代码，不过流程就是这么个流程，还是很简单的。



## 常用方法

### ActionBarEx

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

### SimpleActionBar

- **getLeftImageView()**：获取左侧图标视图
- **getLeftTextView()**：获取左侧文本视图
- **getTitleTextView()**：获取中间标题视图
- **getRightTextView()**：获取右侧文本视图
- **getRightImageView()**：获取右侧图标视图
- **setOnLeftImageClickListener(final OnLeftImageClickListener onLeftImageClickListener)**：左侧图标点击事件
- **setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener)**：左侧文本点击事件
- **setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener)**：右侧文本点击事件
- **setOnRightImageClickListener(final OnRightImageClickListener onRightImageClickListener)**：右侧图标点击事件

### SearchActionBar

- **getLeftImageView()**：获取左侧图标视图
- **getLeftTextView()**：获取左侧文本视图
- **getEditTextView()**：获取中间输入框视图
- **getRightTextView()**：获取右侧文本视图
- **getRightImageView()**：获取右侧图标视图
- **setOnLeftImageClickListener(final OnLeftImageClickListener onLeftImageClickListener)**：左侧图标点击事件
- **setOnLeftTextClickListener(final OnLeftTextClickListener onLeftTextClickListener)**：左侧文本点击事件
- **setOnRightTextClickListener(final OnRightTextClickListener onRightTextClickListener)**：右侧文本点击事件
- **setOnRightImageClickListener(final OnRightImageClickListener onRightImageClickListener)**：右侧图标点击事件



## 属性介绍

### ActionBarEx属性介绍

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

### SimpleActionBar属性介绍

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

### SearchActionBar属性介绍

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