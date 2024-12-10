var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

  data: {
    knowledgeList: [],
  },
  onLoad: function () {
    this.getKnowledgeList();
  },

  // 查询所有课程类别
  getKnowledgeList() {
    let userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }
    call.getData('wx/appointmentFavoriteList/' + userId, this.onSuccessKnowledgeAll, this.onFaiKnowledgeAll);

  },

  onSuccessKnowledgeAll(res) {
    if (res.code == 20000) {
      this.setData({
        knowledgeList: res.data.row
      })
    }
  },

  onFaiKnowledgeAll() {
    help.show("网络请求失败");
  }
})