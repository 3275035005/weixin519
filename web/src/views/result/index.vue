<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="评测学生姓名">
        <el-select v-model="dataVo.userId" clearable placeholder="请选择发布评测学生">
          <el-option v-for="item in userList" :value="item.id" :label="item.name"  :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-button type="default" @click="resetData()">重置</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
      empty-text="暂无数据"
    >

      <el-table-column
        label="序号"
        width="60"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}  <!--序号,算法-->
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="评测学生姓名" />
      <el-table-column prop="score" label="分数" />
      <el-table-column prop="type" label="性格">
        <template slot-scope="scope">
          <el-tag type="warning">{{scope.row.type}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="评测时间" />
      <el-table-column label="操作" align="center" width="500">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="updateAndSave(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹框 -->
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form :model="formData" label-position="left" label-width="100px">
        <el-form-item label="分数">
          <el-input-number v-model="formData.score" :min="0" :max="100"/>
        </el-form-item>
        <el-form-item label="性格">
          <el-select v-model="formData.type" clearable placeholder="请选择性格">
            <el-option value="外向" label="外向"/>
            <el-option value="感觉" label="感觉"/>
            <el-option value="内向" label="内向"/>
            <el-option value="直觉" label="直觉"/>
            <el-option value="实感" label="实感"/>
            <el-option value="enfj" label="知觉"/>
            <el-option value="entj" label="指导者"/>
            <el-option value="" label="判断"/>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleBtn">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      style="padding: 30px 0; text-align: center;"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      :total="total"
      @current-change="getList"
    />
  </div>
</template>
<script>
import { deleteById, update , pageQuery } from '@/api/result'
import { getUserAll } from '@/api/user'
export default {
  data() { // 定义变量和初始值
    return {
      list: null, // 查询之后接口返回集合
      dataVo: {}, // 条件封装对象
      page: 1, // 当前页
      limit: 9, // 每页记录数
      total: 100, // 总页数
      dialogVisible: false,
      formData: {},
      title: '',
      userList:[]
    }
  },
  created() { // 页面渲染之前执行，一般调用method定义方法
    this.getList();
    this.init();
  },
  methods: { // 创建具体的方法, 定义的方法

    init(){
      getUserAll("3").then(res=>{
        this.userList = res.data.row
      })
    },
    // 查询
    getList(page = 1) {
      this.page = page // 获取咨询师点击的页码赋值
      pageQuery(this.page, this.limit, this.dataVo)
        .then(response => { // 请求成功
          this.list = response.data.rows.records
          this.total = response.data.rows.total
        })
    },

    // 修改和更新功能
    updateAndSave(row) {
      this.title = '修改评测结果信息'
      this.formData = row;
      this.dialogVisible = true
    },

    // 确认按钮
    handleBtn() {
      // 关闭弹窗
      this.dialogVisible = false
      update(this.formData)
        .then(response => { // 请求成功
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.getList()
        })

    },

    // 删除
    removeDataById(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => { // 确定执行的方法
        deleteById(id)
          .then(response => { // 删除成功执行的方法
            if (response.success) {
              this.$message({
                type: 'success',
                message: '删除成功！'
              })
              // 刷新表格
              this.getList()
            } else {
              this.$message({
                type: 'error',
                message: '删除失败！'
              })
            }
          })
      })
    },
    // 清空按按钮执行的方法
    resetData() {
      // 第一步清空条件数据
      this.dataVo = {};
      this.page = 1; // 当前页
      this.limit = 10; // 当前页
      this.getList()
    }
  }
}
</script>
<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border-style: dotted;
  margin: 10px 0;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
