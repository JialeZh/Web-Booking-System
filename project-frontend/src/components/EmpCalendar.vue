
<template>
  <div id="app">
    <el-row>
    </el-row>
    <v-sheet height="64">
      <v-toolbar flat>
        <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
          Today
        </v-btn>
        <v-btn fab text small color="grey darken-2" @click="prev">
          <v-icon small> &lt; </v-icon>
        </v-btn>
        <v-btn fab text small color="grey darken-2" @click="next">
          <v-icon small> &gt; </v-icon>
        </v-btn>
        <v-toolbar-title v-if="$refs.calendar">
          {{ $refs.calendar.title }}
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
    </v-sheet>
    <v-app id="inspire">
      <v-row>
        <v-col>
          <v-sheet height="550">
            <v-calendar
              ref="calendar"
              :now="today"
              :value="today"
              :events="events"
              color="primary"
              type="week"
              v-model="focus"
            ></v-calendar>
          </v-sheet>
        </v-col>
      </v-row>
    </v-app>
  </div>
</template>
<script>
export default {
  data: () => ({
    today: new Date(+new Date() - 6 * 3600 * 1000).toISOString().substr(0, 10), //"2019-01-07",
    events: [],
    names: [],
    starts: [],
    ends: [],
    focus: "",
  }),
  methods: {
    getCurrentDate() {
      this.today = this.$moment(new Date()).format("YYYY-MM-DD");
    },
    async getEvents() {
      const events = [];
      const { data: res } = await this.$http.get("appointment/allAppointment");
      res.data.forEach((element) => {
        events.push({
          name: element.customer_firstname,
          start: this.$moment(
            new Date(element.date + " " + element.time + ":00").getTime()
          ).format("YYYY-MM-DD HH:mm"),
          end: this.$moment(
            (new Date(element.date + " " + element.time + ":00").getTime()
          )+ (30 * 60 * 1000)).format("YYYY-MM-DD HH:mm"),
        });
      });
      this.events = events;
      //console.log(this.events);
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
  },
  mounted() {
    this.$refs.calendar.scrollToTime("09:00");
    this.$refs.calendar.checkChange();
    console.log(this.$refs.calendar);
  },
  created() {
    this.getEvents();
  },
};
</script>
<style lang="sass" scoped>
</style>
