# 数据中转服务器 使用说明
###### 数据下载
http get 请求例子:\
curl --request GET 'http://www.jiuchang.me/data?deviceId=1'
###### 数据上传
http post 请求例子:\
curl --request POST 'http://www.jiuchang.me/data' \
--header 'Content-Type: application/json' \
--data-raw '{
  "deviceId" : 1,
  "payload" : {
  	"car" : "benz"
  }
 }'
###### 命令下载
http get 请求例子:\
curl --request GET 'http://www.jiuchang.me/command?deviceId=1'
###### 命令上传
http post 请求例子:\
curl --request POST 'http://www.jiuchang.me/command' \
--header 'Content-Type: application/json' \
--data-raw '{
  "deviceId" : 1,
  "payload" : {
  	"command" : "move up"
  }
 }'
