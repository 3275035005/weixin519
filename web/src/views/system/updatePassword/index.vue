<template>
  <div class="app-container">
    <template>
      <div class="app-container">
        <el-form label-width="120px">
          <el-form-item label="旧密码">
            <el-input type="password" v-model="dataVo.oldPassword" placeholder="请输入旧密码"/>
          </el-form-item>
          <el-form-item label="新密码">
            <el-input type="password" v-model="dataVo.password" placeholder="请输入新密码"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updatePasswords">修改密码</el-button>
          </el-form-item>
        </el-form>
      </div>
    </template>
  </div>
</template>

<script>
  import {updatePassword} from '@/api/user'
  import { getToken } from '@/utils/auth'
  export default {
    data() {

      return {
        dataVo:{
          oldPassword:"",
          password:"",
          id:"",
        }
      }

    },
    methods: {
      updatePasswords() {
        this.dataVo.id = getToken();
        updatePassword(this.dataVo)
          .then(response => {
            if(response.code === 20000){
              // 提示信息
              this.$message({
                type: 'success',
                message: response.message
              });
            }else{
              // 提示信息
              this.$message({
                type: 'error',
                message: response.message
              });
            }
            this.dataVo = {}
          })
      }
    },

  }
</script>
