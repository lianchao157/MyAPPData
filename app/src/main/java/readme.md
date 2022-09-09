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
    
    
    
    
  MD5: 87:74:9C:14:70:D6:33:D1:8A:48:74:0E:ED:75:55:6E
           SHA1: FE:33:BD:5B:38:50:3A:B8:D0:D3:1C:BE:73:E5:5E:F3:7F:9E:8D:E4
           SHA256: 1C:0B:F7:E2:4F:7B:C1:51:BC:41:1C:C8:67:F8:B5:47:07:06:4C:5F:E0:7A:A2:53:DE:30:37:AA:3B:E5:5A:3D
           签名算法名称: SHA256withRSA
           版本: 3  
           
           
            MD5: 87:74:9C:14:70:D6:33:D1:8A:48:74:0E:ED:75:55:6E
                    SHA1: FE:33:BD:5B:38:50:3A:B8:D0:D3:1C:BE:73:E5:5E:F3:7F:9E:8D:E4
                    SHA256: 1C:0B:F7:E2:4F:7B:C1:51:BC:41:1C:C8:67:F8:B5:47:07:06:4C:5F:E0:7A:A2:53:DE:30:37:AA:3B:E5:5A:3D
                    签名算法名称: SHA256withRSA
                    版本: 3
                    
                    
                    myAppData-测试	607e906be14d8620f7589b7d39d0d093	—	Android平台	设置
                    
                    keytool -v -list -keystore D:\savejksfiel\myappdata.jks
               线上版本打包   **********************************************************************
                    
                     MD5: 17:C3:A7:75:8A:56:82:30:38:EF:FC:43:22:DE:11:83
                             SHA1: ED:59:50:00:DE:D0:1C:A1:D7:60:73:CE:5E:77:DA:B9:B4:FF:05:4E
                             SHA256: C2:EB:23:77:EB:AE:EF:68:62:80:A7:FF:42:CC:44:82:F1:97:FC:1A:98:D5:87:DE:DB:13:7D:5B:E4:96:7C:2D
                             签名算法名称: SHA256withRSA
                             版本: 3
                             
                             
                             生成的key值    
                             
                             607e906be14d8620f7589b7d39d0d093

    
    
    生成sha1 的值的方法
    https://blog.csdn.net/afufufufu/article/details/122565720
    
    第一步
    点击Terminal
    在这里插入图片描述
    第二步
    输入下面语句
    E:\Android\text.jks 是你密钥的地址路径
    keytool -v -list -keystore E:\Android\text.jks
    
    高德地图
    
    https://lbs.amap.com/demo/sdk/traffic-transfer#android