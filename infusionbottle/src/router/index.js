import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      // component: () => import('@/views/login')
      component: resolve => require(['@/views/login'], resolve),
    },
    //公示名单更多
    {
      path: '/publicity',
      component: resolve => require(['@/views/publicity'], resolve),
    },
    {
      path: '/gongshiMore',
      component: resolve => require(['@/views/gongshiMore'], resolve),
    },
    {
      path: '/qiyeMore',
      component: resolve => require(['@/views/qiyeMore'], resolve),
    },
    //大屏展示
    {
      path: '/bigPage',
      component: resolve => require(['@/views/bigpage/index'],resolve),
    },
    //处理厂大屏展示
    {
      path: '/treatmentplantBigPage',
      component: resolve => require(['@/views/treatmentplant/bigpage'],resolve),
    },
    //运输公司大屏展示
    {
      path: '/transportcompanyBigPage',
      component: resolve => require(['@/views/transportcompany/bigpage'],resolve),
    },
    {
      path: '/index',
      component: resolve => require(['@/views/index/index'], resolve),
      redirect: '/userlist',
      children:[

          //重要通知
         {
          path: '/newsList',
          component: resolve => require(['@/views/news/news'],resolve),
        },
        {
          path: '/Manual',
          component: resolve => require(['@/views/news/manual'],resolve),
        },
        {
          path: '/upManual',
          component: resolve => require(['@/views/news/upmanual'],resolve),
        },
         //公示名单
         {
          path: '/nterPrise',
          component: resolve => require(['@/views/shangwu/nterprise'],resolve),
        },
         //医院个人信息
         {
            path: '/yiyuanCenter',
            component: resolve => require(['@/views/index/yiyuancenter'],resolve),
          },

          //处理厂个人信息
         {
            path: '/chuliCenter',
            component: resolve => require(['@/views/index/chulicenter'],resolve),
          },
          //运输公司个人信息
         {
          path: '/yunshuCenter',
          component: resolve => require(['@/views/index/yunshucenter'],resolve),
        },
          //用户列表
          {
            path: '/userlist',
            component: resolve => require(['@/views/userlist/index'],resolve),
          },
          //商务部门用户列表
          {
            path: '/shangwuUser',
            component: resolve => require(['@/views/userlist/shangwu'],resolve),
          },
          // 医疗机构管理
          {
            path: '/hospitalList',
            component: resolve => require(['@/views/hospital/hospitallist'],resolve),
          },
          {
            path: '/joinList',
            component: resolve => require(['@/views/hospital/jiaojie'],resolve),
          },
          {
            path: '/Department',
            component: resolve => require(['@/views/hospital/department'],resolve),
          },
          //垃圾袋管理
          {
            path: '/Garbagebag',
            component: resolve => require(['@/views/garbagebag/garbagebag'],resolve),
          },
           //输液袋管理
           {
            path: '/Infusionbag',
            component: resolve => require(['@/views/infusionbag'],resolve),
          },
          //垃圾袋码
          {
            path: '/barCode',
            component:
            resolve => require(['@/views/hospital/barcode.vue'],resolve),
          },
          //再利用企业管理
          {
            path: '/productionPlant',
            component: resolve => require(['@/views/productionplant'],resolve),
          },
          {
            path: '/productionJoinList',
            component: resolve => require(['@/views/productionplant/joinList'],resolve),
          },
          //回收利用企业管理
          {
            path: '/treatmentPlant',
            component:
            resolve => require(['@/views/treatmentplant'],resolve),
          },
          //回收利用医疗机构
          {
            path: '/treatmentPlantHospital',
            meta: {
              keepAlive: true
            },
            component: resolve => require(['@/views/treatmentplant/hospital'],resolve),
          },
          //回收利用医疗机构交接记录
          {
            path: '/treatmentPlantHospitalJoin',
            component: resolve => require(['@/views/treatmentplant/hospitaljoin'],resolve),
          },
          //回收利用企业交接记录汇总
          {
            path: '/treatmentPlantRecords',
            component: resolve => require(['@/views/treatmentplant/records'],resolve),
          },
          //回收利用企业出库记录
          {
            path: '/outDetail',
            meta: {
              keepAlive: true
            },
            component: resolve => require(['@/views/treatmentplant/outdetail'],resolve),
          },
          //回收利用企业入库记录
          {
            path: '/inDetail',
            component: resolve => require(['@/views/treatmentplant/indetail'],resolve),
          },
          //暂存点列表
          {
            path: '/yunShu',
            meta: {
              keepAlive: true
            },
            component: resolve => require(['@/views/treatmentplant/yunshu'],resolve),
          },
          //暂存点车辆列表
          {
            path: '/yunShuCarList',
            component: resolve => require(['@/views/treatmentplant/yunshucar'],resolve),
          },
          //破碎料入库统计
          {
            path: '/dataShow',
            component: resolve => require(['@/views/treatmentplant/producnews/datashow'],resolve),
          },
          //破碎料出库统计
          {
            path: '/dataShowOut',
            component: resolve => require(['@/views/treatmentplant/producnews/datashowout'],resolve),
          },
          //破碎料库存统计
          {
            path: '/kuCunShow',
            component: resolve => require(['@/views/treatmentplant/producnews/kucun/datashow'],resolve),
          },
          //破碎料出库
          {
            path: '/producOut',
            component: resolve => require(['@/views/treatmentplant/producnews/productout'],resolve),
          },
          //破碎料入库
          {
            path: '/producIn',
            component: resolve => require(['@/views/treatmentplant/producnews/productin'],resolve),
          },
          //破碎料类别列表
          {
            path: '/producType',
            component: resolve => require(['@/views/treatmentplant/producnews/type'],resolve),
          },
          //颗粒料入库统计
          {
            path: '/graindataShow',
            component: resolve => require(['@/views/treatmentplant/grain/datashow'],resolve),
          },
          //颗粒料出库统计
          {
            path: '/graindataShowOut',
            component: resolve => require(['@/views/treatmentplant/grain/datashowout'],resolve),
          },
          //颗粒料库存统计
          {
            path: '/kuCunShowKeli',
            component: resolve => require(['@/views/treatmentplant/grain/kucun/datashow'],resolve),
          },
          //颗粒料出库
          {
            path: '/grainOut',
            component: resolve => require(['@/views/treatmentplant/grain/grainout'],resolve),
          },
          //颗粒料入库
          {
            path: '/grainIn',
            component: resolve => require(['@/views/treatmentplant/grain/grainin'],resolve),
          },
          //颗粒料类别列表
          {
            path: '/grainproducType',
            component: resolve => require(['@/views/treatmentplant/grain/type'],resolve),
          },

          //回收利用企业订单
          {
            path: '/order',
            component: resolve => require(['@/views/treatmentplant/order'],resolve),
          },


          //出库详细
          {
            path: '/outDetailNews',
            component: resolve => require(['@/views/treatmentplant/outdetailnews'],resolve),
          },
          //回收物流
           {
            path: '/transportcompany',
            meta: {
              keepAlive: true
            },
            component: resolve => require(['@/views/transportcompany'],resolve),
          },
          
          //车辆管里
          {
            path: '/carList',
            component: resolve => require(['@/views/transportcompany/carlist'],resolve),
          },
          //车次管理
          {
            path: '/Truck',
            meta: {
              keepAlive: true
            },
            component: resolve => require(['@/views/transportcompany/truck'],resolve),
          },
          //入库统计管理
          {
            path: '/rukuList',
            component: resolve => require(['@/views/transportcompany/ruku'],resolve),
          },


          //监管单位
          {
            path: '/SuperVisionUnit',
            component: resolve => require(['@/views/supervisionunit'],resolve),
          },
          //设置管理员
          {
            path: '/adminUser',
            component: resolve => require(['@/views/supervisionunit/adminuser'],resolve),
          },
          //大屏展示
          {
            path: '/bigPage',
            component: resolve => require(['@/views/bigpage/index'],resolve),
          },
      ],
      
    },
  ]
})
