var call = require("../../utils/request.js")
var help = require("../../utils/help.js")


Page({
  data: {
    // --- 轮播图 --- //
    bannerList: [
        '/images/button2.png','/images/button1.png'
    ],
    // --- 通知 --- //
    noticeList: [],
    // --- 心理知识--- //
    knowledgeList: []
  },
  onLoad: function () {
    this.getHome();
    this.getInit();

  },
  /**
   * 获取登录用户信息
   */
  getInit() {
    let userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }
  },
  // 获取班级列表
  getHome() {
    call.getData('wx/getHome', this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        noticeList: res.data.row.notices,
        knowledgeList: res.data.row.knowledges
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },




})
