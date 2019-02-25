<template>
  <div id="app">
    <div class="w-min">

      <header>
        <nav class="navbar fixed-top navbar-expand header">
          <div class="container">
            <h5 class="my-0 logo" @click="main">EMPLOYEES</h5>
            <div>
              <div v-if="auth === null || auth === false">
                <div class="btn-group" role="group">
                  <button type="button" class="btn btn-info" @click="signIn">Sign In</button>
                  <button type="button" class="btn btn-warning" @click="signUp">Sign Up</button>
                </div>
              </div>
              <div v-else-if="auth === true" class="d-flex flex-align-center">
                <div class="rounded-circle user-icon-fone mr-3" @click="userMe">
                  <icon class="user-icon" name="user" @click="userMe"></icon>
                </div>
                <button type="button" class="btn btn-outline-light" @click="signOut">Sign Out</button>
              </div>
            </div>
          </div>
        </nav>
      </header>

      <main role="main" class="main container">
        <router-view></router-view>
      </main>

      <footer class="footer">
        <div class="container d-flex flex-align-center flex-jc-center h-100">
          <h6 class="footer-info">Spring Boot + Spring Security + Vue.js + Vuex + Crud operations</h6>
        </div>
      </footer>

    </div>
  </div>
</template>

<script>
  export default {

    name: "App",

    data() {

      return {

      }
    },

    computed: {

      auth() {
        return this.$store.getters.isAuthenticated;
      },

      user() {
        return this.$store.getters.getUser;
      }
    },

    mounted() {
      this.$store.dispatch('currentUser');
    },

    methods: {

      main() {
        this.$router.push('/')
      },

      signIn() {
        this.$router.push('/sign-in')
      },

      signUp() {
        this.$router.push('/sign-up')
      },

      signOut() {
        this.$store.dispatch('signOut')
      },

      userMe() {
        this.$router.push('/user/me')
      }
    }
  }
</script>

<style lang="scss">

  html {
    position: relative;
    min-height: 100%;
    font-size: calc( (100vw - 320px)/(1280 - 320) * (16 - 4) + 4px);
  }

  @media (max-width: 320px) {
    html {
      font-size: 4px !important;
    }
  }

  @media (min-width: 1281px) {
    html {
      font-size: 16px !important;
    }
  }

  footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    background-color: #f5f5f5;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .main {
    margin-top: 5rem;
    margin-bottom: 5rem;
  }

  .w-min {
    min-width: 320px;
  }

  .header,
  .footer {
    height: 3rem;
    background: #002a37;
  }

  .container {
    padding-right: 0.9375rem;
    padding-left: 0.9375rem;
  }

  @media (max-width: 768px) {
    .container {
      max-width: 100%;
    }
  }

  .logo {
    color: white;
    font-weight: bold !important;
    cursor: pointer;
  }

  .user-icon-fone {
    width: 2rem;
    height: 2rem;
    background-color: aliceblue;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }

  .user-icon {
    color: white;
    width: 1.2rem;
    height: 1.2rem;
    cursor: pointer;
  }

  .flex-align-center {
    align-items: center;
  }

  .flex-jc-center {
    justify-content: center;
  }

  .flex-jc-space {
    justify-content: space-between;
  }

  .footer-info {
    color: white;
  }
</style>
