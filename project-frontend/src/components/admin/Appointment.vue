<template>
  <div>
    Appointments List
    <el-card>
      <el-row :gutter="25">
        <el-form
          :inline="true"
          :model="appointmentInline"
          class="demo-form-inline"
        >
          <el-form-item label="Begin Date">
            <el-date-picker
              v-model="appointmentInline.begin"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="Choose Date"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="End Date">
            <el-date-picker
              v-model="appointmentInline.end"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="Choose Date"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="SearchOnSubmit">Search</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" @click="resetData">Clear</el-button>
          </el-form-item>
        </el-form>
        <el-col :span="4">
          <el-button type="primary" @click="makeAppointment"
            >Add Appointment</el-button
          >
        </el-col>
      </el-row>
      <el-table stripe border :data="customerForm" style="width: 100%">
        <el-table-column type="index" />
        <el-table-column
          prop="customer_firstname"
          label="Customer Firstname"
          width="180"
        >
        </el-table-column>
        <el-table-column
          prop="customer_lastname"
          label="Customer Lastname"
          width="180"
        >
        </el-table-column>
        <el-table-column prop="service_type" label="Service Type">
        </el-table-column>
        <el-table-column prop="date" label="Appointment Date">
        </el-table-column>
        <el-table-column prop="time" label="Appointment Time">
        </el-table-column>
        <el-table-column prop="notes" label="Notes"> </el-table-column>
        <el-table-column label="Option">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.appointment_id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteAppointment(scope.row.appointment_id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="Appointment"
      :visible.sync="dialogVisible"
      width="30%"
      append-to-body
    >
      <el-form
        ref="customerFormDia"
        :model="customerFormDia"
        label-width="100px"
      >
        <el-form-item label="FirstName" prop="customer_firstname">
          <el-input v-model="customerFormDia.customer_firstname"></el-input>
        </el-form-item>
        <el-form-item label="LastName" prop="customer_lastname">
          <el-input v-model="customerFormDia.customer_lastname"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone_number">
          <el-input v-model="customerFormDia.phone_number"></el-input>
        </el-form-item>
        <el-form-item label="Service Type" prop="service_type">
          <el-select
            v-model="customerFormDia.service_type"
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
              v-model="customerFormDia.date"
              value-format="yyyy-MM-dd"
              style="width: 100%"
              :picker-options="pickerOptions"
            ></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-time-select
              placeholder="Choose Time"
              v-model="customerFormDia.time"
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
          <el-input type="textarea" v-model="customerFormDia.notes"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Submit</el-button>
          <el-button @click="cancelApp">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="Edit Appointment"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form :model="editForm" ref="editFormRef" label-width="100px">
        <el-form-item label="FirstName" prop="customer_firstname">
          <el-input v-model="editForm.customer_firstname"></el-input>
        </el-form-item>
        <el-form-item label="LastName" prop="customer_lastname">
          <el-input v-model="editForm.customer_lastname"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone_number">
          <el-input v-model="customerFormDia.phone_number"></el-input>
        </el-form-item>
        <el-form-item label="Service Type" prop="service_type">
          <el-select
            v-model="editForm.service_type"
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
              v-model="editForm.date"
              value-format="yyyy-MM-dd"
              style="width: 100%"
              :picker-options="pickerOptions"
            ></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-time-select
              placeholder="Choose Time"
              v-model="editForm.time"
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
          <el-input type="textarea" v-model="editForm.notes"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editAppointment">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editForm: {},
      editDialogVisible: false,
      dialogVisible: false,
      customerForm: [],
      customerFormDia: {
        customer_firstname: "",
        customer_lastname: "",
        service_type: "",
        date: "",
        time: "",
        notes: "",
        phone_number: "",
      },
      pickerOptions: {
        disabledDate: (date) => {
          return this.dealDisabledDate(date);
        },
      },
      appointmentInline: {},
    };
  },
  methods: {
    async getAllAppointment() {
      const { data: res } = await this.$http.get("appointment/allAppointment");
      this.customerForm = res.data;
    },
    makeAppointment() {
      this.dialogVisible = true;
    },
    async onSubmit() {
      const { data: res } = await this.$http.post(
        "appointment/appointment",
        this.customerFormDia
      );
      if (res.code == "200") {
        this.$message.success("operation success");
        this.dialogVisible = false;
        this.$refs.customerFormDia.resetFields();
        this.getAllAppointment();
      } else {
        this.$message.error("operation failed");
      }
    },
    cancelApp() {
      this.dialogVisible = false;
      this.$refs.customerFormDia.resetFields();
    },
    async SearchOnSubmit() {
      const { data: res } = await this.$http.post(
        "appointment/getSearch",
        this.appointmentInline
      );
      this.customerForm = res.data;
    },
    resetData() {
      this.appointmentInline = {};
      this.getAllAppointment();
    },
    dealDisabledDate(date) {
      let datePicker = new Date().setHours(1, 0, 0, 0);
      if (date.getTime() < datePicker) {
        return true;
      } else {
        return false;
      }
    },
    async deleteAppointment(appointment_id) {
      const confirmResult = await this.$confirm("Are you sure?", "warning", {
        confirmButtonText: "confirm",
        cancelButtonText: "cancel",
        type: "warning",
      }).catch((err) => err);
      if (confirmResult != "confirm") {
        return this.$message.info("canceled");
      }
      const { data: res } = await this.$http.delete(
        `appointment/deleteAppointment/${appointment_id}`
      );
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.getAllAppointment();
    },
    async showEditDialog(appointment_id) {
      const { data: res } = await this.$http.get(
        `appointment/getUpdate/${appointment_id}`
      );
      this.editForm = res.data;
      this.editDialogVisible = true;
      console.log(res.data);
    },
    async editAppointment() {
      const { data: res } = await this.$http.put(
        "appointment/editAppointment",
        this.editForm
      );
      if (res != "success") {
        return this.$message.error("Failed !!!");
      }
      this.$message.success("Success !!!");
      this.editDialogVisible = false;
      this.getAllAppointment();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields(); 
    },
  },
  created() {
    this.getAllAppointment();
  },
};
</script>

<style scoped>
</style>
