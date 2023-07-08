<template>
  <div id="app">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <the-header></the-header>
      </b-col>
      <b-col></b-col>
    </b-row>
    <the-nav></the-nav>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <router-view id="view"></router-view>
      </b-col>
      <b-col></b-col>
    </b-row>
    <b-row id="footer">
      <b-col></b-col>
      <b-col cols="8">
        <the-footer></the-footer>
      </b-col>
      <b-col></b-col>
    </b-row>
  </div>
</template>

<script>
import TheHeader from "@/components/layout/TheHeader.vue";
import TheNav from "@/components/layout/TheNav.vue";
import TheFooter from "@/components/layout/TheFooter.vue";

export default {
  name: "App",
  components: {
    TheHeader,
    TheNav,
    TheFooter,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

#view {
  padding-bottom: 5rem;
  border-bottom: 1px solid #dee2e6;
}
#footer {
  margin-top: 2rem;
}
</style>
