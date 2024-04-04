var app = getApp()
Page({
  /**
  * 页面的初始数据
  */
  data: {
    avatarUrl: "",
    name: "",
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: false,
    canIUseOpenData: false,
    isLogin: null,
    mySet: [
      /* {
        'name': "我的预约",
        'img': "../images/预约.png"
      }, */
      {
        'name': "我的收藏",
        'img': "../../img/like.png"
      },
      /* {
        'name': "个人设置",
        'img': "../images/设置.png"
      }, */
      {
        'name': "关于我们",
        'img': "../../img/about.png"
      },
    ]
  },
  /**
  * 生命周期函数--监听页面加载
  */
  onLoad: function (options) {
    var login = app.globalData.token != null && app.globalData.token.length != 0
    console.log(app.globalData)
    console.log("当前登录"+ login )
    this.setData({
      userInfo: app.globalData.userInfo,
      isLogin: login
    })
  },
  login(){
    wx.navigateTo({
      url: '../register/register',
    })
  },
  
  /**
* 用户信息获取权限
*/
  // getUserProfile: function () {
  //   /* wx.login({
  //     timeout:10000,
  //     success: (result) => {
  //       console.log("登录结果"+result.code)
        
  //     },
  //     fail: () => {},
  //     complete: () => {}
  //   }); */
      
  //   if (this.data.hasUserInfo == false) {
  //     wx.getUserProfile({
  //       desc: '信息仅作为个人展示',
  //       success: (res) => {
  //         console.log('获取成功', res)
  //         wx.setStorage({
  //           data: res.userInfo,
  //           key: 'userInfo',
  //         });
  //         this.setData({
  //           userInfo: res.userInfo,
  //           hasUserInfo: true,
  //           canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName'), // 如需尝试获取用户信息可改为false
  //         })
  //         this.onShow();
  //       }
  //     })
  //   } else {
  //     wx.showToast({
  //       title: '您已登录',
  //     })
  //   }
  // },
  /**
* 点击我的预约等板块后进行页面跳转
*/
  onMySet: function (e) {
    /* if (e.currentTarget.dataset.type == "个人设置") {
      wx.navigateTo({
        url: '/pages/index/setting/setting',
      })
    }
    else if (e.currentTarget.dataset.type == "我的预约") {
      wx.navigateTo({
        url: '/pages/index/want/want',
      })
    } */
    var that = this
    var token = app.globalData.token
    if (e.currentTarget.dataset.type == "我的收藏") {
     
    // 判断是否登录
    if(app.globalData.token == null || token.length == 0){
      //未登录
      wx.showModal({
        title: '提示',
        content: '您未登录,请登录后再操作',
        complete: (res) => {
          if (res.cancel) {
            
          }
      
          if (res.confirm) {
            wx.navigateTo({
              url: '../register/register',
            })
          }
        }
      })
    }else{
      wx.navigateTo({
        url: '/pages/favor/favor',
      })
    }
      
    }else if(e.currentTarget.dataset.type == "关于我们"){
      wx.showModal({
        title: '提示',
        content: '开发不易，请多多支持',
        showCancel:false,
        complete: (res) => {
          if (res.cancel) {
            
          }
      
          if (res.confirm) {
            
          }
        }
      })
    }
  },
  /**
  * 生命周期函数--监听页面显示
  */
  // onShow: function () {
  //   var useInfo = wx.getStorageSync('userInfo')
  //   var that = this;
  //   that.setData({
  //     avatarUrl: useInfo.avatarUrl,
  //     name: useInfo.nickName,
  //   })
  // },
  logout: function () {
    var that = this
    wx.removeStorage({
      key: 'userInfo',
      success(res) {
        wx.showModal({
          title: '提示',
          content: '确认退出登录？',
          cancelText: '取消',
          confirmText: '确认',
          confirmColor: '#000000',
          cancelColor: '#576b95',
          success(res) {
            if (res.confirm) {
              // 退出登录
              wx.clearStorageSync()
              app.globalData.userInfo = null
              app.globalData.token = null
              // console.log(wx.globalData)
              that.setData({
                userInfo: null,
                isLogin: false
              })
              wx.showToast({
                title: '注销登录成功',
              })
              setTimeout((res)=>{
                
                that.onShow()
              },1000)
              
              // wx.reLaunch({
              //   url: '/pages/me/me',
              // })
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
      }
    })
  }
})