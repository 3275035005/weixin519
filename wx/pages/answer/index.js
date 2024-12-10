const app = getApp();
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = "";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    questionList: [
      {question:"在社交场合中，你更倾向于：", option:['A. 热情且外向','B. 友善而适应性强',
      'C. 中立，不太在意社交','D. 内向而需要独处时间','E. 宁愿避免社交，感到疲惫'], checked: false},
      {question:"在做决定时，你更依赖于：", option:['A. 直觉和心情','B. 具体的事实和经验',
      'C. 逻辑和分析','D. 个人价值观和原则','E. 避免做决定，感到不确定'], checked: false},
      {question:"当面对新情境时，你的第一反应是：", option:['A. 热衷而充满信心','B. 乐观但略带紧张',
      'C. 中立，适应能力强','D. 谨慎和担忧','E. 害怕并回避'], checked: false},
      {question:"在工作中，你更喜欢：", option:['A. 灵活适应，善于应变','B. 与他人合作，共同努力',
      'C. 逻辑清晰，注重细节','D. 独立工作，追求个人目标','E. 避免工作压力，寻求平静'], checked: false},
      {question:"对于计划和组织，你更倾向于：", option:['A. 灵活调整，不喜欢束缚','B. 与团队协作，共同制定计划','C. 制定详细的计划和安排','D. 按照个人喜好制定计划','E. 不喜欢制定计划，更喜欢随心所欲'], checked: false},
      {question:"在处理冲突时，你更愿意：", option:['A. 寻求共识和妥协','B. 通过沟通解决分歧','C. 避免冲突，保持和谐 ','D. 坚持个人立场','E. 避免参与冲突，感到不安'], checked: false},
      {question:"你更喜欢的学习方式是：", option:['A. 通过实践和体验','B. 与他人一起学习','C. 通过逻辑分析','D. 独立思考和研究','E. 不太喜欢学习，感到厌烦'], checked: false},
      {question:"在压力下，你的应对方式是：", option:['A. 保持冷静，迅速适应','B. 寻求他人支持，共同面对','C. 逻辑分析，找出解决方案','D. 深思熟虑，坚持原则','E. 感到无法应对，可能逃避'], checked: false},
      {question:"你更倾向于关注：", option:['A. 未来和可能性','B. 当前的现实情况','C. 逻辑和事实','D. 个人价值和信仰','E. 不太关注，感到困扰'], checked: false},
      {question:"在团队中，你更愿意担任：", option:['A. 灵活的执行者','B. 协调者和支持者','C. 逻辑分析者','D. 独立的决策者','E. 不太愿意担任角色，感到束缚'], checked: false},

      {question:"在社交场合，你更喜欢：", option:['A. 与他人交谈，充分享受社交氛围','B. 在角落里独自思考，保持独立的思考空间'], checked: false},
      {question:"当需要做出决定时，你更倾向于：", option:['A. 听取他人的建议和意见，考虑集体决策','B. 依据自己的直觉和感觉，相信个人的直觉'], checked: false},
      {question:"你更享受：", option:['A. 与一群朋友一起参加活动或聚会，热衷于社交','B. 与一个密友深入交流，更喜欢深层次的一对一交往'], checked: false},
      {question:"解决问题时，你更倾向于：", option:['A. 依赖已有的事实和经验，注重实际情况','B. 寻找新的可能性和解决方案，追求创新和新颖思维'], checked: false},
      {question:"完成任务时，你更倾向于：", option:['A. 按照预先制定的计划和步骤进行，偏向有组织的工作方式','B. 随机应变和灵活处理，更喜欢灵活性和适应性'], checked: false},
      {question:"面对冲突或困难时，你更倾向于：", option:['A. 保持冷静和理性，强调逻辑的解决方式','B. 保持冷静和理性，强调逻辑的解决方式'], checked: false},
      {question:"做出决定时，你更注重：", option:['A. 逻辑和客观分析，倾向于客观和理性的决策','B. 价值观和个人情感，决策更受个人价值观和情感影响'], checked: false},
      {question:"处理任务时，你更倾向于：", option:['A. 事先做好计划和组织，喜欢按计划进行','B. 随时随地做出调整和变化，更喜欢灵活性和随机性'], checked: false},
      {question:"与他人沟通时，你更注重：", option:['A. 问题的解决和结果，强调解决实际问题','B. 与他人的情感和关系，关心人际关系和情感交流'], checked: false},
      {question:"在工作或学习中，你更喜欢：", option:['A. 按部就班地完成任务，注重有序的工作方式','B. 寻找新的挑战和机会，更喜欢灵活应对新情况'], checked: false}
    ],
    index: 0,
    chooseValue: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.getInit();
  },
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
  // 选中选项事件
  radioChange(e){
    this.data.chooseValue[this.data.index] = e.detail.value;
  },

  // 下一题/提交 按钮
  nextSubmit(){
    // 如果没有选择
    if (this.data.chooseValue[this.data.index] == undefined || this.data.chooseValue[this.data.index].length == 0) {  
      return wx.showToast({
        title: '请选择答案!',
        icon: 'none'
      })
    }
    this.lastJudge();
  },

  // 判断是不是最后一题
  lastJudge(){
    if (this.data.index < this.data.questionList.length - 1) {
      // 如果不是最后一题，则切换下一题
      let index = this.data.index + 1;
      this.setData({
        index
      })
    } else {
      // 如果是最后一题，则提交答卷
      this.addExamRecord()
    }
  },

  // 提交答卷
  addExamRecord(){
    console.log();
    wx.showLoading({
      title: '提交评测中'
    });

    call.request('wx/sendResult/'+userId, this.data.chooseValue, this.onSuccess, this.onFail);


      wx.hideLoading();
  },

  onSuccess(res) {
    wx.hideLoading();
    if (res.code == 20000) {
      help.show("提交成功");
      // 跳转到答题结果页，查看成绩
      wx.reLaunch({
        url: '../answer_info/index?id=' + res.data.id
      });
    } else {
      help.show(res.message)
    }
  },


  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  }

})