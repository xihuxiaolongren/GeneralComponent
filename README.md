#GeneralComponent


###项目简介：
收集好用的轮子&开发测试工具，详细了解它的适用场景、用法（常规用法给出使用示例）以及和相似轮子的对比，实现快速搭建一个能够实现完整功能并具有良好扩展性的Android工程；

###功能清单：（排名分先后）

1. 日志上传：
	- 	崩溃日志（acra，比友盟好在开源，定制化比较高，可由技术主导，把崩溃等信息和其它数据分开）
   - 用户分析、渠道统计相关：友盟(第三方数据统计平台，也集成了崩溃日志记录功能，公司常用于对外展示数据)；    
2. 测试发布平台：fir.im   **已完成**
3. 事件总线：Eventbus	   **已完成**
4. 响应式编程：RxJava    **已完成**
5. 分享：shareSDK（同时包括社会化登录，可集成至登录模块）
6. 热更新：andfix
7. 图片加载、缓存：glide、imageloader  **已完成**
8. orm数据库框架：greendao  **已完成**
9. ui库，自定义ui框架等:
     - 滚轮等控件 **已完成**
     - 图片滤镜：简单的应用，使用 gpuimage 即可；实际应用，在没有一个开发团队的情况下，建议接入专业第三方sdk，例如：<https://tusdk.com/>
     - 图片上传：客户端ui，后台强烈建议使用阿里云、七牛等云服务； **已完成**
     - 地图引入，gps等：百度地图   （定位&轨迹）**已完成**
     - 二维码
     - 音乐、视频等
     - 蓝牙
10. 动画：
    引导页动画：视差效果、自定义放大缩小旋转位移等基本动画、页面间动画；
11. 登录、注册、退出等公共逻辑
12. 设计模式：mvp（mosby）    **已完成**
13. 依赖注入：dagger2  **已完成**
14. 版本管理
15. 日志：logger，Timber **已完成**
16. 兼容机型和版本 阿里云测、test in等；（很重要，instant run不支持5.0以下的问题就被测出来了） 阿里云测  **已完成**
17. im相关：没有一个前后端团队，建议接入第三方：环信等；

    im讲解的比较好的文章：[Android平台上关于IM的实践总结](http://blog.csdn.net/d_clock/article/details/45424191)
18. 推送：同上，推荐使用个推等第三方服务；
19. 直播：[一言不合你就用环信搞个直播APP](http://blog.csdn.net/mengmakies/article/details/51794248)

###参考：
[Android 开源项目分类汇总](https://github.com/Trinea/android-open-project)

[最美应用－从Android研发工程师的角度之[厨房故事]] (http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0813/3296.html)

[Jenkins Gitlab持续集成打包平台搭建](http://skyseraph.com/2016/07/18/Tools/Jenkins%20Gitlab%E6%8C%81%E7%BB%AD%E9%9B%86%E6%88%90%E6%89%93%E5%8C%85%E5%B9%B3%E5%8F%B0%E6%90%AD%E5%BB%BA/)

[Android 开发最佳实践](https://github.com/futurice/android-best-practices/blob/master/translations/Chinese/README.cn.md)

[Android开发时你遇到过什么相见恨晚的工具或网站](https://www.zhihu.com/question/27140400)
