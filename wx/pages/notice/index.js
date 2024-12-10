var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    /**
     * 页面的初始数据
     */
    data: {
        list:[]
    },
    onLoad: function () {
     this.getInit();
    },
    getInit(){
      call.getData('wx/getNotice' , this.onSuccessclassAll, this.onFaiclassAll);
    },
  onSuccessclassAll(res) {
    if(res.code == 20000){
     this.setData({
      list:res.data.row
    })
  }
},
onFaiclassAll() {
  help.show("网络请求失败");
}
})
