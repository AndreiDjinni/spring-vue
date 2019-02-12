<template>
  <div id="app">
    <div class="w-min">

      <header>
        <nav class="navbar fixed-top navbar-expand header">
          <div class="container logo"><p>EMPLOYEES</p></div>
        </nav>
      </header>

      <main role="main" class="main container">
        <router-view></router-view>
      </main>

      <footer class="footer">
        <div class="container"></div>
      </footer>

    </div>
  </div>
</template>

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
    font-weight: 700 !important;
  }
</style>
