<template>
  <div class="login_container">
    <!-- login block -->
    <div class="login_box">
      <!-- logo -->
      <div class="avatar_box">
        <img src="../assets/logoshapes.png" alt="" srcset="" />
      </div>
      <el-tabs
        type="border-card"
        v-model="activeName"
        class="login_form"
        :stretch="true"
      >
        <el-tab-pane label="Customer" name="first">
          <el-button class="log_btn" type="primary" @click="makeAppointment"
            >Appoinment</el-button
          >
        </el-tab-pane>
        <el-tab-pane label="Staff" name="second">
          <el-form
            ref="loginFormRef"
            :rules="loginRules"
            :model="loginForm"
            label-width="0"
          >
            <el-form-item prop="username">
              <el-input
                prefix-icon="el-icon-user"
                v-model="loginForm.username"
                placeholder="Staff Username"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                prefix-icon="el-icon-user-solid"
                v-model="loginForm.password"
                type="password"
                placeholder="Password"
              ></el-input>
            </el-form-item>
            <el-form-item class="btns">
              <el-button type="primary" @click="emplogin">Login</el-button>
              <el-button type="info" @click="resetLoginForm">Reset</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
                <el-tab-pane label="Admin" name="third">
          <el-form
            ref="loginFormRef"
            :rules="loginRules"
            :model="loginForm"
            label-width="0"
          >
            <el-form-item prop="username">
              <el-input
                prefix-icon="el-icon-user"
                v-model="loginForm.username"
                placeholder="Admin Username"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                prefix-icon="el-icon-user-solid"
                v-model="loginForm.password"
                type="password"
                placeholder="Password"
              ></el-input>
            </el-form-item>
            <el-form-item class="btns">
              <el-button type="primary" @click="login">Login</el-button>              
              <el-button type="info" @click="resetLoginForm">Reset</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
      <el-dialog
        title="Appointment"
        :visible.sync="dialogVisible"
        width="30%"
        append-to-body
      >
        <el-form
          ref="customerFormRef"
          :rules="customerFormRules"
          :model="customerForm"
          label-width="100px"
        >
          <el-form-item label="FirstName" prop="customer_firstname">
            <el-input v-model="customerForm.customer_firstname"></el-input>
          </el-form-item>
          <el-form-item label="LastName" prop="customer_lastname">
            <el-input v-model="customerForm.customer_lastname"></el-input>
          </el-form-item>
          <el-form-item label="Phone" prop="phone_number">
            <el-input v-model="customerForm.phone_number"></el-input>
          </el-form-item>
          <el-form-item label="Service Type" prop="service_type">
            <el-select
              v-model="customerForm.service_type"
              placeholder="choose service"
            >
              <el-option
                label="Eyebrow Threading"
                value="Eyebrow Threading"
              ></el-option>
              <el-option
                label="Eyebrow Tinting"
                value="Eyebrow Tinting"
              ></el-option>
              <el-option
                label="Facial Threading"
                value="Facial Threading"
              ></el-option>
              <el-option
                label="Full Package Special"
                value="Full Package Special"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Service Date" prop="date">
            <el-col :span="11">
              <el-date-picker
                type="date"
                placeholder="Choose Date"
                v-model="customerForm.date"
                value-format="yyyy-MM-dd"
                style="width: 100%"
                :picker-options="pickerOptions"
              ></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-time-select
                placeholder="Choose Time"
                v-model="customerForm.time"
                format="HH:mm"
                value-format="HH:mm"
                style="width: 100%"
                :picker-options="{
                  start: '09:00',
                  step: '00:30',
                  end: '18:00',
                }"
              ></el-time-select>
            </el-col>
          </el-form-item>
          <el-form-item label="Note" prop="notes">
            <el-input type="textarea" v-model="customerForm.notes"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">Submit</el-button>
            <el-button @click="cancelApp">Cancel</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: "", 
      },
      activeName: "first",
      dialogVisible: false,
      customerForm: {
        customer_firstname: "",
        customer_lastname: "",
        service_type: "",
        date: "",
        time: "",
        notes: "",
        phone_number:""
      },
      pickerOptions: {
        disabledDate: (date)=>{
          return this.dealDisabledDate(date)
        }
      },

      loginRules: {
        username: [
          { required: true, message: "Username is required", trigger: "blur" }, 
          { min: 5, max: 12, message: "5~12 longer", trigger: "blur" }, 
        ],
        password: [
          { required: true, message: "Password is required", trigger: "blur" }, 
          { min: 6, max: 10, message: "6~10 longer", trigger: "blur" }, 
        ],
      },
      customerFormRules:{
        customer_firstname: [
          { required: true, message: "customer firstname is required", trigger: "blur" }, 
        ],
        service_type:[
          { required: true, message: "service type is required", trigger: "blur" },
        ],
        date:[
          { required: true, message: "date is required", trigger: "blur" },
        ],
        time:[
          { required: true, message: "time is required", trigger: "blur" },
        ]
      }
    };
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return; 
        const { data: res } = await this.$http.post("login", this.loginForm); 
        this.$emit("loginEmit", this.loginForm.username);
        console.log(res);
        if (res.code == "200") {
          this.$message.success("operation success");
          window.sessionStorage.setItem("user", JSON.stringify(res.data)); 

          this.$router.push({ path: "/home" });
        } else {
          this.$message.error("operation failed");
        }
      });
    },
        emplogin() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return; 
        const { data: res } = await this.$http.post("login", this.loginForm); 
        this.$emit("loginEmit", this.loginForm.username);
        if (res.code == "200") {
          this.$message.success("operation success");
          window.sessionStorage.setItem("user", JSON.stringify(res.data)); 

          this.$router.push({ path: "/emphome" });
        } else {
          this.$message.error("operation failed");
        }
      });
      },
    makeAppointment() {
      this.dialogVisible = true;
    },
    async onSubmit() {
      console.log(this.customerForm);
      const { data: res } = await this.$http.post(
        "appointment/appointment",
        this.customerForm
      );
      if (res.code == "200") {
        this.$message.success("operation success");
        this.dialogVisible = false;
        this.customerForm = "";
      } else {
        this.$message.error("operation failed");
      }
    },
    cancelApp() {
      this.dialogVisible = false;
      this.$refs.customerFormRef.resetFields();
    },
    dealDisabledDate(date) {
      let datePicker = new Date().setHours(1, 0, 0, 0);
      if (date.getTime() < datePicker) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 5px;
    box-shadow: 0 0 2px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.btns {
  display: flex;
  justify-content: flex-end;
}
.login_form {
  bottom: 0%;
  width: 100%;
  margin-top: 75px;
  padding: 0 10px;
  box-sizing: border-box;
}
.log_btn {
  top: 60px;
  width: 100%;
  height: 60px;
  margin: 10px;
}
</style>
