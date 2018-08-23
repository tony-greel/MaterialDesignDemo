# MaterialDesignDemo
Retrofit+jiechao视频框架+RecyclerView+Glide 

## 简介
先吐槽一下,github的图片是的难上传,有几次都不想上传了,奈何心底善良的我还是上传了！不多说了,开始分别介绍一下上面图片。
### 图一和图二是下拉上拉加载框架SmartRefreshLayout
   > * implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.5.1'
   > * implementation 'com.scwang.smartrefresh:SmartRefreshHeader:1.0.5.1'//没有使用特殊Header，可以不加这行
### 请求的则是用Retrofit,显示网络图片Glide,下面两个Glide依赖，一个是高斯模糊就是图三
  > *  implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
  > * //加载图片
  > *  implementation 'com.github.bumptech.glide:glide:3.7.0'
  > * //加载图片
  > *  implementation 'jp.wasabeef:glide-transformations:2.0.1'
### 图三用的节操(jiecao视频加载框架)，图四就是Retrofit的简单用法加上配合接口做的一个简单的翻译Demo，如果想要封装Retrofit可参考图片加载+共享的那个项目
    > * implementation 'fm.jiecao:jiecaovideoplayer:5.5'
### 总结
    > * 很多新奇好玩的东西请关注我的博客:https://tony-greel.github.io/

![image](https://github.com/tony-greel/MaterialDesignDemo/blob/master/RetrofitDemo/src/main/res/mipmap-xxxhdpi/84004645-DFBB-48E2-9474-D71FE482F7EC.png)
![image](https://github.com/tony-greel/MaterialDesignDemo/blob/master/RetrofitDemo/src/main/res/mipmap-xxxhdpi/1F7229BD-01C0-42F0-8DE1-D1972159C854.png)
![image](https://github.com/tony-greel/MaterialDesignDemo/blob/master/RetrofitDemo/src/main/res/mipmap-xxxhdpi/04C88EA3-6D18-481E-9C89-BBF3317F27E6.png)
![image](https://github.com/tony-greel/MaterialDesignDemo/blob/master/RetrofitDemo/src/main/res/mipmap-xxxhdpi/52D8675E-DE7D-4B91-95AB-78FD74DCEFA0.png)
![image](https://github.com/tony-greel/MaterialDesignDemo/blob/master/RetrofitDemo/src/main/res/mipmap-xxxhdpi/C8A76EA8-CB38-4F0F-B0EF-E31412CABF3E.png)
