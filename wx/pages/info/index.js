var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {

    name: '',// 姓名
    phone: '', // 手机号
    username: '', // 账号
    age:'', //年龄
    sexList: [
      {title:'请选择性别',id:''},
      {title:'女',id:'0'},
      {title:'男',id:'1'}
    ],
    sex_Index: 0
  },
  onLoad: function () {
    this.getInit();
    this.getInfo();
   
  },
  /**
 * 获取登录用户信息
 */
  getInit() {
    userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }
  },

  getInfo() {
    call.getData('wx/getUserInfo?id=' + userId, this.onSuccessInfo, this.onFaiInfo);
  },
  onSuccessInfo(res) {
    if (res.code == 20000) {
      this.setData({
        name: res.data.row.name,
        phone: res.data.row.phone,
        username:  res.data.row.username,
        age:  res.data.row.age,
        sex_Index:  parseInt(res.data.row.sex)+1 
      })
    }
  },
  onFaiInfo() {
    help.show("网络请求失败");
  },

  // 修改
  updateInfo() {

    var myreg = /^(14[0-9]|13[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]19[0-9])\d{8}$$/;
    if (this.data.phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    } else if (!myreg.test(this.data.phone)) {
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if (this.data.name == "") {
      wx.showToast({
        title: '姓名不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if (this.data.sex_Index == 0) {
        wx.showToast({
          title: '请选择性别',
          icon: 'none',
          duration: 1000
        })
      } 
       if (this.data.age == "") {
        wx.showToast({
          title: '年龄不能为空',
          icon: 'none',
          duration: 1000
        })
      }
    wx.showLoading({
      title: '修改中...'
    });
    call.request('wx/updateUserInfo', {
      id: userId,
      name: this.data.name,
      phone: this.data.phone,
      age: this.data.age,
      sex:this.data.sexList[this.data.sex_Index].id
    }, this.onSuccess, this.onFail);
  },

  onSuccess(res) {
    wx.hideLoading();
    if (res.code == 20000) {
      help.show("修改成功")
      setTimeout(function () {
        wx.reLaunch({
          url: '/pages/wode/wode'
        })
      }, 2000)

    } else {
      help.show(res.message)
    }
  },

    // 性别
    bindRegionChange: function (e) {
        this.setData({
          sex_Index : e.detail.value
        })
      
      },
  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  }
})