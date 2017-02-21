用BlueZone和testNG替换RFT和FTE：

   (1) BlueZone替换RFT
   
       a. 封装BlueZone Host Automation Object的方法。 -- BlueZoneHAOUtil.java， Done
       
       b. 修改脚本（不只test case）中与RFT相关的部分.    -- Ongoing. idea_FTE_to_BlueZone.txt
       
       c. 修改EAGS，改用BlueZone。                    -- Not start
       
       
       
   (2) testNG替换FTE

       a. case在新框架下run起来，CMD／Eclipse IDE

       b. 每个vefification point写入HTML Log。

       c. 定制Test Result HTML Log

       d. 利用testNG多线程跑case？

       e. 生成的HTML Log，如何让CATS读取？
 
 
 
 
 

