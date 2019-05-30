# kx-fram-admin

## 管理后台接口文档

#### 后台用户相关接口
* [登陆接口](#登陆接口)
* [添加用户](#添加用户)
* [更新用户](#更新用户)
* [删除用户](#删除用户)
* [用户详情](#用户详情)
* [用户列表](#用户列表)
* [重置密码](#重置密码)
* [更新密码](#更新密码)

#### 信誉评分
* [作物品种分值列表](#作物品种分值列表)


#### 农场管理相关接口
* [农场列表](#农场列表)
* [添加农场](#添加农场)
* [农场详情](#农场详情)
* [修改农场](#修改农场)
* [删除农场](#删除农场)
* [所有客户列表](#所有客户列表)

#### 农场记录相关接口
* [联级菜单接口](#联级菜单接口)
* [农事记录列表](#农事记录列表)
* [获取下一个未评估](#获取下一个未评估)
* [评估农事记录](#评估农事记录)
* [导出记录](#导出记录)
* [导出品种作物](#导出品种作物)

#### 作物任务相关接口

* [添加作物任务](#添加作物任务)
* [修改作物任务](#修改作物任务)
* [作物任务详情](#作物任务详情)
* [删除作物任务](#删除作物任务)
* [作物任务列表](#作物任务列表)
* [所有农事环节集合](#所有农事环节集合)
* [所有农作物集合](#所有农作物集合)

#### 统一返回格式-普通
~~~
{
    "status": 200,
    "message": "ok",
    "data": {}
}
~~~

#### 统一返回格式-分页
~~~
{
    "status": 200,
    "message": "ok",
    "data": {
        "count": 1,
        "list": []
    }
}
~~~


#### 登陆接口

请求路径 api/login

请求方式 POST

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 username  | String | 是 | 17611013091| 登入账号
 password  | String | 是 | 123456 | 登入密码
 

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 adminId  | Long  | 管理员编号
 username  | String  | 管理员名称
  mobile  | String  | 手机号 登入账号
 levelName  | String  | 角色名称
  grade  | Long  | 角色等级



#### 添加用户

请求路径 api/admin/add

请求方式 POST

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 username  | String | 是 | 17611013091| 用户姓名
  mobile  | String | 是 | 17611013091| 登入账号，手机号
 email  | String | 否 | qiwei@126.com | 邮箱

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 
 
 #### 更新用户
 
 请求路径 api/admin/update
 
 请求方式 PUT
 
 **请求参数**
 
  参数  | 类型  | 是否必填 |示例 | 描述
  |---- | ----- | ------  | ------| ------
  adminId  | Long | 是 | 17611013091| 用户id
  username  | String | 是 | 17611013091| 用户姓名
  mobile  | String | 是 | 17611013091| 登入账号，手机号
  email  | String | 否 | qiwei@126.com | 邮箱
 
 **响应参数**
 
  参数  | 类型   | 描述
  |---- | ----- | ------
 
 
 #### 重置密码
 
 请求路径 api/admin/reset
 
 请求方式 POST
 
 **请求参数**
 
  参数  | 类型  | 是否必填 |示例 | 描述
  |---- | ----- | ------  | ------| ------
  adminId  | Long | 是 | 212| 用户id
 
 **响应参数**
 
  参数  | 类型   | 描述
  |---- | ----- | ------


 #### 修改密码
 
 请求路径 api/admin/updatePassword
 
 请求方式 POST
 
 **请求参数**
 
  参数  | 类型  | 是否必填 |示例 | 描述
  |---- | ----- | ------  | ------| ------
  adminId  | Long | 是 | 212| 用户id
   newPassword  | String | 是 | "2231"| 登入账号，手机号
   password  | String | 是 | "21321" | 登入密码
 
 **响应参数**
 
  参数  | 类型   | 描述
  |---- | ----- | ------


#### 删除用户

请求路径 api/admin/{adminId}

请求方式 DELETE

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------


#### 用户详情

请求路径 api/admin/{adminId}

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 adminId  | Long  | 管理员编号
 username  | String  | 管理员名称
  mobile  | String  | 手机号 登入账号
 levelName  | String  | 角色名称
  grade  | Long  | 角色等级
  
  
#### 用户列表

请求路径 api/admin/page

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 pageNo  | Integer | 是 | 1| 页码
  pageSize  | Integer | 是 | 10| 大小
  username  | String | 否 | “dsada”| 用户名称

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 adminId  | Long  | 管理员编号
 username  | String  | 管理员名称
  mobile  | String  | 手机号 登入账号
 levelName  | String  | 角色名称
  grade  | Long  | 角色等级



#### 添加农场

请求路径 api/farm/add

请求方式 POST

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 name  | String | 是 | "农场"| 农场名称
  area  | Integer | 是 | 100| 面积
 creatorId  | String | 是 | "1234877" | 创建客户id
   brooderCount  | Integer | 是 | 10 | 暖棚数量
   coolCount  | Integer | 是 | 10 | 冷棚数量

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 
 
 #### 修改农场

请求路径 api/farm/update

请求方式 PUT

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
  farmId  | Long | 是 | "1234877" | 创建客户id
 name  | String | 否 | "农场"| 农场名称
  area  | Integer | 否 | 100| 面积


**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------


#### 农场详情

请求路径 api/farm/{farmId}

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
  id  | Long  | 农场id
 name  | String  | 农场名称
  area  | Integer  | 面积
 score  | Integer  | 分数
  creatorId  | String  | 创建人id
   createTime  | String  | 创建时间

 
 #### 删除农场

请求路径 api/farm/{farmId}

请求方式 DELETE

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 
 
 #### 农场列表

请求路径 api/farm/page

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 pageNo  | Integer | 是 | 1| 页码
  pageSize  | Integer | 是 | 10| 大小
    farmName  | String | 否 | | 农场名称

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
  id  | Long  | 农场id
 name  | String  | 农场名称
  area  | Integer  | 面积
 score  | Integer  | 分数
 brooderCount  | Integer  | 暖棚数量
 coolCount  | Integer  | 冷棚数量
 creatorName | String  | 农场主
 createTime  | String  | 加入日期



 #### 所有农作物集合

请求路径 api/farm/crop/list

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
  id  | Long  | 作物id
 name  | String  | 作物名称

 #### 所有农事环节集合

请求路径 api/farm/work/list

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------

**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
  id  | Long  | 农事环节id
 detail  | String  | 具体值
 


#### 联级菜单接口

请求路径 api/farm/linkage/menu

请求方式 GET

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 |---- | ----- | ------  | ------| ------
 farmId  | Long | 否 | 1 | 农场id
 plotId  | Long | 否 | 1 | 地块id
 cropId  | Long | 否 | 1 | 作物id


**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
  id  | Long  | 当前联动 数据id
 name  | String  | 名称



#### 添加作物任务

请求路径 api/task/add

请求方式 POST

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 | ---- | ----- | ------  | ------| ------
 cropId  | Long | 是 | 12 | 作物id
 farmingId  | Long | 是 | 10| 农事环节
 farmingName  | String | 是 | “土壤消毒” | 具体农事环节名称
 number  | Integer | 是 | 0  | 排序编号
 score  | Integer | 是 | 0  | 预计得分


**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 无


#### 修改作物任务

请求路径 api/task/update

请求方式 PUT

**请求参数**

 参数  | 类型  | 是否必填 |示例 | 描述
 | ---- | ----- | ------  | ------| ------
 taskId  | Long | 是 | 12 | 任务id
 cropId  | Long | 否 | 12 | 作物id
 farmingId  | Long | 否 | 10| 农事环节
 farmingName  | String | 否 | “土壤消毒” | 具体农事环节名称
 number  | Integer | 否 | 0  | 排序编号
 score  | Integer | 否 | 0  | 预计得分


**响应参数**

 参数  | 类型   | 描述
 |---- | ----- | ------
 无
 
#### 删除作物任务

请求路径 api/task/{taskId}

请求方式 DELETE

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
  
  
#### 作物任务详情

请求路径 api/task/{taskId}

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
taskId  | Long  | 当前任务id
cropName  | String  | 作物名称
farmingName  | String  | 真是农事环节名称
cropId  | Long  | 作物id
farmingId  | Long  | 农事环节id
parentId  | Long  | 农事环节父节点id
number  | Integer  | 排序编号
score  | Integer  | 预计得分
updateTime  | String  | 更新时间


#### 作物任务列表

请求路径 api/task/page

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
 pageNo  | Integer | 是 | 1| 页码
  pageSize  | Integer | 是 | 10| 大小
    cropId  | Long | 否 | | 作物id
**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
taskId  | Long  | 当前任务id
cropName  | String  | 作物名称
farmingName  | String  | 真是农事环节名称
cropId  | Long  | 作物id
cropName  | String  | 名称
farmingId  | Long  | 农事环节id
parentId  | Long  | 农事环节父节点id
number  | Integer  | 排序编号
score  | Integer  | 预计得分
updateTime  | String  | 更新时间


#### 农事记录列表

请求路径 api/record/page

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
 pageNo  | Integer | 是 | 1| 页码
 pageSize  | Integer | 是 | 10| 大小
farmId  | Long | 是 | | 农场id
plotId  | Long | 否 | | 地块id
cropId  | Long | 否 | | 作物id
plotCropId  | Long | 否 | | 品种作物id
date  | String | 否 | | 时间 2019

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
recordId  | Long  | 记录id
plotName  | String  | 地块
cropName  | String  | 作物
cropVariety  | String  | 批次
cropName  | String  | 作物
time  | String  | 农事时间
updateTime  | String  | 记录时间
farmWorkName  | String  | 农事环节
score  | Integer  | 系统得分
assessFlag  | Integer  | 是否评估标识 0-否 1-是
deductMarks  | Integer  | 人工扣除分
remark  | String  | 系统扣分说明
reason  | String  | 人工扣分说明
status  | String  | 完成状态
images  | Array  | 图片集合
detail  | String  | 详细信息

Ps: 总得分需要前端处理一下  系统得分-人工扣分  score-deductMarks



#### 获取下一个未评估

请求路径 api/record/next

请求方式 GEt

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
farmId  | Long | 是 | | 农场id
plotId  | Long | 否 | | 地块id
cropId  | Long | 否 | | 作物id
plotCropId  | Long | 否 | | 品种作物id

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
recordId  | Long  | 记录id
plotName  | String  | 地块
cropName  | String  | 作物
cropVariety  | String  | 批次
cropName  | String  | 作物
time  | String  | 农事时间
updateTime  | String  | 记录时间
farmWorkName  | String  | 农事环节
score  | Integer  | 系统得分
assessFlag  | Integer  | 是否评估标识 0-否 1-是
deductMarks  | Integer  | 人工扣除分
remark  | String  | 系统扣分说明
reason  | String  | 人工扣分说明
status  | String  | 完成状态
images  | Array  | 图片集合
detail  | String  | 详细信息


#### 评估农事记录

请求路径 api/record/assess

请求方式 POST

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
recordId  | Long | 是 | | 记录id
deductMarks  | Integer | 否 | | 扣分
deductMarksType  | Integer | 否 | | 扣分类型 0-存疑图片 1-严重作弊行为
reason  | String | 否 | | 理由


**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
无



#### 导出记录

请求路径 api/record/exportRecord

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
farmId  | Long | 是 | | 农场id
plotId  | Long | 否 | | 地块id
cropId  | Long | 否 | | 作物id
plotCropId  | Long | 否 | | 品种作物id

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
无


#### 导出品种作物

请求路径 api/crop/export

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
farmId  | Long | 是 | | 农场id
plotId  | Long | 否 | | 地块id
status  | Integer | 否 | | 状态 0-种植中 1-已终止
date  | String | 否 | | 时间 2019

**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
无



#### 作物品种分值列表

请求路径 api/crop/page

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------
 pageNo  | Integer | 是 | 1| 页码
 pageSize  | Integer | 是 | 10| 大小
farmId  | Long | 是 | | 农场id
plotId  | Long | 否 | | 地块id
status  | Integer | 否 | | 状态 0-种植中 1-已终止
date  | String | 否 | | 时间 2019


**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
plotName  | String  | 地块
cropName  | String  | 作物
cropVariety  | String  | 品种
cropName  | String  | 作物
batchTime  | String  | 批次时间
score  | Integer  | 得分
rawScore  | Integer  | 总分
status  | String   | 作物状态


#### 所有客户列表

请求路径 api/farm/user

请求方式 GET

**请求参数**

参数  | 类型  | 是否必填 |示例 | 描述
|---- | ----- | ------  | ------| ------


**响应参数**

参数  | 类型   | 描述
|---- | ----- | ------
id  | String  | 用户名
mobile  | String  | 手机号
name  | String  | 真实姓名

# kexinFarm
# kexinFarm
