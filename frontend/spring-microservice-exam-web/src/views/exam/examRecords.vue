<template>
  <div class="app-container">
    <el-row class="exam-recode-msg">
      <el-col :span="24" style="color: black;">
        <h1>考试记录</h1>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="20" :offset="2">
        <el-table
          v-loading="listLoading"
          :key="tableKey"
          :data="examRecodeList"
          :default-sort="{ prop: 'id', order: 'descending' }"
          @cell-dblclick="handleDetail"
          highlight-current-row
          style="width: 100%;">
          <el-table-column label="考试名称" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.examinationName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试类型" min-width="90" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.type | typeFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="所属课程" min-width="90" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.type | typeFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试时间" sortable prop="start_time" min-width="90" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.startTime | timeFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="状态" min-width="90" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.submitStatus | submitStatusTypeFilter">{{ scope.row.submitStatus | submitStatusFilter }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="成绩" sortable prop="score" align="center" width="120px">
            <template slot-scope="scope">
              <span>{{ scope.row.score }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="success" size="mini" @click="handleDetail(scope.row)" :disabled="scope.row.submitStatus !== 3">成绩详情</el-button>
              <el-button type="danger" size="mini" @click="incorrectAnswer(scope.row)" :disabled="scope.row.submitStatus !== 3">查看错题</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination-container">
          <el-pagination v-show="total>0" :current-page="listQuery.pageNum" :page-sizes="[10,20,30, 50]" :page-size="listQuery.pageSize" :total="total" background layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
        </div>
      </el-col>
    </el-row>

    <!-- 成绩详情 -->
    <el-dialog :visible.sync="dialogDetailVisible" title="成绩详情">
      <el-row>
        <el-col :span="24">
          <div slot="header" class="score-gray-box-title">
            <span>考试成绩</span>
          </div>
          <div class="score">
            <h4>成绩: <span type="success">{{ tempScore.score }}</span></h4>
            <h4>正确题数: <span type="success">{{ tempScore.correctNumber }}</span></h4>
            <h4>错误题数: <span type="success">{{ tempScore.inCorrectNumber }}</span></h4>
            <h4>开始时间: <span type="success">{{ tempScore.startTime | timeFilter }}</span></h4>
            <h4>结束时间: <span type="success">{{ tempScore.endTime | timeFilter }}</span></h4>
          </div>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>
<script>
import { mapState } from 'vuex'
import { fetchList } from '@/api/exam/examRecord'
import store from '@/store'
import { formatDate } from '@/utils/util'

export default {
  filters: {
    typeFilter (type) {
      const typeMap = {
        0: '正式考试',
        1: '模拟考试',
        2: '在线练习'
      }
      return typeMap[type]
    },
    submitStatusFilter (type) {
      const typeMap = {
        0: '未提交',
        1: '已提交',
        2: '待批改',
        3: '批改完成'
      }
      return typeMap[type]
    },
    submitStatusTypeFilter (status) {
      const statusMap = {
        0: 'warning',
        1: 'warning',
        2: 'warning',
        3: 'success'
      }
      return statusMap[status]
    },
    timeFilter (time) {
      return formatDate(new Date(time), 'yyyy-MM-dd hh:mm')
    }
  },
  data () {
    return {
      examRecodeList: [],
      total: 0,
      listLoading: true,
      dialogDetailVisible: false,
      tableKey: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        courseId: '',
        sort: 'id',
        order: 'descending'
      },
      tempScore: {
        score: '',
        correctNumber: '',
        inCorrectNumber: ''
      }
    }
  },
  computed: {
    // 获取用户信息
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  created () {
    this.listQuery = {
      userId: this.userInfo.id
    }
    this.getList()
  },
  methods: {
    // 加载考试记录
    getList () {
      fetchList(this.listQuery).then(response => {
        this.examRecodeList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(() => {
        this.$notify({
          title: '失败',
          message: '加载考试记录失败',
          type: 'error',
          duration: 2000
        })
      })
    },
    handleSizeChange (val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange (val) {
      this.listQuery.pageNum = val
      this.getList()
    },
    // 查看成绩详情
    handleDetail (row) {
      this.tempScore = row
      this.dialogDetailVisible = true
    },
    // 查看错题
    incorrectAnswer (row) {
      store.dispatch('SetIncorrectRecord', { id: row.id }).then(() => {
        this.$router.push({ name: 'incorrect' })
      }).catch((error) => {
        console.error(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../../assets/css/common.scss";
  .exam-recode-msg {
    @extend %message-common;
  }

  .incorrect-answer-gray-box {
    @extend .gray-box;
    margin-top: 50px;
    margin-bottom: 50px;
    min-height: 200px;
  }
  .incorrect-answer-gray-box-title {
    text-align: center;
  }
  /* 题目 */
  .subject-title {
    color: #333333;
    font-size: 16px;
    line-height: 22px;
    margin-bottom: 10px;
    padding-left: 20px;
    position: relative;
    .subject-title-number {
      position: absolute;
      left: -25px;
      top: 0;
      display: inline-block;
      width: 40px;
      line-height: 22px;
      text-align: right;
    }
  }
  /* 题目选项 */
  .subject-option {
    padding-bottom: 10px;
    padding-left: 10px;
  }
  .score {
    margin: 20px;
  }
  .subject-content {
    background: #F6F7FA;
    border-radius: 4px;
    margin-bottom: 21px;
    padding: 12px 0 12px 22px;
    position: relative;
    min-height: 240px;
    color: #666666;
    text-align: left;
  }
  .correct {
    color: #F56C6C;
  }
  .right {
    color: #67C23A;
  }

  .score-gray-box {
    @extend .gray-box;
    margin-top: 50px;
  }
  .score-gray-box-title {
    text-align: center;
  }

</style>
