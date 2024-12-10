<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="咨询学生姓名">
        <el-select v-model="dataVo.userId" clearable placeholder="请选择咨询学生姓名">
          <el-option v-for="item in userList" :value="item.id" :label="item.name"  :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="咨询方式">
        <el-select v-model="dataVo.flag" clearable placeholder="请选择咨询方式">
          <el-option value="0" label="在线咨询" />
          <el-option value="1" label="匿名咨询" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
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
      <el-table-column prop="studentName" label="咨询学生姓名">
        <template slot-scope="scope">
            <span  v-if="scope.row.flag == '0'" >
               {{scope.row.studentName}}
            </span>
          <span  v-else >
               匿名
            </span>
        </template>
      </el-table-column>
      <el-table-column prop="counselorName" label="咨询师" />
      <el-table-column prop="flag" label="咨询类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.flag == '0'" type="warning">在线咨询</el-tag>
          <el-tag v-if="scope.row.flag == '1'" type="primary">匿名咨询</el-tag>
        </template>
      </el-table-column>



      <el-table-column prop="createTime" label="最新时间" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="updateAndSave(scope.row)">查看咨询内容</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增修改弹框 -->
    <el-dialog
      title="咨询内容"
      :visible.sync="dialogVisible"
      width="35%"
    >
      <el-form
        class="detail-form-content"
        label-width="80px"
      >
        <div class="chat-content">
          <div v-bind:key="item.id" v-for="item in replyList">
            <div v-if="item.type == '0'" class="left-content">
              <el-alert class="text-content" :title="item.content" :closable="false" type="success"></el-alert>
            </div>
            <div v-else class="right-content">
              <el-alert class="text-content" :title="item.content" :closable="false" type="warning"></el-alert>
            </div>
            <div class="clear-float"></div>
          </div>
        </div>
        <div class="clear-float"></div>
        <el-form-item>
          <el-button @click="back()">关闭</el-button>
        </el-form-item>
      </el-form>
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
import { sendReply, pageQuery, getReply} from '@/api/reply'
import { getUserAll } from '@/api/user'
import { deleteById } from '../../api/reply'
export default {
  data() { // 定义变量和初始值
    return {
      list: [], // 查询之后接口返回集合
      dataVo: {}, // 条件封装对象
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 100, // 总页数
      dialogVisible: false,
      formData: {},
      title: '',
      userList:[],
      replyList:[],
      content:''
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
      this.page = page // 获取用户点击的页码赋值
      pageQuery(this.page, this.limit, this.dataVo)
        .then(response => { // 请求成功
          this.list = response.data.rows.records;
          this.total = response.data.total;
        })
    },

    // 修改和更新功能
    updateAndSave(row) {
      getReply(row.userId, row.counselorId, row.flag).then(res=>{
        this.replyList = res.data.row;
      })
      this.formData = row;
      this.dialogVisible = true
    },
    back(){
      this.dialogVisible = false
    },

    // 删除
    removeDataById(data) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => { // 确定执行的方法
        deleteById(data)
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
      this.page = 1;// 当前页
      this.limit = 10; // 每页记录数
      this.dataVo = {};
      this.getList()
    }
  }
}
</script>
<style lang="scss" scoped>
.chat-content {
  margin-left: 80px;
  padding-bottom: 60px;
  width: 500px;
  margin-bottom: 30px;
  max-height: 300px;
  height: 300px;
  overflow-y: scroll;
  border: 1px solid #eeeeee;
  .left-content {
    float: left;
    margin-bottom: 10px;
    padding: 10px;
  }
  .right-content {
    float: right;
    margin-bottom: 10px;
    padding: 10px;
  }
}
.clear-float {
  clear: both;
}
</style>
