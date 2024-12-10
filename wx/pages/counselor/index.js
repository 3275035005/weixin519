var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    data: {
        counselorList:[]
    },
    onLoad: function () {
        this.getCounselorList();
     },
    // 查询所有咨询师
    getCounselorList(){
      call.getData('wx/getCounselor' , this.onSuccessCounselorAll, this.onFaiCounselorAll);
    },
    onSuccessCounselorAll(res) {
      if(res.code == 20000){
        this.setData({
            counselorList:res.data.row
      })
      } 
    },
    onFaiCounselorAll() {
      help.show("网络请求失败");
    },

})