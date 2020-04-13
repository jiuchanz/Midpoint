# 数据中转服务器 
### 使用说明
###### 数据下载
http get 请求例子:\
`curl --request GET 'http://www.jiuchang.me/data?deviceId=1'`
###### 数据上传
http post 请求例子:\
`curl --request POST 'http://www.jiuchang.me/data' \
--header 'Content-Type: application/json' \
--data-raw '{
  "deviceId" : 1,
  "payload" : {
  	"car" : "benz"
  }
 }'`
###### 命令下载
http get 请求例子:\
`curl --request GET 'http://www.jiuchang.me/command?deviceId=1'`
###### 命令上传
http post 请求例子:\
`curl --request POST 'http://www.jiuchang.me/command' \
--header 'Content-Type: application/json' \
--data-raw '{
  "deviceId" : 1,
  "payload" : {
  	"command" : "move up"
  }
 }'`
###### 设备列表
可获得所有运行的设备ID列表，如超过十秒未更新的设备将被剔除，请求例子:\
`curl --request GET 'http://www.jiuchang.me/all'`

### 功能说明
1. 过期数据或命令自动删除，有效时长十秒，过期后无法GET到原数据
2. 收发的数据或命令都为JSON格式，发送更新必须携带deviceId信息，否则无效
3. GET到的JSON数据或命令里面除了原有信息，还会带有服务器收到该更新的时间，例如：\
    `{
        "deviceId": 1,
        "payload": {
            "command": "move up"
        },
        "creationDate": "2020-04-13T06:43:33.830+0000"
    }`