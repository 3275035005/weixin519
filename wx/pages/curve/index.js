// 引入 echarts 文件
import * as echarts from '../../ec-canvas/echarts';

let rowRows = {
  day:[],
  score:[]
};
// 定义 initChart 方法
// initChart 需要传递四个参数 canvas, width, height, dpr
function initChart(canvas, width, height, dpr) {
  // 使用引入的 echarts的init方法对 chart 变量赋值进行初始化
  const chart = echarts.init(canvas, null, {
    width: width,
    height: height,
    devicePixelRatio: dpr // 像素
  });

  canvas.setChart(chart);

  var option = {
    xAxis: {
      type: 'category',
      data: rowRows.day
    },
    yAxis: {
      type: 'value',
      max: 100, // y轴最大值
      min: 0,  // y轴最小值
      interval: 20 // 间隔
    },
    series: [
      {
        data: rowRows.score,
        type: 'line'
      }
    ]
  }

  chart.setOption(option);

  return chart;
}
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
Page({
  data: {
    // 此处的ec名称与wxml结构中命名保持一致
    ec: {
      // 使用 onInit 方法定义
      onInit: initChart
    },
    content:'暂无'
  },
  onLoad: function () {
    this.getInit();
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
    } else {
      this.getInfo();
    }
  },
   // 
   getInfo(){
    call.getData('wx/getCurve/'+userId , this.onSuccessCounselorAll, this.onFaiCounselorAll);
  },
  onSuccessCounselorAll(res) {
    if(res.code == 20000){
      rowRows = {
        score:res.data.row.score,
        day: res.data.row.day,
      }
      this.setData({
        ec: {
          // 使用 onInit 方法定义
          onInit: initChart
        }
      })
     
      let score = res.data.row.scoreTwo
      console.log(score);
      if(score >= 85){
        this.setData({
            content: '在心理健康方面表现出色，个体展现出稳定的情绪、积极的心态和强大的应对能力。他们可能具有较高的自我意识和情感调节能力，能够有效处理生活中的压力和挑战。这些人通常能够保持平衡的心理状态，对生活中的变化和挑战有着积极的态度，能够建立健康的人际关系并有效地应对各种情境。'
        })
      }else if(85 > score && 75<=score ){
        this.setData({
            content: '在心理健康方面表现良好，具有较好的情绪管理和适应能力。这些人可能在面对一些挑战时感觉有些压力，但通常能够有效地应对，并保持相对平衡的心理状态。他们可能会寻求支持和资源来帮助自己应对压力和挑战，同时能够保持积极的心态和适当的情绪表达。'
        })
      }else if(74 > score && 60<=score ){
        this.setData({
            content: '在心理健康方面表现一般，可能面临一些情绪波动或适应困难。这些人可能在某些情况下感受到一定的压力，需要更多的支持和策略来帮助他们应对生活中的挑战。他们可能会寻求帮助以改善情绪状态和应对策略，同时可能会感受到一定的情绪困扰和压力。'
        })
      }else if(60 > score){
        this.setData({
            content: '在心理健康方面表现不佳，可能经历较大的情绪困扰和应对困难。这些人可能需要专业的心理健康支持，以帮助他们理解和处理内在的问题，改善心理状态和生活质量。他们可能会经历严重的情绪困扰，难以有效地应对生活中的挑战，需要更多的支持和资源来帮助他们重建心理健康。'
        })
      }
    } 
  },
  onFaiCounselorAll() {
    help.show("网络请求失败");
  },

});