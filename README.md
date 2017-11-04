![image](https://github.com/kaluzh/EditClearText_v1.0/blob/master/Screenrecorder-2017-11-05.gif )  

```
<declare-styleable name="EditClearText">
        <!-- 左边图片 -->
        <attr name="ect_icon_left" format="reference" />
        <!-- 左边图片宽度 -->
        <attr name="ect_icon_left_width" format="reference|dimension" />
        <!-- 左边图片高度 -->
        <attr name="ect_icon_left_height" format="reference|dimension" />
        <!-- 右侧图片 -->
        <attr name="ect_icon_right" format="reference" />
        <!-- 右侧图片按压状态 -->
        <attr name="ect_icon_right_pressed" format="reference" />
        <!-- 右侧图片宽度 -->
        <attr name="ect_icon_right_width" format="reference|dimension" />
        <!-- 右侧图片高度 -->
        <attr name="ect_icon_right_height" format="reference|dimension" />
        <!-- 光标颜色 -->
        <attr name="ect_cursor_color" format="reference|color" />
        <!-- 背景颜色 -->
        <attr name="ect_background_color" format="reference|color" />
        <!-- 背景圆角 -->
        <attr name="ect_background_corners" format="reference|dimension" />
        <!-- 背景边框颜色 -->
        <attr name="ect_background_store_color" format="reference|color" />
        <!-- 背景边框宽度 -->
        <attr name="ect_background_store_size" format="reference|dimension" />
</declare-styleable>
```
```
<com.demo.edittext.EditClearText
        android:id="@+id/et_input"
        android:layout_width="match_parent"
        android:layout_height="@dimen/d32"
        android:background="@null"
        android:hint="请输入商品名称"
        android:minHeight="@dimen/d32"
        android:paddingLeft="@dimen/d14"
        android:paddingRight="@dimen/d14"
        android:textColor="#ffffff"
        android:textSize="@dimen/s13"
        android:drawablePadding="2dp"
        android:textColorHint="@android:color/darker_gray"
        app:ect_background_color="@android:color/holo_blue_dark"
        app:ect_background_corners="@dimen/d45"
        app:ect_icon_left="@drawable/icon_user"
        app:ect_icon_right="@drawable/icon_clear"
        app:ect_icon_right_pressed="@drawable/icon_user"
        app:ect_background_store_color="@android:color/black"
        app:ect_background_store_size="@dimen/d2"/>
```
