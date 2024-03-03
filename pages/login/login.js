// pages/login/login.js
const app = getApp()
Page({
   /**
   * 页面的初始数据
   */
  data: {
    motto: '发布',
    // 初始化用户信息：userInfo hasUserInfo
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
   // 点击“点击授权一键登录，调用getUserInfo获取用户信息”
  getUserInfo: function (e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
    // 获取到用户信息后，调用wx.navigateBack()返回上一页，即个人中心页，wx.navigateBack()一定要在这个位置：
    let currentPages =  getCurrentPages();
    console.log("当前页面栈"+currentPages.length)
    if(currentPages.length>1){
      wx.navigateBack()
    }else{
      wx.redirectTo({
        url: '/pages/home/home',
        success: (result) => {
          
        },
        fail: () => {},
        complete: () => {}
      });
        
    }
      
  },
  start:function(){
    let currentPages = getCurrentPages();
    console.log("当前页面栈" + currentPages.length)
    if (currentPages.length > 1) {
      wx.navigateBack()
    } else {
      wx.redirectTo({
        url: '/pages/home/home',
        success: (result) => {
         },
        fail: () => { },
        complete: () => { }
      });
     }
  },
   /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
   },
   /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
   },
   /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
   },
   /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
   },
   /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
   },
   /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
   },
   /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
   },
   /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {
   }
})