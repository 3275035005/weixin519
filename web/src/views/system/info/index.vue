<template>
  <div class="app-container">
    <template>
      <div class="app-container">
        <el-form label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="personageVo.username" :disabled="true"/>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="personageVo.name"/>
          </el-form-item>
          <el-form-item label="头像">
            <!-- 头衔缩略图 -->
            <pan-thumb :image="String(personageVo.avatar)"/>
            <!-- 文件上传按钮 -->
            <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
            </el-button>

            <!--
                v-show：是否显示上传组件
                :key：类似于id，如果一个页面多个图片上传控件，可以做区分
                :url：后台上传的url地址
                @close：关闭上传组件
                @crop-upload-success：上传成功后的回调 -->
            <image-cropper
              v-show="imagecropperShow"
              :width="300"
              :height="300"
              :key="imagecropperKey"
              url="http://localhost:9999/files/file"
              field="file"
              @close="close"
              @crop-upload-success="cropSuccess"/>

          </el-form-item>

          <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="isSaveAndAlter">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
    </template>
  </div>
</template>

<script>
  import {update, getInfo} from '@/api/user'
  import ImageCropper from '@/components/ImageCropper' // 引入头像
  import PanThumb from '@/components/PanThumb'
  import { getToken } from '@/utils/auth'
  export default {
    components: {ImageCropper, PanThumb}, // 声明
    data() {

      return {
        personageVo: {
          avatar: ''  // 初始化默认地址
        },
        saveBtnDisabled: false,
        // 上传头像的组件是否显示
        imagecropperShow: false,
        imagecropperKey: 0, // 上传组件的Key值
      }

    },
    created() {
      this.init();
    },

    methods: {

      // 关闭弹窗的方法
      close() {
        this.imagecropperShow = false; // 关闭弹框
        this.imagecropperKey = this.imagecropperKey + 1; // 保证标识唯一解决bug
      },

      // 文件上传成功执行的方法
      cropSuccess(res) {
        this.imagecropperKey = this.imagecropperKey + 1;
        this.imagecropperShow = false; // 关闭弹框
        // 上传成功返回地址
        this.personageVo.avatar = res.row;
      },

      init() {
        this.findById(getToken())
      },

      // 修改ger
      isSaveAndAlter() {
        update(this.personageVo)
          .then(response => {
            // 提示信息
            this.$message({
              type: 'success',
              message: '修改成功'
            });
          })
      },

      // 通过id查询
      findById(id) {
        getInfo(id)
          .then(response => {
            console.log(response)
            this.personageVo = response.data.data;
          })
      }
    },

  }
</script>
