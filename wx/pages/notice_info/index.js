var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    /**
     * 页面的初始数据
     */
    data: {

        title:"",
        content:""

    },  

    onLoad: function (options) {
      const {id} = options;
      this.getInfo(id);
    },
    getInfo(id){
      call.getData('wx/getNoticeById?id='+id , this.onSuccessclassAll, this.onFaiclassAll);
    },
  onSuccessclassAll(res) {
    if(res.code == 20000){
     this.setData({
      title :res.data.row.title,
      content :res.data.row.content,
      createTime :res.data.row.createTime
    })
  }
},
onFaiclassAll() {
  help.show("网络请求失败");
},
})