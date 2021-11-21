<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted(){
      // 关闭浏览器窗口的时候清空浏览器缓存在localStorage的数据
      window.onbeforeunload = function (e) {
          // var storage = window.localStorage;
          // storage.clear()
          alert()
      }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  
/* background: #F5F7F9; */
  /* width: 100vw; */
  min-height: 100%;
}
.dialogMain{
  width: 90%;
  margin: auto;
}
#joinBox .dialogMain{
  width: 100%;
}
.dialogMain .el-select{
  width: 100%;
}
.el-dialog__body{
  padding: 0px 30px 30px;
}
#bigShowBox .el-scrollbar__wrap{
    overflow-x: hidden;
}
#indexBox .el-scrollbar__wrap{
    overflow-x: hidden;
}
.tangram-suggestion-main {
        z-index: 999999;
    }
.anchorBL{display:none;}
.dialogMain tr:hover{
    cursor: pointer;
}
#loginBox .el-table th, #loginBox .el-table tr{
  background: transparent;
  color:#1b5402
}
#loginBox .el-table td, .el-table th{
  padding: 6px 0;
}
.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  #orderBox .dialogMain > .el-form{
    width: 90%;
  }
  #orderBox .dialogMain > .el-form .el-form-item{
    margin-bottom: 5px
  }
</style>
