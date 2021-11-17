# annotationprocessing101
This repository is part of the blog entry I have written about annotation processing.

> 参考： http://hannesdorfmann.com/annotation-processing/annotationprocessing101

# 代码功能说明
    apt技术使用demo
# 使用方式
  1. 进入factory目录，执行本地打包命令
```bash
factory git:(master) mvn clean install
[INFO] Scanning for projects...
...
[INFO] parent ............................................. SUCCESS [  0.773 s]
[INFO] annotation ......................................... SUCCESS [  5.190 s]
[INFO] processor .......................................... SUCCESS [  2.255 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.415 s
[INFO] Finished at: 2021-11-17T20:03:00+08:00
[INFO] ------------------------------------------------------------------------
```
 2. 运行或断点调试FruitStore_StaticFactory即可
```bash
Connected to the target VM, address: '127.0.0.1:57486', transport: 'socket'
What do you like in orange/apple/banana?
apple
Bill: $4.5
Disconnected from the target VM, address: '127.0.0.1:57486', transport: 'socket'

Process finished with exit code 0
```
