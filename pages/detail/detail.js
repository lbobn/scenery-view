// pages/detail/detail.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {  
    hidden:true,
    nocancel:false,
    wordcloudsrc:'',
    spot: {  
      // name: '文体馆',  
      // images: [  
      //   'http://localhost:8080/image/swiper1.png',  
      //   'http://localhost:8080/image/swiper2.png',  
      //   // ... 其他图片链接  
      // ],  
      // intro: '文化馆是县、市一级的群众文化事业单位，有的地方称文化中心、文化活动中心，作用是开展群众文化活动，并给群众文娱活动提供场所 。我国各省市基本都有文化馆，并且随着社会经济发展，人们精神文化水平的提高，越来越多的文化馆将被建设。在国外，正在兴建的很多孔子学院类似于宣传中国传统文化的文化馆。'  ,
      
    },  
    comments: [  
      // {
      //   "id": 5,
      //   "username": "Amber Jimenez",
      //   "scenery_name": "南环体育场",
      //   "comment": "To start working with your server in Navicat, you should first establish a connection               ",
      //   "like": 698,
      //   "create_time": "2024-03-30 22:04:50"
      // }
    ]  ,
    commentContent:'',
    like: false
  },  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // var that = this;
    //获取列表页传来的景点id
    // console.log(options.spotId)
    this.setData({
      spotId: options.spotId
    })
  },

  handleCommentInput: function(e) {  
    // console.log(e.detail.value)
    this.setData({  
      commentContent: e.detail.value  
    });  
  },  
  
  submitComment: function() {  
    // 提交评论的逻辑  
    // ...  
    // console.log(this.data.commentContent);
    var that = this
    var token = app.globalData.token
    // 判断是否登录
    // console.log(token.length == 0)
    if(app.globalData.token == null || token.length == 0){
      //未登录，跳转登录页
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
      //非空判断
      if(this.data.commentContent != null && this.data.commentContent.length != 0){
        // 提交评论给后端
     wx.request({
       url: app.globalData.serverApi + '/comment/add', //仅为示例，并非真实的接口地址
       method:'POST',
       data: {
         "comment": that.data.commentContent,
         "scenery_id":that.data.spot.id
       },
       header: {
         'content-type': 'application/json', // 默认值,
         'token': app.globalData.token
       },
       success (res) {
         if(res.data.code === 1){
             wx.showToast({
               title: '成功',
             })
             setTimeout((res)=>{
               that.onShow()
             },1000)
         }else{
           wx.showModal({
             title: '提示',
             content: '评论失败,'+res.data.msg,
             showCancel:false,
             complete: (res) => {
 
             }
           })
         }
       }
     })
     }
    }
    // 清空输入框内容  
    this.setData({  
      commentContent: ''  
    });  
  },  
  
  handleCollect: function() {  
    // 收藏的逻辑  
    // ...  
    this.setData({  
      like: !this.data.like  
    });
  }  ,

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    var that = this
     //获取页面数据
     wx.request({
      url: app.globalData.serverApi+ '/detail/'+ that.data.spotId,
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          spot: res.data.data
        })

      },
      fail(res) {
        console.error("失败", res.data)
      },
      complete() {
        console.log("调用完成")
      }
    })
    //获取评论数据
    wx.request({
      url: app.globalData.serverApi+ '/detail/comments/'+that.data.spotId,
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          comments: res.data.data
        })

      },
      fail(res) {
        console.error("失败", res.data)
      },
      complete() {
        console.log("调用完成")
      }
    })
   
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

  },

  getWordcloud(){
    var that = this
    var spotid = this.data.spot.id
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: app.globalData.serverApi + '/comment/wordcloud/' + spotid, //调用后台接口的全路径
      // data: {memberId: this.data.member.id},
      method: "GET",
      // header: {
      //   'Content-type': 'application/x-www-form-urlencoded',
      //   'Cookie': app.globalData.userInfo && app.globalData.userInfo.cookie ? app.globalData.userInfo.cookie : '',
      // },
      responseType: 'arraybuffer', //此处是请求文件流，必须带入的属性
      success: res => {
        if (res.statusCode === 200) {
          console.log(res.data)
          const fs = wx.getFileSystemManager(); //获取全局唯一的文件管理器
          fs.writeFile({
            filePath: wx.env.USER_DATA_PATH + "/wordcloud.jpg", // wx.env.USER_DATA_PATH 指定临时文件存入的路径，后面字符串自定义
            data: res.data,
            encoding: "binary", //二进制流文件必须是 binary
            success (res){
              // wx.openDocument({ // 打开文档
              //   filePath: wx.env.USER_DATA_PATH + "/wordcloud.jpg",  //拿上面存入的文件路径
              //   showMenu: true, // 显示右上角菜单
              //   success: function (res) {
              //     setTimeout(()=>{wx.hideLoading()},500)
              //   }
              // })
              
              that.setData({
                wordcloudsrc:wx.env.USER_DATA_PATH + "/wordcloud.jpg",
                hidden: false
              })
              wx.hideLoading()

            }
          })
        }
      }
    })

  },
    /**
     * 点击取消
     */
    cancel: function(){
      this.setData({
           hidden: true
      });
  },

  /**
   *  点击确认
   */
  confirm: function(){
      this.setData({
        hidden: true
      })

    }
})