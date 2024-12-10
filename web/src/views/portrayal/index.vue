<template>
  <div style="margin-top: 200px">
    <!-- 统计图容器 -->
    <div id="main" style="width: 100%;height: 500px;" />
  </div>
</template>
<script>
import { getTypeHistogram } from '@/api/result'
import echarts from 'echarts'
export default {
  data() {
    return {

    }
  },
  created() {
    getTypeHistogram().then(result =>{
      let echarts = require('echarts')
      // 初始化echarts实例
      let myChart = echarts.init(document.getElementById('main'))
      // 配置参数
      let option = {
        tooltip: {},

        xAxis: {
          data: result.data.data.type
        },
        yAxis: {},
        series: [{
          type: 'bar',//line、pie
          data:  result.data.data.number,
          //给柱状图整体设置颜色
          itemStyle: {
            normal: {
              color: '#4ad2ff'
            },
          }
        }]
      }
      myChart.setOption(option)
    })
  }
}
</script>
