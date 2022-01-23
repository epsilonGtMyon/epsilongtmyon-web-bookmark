module.exports = {
  outputDir: '../src/main/resources/public',
  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: false
    }
  },
  transpileDependencies: [
    'quasar'
  ],
  devServer: {
    open: true,
    port: 8000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/'
      }
    }
  },
}
