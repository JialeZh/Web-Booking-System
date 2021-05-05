<template>
  <div>
  Employee List
  <el-card>
   <el-row :gutter="25">
   <el-col :span="4">
          <el-button type="primary" @click="makeEmployee"
            >Add Employee</el-button
          >
        </el-col>
   </el-row>

    <el-table
      stripe border
      :data="employeeForm"
      style="width: 100%"
    >
      <el-table-column type="index" />
      <el-table-column prop="employee_id" label="Employee ID" width="180"> </el-table-column>
      <el-table-column prop="employee_firstname" label="Employee Firstname" width="180"> </el-table-column>
      <el-table-column prop="employee_lastname" label="Employee Lastname"> </el-table-column>
      <el-table-column prop="username" label="Employee Username"> </el-table-column>
      <el-table-column prop="birthday" label="Employee Date of Birth"> </el-table-column>
      <el-table-column label="Option">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.employee_id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteEmployee(scope.row.employee_id)"
              
              
            ></el-button>
          </template>
        </el-table-column>
      
    </el-table>
   </el-card>

   <el-dialog
      title="Employee"
      :visible.sync="dialogVisible"
      width="30%"
      append-to-body
    >
      <el-form
        ref="employeeFormDia"
        :model="employeeFormDia"
        label-width="100px"
      >
        <el-form-item label="FirstName" prop="employee_firstname">
          <el-input v-model="employeeFormDia.employee_firstname"></el-input>
        </el-form-item>
        <el-form-item label="LastName" prop="employee_lastname">
          <el-input v-model="employeeFormDia.employee_lastname"></el-input>
        </el-form-item>
        <el-form-item label="Username" prop="username">
          <el-input v-model="employeeFormDia.username"></el-input>
        </el-form-item>
        <el-form-item label="Date of birth" prop="birthday">
          <el-input v-model="employeeFormDia.birthday"></el-input>
        </el-form-item>
         <el-form-item>
          <el-button type="primary" @click="onSubmit">Submit</el-button>
          <el-button @click="cancelApp">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
      title="Edit Employee"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form :model="editForm" ref="editFormRef" label-width="100px">
        <el-form-item label="FirstName" prop="employee_firstname">
          <el-input v-model="editForm.employee_firstname"></el-input>
        </el-form-item>
        <el-form-item label="LastName" prop="employee_lastname">
          <el-input v-model="editForm.employee_lastname"></el-input>
        </el-form-item>
        <el-form-item label="Username" prop="username">
          <el-input v-model="editForm.username"></el-input>
        </el-form-item>
        <el-form-item label="Date of birth" prop="birthday">
          <el-input v-model="editForm.birthday"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editEmployee">Confirm</el-button>
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
      employeeForm: [],
      employeeFormDia: {
        employee_id: "",
        employee_firstname: "",
        employee_lastname: "",
        username: "",
        birthday: "",
      },
    }
  },
  methods:{
    async getAllEmployee(){
      const {data:res} = await this.$http.get("allEmployee");
      this.employeeForm=res.data
    },
    makeEmployee(){
      this.dialogVisible = true;
    },
    async onSubmit(){

      const { data: res } = await this.$http.post(
        "employee",
        this.employeeFormDia
      );
      if(res.code == "200"){
        this.$message.success("operation success");
        this.dialogVisible = false;
        this.$refs.employeeFormDia.resetFields()
        this.getAllEmployee()
      }else {
        this.$message.error("operation failed");
      }
    },
    cancelApp() {
      this.dialogVisible = false;
      this.$refs.employeeFormDia.resetFields();
    },
    async deleteEmployee(employee_id) {
      console.log(employee_id);
      const confirmResult = await this.$confirm("Are you sure?", "warning", {
        confirmButtonText: "confirm",
        cancelButtonText: "cancel",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult != "confirm") {
        return this.$message.info("canceled");
      }
      const { data: res } = await this.$http.delete(
        `deleteEmployee/${employee_id}`
      );
       if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.getAllEmployee();

    },
    async showEditDialog(employee_id) {
      const { data: res } = await this.$http.get(`getEmployee/${employee_id}`);
      this.editForm = res.data;
      this.editDialogVisible = true;
      console.log(res.data);
    },
    async editEmployee() {
      const { data: res } = await this.$http.put("editEmployee", this.editForm);
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.editDialogVisible = false;
      this.getAllEmployee();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
  },
  created(){
    this.getAllEmployee()
  }
};
</script>

<style scoped>

</style>
