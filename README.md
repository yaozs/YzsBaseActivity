# YzsBaseActivity
 ![image](https://github.com/yaozs/YzsLib/blob/master/app/src/main/res/mipmap-xxxhdpi/icon.png)<br />

本项目为yzslib的一个分支，目的是为了方便大家使用，减少依赖，如果需要整个开发框架请移步<br />
[YzsLib——超好用的开发框架](https://github.com/yaozs/YzsLib)

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
            compile 'com.github.yaozs:YzsBaseActivity:0.3.1'
        }

        目前最新版本为0.3.1，就是最新的release版本，之后引用修改版本号就可以

  
        
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

### 本项目使用开源项目
1.[FlycoTabLayout——viewpager指示器 与 导航栏控件](https://github.com/H07000223/FlycoTabLayout)<br />
2.[fragmentation——为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生,替代官方fragment](https://github.com/YoKeyword/Fragmentation)<br />
3.[eventbus——事件总线](https://github.com/greenrobot/EventBus)<br />
4.[BaseRecyclerViewAdapterHelper——RecyclerView的强大的BaseAdapter](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)<br />


本项目中使用其他的开源项目和参考修改，都会在近期更新出来，保护程序猿，就是保护我们自己O(∩_∩)O哈哈~
       
        喜欢的话给个star，留下点印记吧.
