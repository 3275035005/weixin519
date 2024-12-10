// pages/login/index.js
const app = getApp();
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userInfo = null;
Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		username:'',
		password:'',
		version: app.globalData.version,
	},
    onLoad:function(){
        userInfo = wx.getStorageSync('token')
        console.log(userInfo)
        if(userInfo!=null&&userInfo!=''){
            wx.reLaunch({
                url: '/pages/index/index'
            })
        }
    },
	getUserValue:function(e){
		this.setData({
			username : e.detail.value
		})
	},
	
	getPasswordValue:function(e){
		this.setData({
			password : e.detail.value
		})
	},

	

	// 用户名登录 - 提交表单信息
	login_User_Button:function(){
		if(this.data.username == ""){
			wx.showToast({
				title: '用户名不能为空',
				icon: 'none',
				duration: 1000
			})
			return false;
		}
		if(this.data.password == ""){
			wx.showToast({
				title: '密码不能为空',
				icon: 'none',
				duration: 1000
			})
			return false;
        }
        wx.showLoading({
          title: '正在登录...'
        });
        call.request('wx/login',{
          username:this.data.username,
          password:this.data.password
      }, this.onSuccess, this.onFail);
    },
    onSuccess(res) {
        wx.hideLoading();
        if(res.success){
            wx.setStorageSync('token', res.data.token)
            wx.reLaunch({
                url: '/pages/index/index'
            })
        }else{
            help.show(res.message)
        }
    },
    onFail() {
        wx.hideLoading();
        help.show("网络请求超时,请稍后再试")
    },

    toRegister(){
        wx.navigateTo({
          url: '/pages/register/index',
        })
    }



})