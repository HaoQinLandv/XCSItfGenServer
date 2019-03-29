# XCS接口服务

------

XCS通用接口服务，基于Spring4.3 + Mybatis3.4 + Jtds + Sybase 开发的后台数据服务，采用SPI加载，SpringRMI，rpc远程调用，支持的功能有：
Check In 公安，客控，等数据推送。

大致功能点：

> * 接口日志查看
> * ckin数据推送
> * ckot数据推送
> * 第三方请求服务

其框架的大致UML图如下

![xcs-geninterface-logo](http://www.plantuml.com/plantuml/png/vLKzRpD54ExtLsmP4iwYFbbM4WSHIuH4TgYZzNhYRBmVgzsvbnZZ1aL2YlWeQ43YuqqaH4JXX8e6_njikUCliEVp9USx9nAqT9xTvtdccTcPywv7vZ1HaWYDuCuP1zfEj6PT2MH4TboYjzuXOzBw07JJA6KqhNzMgz4cEjasHeLZS9h9M2WhGO56J_Qw7XtZc6CEcUv9S84OHHKYZU4AGVSyoTDck4B2cEQGLbKoU7aXtx-sT7QI242GJuxA5tHxOm7UYjdDsyLq-kplY5aBRedVGDXjWpU9uq0gT_N0E68YBRfZdT3zrd7pxp-_F6XLjEkXyWnvDEmo57xnnM-pc_lvTszdZzU44CluWFM1TiAj3qsj7p1awSz6Wuu8vP9v7mGyWZj3K3O_Eb7-h9i8sGl9oPXeWq2x1j6es2-bPfEV_ff-kxYzj-RI498B2Ht6HJ6HpKjsNAbzHABA6_9Lt59MagXS9byBoOxG0WMJubC8URBnp5rAECTuDhcRV_thOZfPFDpDV_XgTdDB2gDTKW-E2nsWVS0iF0tH_d2FSurR7KoVWR8q21a_51xhnHurKbeus5_5-yFJeOMSXk5tO0K7DB4zXh2EPFsjeXQrokSv2iQMATggWomjjZ89KRHD2-YO9XusWegfCbInDKyBHqQbDey9M-tFIxkMKaAYUbRrk_IuUmaS6ylNSm7M78Ivs0GGsYQXhQ-JZKQX4-ZzTuM1mLV8bEna5yzzNiLSzSBjUArF_wVIyzLoed-HxzRicr_cFr-NDpp_HgRju1V01oVEM70em7zay7s5RfWkxCWZGy77XuYs0-uDk8zHI5yBnmxVIn75lgt71aEyPcZ-_MJ-usDcApTKKWvsCkt0P5AQJvIu2kKHmZWQHvk9HwF0LR_rKTGeQz5ukJCvPnCalWZnuk6F-U-VapAxLcji-3DmbfDTqBr4oNy0)



作者 [@tq][1]     
2018 年 12月 01日    

[1]: https://github.com/tq835465605
