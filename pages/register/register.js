var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    current:1,
    codeText:'获取验证码',
    counting:false,
    username: '',
    nickname:'',
    password:'',
    passwordCheck:''
  },
  // 登陆注册监听
  click(e){
    let index = e.currentTarget.dataset.code;
    this.setData({
      current:index
    })
  },
  //获取验证码 
  getCode(){
    var that = this;
    if (!that.data.counting) {
      wx.showToast({
        title: '验证码已发送',
      })
      //开始倒计时60秒
      that.countDown(that, 60);
    } 
  },
  //倒计时60秒
  countDown(that,count){
    if (count == 0) {
      that.setData({
        codeText: '获取验证码',
        counting:false
      })
      return;
    }
    that.setData({
      counting:true,
      codeText: count + '秒后重新获取',
    })
    setTimeout(function(){
      count--;
      that.countDown(that, count);
    }, 1000);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },
  usernameInput(event){
    // console.log(event.detail)
    this.setData({
      username: event.detail.value
    })
  },
  nicknameInput(event){
    this.setData({
      nickname: event.detail.value
    })
  },
  passwordInput(event){
    this.setData({
      password:event.detail.value
    })
  },
  passwordCheck(event){
    this.setData({
      passwordCheck: event.detail.value
    })
  },

  commit(e){
    // console.log("提交")
    var that = this
    var type = e.currentTarget.dataset.type
    if(type == 1){
      console.log("登录")
      // 登录逻辑
      wx.request({
        url: app.globalData.serverApi + '/login' , 
        data: {
          username: that.data.username,
          password: that.data.password
        },
        method:'POST',
        // header: {
        //   'content-type': 'application/json' // 默认值
        // },
        success (res) {
          console.log(res.data)
          if(res.data.code === 1){
            console.log("登录成功")
            wx.showToast({
              title: '登录成功',
            })
            //存储token,返回上一页
            wx.setStorage({
              key:"token",
              data:res.data.data.token
            })
            wx.setStorage({
              key:"userInfo",
              data:res.data.data
            })
            app.globalData.userInfo = res.data.data
            app.globalData.token = res.data.data.token
            
            console.log(app.globalData) 
            var pages = getCurrentPages();
            var prevPage = pages[pages.length - 2]; // 获取上一个页面实例对象
            setTimeout((res)=>{
              wx.navigateBack({
                delta:1,
                success(){
                  prevPage.onLoad()
                }
              })
            },1000)
            
          }else{
            wx.showModal({
              title: '提示',
              content: res.data.msg,
              showCancel:false,
              complete: (res) => {
              }
            })
            console.log(res.data.msg)
          }
          
        }
      })

    }else{
      // console.log("注册")
      if(that.data.password !== that.data.passwordCheck){
        //两次密码不一致
        // console.log("两次密码不一致")
        wx.showModal({
          title: '提示',
          content: "两次密码不一致",
          showCancel:false,
          complete: (res) => {
            console.log("点击确定")
          }
        })
      }else{
        // 注册逻辑
        console.log("密码一致")
        wx.request({
          url: app.globalData.serverApi + '/register' , 
          data: {
            username: that.data.username,
            nickname: that.data.nickname,
            password: that.data.password
          },
          method:'POST',
          success (res) {
            if(res.data.code === 1){
              // console.log("注册成功")
              wx.showToast({
                title: '注册成功',
              })
            }else{
              // console.log(res.data.msg)
              wx.showModal({
                title: '提示',
                content: res.data.msg,
                showCancel:false,
                complete: (res) => {
                  console.log("点击确定")
                }
              })
            }
          },
          fail(res) {
            console.error("失败", res.data)
          },
        })
      }
    }
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
