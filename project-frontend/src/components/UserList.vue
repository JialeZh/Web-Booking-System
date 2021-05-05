<template>
  <div>
     Users List
  <el-card>
   <el-row :gutter="25">
   <el-col :span="4">
          <el-button type="primary" @click="makeUser"
            >Add User</el-button
          >
        </el-col>
   </el-row>

    <el-table
      stripe border
      :data="userForm"
      style="width: 100%"
    >
      <el-table-column type="index" />
      <el-table-column prop="username" label="Username" width="180"> </el-table-column>
      <el-table-column prop="password" label="password" width="180"> </el-table-column>
      <el-table-column prop="role" label="Role"> </el-table-column>
      <el-table-column label="Option">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.user_id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteUser(scope.row.user_id)"
              
              
            ></el-button>
          </template>
        </el-table-column>
      
    </el-table>
   </el-card>

   <el-dialog
      title="User"
      :visible.sync="dialogVisible"
      width="30%"
      append-to-body
    >
      <el-form
        ref="userFormDia"
        :model="userFormDia"
        label-width="100px"
      >
        <el-form-item label="Username" prop="username">
          <el-input v-model="userFormDia.username"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="userFormDia.password"></el-input>
        </el-form-item>
<el-form-item label="Role" prop="role">
          <el-select
            v-model="userFormDia.role"
           placeholder="Choose Role"
          >
            <el-option
              label="Staff"
              value="Staff"
            ></el-option>
            <el-option
              label="Admin"
              value="Admin"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Submit</el-button>
          <el-button @click="cancelApp">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  <!--168-->
    <el-dialog
      title="Edit User"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form :model="editForm" ref="editFormRef" label-width="100px">
        <el-form-item label="Username" prop="username">
          <el-input v-model="editForm.username"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
<el-form-item label="Role" prop="role">
          <el-select
            v-model="editForm.role"
           placeholder="Choose Role"
          >
            <el-option
              label="Staff"
              value="Staff"
            ></el-option>
            <el-option
              label="Admin"
              value="Admin"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editUser">Confirm</el-button>
      </span>
    </el-dialog>  
  </div> 
</template>

<script>
export default {
  data(){
    return{
      editForm: {},
      editDialogVisible: false,
      dialogVisible: false,
      userForm: [],
      userFormDia: {
        user_id:"",
        username: "",
        password: "",
        role: "",
      },
    }
  },
  methods:{
    async getAllUser(){
      const {data:res} = await this.$http.get("allUser");
      this.userForm=res.data;
    },
    makeUser(){
      this.dialogVisible = true;
    },
    async onSubmit(){

      const { data: res } = await this.$http.post(
        "userList",
        this.userFormDia
      );
      if(res.code == "200"){
        this.$message.success("operation success");
        this.dialogVisible = false;
        this.$refs.userFormDia.resetFields()
        this.getAllUser()
      }else {
        this.$message.error("operation failed");
      }
    },
    cancelApp() {
      this.dialogVisible = false;
      this.$refs.userFormDia.resetFields();
    },
    async deleteUser(user_id) {
      console.log(user_id);
      const confirmResult = await this.$confirm("Are you sure?", "warning", {
        confirmButtonText: "confirm",
        cancelButtonText: "cancel",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult != "confirm") {
        return this.$message.info("canceled");
      }
      const { data: res } = await this.$http.delete(
        `deleteUser/${user_id}`
      );
       if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.getAllUser();

    },
    async showEditDialog(user_id) {
      const { data: res } = await this.$http.get(`getUser/${user_id}`);
      this.editForm = res.data;
      this.editDialogVisible = true;
      console.log(res.data);
    },
    async editUser() {
      const { data: res } = await this.$http.put("editUser", this.editForm);
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.editDialogVisible = false;
      this.getAllUser();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields(); 
    },
  },
  created(){
    this.getAllUser()
  }
};
</script>

<style scoped>

</style>
