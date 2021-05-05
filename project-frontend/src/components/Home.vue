<template>
  <el-container class="home-container">
    <el-header>
      <div>
        <img src="../assets/logoshapes.png" alt />

        <span>Shapes Brow Bar Booking System</span>

      </div>
      <el-button type="info" @click="logout">Logout</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse?'64px':'200px'">
          <div class="toggle-button" @click="toggleCollapse">|||</div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          unique-opened 
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
        >
        <el-menu-item @click="openAppointment">
          <i class="el-icon-date"></i>
          <span>Appointments</span>
        </el-menu-item>
        
         <el-menu-item @click="openCalendar">
          <i class="el-icon-date"></i>
          <span>Appointments Calendar</span>
        </el-menu-item> 
        
        <el-menu-item @click="openCustomer">
          <i class="el-icon-date"></i>
          <span>Customers</span>
                  </el-menu-item>
                  
        <el-menu-item @click="openEmployee">
          <i class="el-icon-date"></i>
          <span>Employee manage</span>
        </el-menu-item>
        
        <el-menu-item @click="openUser">
          <i class="el-icon-date"></i>
          <span>User manage</span>
        </el-menu-item>

      
        </el-menu>
      </el-aside>
      <el-main>
          <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
    data(){
      return {
          isCollapse: false,  
          activePath: '/welcome',
      }  
    },
    created(){
        window.sessionStorage.getItem('activePath'); 
    },
  methods: {
    logout() {
      window.sessionStorage.clear(); 
      this.$router.push("/login"); 
    },
    toggleCollapse(){
        this.isCollapse = !this.isCollapse;
    },
    openAppointment(){
      this.$router.push("/appointment")
    },

    openEmployee(){
      this.$router.push("/employee")
    },
    openUser(){
      this.$router.push("/userList")
    },
    
    openCalendar(){
      this.$router.push("/calendar")
    },
        openCustomer(){
      this.$router.push("/customer")
    },
    
  },
};
</script>
<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between; 
  padding-left: 0%; 
  align-items: center; 
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu {
      border-right: none;
  }
}
.el-main {
  background-color: #eaedf1;
}
img {
  width: 55px;
  height: 55px;
}
// |||
.toggle-button {
    background-color: #4A5064;
    font-size: 10px;
    line-height: 24px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;    
}
.el-menu-item {
  height: 60px;
  line-height: 60px;
}
</style>
