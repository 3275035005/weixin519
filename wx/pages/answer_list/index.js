
//获取应用实例

const app = getApp()
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    historyList: []
  },
  
  onLoad() {
    this.getInit();
  },
  getInit() {
    let userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }

    call.getData('wx/getResultByUserId/' + userId , this.onSuccessAll, this.onFaiAll);
  },

  onSuccessAll(res) {
    if (res.code == 20000) {
      this.setData({
        historyList: res.data.data
      })
    }
  },
  onFaiAll() {
    help.show("网络请求失败");
  },

  goToResult(event){
    const { id } = event.currentTarget.dataset;
    wx.navigateTo({
      url: '../answer_info/index?id=' + id
    })
  },
})
