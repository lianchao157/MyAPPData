总结记录
1 数据库操作
2 三方登陆



3 记录日志信息
LogService很全面



4 服务已经重新写了一个  myappdatabyService   不要使用是springbootfoure

https://www.jianshu.com/p/e4c972ca6c94



测试用的url
https://devapi.qweather.com/v7/weather/now?location=101010100&key=a5cf6ab782a14013b08fb92a57dd2f72
1.42 上线地址
https://github.com/lianchaolc/PDAA20.git




2022.4.28
ActivityQueueManager 自己管理的activity队列
这样还有一个很明显的好处就是，当我们退出应用时直接遍历这个队列把其中加人的activity挨个finish掉即可
添加一个activity（pushActivity）
删除一个activity（popActivity）
获取队列中最上层activity（pop）
获取队列中的某一个activity（popIndex）
删除掉队列中所有的activity，退出应用使用（finishAllActivity）
关闭掉队列中的最上层的N个activity，很多应用需要的一个方法（closeNumberActivities）



apk 加密
https://www.jianshu.com/p/40cdd4841d58 

Android打包加固的原因
防止反解密植入广告并放入病毒

多渠道打包的命令和操作gradlew assembleRelease
https://www.meiwen.com.cn/subject/tboagttx.html

2022.6.7  
1百度地图 复杂性
2 打包问题解决
3 mvp 书写  10%


进度条
Android-SpinKit 进度条 

   attrs.xml文件

    所有自定义属性需要在文件中添加declare-styleable节点来声明，例如定义属性background_color设置背景色。