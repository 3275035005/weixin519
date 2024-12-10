var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    data: {
        knowledgeList:[],
    },
    onLoad: function () {
        this.getKnowledgeList();
     },


      // 查询所有课程类别
      getKnowledgeList(){
        call.getData('wx/getKnowledge' , this.onSuccessKnowledgeAll, this.onFaiKnowledgeAll);
      },
     onSuccessKnowledgeAll(res) {
      if(res.code == 20000){
        this.setData({
          knowledgeList:res.data.row
      })
    } 
    },
  
    onFaiKnowledgeAll() {
      help.show("网络请求失败");
    }
})