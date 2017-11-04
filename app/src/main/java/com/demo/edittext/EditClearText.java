package com.demo.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * description: 有清除功能,只有当有焦点和文本不为空的时候才显示
 * created by kalu on 17-10-17 下午12:10
 */
public class EditClearText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

	// 左侧提图片资源ID（仅仅是个图标，没有实际意义）
    private int leftIcon;
    // 左侧删除图片（宽度）
    private float leftIconWidth;
    // 左侧删除图片（高度）
    private float leftIconHeight;
    // 左侧删除图片
    private Drawable leftDrawable;
	// 右侧删除图片资源ID（默认状态，获取焦点，内容不为空时，显示图片）
    private int rightIcon;
    // 右侧删除图片资源ID（按下状态）
    private int rightIconPressed;
    // 右侧删除图片（默认状态，按下状态）
    private Drawable rightDrawable;
    // 右侧删除图片（宽度）
    private float rightIconWidth;
    // 右侧删除图片（高度）
    private float rightIconHeight;
	// 背景颜色
    private int backgroundColor;
    // 背景圆角
    private float backgroundCorners;
    // 背景边框颜色
    private int backgroundStoreColor;
    // 背景边框宽度
    private float backgroundStoreSize;
    // 空字符串
    private final String NULL = "";

    public EditClearText(Context context) {
        this(context, null);
    }

    public EditClearText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public EditClearText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 单行输入
        setSingleLine();

        TypedArray typedArray = null;

        try {

            final float scale = context.getResources().getDisplayMetrics().density;
            float defaultIconSize = 20 * scale + 0.5f;

            //获取自定义属性。
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditClearText, defStyleAttr, 0);

            leftIcon = typedArray.getResourceId(R.styleable.EditClearText_ect_icon_left, -1);
            leftIconWidth = typedArray.getDimension(R.styleable.EditClearText_ect_icon_left_width, defaultIconSize);
            leftIconHeight = typedArray.getDimension(R.styleable.EditClearText_ect_icon_left_height, defaultIconSize);

            //左边按钮的大小dp
            rightIconWidth = typedArray.getDimension(R.styleable.EditClearText_ect_icon_right_width, defaultIconSize);
            rightIconHeight = typedArray.getDimension(R.styleable.EditClearText_ect_icon_right_height, defaultIconSize);
            // 删除按钮
            rightIcon = typedArray.getResourceId(R.styleable.EditClearText_ect_icon_right, -1);
            rightIconPressed = typedArray.getResourceId(R.styleable.EditClearText_ect_icon_right_pressed, -1);
            // 背景颜色
            backgroundColor = typedArray.getColor(R.styleable.EditClearText_ect_background_color, Color.TRANSPARENT);
            // 背景边框颜色
            backgroundStoreColor = typedArray.getColor(R.styleable.EditClearText_ect_background_store_color, Color.TRANSPARENT);
            // 背景圆角
            backgroundCorners = typedArray.getDimension(R.styleable.EditClearText_ect_background_corners, 0);
            // 背景边框宽度
            backgroundStoreSize = typedArray.getDimension(R.styleable.EditClearText_ect_background_store_size, 0);
        } catch (Exception e) {
        } finally {

            if (null != typedArray) {
                typedArray.recycle();
            }
        }

        // 设置对其方式:center_vertical
        // setGravity(Gravity.CENTER_VERTICAL);

        //设置监听
        this.setOnFocusChangeListener(this);
        this.addTextChangedListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (leftIcon != -1) {
            leftDrawable = getResources().getDrawable(leftIcon);
            leftDrawable.setBounds(0, 0, (int) leftIconWidth, (int) leftIconHeight);
        }

        //右侧图标
        if (rightIcon != -1) {

            // 存在按下状态
            if (rightIconPressed != -1) {

                Drawable drawable1 = getResources().getDrawable(rightIcon);
                Drawable drawable2 = getResources().getDrawable(rightIconPressed);

                rightDrawable = new StateListDrawable();
                ((StateListDrawable) rightDrawable).addState(new int[]{-android.R.attr.state_pressed}, drawable1);
                ((StateListDrawable) rightDrawable).addState(new int[]{android.R.attr.state_pressed}, drawable2);
                rightDrawable.setBounds(0, 0, (int) rightIconWidth, (int) rightIconHeight);
            }
            // 没有按压状态
            else {
                rightDrawable = getResources().getDrawable(rightIcon);
                rightDrawable.setBounds(0, 0, (int) rightIconWidth, (int) rightIconHeight);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 圆角矩形背景
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(backgroundCorners);
        bg.setColor(backgroundColor);
        bg.setSize(getWidth(), getHeight());
        bg.setStroke((int) backgroundStoreSize, backgroundStoreColor);
        setBackgroundDrawable(bg);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //可以获得上下左右四个drawable，右侧排第二。图标没有设置则为空。
        Drawable rightIcon = getCompoundDrawables()[2];
        if (rightIcon != null && event.getAction() == MotionEvent.ACTION_UP) {
            //检查点击的位置是否是右侧的删除图标
            //注意，使用getRwwX()是获取相对屏幕的位置，getX()可能获取相对父组件的位置
            int leftEdgeOfRightDrawable = getRight() - getPaddingRight() - rightIcon.getBounds().width();
            if (event.getRawX() >= leftEdgeOfRightDrawable) {
                setText(NULL);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if (length() > 0 && hasFocus()) {
            setCompoundDrawables(leftDrawable, null, rightDrawable, null);
        } else {
            setCompoundDrawables(leftDrawable, null, null, null);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (length() > 0 && hasFocus()) {
            setCompoundDrawables(leftDrawable, null, rightDrawable, null);
        } else {
            setCompoundDrawables(leftDrawable, null, null, null);
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onDetachedFromWindow() {

        // 移除监听
        this.removeTextChangedListener(this);
        this.addTextChangedListener(null);
        this.setOnFocusChangeListener(null);
        // 释放资源
        rightDrawable = null;
        leftDrawable = null;

        super.onDetachedFromWindow();
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {

        if (visibility != VISIBLE) {
            // 移除监听
            this.removeTextChangedListener(this);
            this.addTextChangedListener(null);
            this.setOnFocusChangeListener(null);
            // 释放资源
            rightDrawable = null;
            leftDrawable = null;
        }

        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onFinishTemporaryDetach() {

        // 移除监听
        this.removeTextChangedListener(this);
        this.addTextChangedListener(null);
        this.setOnFocusChangeListener(null);
        // 释放资源
        rightDrawable = null;
        leftDrawable = null;

        super.onFinishTemporaryDetach();
    }
}