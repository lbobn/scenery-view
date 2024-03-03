// pages/home/home.js
var wxPano = requirePlugin("wxPano")
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // console.log(option.query)
        const eventChannel = this.getOpenerEventChannel()
        // eventChannel.emit('acceptDataFromOpenedPage', { data: 'test' });
        // eventChannel.emit('someEvent', { data: 'test' });
        // 监听acceptDataFromOpenerPage事件，获取上一页面通过eventChannel传送到当前页面的数据
        eventChannel.on('acceptDataFromOpenerPage', function (data) {
            console.log(data)
        })
        
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
        wxPano.onReady = function () { //wxPano初始化完成后会触发此事件
            // wxPano.enableTouch();
        }
        wxPano.config({
            panolist: [{
                name: "xindamen",
                //"http://localhost:8080/image/img_0.png"
                src: "http://127.0.0.1:8080/image/img_3.png",
                infospots: [ //信息标记
                    {
                        type: "modal",
                        modal: {
                            title: "wxPano",
                            content: "欢迎使用wxPano"
                        },
                        position: {
                            x: 0.092,
                            y: 0.434
                        },
                        size: 1,
                        icon: "info",
                        bindcamera: true,
                        bindsize: 0.5,
                        bindicon: "info",
                        bindopacity: 0.75,
                        bindposition: {
                            x: 0.5,
                            y: 0.75
                        }
                    },
                    {
                        type: "page",
                        page: function () {
                            wx.navigateTo({
                                url: "ar",
                                success(evt) {
                                    console.log(evt);
                                }
                            })
                        },
                        position: {
                            x: 0.437,
                            y: 0.447
                        },
                        size: 1,
                        icon: "info"
                    }
                ]
            }],
            request: wx.request,
            loader: "GLLoader",
            entryname: "xindamen"
        });
    },
    covertap: function () {
        var panoId = wxPano.addPano({
            name: "dongdamen",
            src: 'https://game.flyh5.cn/resources/game/wechat/szq/krpano/krpano_03.jpg',
            infospots: [{
                type: "pano",
                entryname: "xindamen",
                position: {
                    x: 0.695,
                    y: 0.503
                },
                size: 1,
                icon: "arrow"
            }, {
                type: "modal",
                modal: {
                    title: "东大门",
                    content: "对面有公交站和唐家湾轻轨站"
                },
                position: {
                    x: 0.092,
                    y: 0.434
                },
                size: 1,
                icon: "info"
            }]
        });
        wxPano.navigateMethod({
            type: "pano",
            entryname: "dongdamen"
        });
    },
    setCameraLookAt: function () {
        wxPano.setCameraLookAt({
            x: 0.5,
            y: 0.5
        })
    },
    enableTouch: function () {
        wxPano.enableTouch();
    },
    disableTouch: function () {
        wxPano.disableTouch();
    },
    getPanoInfo: function () {
        console.log(wxPano.getPanoInfo());
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