# YzsBaseActivity
[![](https://jitpack.io/v/yaozs/YzsBaseActivity.svg)](https://jitpack.io/#yaozs/YzsBaseActivity)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-YzsBaseActivity-green.svg?style=true)](https://android-arsenal.com/details/1/2756)
![](https://img.shields.io/badge/minSdk-15-blue.svg)
[![](https://img.shields.io/github/stars/yaozs/YzsBaseActivity.svg)](https://github.com/yaozs/YzsBaseActivity/stargazers)
[![](https://img.shields.io/github/forks/yaozs/YzsBaseActivity.svg)](https://github.com/yaozs/YzsBaseActivity/network)

![image](https://github.com/yaozs/YzsLib/blob/master/app/src/main/res/mipmap-xxxhdpi/icon.png)<br />


![Android百科全书二维码.jpg](http://upload-images.jianshu.io/upload_images/5637205-5afd370499eee2d3.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 本人最新公众号《Android百科全书》，汇集了各个公众号的优秀文章，进行分类整理，让大家能够更方便的查阅，希望大家多多支持，来个关注奥。

如果你想详细的查看一下该框架的所有方法<br />
### [YzsBaseActivity框架方法详解](https://www.jianshu.com/p/5df398294cbf)<br />

本项目为yzslib的一个分支，目的是为了方便大家使用，减少依赖，如果需要整个开发框架请移步<br />
[YzsLib——超好用的开发框架](https://github.com/yaozs/YzsLib)<br />

            YzsLib开源交流群：331973212

        远程依赖方法在项目根目录下的build.gradle添加如下

        allprojects {
        		repositories {
        			...
        			<!--添加的话，就这一句-->
        			maven { url "https://jitpack.io" }
        		}
        	}

        在引用项目处添加
        dependencies {
             compile 'com.github.yaozs:YzsBaseActivity:0.4.3'
        }

        目前最新版本为0.4.3，就是最新的release版本，之后引用修改版本号就可以


### 本项目采用的架构是 多Activity 多fragment的模块化架构，使用fragmentation作为底层进行的二次开发封装，使界面加载更加的快速，
切换动画比ac更加容易控制等优点。具体使用详见demo

 
        
        * 16.11.7  更新BaseActivity，支持4.4版本以上沉浸式
        * 16.11.15 更新style——activity，配合YzsBase使用实现沉浸式
        * 16.11.20 更新工具类，更新fragment替代库，封装eventbus到activity与fragment中
        * 16.11.21 增加YzsBaseWebActivity,更新整合Demo展示
        * 16.12.7  base重新分包，添加listView、gridview等的adapter，与BaseRecyclerViewAdapterHelper呼应共同维护list数据
        * 16.12.15 新增baseListActivity(列表数据视图base父类)，baseListFragment，新增导航条控件，更新demo整体效果，更像一个app
        * 16.12.21 新增YzsLoadingDialog,可自定义图片的loading动画
        * 16.12.22 新增YzsBaseHomeActivity(首页base父类)
        * 17.03.06 优化base方法名，新增demo方便查看
        * 17.03.09 修复YzsBaseHomeActivity使用framelayout初始化选中，设置首页缓存数量（依然支持懒加载）
        * 17.03.13 增加basefragment默认初始化tooblar
        * 17.04.15 更改部分list界面方法，修改baseFragment为默认注册eventbus
        * 17.04.22 增加activity栈管理器，采用弱引用方式，使管理器更加安全，删除base中loading方法和toast方法，可以直接使用utils进行操作，更加便捷，
                   将跳转页面方法拆分，变为ActivityGoUtils
        * 17.07.04 重大更新。。。修改activity栈管理器，重构项目，兼容mvp模式与mvc模式，继续提供list界面和home界面的支持，
            list界面托管模式开启，全新架构，最少使用activity，采用ImmersionBar解决沉浸式方案，eventbus一键开启关闭
        * 17.07.05 修复加载更多bug，添加showBackButton（显示返回方法）
        * 17.07.13 优化fragment切换出现白边
        * 17.08.02 优化默认tooblar颜色设置，自动适配项目颜色
        * 17.08.07 优化list界面自动化处理，一句代码搞定各种加载状态     autoListLoad  方法诞生
        * 18.04.19 添加YzsBaseActivity框架方法详解，整理代码，梳理逻辑
        * 18.04.26 修复list页面设置第一页为0刷新后下一页为2的bug


### 本项目使用开源项目
1.[FlycoTabLayout——viewpager指示器 与 导航栏控件](https://github.com/H07000223/FlycoTabLayout)<br />
2.[fragmentation——为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生,替代官方fragment](https://github.com/YoKeyword/Fragmentation)<br />
3.[eventbus——事件总线](https://github.com/greenrobot/EventBus)<br />
4.[BaseRecyclerViewAdapterHelper——RecyclerView的强大的BaseAdapter](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)<br />
5.[android 4.4以上沉浸式状态栏和沉浸式导航栏管理，一句代码轻松实现](https://github.com/gyf-dev/ImmersionBar)<br />

### 本项目参考的开源项目
1.[AndroidFire](https://github.com/jaydenxiao2016/AndroidFire)<br />


本项目中使用其他的开源项目和参考修改，都会在近期更新出来，保护程序猿，就是保护我们自己O(∩_∩)O哈哈~
       
        喜欢的话给个star，留下点印记吧.
